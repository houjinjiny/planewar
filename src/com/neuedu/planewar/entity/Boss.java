package com.neuedu.planewar.entity;

import com.neuedu.planewar.client.PlaneWarClient;
import com.neuedu.planewar.common.ImageUtil;
import com.neuedu.planewar.constant.Constant;

import java.awt.*;
import java.util.Random;

public class Boss extends PlaneWarObject{
    public static Image[] images=new Image[4];
    double theta=Math.random()*(Math.PI/2);
    static {
        for(int i=0;i<images.length;i++){
            images[i]=ImageUtil.images.get("boss0"+(i+1));
        }
    }
    public Boss(){}
    boolean good=false;
    public int HP=10000;
    public double maxHp=HP;
    public int getHP() {
        return HP;
    }

    public void setHP(int HP) {
        this.HP = HP;
    }

    public Boss(PlaneWarClient pwc, int x, int y){
        this.pwc=pwc;
        this.x=x;
        this.y=y;
        this.width=images[0].getWidth(null);
        this.height=images[0].getHeight(null);
        this.speed=10;
    }
    int count=0;
    static Random r=new Random();
    public void draw(Graphics g){
            if(count>3){
            count=0;
        }
        g.drawImage(images[count],x,y,null);
            bbb.draw(g);
            count++;
            move();
            outOfBounds();
            if(r.nextInt(1000)>=960){
                shoot2();
            }

    }
    public void move() {
        if(y<200){
            y+=speed;
        }else {
            x+=(int)(speed*Math.cos(theta));
            y+=(int)(speed*Math.sin(theta));
            if(y<=30||y>= Constant.FRAME_HEIGHT-this.height){
                theta=-theta;
            }
            if(x<=0||x>Constant.FRAME_WIDTH-this.width){
                theta=Math.PI-theta;
            }
        }


    }
    public void shoot2(){
        BossBullet bossBullet1=new BossBullet(this.pwc,this.x+this.width/2-15,this.y+this.height/2,good,1);
        BossBullet bossBullet2=new BossBullet(this.pwc,this.x+this.width/2-15,this.y+this.height/2,good,2);
        BossBullet bossBullet3=new BossBullet(this.pwc,this.x+this.width/2-15,this.y+this.height/2,good,3);
        BossBullet bossBullet4=new BossBullet(this.pwc,this.x+this.width/2-15,this.y+this.height/2,good,4);
        BossBullet bossBullet5=new BossBullet(this.pwc,this.x+this.width/2-15,this.y+this.height/2,good,5);
        BossBullet bossBullet6=new BossBullet(this.pwc,this.x+this.width/2-15,this.y+this.height/2,good,6);
        BossBullet bossBullet7=new BossBullet(this.pwc,this.x+this.width/2-15,this.y+this.height/2,good,7);
        BossBullet bossBullet8=new BossBullet(this.pwc,this.x+this.width/2-15,this.y+this.height/2,good,8);
        this.pwc.bossBullets.add(bossBullet1);
        this.pwc.bossBullets.add(bossBullet2);
        this.pwc.bossBullets.add(bossBullet3);
        this.pwc.bossBullets.add(bossBullet4);
        this.pwc.bossBullets.add(bossBullet5);
        this.pwc.bossBullets.add(bossBullet6);
        this.pwc.bossBullets.add(bossBullet7);
        this.pwc.bossBullets.add(bossBullet8);
    }
    /**
     * 画血条的内部类，不让外部的其他类直接调用，只在本外部类使用
     * 内部类可以直接访问该外部的所有成员方法和属性
     */
    public BossBloodBar bbb= new BossBloodBar();
    class BossBloodBar{
        public void draw(Graphics g){
            Color c=g.getColor();
            if(HP>(maxHp*0.7)&&HP<=(maxHp)){
                g.setColor(Color.GREEN);
            }else if(HP>(maxHp*0.3)&&HP<=(maxHp*0.7)){
                g.setColor(Color.ORANGE);
            }else {
                g.setColor(Color.RED);
            }
            g.drawRect(x,y-20,width,10);
            g.fillRect(x,y-20,(int)(width*(HP/maxHp)),10);
            g.setColor(c);

        }
    }

    /**
     * 出界
     */
    private void outOfBounds(){
        if(x<=0){
            x=0;
        }
        if(x>= Constant.FRAME_WIDTH-this.width){
            x=Constant.FRAME_WIDTH-this.width;
        }
        if(y>=Constant.FRAME_HEIGHT/2+150){
            y=Constant.FRAME_HEIGHT/2+150;
        }
    }
    //判断是否活着
    public boolean life(){
        if(this.HP>0){
            return true;
        }
        return false;
    }
}
