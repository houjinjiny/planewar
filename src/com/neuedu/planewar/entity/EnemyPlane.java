package com.neuedu.planewar.entity;
import com.neuedu.planewar.client.PlaneWarClient;
import com.neuedu.planewar.common.ImageUtil;
import com.neuedu.planewar.constant.Constant;

import java.awt.*;
import java.util.Random;

public class EnemyPlane extends PlaneWarObject{
    public static Image[] images=new Image[3];
    static {
        for(int i=0;i<images.length;i++){
            images[i]=ImageUtil.images.get("enemyPlane0"+(i+1));
        }
    }
    public EnemyPlane(){}
    boolean good=false;
    public EnemyPlane(PlaneWarClient pwc,int x,int y){
        this.pwc=pwc;
        this.x=x;
        this.y=y;
        this.width=images[0].getWidth(null);
        this.height=images[0].getHeight(null);
        if(this.pwc.myplane.score<1000){
            this.speed=10;
        }
        if(this.pwc.myplane.score<2500&&this.pwc.myplane.score>=1000){
            this.speed=20;
        }
        if(this.pwc.myplane.score>=2500){
            this.speed=30;
        }

    }
    int count=0;
    static Random r=new Random();
    public void draw(Graphics g){
        if(count>2){
            count=0;
        }
        g.drawImage(images[count],x,y,null);
        count++;
        move();
        outOfBounds();
        if(this.pwc.myplane.score<3500){
            if(r.nextInt(1000)>=950){
                shoot();
            }
        }
        if(this.pwc.myplane.score>=3500&&this.pwc.myplane.score<4500){
            if(r.nextInt(1000)>=940){
                shoot();
            }
        }
        if(this.pwc.myplane.score>=4500){
            if(r.nextInt(1000)>=930){
                shoot();
            }
        }

    }

    public void move() {
        this.y+=speed;
    }
    public void shoot(){
        Bullet bullet=new Bullet(this.pwc,this.x+this.width/2-6,this.y+this.height,good);
        this.pwc.bullets.add(bullet);
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
        if(y>=Constant.FRAME_HEIGHT){
            if(this.pwc.myplane.getHP()>0){
                if(this.pwc.myplane.life()&&this.pwc.boss.life()){
                    this.pwc.myplane.setScore(this.pwc.myplane.getScore()-10);
                }
            }
            this.pwc.enemyPlanes.remove(this);
        }
    }
    //
}
