package org.MiniGameKeyboard.Panel;

import org.MiniGameKeyboard.SystemConfig.JComponentUtil;
import org.MiniGameKeyboard.SystemConfig.JLabelUtil;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

/**
 * @apiNote "轮播图面板"
 * @author Sun
 */
public class Carousel extends JPanel {
    public static JComponentUtil componentUtil;
    public Carousel(Msg msg) {
        super(null,true);
        setSize(msg.getWidth(),msg.getHeight());
        setLocation(msg.getX(),msg.getY()+msg.getHeight()+10);
        setOpaque(false);
//        setBorder(BorderFactory.createLineBorder(new Color(255,255,255,50).brighter().brighter(),3,true));
        componentUtil=new JComponentUtil(this,0,getWidth(),getWidth()*2);//第1张图片
        add(componentUtil);
    }
}
