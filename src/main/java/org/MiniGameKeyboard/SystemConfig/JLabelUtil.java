package org.MiniGameKeyboard.SystemConfig;

import org.MiniGameKeyboard.MiniPanel.ConfigPanel;
import org.MiniGameKeyboard.Panel.Carousel;
import org.MiniGameKeyboard.Thread.TimerListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * 轮播按钮
 */
public class JLabelUtil extends JLabel{
    public static String checkbox_label0_str = "src/main/resources/images/checkbox_select.png";
    public static String checkbox_label1_str = "src/main/resources/images/checkbox_normal.png";
    public static String checkbox_label2_str = "src/main/resources/images/checkbox_normal.png";
    public static ImageIcon checkbox_label0_icon;
    public static ImageIcon checkbox_label1_icon;
    public static ImageIcon checkbox_label2_icon = new ImageIcon(checkbox_label2_str);
    private JLabel label0 = new JLabel(){
        @Override
        public void paint(Graphics g) {
            super.paint(g);
            checkbox_label0_icon =new ImageIcon(checkbox_label0_str);
            Graphics2D graphics2D = (Graphics2D) g;
            graphics2D.drawImage(checkbox_label0_icon.getImage(),0,0,getWidth(),getHeight(),this);
        }
    };
    private JLabel label1 = new JLabel(){
        @Override
        public void paint(Graphics g) {
            super.paint(g);
            checkbox_label1_icon = new ImageIcon(checkbox_label1_str);
            Graphics2D graphics2D = (Graphics2D) g;
            graphics2D.drawImage(checkbox_label1_icon.getImage(),0,0,getWidth(),getHeight(),this);
        }
    };
    private JLabel label2 = new JLabel(){
        @Override
        public void paint(Graphics g) {
            super.paint(g);
            checkbox_label2_icon = new ImageIcon(checkbox_label2_str);
            Graphics2D graphics2D = (Graphics2D) g;
            graphics2D.drawImage(checkbox_label2_icon.getImage(),0,0,getWidth(),getHeight(),this);
        }
    };
    public JLabelUtil(Carousel carousel, JComponentUtil componentUtil, ConfigPanel configPanel){
        setSize(carousel.getSize().width/2,carousel.getHeight()/5);
        setLocation(configPanel.getWidth()/2 - this.getWidth()/2,carousel.getY()+carousel.getHeight()+10);
        setForeground(Color.CYAN);
        setHorizontalAlignment(SwingConstants.CENTER);
        label0.setSize(22,23);
        label0.setLocation((int)(this.getWidth()/3.1),this.getHeight()/2 -label0.getHeight()/2);
        label0.setForeground(Color.CYAN);
        label0.setHorizontalAlignment(SwingConstants.CENTER);

        label1.setSize(22,23);
        label1.setLocation((int)(this.getWidth()/2.1),this.getHeight()/2 -label1.getHeight()/2);
        label1.setForeground(Color.CYAN);
        label1.setHorizontalAlignment(SwingConstants.CENTER);

        label2.setSize(22,23);
        label2.setLocation((int)(this.getWidth()/1.6),this.getHeight()/2 -label2.getHeight()/2);
        label2.setForeground(Color.CYAN);
        label2.setHorizontalAlignment(SwingConstants.CENTER);
        TimerListener timerListener = new TimerListener(componentUtil,label0,label1,label2);//添加事件
        timerListener.addAllListener();
        add(label0);
        add(label1);
        add(label2);
    }

}
