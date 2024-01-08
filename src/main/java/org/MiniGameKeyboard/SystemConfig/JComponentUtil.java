package org.MiniGameKeyboard.SystemConfig;

import org.MiniGameKeyboard.Panel.Carousel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * @author Sun
 * @apiNote "增加组件Util"
 */
public class JComponentUtil extends JPanel{
    public static Carousel carousel;
    private int x0,x1,x2;
    private Timer timer;
    private Point point=new Point();
    public static int X;//轮播大面板的X坐标
    public static JPanel panel0 = new JPanel(){
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D graphics2D =(Graphics2D) g;
            graphics2D.drawImage(new ImageIcon("src/main/resources/images/lunbo1.jpg").getImage(),0,0,getWidth(),getHeight(),this);
        }
    };
    public static JPanel panel1 = new JPanel(){
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D graphics2D =(Graphics2D) g;
            graphics2D.drawImage(new ImageIcon("src/main/resources/images/lunbo2.jpg").getImage(),0,0,getWidth(),getHeight(),this);
        }
    };
    public static JPanel panel2 = new JPanel(){
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D graphics2D =(Graphics2D) g;
            graphics2D.drawImage(new ImageIcon("src/main/resources/images/lunbo2.jpg").getImage(),0,0,getWidth(),getHeight(),this);
        }
    };
    public JComponentUtil(Carousel carousel){}
    public JComponentUtil(Carousel carousel,int... args){
        super(null,true);
        this.x0 = args[0];
        this.x1 = args[1];
        this.x2 = args[2];
        JComponentUtil.carousel = carousel;
        setSize(carousel.getWidth()*3,carousel.getHeight());
        setLocation(X,0);
        setOpaque(false);
        //第一块
        panel0.setSize(carousel.getWidth(),carousel.getHeight());
        panel0.setLocation(args[0],0);
        panel0.setOpaque(false);
        //第二块
        panel1.setSize(carousel.getWidth(),carousel.getHeight());
        panel1.setLocation(args[1],0);
        panel1.setOpaque(false);
        //第3块
        panel2.setSize(carousel.getWidth(),carousel.getHeight());
        panel2.setLocation(args[2],0);
        panel2.setOpaque(false);
        add(panel0);
        add(panel1);
        add(panel2);
    }
}
