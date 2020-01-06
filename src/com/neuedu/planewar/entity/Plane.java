package com.neuedu.planewar.entity;
import com.neuedu.planewar.client.PlaneWarClient;
import com.neuedu.planewar.common.ImageUtil;
import com.neuedu.planewar.constant.Constant;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.List;

public class Plane extends PlaneWarObject {
    static Image[] imgs=new Image[3];
    public PlaneWarClient pwc;
    static {
        //我方飞机
        for(int i=0;i<3;i++){
            imgs[i]=ImageUtil.images.get("myplane0"+(i+1));
        }
    }
    public Plane(){}
    boolean good=true;
    //血量
    public int HP=500;
    //防御
    public int DEF=0;
    //最大血量
    public double maxHp=HP;
    //分数
    public int score=0;

    public int getDEF() {
        return DEF;
    }

    public void setDEF(int DEF) {
        this.DEF = DEF;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getHP() {
        return HP;
    }

    public void setHP(int HP) {
        this.HP = HP;
    }

    public Plane(PlaneWarClient pwc, int x, int y){
        this.x=x;
        this.y=y;
        this.pwc=pwc;
//        this.img=ImageUtil.images.get("myplane");
        this.width=imgs[0].getWidth(null);
        this.height=imgs[0].getHeight(null);
        this.speed=30;
    }
//    public Plane(int x,int y,Image[] imgs){
//        this(x,y);
////        this.img=FrameUtil.getImage(imgpath);
//        this.imgs=imgs;
//    }
    @Override
    public void move() {
        if(left){
            x-=speed;
        }
        if(up){
            y-=speed;
        }
        if(right){
            x+=speed;
        }
        if(down){
            y+=speed;
        }
        if(life()){
            outOfBounds();
        }

    }
    int count=0;
    @Override
    public void draw(Graphics g) {//40毫秒调用一次
//        g.drawImage(img,x,y,null);
        //画图组的方法
        if(shoot){
            if(this.pwc.myplane.score<1000){
                shoot();
            }
            if(this.pwc.myplane.score>=1000&&this.pwc.myplane.score<2500){
                shoot2();
            }
            if(this.pwc.myplane.score>=2500){
                shoot3();
            }

        }
        if(count>2){
            count=0;
        }
        g.drawImage(imgs[count],x,y,null);
        bb.draw(g);
        count++;
        move();

    }
    public boolean left,up,right,down,shoot,start,over,re;

    /**
     * 飞机按键控制
     * @param e
     */
    /**
     * 按下
     * @param e
     */
    public void keyPressed(KeyEvent e){
        switch (e.getKeyCode()){
            case KeyEvent.VK_LEFT:
                left=true;
                break;
            case KeyEvent.VK_UP:
                up=true;
                break;
            case KeyEvent.VK_RIGHT:
                right=true;
                break;
            case KeyEvent.VK_DOWN:
                down=true;
                break;
            case KeyEvent.VK_A:
                left=true;
                break;
            case KeyEvent.VK_W:
                up=true;
                break;
            case KeyEvent.VK_D:
                right=true;
                break;
            case KeyEvent.VK_S:
                down=true;
                break;
            case KeyEvent.VK_K:
                shoot=true;
                break;
            case KeyEvent.VK_P:
                start=true;
                break;
            case KeyEvent.VK_T:
                over=true;
                break;
        }
    }

    /**
     * 弹起
     * @param e
     */
    public void keyReleased(KeyEvent e){
        switch (e.getKeyCode()){
            case KeyEvent.VK_LEFT:
                left=false;
                break;
            case KeyEvent.VK_UP:
                up=false;
                break;
            case KeyEvent.VK_RIGHT:
                right=false;
                break;
            case KeyEvent.VK_DOWN:
                down=false;
                break;
            case KeyEvent.VK_A:
                left=false;
                break;
            case KeyEvent.VK_B:
                up=false;
                break;
            case KeyEvent.VK_W:
                right=false;
                break;
            case KeyEvent.VK_D:
                down=false;
                break;
            case KeyEvent.VK_K:
                shoot=false;
                break;
        }
    }

    /**
     * 出界
     */
    private void outOfBounds(){
        if(x<=0){
            x=0;
        }
        if(y<=30){
            y=30;
        }
        if(x>=Constant.FRAME_WIDTH-this.width){
            x=Constant.FRAME_WIDTH-this.width;
        }
        if(y>=Constant.FRAME_HEIGHT-this.height){
            y=Constant.FRAME_HEIGHT-this.height;
        }
    }
    /**
     * 一级发射子弹
     */
    public void shoot(){
        Bullet bullet=new Bullet(this.pwc,this.x+this.width/2-8,this.y-this.height/2-3,good);
        this.pwc.bullets.add(bullet);
    }
    public void shoot2(){
        Bullet bullet=new Bullet(this.pwc,this.x+10,this.y-this.height/2-3,good);
        Bullet bullet2=new Bullet(this.pwc,this.x+this.width/2+10,this.y-this.height/2-3,good);
        this.pwc.bullets.add(bullet);
        this.pwc.bullets.add(bullet2);
    }
    public void shoot3(){
        Bullet bullet=new Bullet(this.pwc,this.x+this.width/2-8,this.y-this.height/2-3,good);
        Bullet bullet2=new Bullet(this.pwc,this.x+8,this.y-this.height/2-3,good);
        Bullet bullet3=new Bullet(this.pwc,this.x+this.width/2+12,this.y-this.height/2-3,good);
        this.pwc.bullets.add(bullet);
        this.pwc.bullets.add(bullet2);
        this.pwc.bullets.add(bullet3);
    }
    /**
     * 画血条的内部类，不让外部的其他类直接调用，只在本外部类使用
     * 内部类可以直接访问该外部的所有成员方法和属性
     */
    public BloodBar bb=new BloodBar();
    class BloodBar{
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
     * 吃道具
     */
    public boolean eatItem(Item item){
        if(this.getRectangle().intersects(item.getRectangle())){
            switch (item.type){
                case 0:
                    this.HP+=20;
                    if(this.HP>=this.maxHp){
                        this.HP= (int) this.maxHp;
                    }
                    this.pwc.items.remove(item);
                    break;
                case 1:
                    this.DEF+=50;
                    this.pwc.items.remove(item);
                    break;

            }
            return true;
        }
        return false;
    }
    public boolean eatItem(List<Item> items){
        if(this.pwc.myplane.life()&&this.pwc.boss.life()){
            for (int i = 0; i < items.size(); i++) {
                Item item=items.get(i);
                if(eatItem(item)){
                    return true;
                }
            }
        }

        return false;
    }
    //判断是否活着
    public boolean life(){
        if(this.HP>0){
            return true;
        }
        return false;
    }
}
