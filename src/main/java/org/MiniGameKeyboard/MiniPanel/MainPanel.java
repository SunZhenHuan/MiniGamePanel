package org.MiniGameKeyboard.MiniPanel;

import org.MiniGameKeyboard.Panel.KeyboardConfig;
import org.MiniGameKeyboard.SystemConfig.FontUtil;
import org.MiniGameKeyboard.SystemConfig.IGlobalUtil;
import org.MiniGameKeyboard.SystemConfig.MapUtil;

import javax.swing.*;
import javax.swing.Timer;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.RoundRectangle2D;
import java.util.*;
import java.util.List;

/**
 * @author Sun
 * TODO: 2023/9/26
 */
public class MainPanel extends JFrame {
    private final int WIDTH = IGlobalUtil.WIDTH;
    private final int HEIGHT = IGlobalUtil.HEIGHT / 2;
    private final int X = 0;
    private final int Y = HEIGHT;
    private final JPanel KeyboardArea = new JPanel();//键盘区
    private final JLabel exit = new JLabel();
    private final JPanel MouseArea = new JPanel();//鼠标区
    private KeyboardConfig config;
    private JLabel[] labels;
    private List<String> value = new ArrayList<>();//存储字母
    private Point[] points;
    private int alpha =10;
    public MainPanel(KeyboardConfig config, Map<Integer,String>map){
        this.config = config;
        value.addAll(map.values());
        labels = new JLabel[map.size()];
        points = new Point[map.size()];
        int[]flags = new int[map.size()];
        Timer[]timers = new Timer[map.size()];
        int[] alpha = new int[map.size()];
        setUndecorated(true);
        setSize(WIDTH,HEIGHT);
        setLayout(null);
        setShape(new RoundRectangle2D.Double(0,0,WIDTH,HEIGHT,20,20));
        setAlwaysOnTop(true);
        setLocationByPlatform(true);
        setLocation(X,Y);
        setBackground(new Color(0,0,0,0));
        //键盘区
        KeyboardArea.setSize(this.getWidth()/2,this.getHeight());
        KeyboardArea.setLayout(new FlowLayout(FlowLayout.CENTER,20,20));
        KeyboardArea.setLocation(0,0);
        KeyboardArea.setOpaque(false);

        //添加按钮
        for (int index = 0;index < labels.length;index++){
            flags[index] = 1;
            alpha[index] = 10;
            labels[index] = new JLabel(value.get(index));
            labels[index].setName(value.get(index));
            labels[index].setFont(new Font(FontUtil.font1,Font.BOLD,60));
            labels[index].setOpaque(true);
            labels[index].setBackground(new Color(0,0,0,alpha[index]));
//            labels[index].setFocusable(true);
//            labels[index].setRequestFocusEnabled(true);
            labels[index].setForeground(new Color(255,255,255,128));
            labels[index].setBorder(BorderFactory.createLineBorder(new Color(255,255,255,20),5,true));
            int finalIndex = index;
            labels[index].addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    super.mousePressed(e);
                    points[finalIndex] = new Point();
                    points[finalIndex].x = e.getX();
                    points[finalIndex].y = e.getY();
                }
            });
            int finalIndex3 = index;
            addKeyListener(new KeyAdapter() {
                @Override
                public void keyPressed(KeyEvent e) {
                    super.keyPressed(e);
                    alpha[finalIndex3] = 10;
                    if (flags[finalIndex3]==1){
                        flags[finalIndex3] = 0;
                        int ascii = e.getKeyCode();
                        if (ascii == MapUtil.GetOtherCode(labels[finalIndex3].getName())){
                            timers[finalIndex3] = new Timer(1, e1 -> {
                                if (alpha[finalIndex3]<=128){
                                    alpha[finalIndex3]+=5;
                                    labels[finalIndex3].setBackground(new Color(0,0,0,alpha[finalIndex3]));
                                    labels[finalIndex3].repaint();
                                }
                                else{
                                    timers[finalIndex3].stop();
                                    flags[finalIndex3] = 1;
                                }

                            });
                            timers[finalIndex3].start();
                        }
                    }
                }

                @Override
                public void keyReleased(KeyEvent e) {
                    super.keyReleased(e);
                    alpha[finalIndex3] = 128;
                    if (flags[finalIndex3]==0) {
                        int ascii = e.getKeyCode();
                        if (ascii == MapUtil.GetOtherCode(labels[finalIndex3].getName())){
                             timers[finalIndex3] = new Timer(1, e12 -> {
                                if (alpha[finalIndex3]>=10){
                                    alpha[finalIndex3]-=5;
                                    labels[finalIndex3].setBackground(new Color(0,0,0,alpha[finalIndex3]));
                                    labels[finalIndex3].repaint();
                                }
                                else{
                                    timers[finalIndex3].stop();
                                    flags[finalIndex3] = 1;
                                }

                            });
                            timers[finalIndex3].start();
                        }
                    }

                }
            });
            int finalIndex1 = index;
            Point[] points1 = new Point[map.size()];
            labels[index].addMouseMotionListener(new MouseAdapter() {
                @Override
                public void mouseDragged(MouseEvent e) {
                    points1[finalIndex1] = labels[finalIndex1].getLocation();
                    labels[finalIndex1].setLocation(points1[finalIndex1].x+e.getX()-points[finalIndex1].x,points1[finalIndex1].y+e.getY()-points[finalIndex1].y);
                }
            });
            KeyboardArea.add(labels[index]);
        }

        add(KeyboardArea);
        //鼠标区
        MouseArea.setSize(this.getWidth()/2,this.getHeight());
        MouseArea.setLayout(null);
        MouseArea.setOpaque(false);
        MouseArea.setLocation(this.getWidth()/2,0);
        MouseArea.setBorder(BorderFactory.createMatteBorder(0,10,0,0,new Color(255,255,255,128)));

        exit.setSize(50,50);
        exit.setToolTipText("关闭");
//        exit.setHorizontalAlignment(SwingConstants.CENTER);
        exit.setForeground(Color.red);
        exit.setOpaque(true);
        exit.setIcon(new ImageIcon("src/main/resources/images/exit_normal.png"));
        exit.setBackground(new Color(0,0,0,100));
        exit.setLocation(MouseArea.getWidth()-exit.getWidth(),exit.getHeight());
        exit.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (e.getButton() ==MouseEvent.BUTTON1){
                    dispose();
                    KeyboardConfig.configPanel.setVisible(true);
                }
            }
        });
        MouseArea.add(exit);

        add(MouseArea);
    }
//    public static void main(String[] args) {
//        SwingUtilities.invokeLater(()-> new MainPanel().setVisible(true));
//    }
}
