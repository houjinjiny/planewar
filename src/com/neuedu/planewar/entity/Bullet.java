package com.neuedu.planewar.entity;
import com.neuedu.planewar.client.PlaneWarClient;
import com.neuedu.planewar.common.ImageUtil;
import com.neuedu.planewar.constant.Constant;
import java.awt.*;
import java.util.List;
import java.util.Random;

public class Bullet extends PlaneWarObject {
    public Bullet(){}
    //表示敌我的boolean变量
    boolean good;
    Random r=new Random();
    public Bullet(PlaneWarClient pwc, int x, int y,boolean good){
        this.pwc=pwc;
        this.x=x;
        this.y=y;
        this.good=good;
        if(good){
            this.img=ImageUtil.images.get("bullet01");
        }else {
            this.img=ImageUtil.images.get("enbullet01");
        }

        this.width=img.getWidth(null);
        this.height=img.getHeight(null);
        this.speed=30;

    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(img,x,y,null);
        move();
    }

    @Override
    public void move() {
        if(good){
            y-=speed;
        }else {
            y+=speed;
        }
        outOfBound();
    }
    public void outOfBound(){
        if(x<-500||x>Constant.FRAME_WIDTH+500||y<-500||y>Constant.FRAME_HEIGHT+500){
            this.pwc.bullets.remove(this);
        }
    }
    //打我方飞机
    public boolean hitPlane(Plane myplane){
        if(this.pwc.boss.life()&&this.pwc.myplane.life()){
            if(this.good!=myplane.good&&this.getRectangle().intersects(myplane.getRectangle())){
                this.pwc.myplane.setScore(myplane.getScore()-10);
                if(myplane.getDEF()>100){
                    myplane.setDEF(myplane.getDEF()-100);
                }else {
                    myplane.setHP(myplane.getHP()-10);
                }
                if(myplane.getHP()<=0){
                    this.pwc.myplane.setScore(this.pwc.myplane.getScore()-50);
                    Explode explode=new Explode(pwc,myplane.x,myplane.y);
                    this.pwc.explodes.add(explode);
                }
                this.pwc.bullets.remove(this);
                return true;
            }
        }

        return false;
    }
    //打敌方飞机
    public boolean hitEnemyPlane(EnemyPlane enemyPlane){
        if(this.good!=enemyPlane.good&&this.getRectangle().intersects(enemyPlane.getRectangle())){
            //打到之后该enemyplane死掉，同时子弹死掉
            this.pwc.enemyPlanes.remove(enemyPlane);
            // 分数加100
            if(this.pwc.myplane.life()&&this.pwc.boss.life()){
                this.pwc.myplane.setScore(this.pwc.myplane.getScore()+100);
            }

            //敌方飞机死亡时爆炸效果出现
            Explode explode=new Explode(pwc,enemyPlane.x,enemyPlane.y);
            this.pwc.explodes.add(explode);
            //出道具
            if(r.nextInt(100)>50) {
                Item item = new Item(pwc, enemyPlane.x + enemyPlane.width / 2, enemyPlane.y + enemyPlane.height / 2, r.nextInt(2));
                this.pwc.items.add(item);
            }
            //销毁子弹
            this.pwc.bullets.remove(this);
            return true;
        }
        return false;
    }
    public boolean hitEnemyPlane(List<EnemyPlane> enemyPlanes){
        for(int i=0;i<enemyPlanes.size();i++){
            EnemyPlane ep=enemyPlanes.get(i);
            if(hitEnemyPlane(ep)){
                return true;
            }
        }
        return false;
    }
    //主战机打boss
    public boolean hitBoss(Boss boss){
        if(boss.life()&&this.pwc.myplane.life()){
            if(boss.y>boss.height){
                if(this.good!=boss.good&&this.getRectangle().intersects(boss.getRectangle())){
                    //打到之后该enemyplane死掉，同时子弹死掉
                    boss.setHP(boss.getHP()-100);
                    //敌方飞机死亡时爆炸效果出现
                    if(boss.HP<0){
                        this.pwc.myplane.setScore(this.pwc.myplane.getScore()+200);
                        Explode explode=new Explode(pwc,boss.x,boss.y);
                        this.pwc.explodes.add(explode);
                    }
                    //销毁子弹
                    this.pwc.bullets.remove(this);
                    return true;
                }
            }

        }

        return false;
    }

}
