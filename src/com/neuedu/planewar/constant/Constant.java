package com.neuedu.planewar.constant;
import java.io.IOException;
import java.util.Properties;
public class Constant {
    /**
     * 创建加载.properties文件的对象
     */
    public static Properties prop=new Properties();
    static {
        try {
            prop.load(Constant.class.getClassLoader().getResourceAsStream("game.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /*
    * 窗口宽度
    * */
    public static final int FRAME_WIDTH=Integer.parseInt(prop.getProperty("FRAME_WIDTH"));
    /*
     * 窗口高度
     * */
    public static final int FRAME_HEIGHT=Integer.parseInt(prop.getProperty("FRAME_HEIGHT"));
    /**
     * 图片的项目根路径
     */
    public static final String IMG_PATH_PRE= prop.getProperty("IMG_PATH_PRE");
}
