package com.neuedu.planewar.entity;

import com.neuedu.planewar.client.PlaneWarClient;
import com.neuedu.planewar.common.ImageUtil;
import com.neuedu.planewar.constant.Constant;

import java.awt.*;
import java.util.List;
import java.util.Random;

public class BossBullet extends PlaneWarObject {
    public BossBullet(){}
    //表示敌我的boolean变量
    boolean good;
    int located;
    Random r=new Random();
    public BossBullet(PlaneWarClient pwc, int x, int y, boolean good,int located){
        this.pwc=pwc;
        this.x=x;
        this.y=y;
        this.good=good;
        this.located=located;
        switch (located){
            case 1:
                this.img=ImageUtil.images.get("bossBullet01");
                break;
            case 2:
                this.img=ImageUtil.images.get("bossBullet02");
                break;
            case 3:
                this.img=ImageUtil.images.get("bossBullet03");
                break;
            case 4:
                this.img=ImageUtil.images.get("bossBullet04");
                break;
            case 5:
                this.img=ImageUtil.images.get("bossBullet05");
                break;
            case 6:
                this.img=ImageUtil.images.get("bossBullet06");
                break;
            case 7:
                this.img=ImageUtil.images.get("bossBullet07");
                break;
            case 8:
                this.img=ImageUtil.images.get("bossBullet08");
                break;
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
        switch (located){
            case 1:
                y-=speed;
                break;
            case 2:
                x+=speed;
                y-=speed;
                break;
            case 3:
                x+=speed;
                break;
            case 4:
                x+=speed;
                y+=speed;
                break;
            case 5:
                y+=speed;
                break;
            case 6:
                x-=speed;
                y+=speed;
                break;
            case 7:
                x-=speed;
                break;
            case 8:
                x-=speed;
                y-=speed;
                break;

        }
        outOfBound();
    }
    public void outOfBound(){
        if(x<-500||x>Constant.FRAME_WIDTH+500||y<-500||y>Constant.FRAME_HEIGHT+500){
            this.pwc.bullets.remove(this);
        }
    }
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
                    Explode explode=new Explode(pwc,myplane.x,myplane.y);
                    this.pwc.explodes.add(explode);
                }
                this.pwc.bullets.remove(this);
                return true;
            }
        }
        return false;
    }
}
