package com.neuedu.planewar.common;
import com.neuedu.planewar.constant.Constant;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;
public class ImageUtil {
    /**
     * 使用Map容器装图片
     */
    public static Map<String,Image> images=new HashMap<>();
    static {
        //我方01飞机的图片
        for(int i=0;i<3;i++){
            images.put("myplane0"+(i+1),FrameUtil.getImage(Constant.IMG_PATH_PRE+"myplane/0"+(i+1)+".png"));
        }
        //我方01飞机子弹的图片
        for(int i=0;i<3;i++){
            images.put("bullet0"+(i+1),FrameUtil.getImage(Constant.IMG_PATH_PRE+ "myplane/bullet/0" +(i+1)+".png"));
        }
        //敌方01飞机的图片
        for(int i=0;i<3;i++){
            images.put("enemyPlane0"+(i+1),FrameUtil.getImage(Constant.IMG_PATH_PRE+"enemyPlane/0"+(i+1)+".png"));
        }
        images.put("enbullet01",FrameUtil.getImage(Constant.IMG_PATH_PRE+"enemyPlane/bullet/01.png"));
        //爆炸图片
        for(int i=0;i<6;i++){
            images.put("boom0"+(i+1),FrameUtil.getImage(Constant.IMG_PATH_PRE+"explode/boom0"+(i+1)+".png"));
        }
        //加血道具
        images.put("HP1",FrameUtil.getImage(Constant.IMG_PATH_PRE+"item/HP1.png"));
        //加防御道具
        images.put("DEF1",FrameUtil.getImage(Constant.IMG_PATH_PRE+"item/DEF1.png"));
        //boss图片
        for(int i=0;i<4;i++){
            images.put("boss0"+(i+1),FrameUtil.getImage(Constant.IMG_PATH_PRE+"boss/0"+(i+1)+".png"));
        }
        //boss子弹
        for(int i=0;i<8;i++){
            images.put("bossBullet0"+(i+1),FrameUtil.getImage(Constant.IMG_PATH_PRE+"boss/bossBullet/0"+(i+1)+".png"));
        }
        //雷击图片
//        for(int i=0;i<10;i++){
//            images.put("bossbBullet0"+(i+1),FrameUtil.getImage(Constant.IMG_PATH_PRE+"boss/bossBullet/l"+(i+1)+".png"));
//        }
        //背景
        images.put("background",FrameUtil.getImage(Constant.IMG_PATH_PRE+"background/bg01.jpg"));
        //开始图片
        for(int i=0;i<3;i++){
            images.put("start"+(i+1),FrameUtil.getImage(Constant.IMG_PATH_PRE+"background/start"+(i+1)+".png"));
        }
        for(int i=0;i<3;i++){
            images.put("l"+(i+1),FrameUtil.getImage(Constant.IMG_PATH_PRE+"boss/bossBullet/l"+(i+1)+".png"));
        }
    }
}
