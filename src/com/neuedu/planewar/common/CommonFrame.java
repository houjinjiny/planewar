package com.neuedu.planewar.common;
import com.neuedu.planewar.constant.Constant;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
public class CommonFrame extends Frame {
    public void loadFrame(String title){
        //1.设置大小
        this.setSize(Constant.FRAME_WIDTH,Constant.FRAME_HEIGHT);
        //2.设置位置
        //水平垂直居中
        this.setLocationRelativeTo(null);
        //3.设置可见性（默认不可见）
        this.setVisible(true);
        //4.设置关闭窗口按钮的方法的实现
        //使用匿名内部类完成抽象类对象的实现
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        //5.设置改变窗口大小的方法（默认可改变）
        this.setResizable(false);
        //6.设置标题
        this.setTitle(title);
        //7.启动线程
        new MyThread().start();
    }
    /**
     * 1.继承Thread
     * 2.重写run()方法
     *
     */
    class MyThread extends Thread{
        public void run(){
            for(;;){
                //调用paint方法
                repaint();
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    //解决图片闪烁
    Image backImg=null;

    @Override
    public void update(Graphics g) {
        if(backImg==null)
        {
            backImg=createImage(Constant.FRAME_WIDTH,Constant.FRAME_HEIGHT);
        }
        Graphics backg=backImg.getGraphics();
        Color c=backg.getColor();
        backg.setColor(Color.white);
        backg.fillRect(0,0,Constant.FRAME_WIDTH,Constant.FRAME_HEIGHT);
        backg.setColor(c);
        paint(backg);
        g.drawImage(backImg,0,0,null);
    }
}
