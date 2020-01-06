package com.neuedu.planewar.entity;
import com.neuedu.planewar.client.PlaneWarClient;
import java.awt.*;
public abstract class PlaneWarObject implements Moveable,Drawable {
    public int x;
    public int y;
    public Image img;
    public int width;
    public int height;
    public int speed;
    public PlaneWarClient pwc;
    @Override
    public void move() {

    }
    @Override
    public void draw(Graphics g) {}
    public Rectangle getRectangle(){
        return new Rectangle(x,y,width,height);
    }
}
