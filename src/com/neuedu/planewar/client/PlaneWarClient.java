package com.neuedu.planewar.client;
import com.neuedu.planewar.common.CommonFrame;
import com.neuedu.planewar.common.FrameUtil;
import com.neuedu.planewar.common.ImageUtil;
import com.neuedu.planewar.common.MusicUtil;
import com.neuedu.planewar.constant.Constant;
import com.neuedu.planewar.entity.*;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PlaneWarClient extends CommonFrame {
    Random r=new Random();
    Image[] starts=new Image[3];
    public static int n=1;
    //背景
    public Background bg1=new Background(this,0,0);
    public Background bg2=new Background(this,0,bg1.y-bg1.height);
    //飞机
    public Plane myplane=new Plane(this,400,400);
    //子弹
    public List<Bullet> bullets=new ArrayList<>();
    //敌方飞机
    //子弹
    public List<BossBullet> bossBullets=new ArrayList<>();
    public List<EnemyPlane> enemyPlanes=new ArrayList<>();
    //爆炸
    public List<Explode> explodes=new ArrayList<>();
    //道具
    public List<Item> items=new ArrayList<>();
    //boss
    public Boss boss=new Boss(this,400,-100);
    int count=0;
    {
        //敌方飞机
        for (int i=0;i<7;i++){
            if(i<4){
                EnemyPlane enemyPlane=new EnemyPlane(this,30+(i*100),120+(i*40));
                enemyPlanes.add(enemyPlane);
            }else {
                EnemyPlane enemyPlane=new EnemyPlane(this,30+(i*100),120+((6-i)*40));
                enemyPlanes.add(enemyPlane);
            }

        }
    }
    public void loadFrame(String title){
        super.loadFrame(title);
        //键盘监听
        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
               myplane.keyPressed(e);
            }
            @Override
            public void keyReleased(KeyEvent e) {
                myplane.keyReleased(e);
            }
        });
        new MusicUtil("com/neuedu/planewar/video/1.mp3",true).start();
    }
    public void paint(Graphics g){
        //开场动画
//            if(count==0 && count<100){
//                g.drawImage(FrameUtil.getImage("background/start1.png"),100,200,200,300,null);
//
//            }
//            if(count==100 && count<200){
//                g.drawImage(FrameUtil.getImage("background/start2.png"),100,200,200,300,null);
//            }
//            if(count==200 && count<300){
//                g.drawImage(FrameUtil.getImage("background/start3.png"),100,200,200,300,null);
//
//            }
//            count++;
        g.drawImage(FrameUtil.getImage("background/start.png"),Constant.FRAME_WIDTH/2-100,Constant.FRAME_HEIGHT/2-53,200,106,null);
        if(this.myplane.start){
            //画轮播图
            bg1.draw(g);
            bg2.draw(g);
            while(bg1.y>bg1.height){
                bg1.y=bg1.y-2*bg1.y+10;
                bg1.draw(g);
            }
            while(bg2.y>bg2.height){
                bg2.y=bg2.y-2*bg2.y+10;
                bg2.draw(g);
            }
            myplane.draw(g);
            //画敌方飞机
            for (int i=0;i<enemyPlanes.size();i++){
                EnemyPlane enemyPlane=enemyPlanes.get(i);
                enemyPlane.draw(g);
                if(enemyPlanes.size()<7){
                EnemyPlane enemyPlane1=new EnemyPlane(this,100+(r.nextInt(1000)),80);
                enemyPlanes.add(enemyPlane1);
                enemyPlane1.draw(g);
            }
        }
            //画Boss子弹
            for (int i = 0; i < bossBullets.size(); i++) {
                BossBullet bossb=bossBullets.get(i);
                bossb.draw(g);
                //攻击主战飞机
                bossb.hitPlane(myplane);
            }
            //画子弹
            for (int i = 0; i < bullets.size(); i++) {
                Bullet b=bullets.get(i);
                b.draw(g);
                //攻击敌方飞机
                b.hitEnemyPlane(enemyPlanes);
                //攻击主战飞机
                b.hitPlane(myplane);
                b.hitBoss(boss);

            }
            //画怪兽
            if(boss.getHP()>0){
                if(myplane.score>5000){
                    boss.draw(g);
                }
            }

            //画爆炸
            for (int i = 0; i < explodes.size(); i++) {
                Explode explode=explodes.get(i);
                explode.draw(g);
                new MusicUtil("com/neuedu/planewar/video/2.mp3").start();
            }
            //画道具
            for (int i = 0; i < items.size(); i++) {
                items.get(i).draw(g);
            }
//            //画激光
//            for (int i = 0; i < bossBulletls.size(); i++) {
//                BossBulletl bossBulletl=bossBulletls.get(i);
//                bossBulletl.draw(g);
//            }
            myplane.eatItem(items);
            //结束
            if(!myplane.life()) {
                boolean key=true;
                if(key){
                    new MusicUtil("com/neuedu/planewar/video/3.mp3").start();
                    key=false;
                }

                g.drawImage(FrameUtil.getImage("background/over.png"), 0, 0, Constant.FRAME_WIDTH, Constant.FRAME_HEIGHT, null);
                Font f = g.getFont();
                Color c = g.getColor();
                g.setColor(Color.red);
                g.setFont(new Font("微软雅黑", Font.BOLD, 30));
                g.drawString("退出游戏：T", Constant.FRAME_WIDTH / 2 - 50, Constant.FRAME_HEIGHT / 2+200);
                g.setColor(c);
                g.setFont(f);
                if(myplane.over){
                    System.exit(0);
                }

            }
            if(!boss.life()){
                boolean key=true;
                if(key){
                    new MusicUtil("com/neuedu/planewar/video/4.mp3").start();
                    key=false;
                }
                g.drawImage(FrameUtil.getImage("background/v1.png"),0,0,Constant.FRAME_WIDTH,Constant.FRAME_HEIGHT,null);
                Font f=g.getFont();
                Color c=g.getColor();
                g.setColor(Color.red);
                g.setFont(new Font("微软雅黑",Font.BOLD,30));
                g.drawString("Score:"+myplane.score,100,100);
                g.drawString("闯关成功",Constant.FRAME_WIDTH/2-50,Constant.FRAME_HEIGHT/2);
                g.drawString("退出游戏：T",Constant.FRAME_WIDTH/2-50,Constant.FRAME_HEIGHT/2+100);
                if(myplane.over){
                    System.exit(0);
                }
                g.setColor(c);
                g.setFont(f);
            }
            if(myplane.life()&&boss.life()){
                Font f=g.getFont();
                Color c=g.getColor();
                g.setColor(Color.green);
                g.setFont(new Font("微软雅黑",Font.BOLD,15));
                g.drawString("Score:"+myplane.score,30,60);
                g.drawString("boosHP:"+boss.getHP(),30,90);
                g.drawString("主战飞机的血量:"+myplane.getHP(),30,120);
                g.drawString("主战飞机的防御:"+myplane.DEF,30,150);
                g.setColor(c);
                g.setFont(f);
            }

        }
    }
    public static void main(String[] args) {
            new PlaneWarClient().loadFrame("飞机大战");

    }

}