package com.neuedu.planewar.entity;
import com.neuedu.planewar.client.PlaneWarClient;
import com.neuedu.planewar.common.ImageUtil;
import java.awt.*;
public class Explode extends PlaneWarObject {
    public static Image[] images=new Image[6];
    static {
        for (int i = 0; i < images.length; i++) {
            images[i]=ImageUtil.images.get("boom0"+(i+1));
        }
    }
    public Explode(){}
    public Explode(PlaneWarClient pwc,int x,int y){
        this.pwc=pwc;
        this.x=x;
        this.y=y;
        this.width=images[0].getWidth(null);
        this.height=images[0].getHeight(null);
    }
    int count=0;

    @Override
    public void draw(Graphics g) {
        if(count>5){
            count=0;
            this.pwc.explodes.remove(this);
            return;
        }
        g.drawImage(images[count],x,y,null);
        count++;
    }
}
