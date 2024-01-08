package org.MiniGameKeyboard.Dialog;

import com.sun.javafx.geom.Arc2D;
import org.MiniGameKeyboard.MiniPanel.ConfigPanel;
import org.MiniGameKeyboard.Panel.Back;
import org.MiniGameKeyboard.Panel.KeyboardConfig;
import org.MiniGameKeyboard.SystemConfig.FontUtil;
import org.MiniGameKeyboard.SystemConfig.Tray;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ExitDialog extends JDialog {
    private KeyboardConfig config;
    private Timer Opacity_dis_timer;
    public static String flag_str;
    private Point oldPoint =new Point();
    public static String exit_str = "src/main/resources/images/exit_normal.png";//退出
    private final ConfigPanel configPanel;
    private ImageIcon icon;
    private   ImageIcon exitIcon = new ImageIcon(exit_str);
    public JPanel back = new JPanel(){
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D graphics2D =(Graphics2D) g;
            graphics2D.drawImage(icon.getImage(),0,0,this.getWidth(),this.getHeight(),this);
            graphics2D.setColor(Color.orange);
            graphics2D.setFont(new Font(Font.DIALOG_INPUT,Font.PLAIN,14));
            graphics2D.drawString("选择隐藏至系统托盘或退出",0, 14);
        }
    };
    public JPanel exit = new JPanel(){
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D graphics2D =(Graphics2D) g;
            graphics2D.drawImage(exitIcon.getImage(),0,0,this.getWidth(),this.getHeight(),this);
        }
    };
    private ImageIcon up_item_icon = new ImageIcon("src/main/resources/images/item1_normal.png");
    private final JLabel up_item = new JLabel(){
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D graphics2D = (Graphics2D) g;
            graphics2D.drawImage(up_item_icon.getImage(),0,0,this.getWidth(),this.getHeight(),this);
        }
    };
    private ImageIcon down_item_icon = new ImageIcon("src/main/resources/images/item2_normal.png");
    private final JLabel down_item = new JLabel(){
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D graphics2D = (Graphics2D) g;
            graphics2D.drawImage(down_item_icon.getImage(),0,0,this.getWidth(),this.getHeight(),this);
        }
    };
    private static String is_select = "none";
    private JLabel Confirm  =new JLabel("确定");
    public ExitDialog(ConfigPanel configPanel,KeyboardConfig config,ImageIcon... icons){
        super(ConfigPanel.configPanel,true);
        this.icon = icons[0];
        this.configPanel = ConfigPanel.configPanel;
        this.config = config;
        setUndecorated(true);
        setLayout(null);
        setSize(configPanel.getWidth(),(int)(configPanel.getHeight()/3));
        setLocationByPlatform(true);
        setLocationRelativeTo(null);
        //背景
        back.setSize(getWidth(),getHeight());
        back.setLocation(0,0);
        back.setLayout(null);
        back.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0,50),2,true));
        back.setDoubleBuffered(true);
        //添加面板
        exit.setSize(exitIcon.getIconWidth(),exitIcon.getIconHeight());
        exit.setToolTipText("取消");
        exit.setLocation(back.getWidth()-exit.getWidth(),0);

        //选项1
        up_item.setSize(back.getWidth(),back.getHeight()/4);
        up_item.setHorizontalAlignment(SwingConstants.CENTER);
        up_item.setLocation(0,exit.getHeight()+10);
        //选项2
        down_item.setSize(back.getWidth(),back.getHeight()/4);
        down_item.setLocation(0, up_item.getHeight()+up_item.getY()+10);

        //确定
        Confirm.setSize(this.getWidth()/4,50);
        Confirm.setLocation(getWidth()/2 - Confirm.getWidth()/2,getHeight() - Confirm.getHeight());
//        Confirm.setOpaque(true);
        Confirm.setFont(new Font(FontUtil.font,Font.PLAIN,20));
        Confirm.setForeground(new Color(255, 255, 255, 200));
        Confirm.setEnabled(false);
        Confirm.setBorder(BorderFactory.createLineBorder(new Color(18, 248, 154, 128),5,true));
        Confirm.setHorizontalAlignment(SwingConstants.CENTER);
//        Confirm.setBackground(new Color(18, 248, 154, 255));

        back.add(Confirm);
        back.add(down_item);
        back.add(up_item);
        up_item.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (e.getButton()==MouseEvent.BUTTON1){
                    up_item.setBorder(BorderFactory.createLineBorder(new Color(255,255,255,100),2,true));
                    up_item_icon = new ImageIcon("src/main/resources/images/item1_select.png");
                    up_item.repaint();
                    down_item.setBorder(null);
                    down_item_icon = new ImageIcon("src/main/resources/images/item2_normal.png");
                    down_item.repaint();

                    Confirm.setForeground(new Color(255, 255, 255, 255));
                    Confirm.setBorder(BorderFactory.createLineBorder(new Color(18, 248, 154, 255),5,true));

                    Confirm.setEnabled(true);
                    is_select = "item1";
                }
            }
        });
        down_item.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (e.getButton()==MouseEvent.BUTTON1){
                    up_item.setBorder(null);
                    up_item_icon = new ImageIcon("src/main/resources/images/item1_normal.png");
                    up_item.repaint();
                    down_item.setBorder(BorderFactory.createLineBorder(new Color(255,255,255,100),2,true));
                    down_item_icon = new ImageIcon("src/main/resources/images/item2_select.png");
                    down_item.repaint();
                    Confirm.setForeground(new Color(255, 255, 255, 255));
                    Confirm.setBorder(BorderFactory.createLineBorder(new Color(18, 248, 154, 255),5,true));
                    Confirm.setEnabled(true);
                    is_select = "item2";
                }
            }
        });
        exit.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (e.getButton()==MouseEvent.BUTTON1){
                    down_item.setBorder(null);
                    down_item_icon = new ImageIcon("src/main/resources/images/item2_normal.png");
                    down_item.repaint();
                    up_item.setBorder(null);
                    up_item_icon = new ImageIcon("src/main/resources/images/item1_normal.png");
                    up_item.repaint();
                    Confirm.setForeground(new Color(255, 255, 255, 200));
                    Confirm.setBorder(BorderFactory.createLineBorder(new Color(18, 248, 154, 128),5,true));
                    Confirm.setEnabled(false);
                    is_select = "none";
                    dispose();
                }

            }
        });
        Confirm.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (e.getButton()==MouseEvent.BUTTON1){
                    if (Confirm.isEnabled()){
                        //选择了第一项
                        if (is_select.equalsIgnoreCase("item1")) {
                            //系统托盘
                            dispose();
                            Opacity_dis_timer = new Timer(1, new ActionListener() {
                                float opacity =configPanel.getOpacity();

                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    if (opacity > 0.05f) {
                                        opacity -= 0.05f;
                                        configPanel.setOpacity(opacity);
                                    }
                                    if (opacity <= 0.05f && opacity >= 0.0f) {
                                        opacity = 0;
                                        configPanel.setOpacity(opacity);
                                        configPanel.dispose();
                                        Opacity_dis_timer.stop();
                                        try {
                                            new Tray(configPanel).getTray();
                                            flag_str = "Tray";
                                            config.setVisible(false);
                                        } catch (AWTException ex) {
                                            throw new RuntimeException(ex);
                                        }
                                    }
                                }
                            });
                            Opacity_dis_timer.start();
                        }
                        else if (is_select.equalsIgnoreCase("item2")){
                            //第二项
                            dispose();
                            System.exit(0);//结束程序
                        }
                    }
                }
            }
        });
        back.add(exit);
        add(back);
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                oldPoint.x = e.getX();
                oldPoint.y = e.getY();
            }
        });
        addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                super.mouseDragged(e);
                Point newPoint = getLocation();//记录新的位置
                setLocation(newPoint.x+e.getX()-oldPoint.x,newPoint.y+e.getY()-oldPoint.y);
            }
        });
        setVisible(true);

    }
}
