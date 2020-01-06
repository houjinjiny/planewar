package com.neuedu.planewar.entity;

import com.neuedu.planewar.client.PlaneWarClient;
import com.neuedu.planewar.common.ImageUtil;

import java.awt.*;

public class Background extends PlaneWarObject {
    static Image img = ImageUtil.images.get("background");
    public Background(){}
    public Background(PlaneWarClient pwc, int x, int y){
        this.pwc = pwc;
        this.x = x;
        this.y = y;
        this.speed = 3;
        this.width = img.getWidth(null);
        this.height = img.getHeight(null);
    }
    @Override
    public void draw(Graphics g) {
        g.drawImage(img,x,y,null);
        move();
    }

    @Override
    public void move() {
        y +=speed;
    }
}
