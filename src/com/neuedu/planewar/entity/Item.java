package com.neuedu.planewar.entity;

import com.neuedu.planewar.client.PlaneWarClient;
import com.neuedu.planewar.common.ImageUtil;
import com.neuedu.planewar.constant.Constant;

import java.awt.*;

public class Item extends PlaneWarObject {
    double theta=Math.random()*(Math.PI/2);
    int type;
    public Item(){}
    public Item(PlaneWarClient pwc,int x,int y,int type){
        this.pwc=pwc;
        this.x=x;
        this.y=y;
        this.type=type;
        this.img=confirmByType(type);
        this.width=img.getWidth(null);
        this.height=img.getHeight(null);
        this.speed=20;
    }

    private Image confirmByType(int type) {
        switch (this.type){
            case 0:
                img=ImageUtil.images.get("HP1");
                break;
            case 1:
                img=ImageUtil.images.get("DEF1");
                break;
                default:
                    break;
        }
        return img;
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(img,x,y,null);
        move();
    }

    @Override
    public void move() {
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
