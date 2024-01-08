package org.MiniGameKeyboard.Thread;

import org.MiniGameKeyboard.SystemConfig.JComponentUtil;
import org.MiniGameKeyboard.SystemConfig.JLabelUtil;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * @apiNote "控制轮播图"
 * @author Sun
 */
public class TimerListener{
    private int flag0 = 1, flag1 = 1, flag2 = 1;
    private JComponentUtil jComponentUtil;
    private JLabel label0,label1,label2;
    private JPanel panel0,panel1,panel2;
    private int X;//记录大面板坐标
    private Timer timer0,timer1,timer2;
    private Thread motionlessTimer;//什么都没做的时候
    public TimerListener(JComponentUtil jComponentUtil, JLabel... jLabels){
        this.jComponentUtil = jComponentUtil;
        this.label0 = jLabels[0];
        this.label1= jLabels[1];
        this.label2 = jLabels[2];
        this.panel0 = JComponentUtil.panel0;
        this.panel1 = JComponentUtil.panel1;
        this.panel2 = JComponentUtil.panel2;
        this.X = JComponentUtil.X;
    }
    public void addAllListener(){
        label0.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);

                if (e.getButton() == MouseEvent.BUTTON1){
                    if (flag0 == 1){
                        flag0 = 0;
                        flag1 = 0;
                        flag2 = 0;
                        JLabelUtil.checkbox_label0_str = "src/main/resources/images/checkbox_select.png";
                        label0.repaint();
                        JLabelUtil.checkbox_label1_str = "src/main/resources/images/checkbox_normal.png";
                        label1.repaint();
                        JLabelUtil.checkbox_label2_str = "src/main/resources/images/checkbox_normal.png";
                        label2.repaint();
                        timer0 = new Timer(1, e1 -> {
                            if (X<0){
                                X+=10;
                                jComponentUtil.setLocation(X,0);//只要不为零无论多远都要回来
                            }
                            if (X==0){
                                timer0.stop();
                                flag1 =1;
                                flag2 =1;
                            }

                        });
                        timer0.start();
                    }
                }
            }
        });
        label1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (e.getButton() == MouseEvent.BUTTON1){
                    if (flag1 == 1){
                        flag0 = 0;
                        flag1 = 0;
                        flag2 = 0;
                        JLabelUtil.checkbox_label0_str = "src/main/resources/images/checkbox_normal.png";
                        label0.repaint();
                        JLabelUtil.checkbox_label1_str = "src/main/resources/images/checkbox_select.png";
                        label1.repaint();
                        JLabelUtil.checkbox_label2_str = "src/main/resources/images/checkbox_normal.png";
                        label2.repaint();
                        timer1 = new Timer(1, e1 -> {
                            if (X <= -(panel1.getX())){
                                X+=10;
                                jComponentUtil.setLocation(X,0);//只要不为零无论多远都要回来
                            }
                            else {
                                if (X<panel1.getX()){//向前或者向后回来
                                    X-=10;
                                    jComponentUtil.setLocation(X,0);//只要不为零无论多远都要回来
                                }
                                else if (X>-(panel2.getX())){
                                    X-=10;
                                    jComponentUtil.setLocation(X,0);//只要不为零无论多远都要回来
                                }
                            }

                            if (X==-(panel1.getX())){
                                timer1.stop();
                                flag0 =1;
                                flag2 =1;
                            }

                        });
                        timer1.start();
                    }

                }
            }
        });
        label2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (e.getButton() == MouseEvent.BUTTON1){
                    if (flag2 == 1) {
                        flag0 = 0;
                        flag1 = 0;
                        flag2 = 0;
                        JLabelUtil.checkbox_label0_str = "src/main/resources/images/checkbox_normal.png";
                        label0.repaint();
                        JLabelUtil.checkbox_label1_str = "src/main/resources/images/checkbox_normal.png";
                        label1.repaint();
                        JLabelUtil.checkbox_label2_str = "src/main/resources/images/checkbox_select.png";
                        label2.repaint();
                        timer2 = new Timer(1, e1 -> {
                            if (X<panel2.getX()){
                                X-=10;
                                jComponentUtil.setLocation(X,0);//只要不为零无论多远都要回来
                            }
                            if (X>-(panel2.getX())){
                                X-=10;
                                jComponentUtil.setLocation(X,0);//只要不为零无论多远都要回来
                            }
                            if (X==(-panel2.getX())){
                                timer2.stop();
                                flag0 = 1;
                                flag1 = 1;
                            }
                        });
                        timer2.start();
                    }

                }
            }
        });
        motionlessTimer=new Thread(()->{
            while (true){
                try {
                    if (X == 0){
                        while (X>-(panel1.getX())){
                            //五秒过后如果X一直是第一张就向后
                            X-=10;
                            jComponentUtil.setLocation(X,0);
                            JLabelUtil.checkbox_label0_str = "src/main/resources/images/checkbox_normal.png";
                            label0.repaint();
                            JLabelUtil.checkbox_label1_str = "src/main/resources/images/checkbox_select.png";
                            label1.repaint();
                            JLabelUtil.checkbox_label2_str = "src/main/resources/images/checkbox_normal.png";
                            label2.repaint();
                            Thread.sleep(1);
                        }
                        flag0=1;
                        flag2 =1;
                    }
                    else if(X == -(panel1.getX())){
                        //第二张向后
                        while (X>-(panel2.getX())){
                            X-=10;
                            jComponentUtil.setLocation(X,0);
                            JLabelUtil.checkbox_label0_str = "src/main/resources/images/checkbox_normal.png";
                            label0.repaint();
                            JLabelUtil.checkbox_label1_str = "src/main/resources/images/checkbox_normal.png";
                            label1.repaint();
                            JLabelUtil.checkbox_label2_str = "src/main/resources/images/checkbox_select.png";
                            label2.repaint();
                            Thread.sleep(1);
                        }
                        flag0=1;
                        flag2 =1;
                    }
                    else if (X==-(panel2.getX())){
                        //第二张向后
                        while (X<panel0.getX()){
                            X+=10;
                            jComponentUtil.setLocation(X,0);
                            JLabelUtil.checkbox_label0_str = "src/main/resources/images/checkbox_select.png";
                            label0.repaint();
                            JLabelUtil.checkbox_label1_str = "src/main/resources/images/checkbox_normal.png";
                            label1.repaint();
                            JLabelUtil.checkbox_label2_str = "src/main/resources/images/checkbox_normal.png";
                            label2.repaint();
                            Thread.sleep(1);
                        }
                        flag1 =1;
                        flag0 =1;
                    }
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        motionlessTimer.start();
    }
}
