package org.MiniGameKeyboard.MiniPanel;

import org.MiniGameKeyboard.Dialog.ExitDialog;
import org.MiniGameKeyboard.Listener.ComponentEvent;
import org.MiniGameKeyboard.Panel.Back;
import org.MiniGameKeyboard.Panel.Carousel;
import org.MiniGameKeyboard.Panel.KeyboardConfig;
import org.MiniGameKeyboard.Panel.Msg;
import org.MiniGameKeyboard.SystemConfig.*;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import java.awt.*;
import java.awt.event.*;
import java.lang.reflect.InvocationTargetException;

/**
 * @apiNote 按键配置界面
 */
public class ConfigPanel extends JFrame {
    public static ConfigPanel configPanel;
    private Timer Opacity_dis_timer;
    private Timer Opacity_vis_timer;
    Point oldPoint = new Point();//记录按下的之前位置
    private int on_top_flag = 1;
    public static String little_str = "src/main/resources/images/little_normal.png";//缩小
    private ImageIcon littleIcon = new ImageIcon(little_str);
    private final JLabel shrink = new JLabel(){
        @Override
        public void paint(Graphics g) {
            super.paint(g);
            littleIcon = new ImageIcon(little_str);
            Image little_image = littleIcon.getImage();
            Graphics2D graphics2D = (Graphics2D) g;
            //画按钮
            graphics2D.drawImage(little_image,0,0,this);
        }
    };
    public static String exit_str = "src/main/resources/images/exit_normal.png";//退出
    public static ImageIcon exitIcon = new ImageIcon(exit_str);
    private final JLabel exit = new JLabel(){
        @Override
        public void paint(Graphics g) {
            super.paint(g);
            exitIcon= new ImageIcon(exit_str);
            Image exit_image = exitIcon.getImage();
            Graphics2D graphics2D = (Graphics2D) g;
            //画按钮
            graphics2D.drawImage(exit_image,0,0,this);
        }
    };
    public static String onTop_str = "src/main/resources/images/OnTop_normal.png";//置顶
    private   ImageIcon OnTopIcon = new ImageIcon(onTop_str);
    private final JLabel onTop = new JLabel(){
        @Override
        public void paint(Graphics g) {
            super.paint(g);
            OnTopIcon= new ImageIcon(onTop_str);
            Image onTop_image = OnTopIcon.getImage();
            Graphics2D graphics2D = (Graphics2D) g;
            //画按钮
            graphics2D.drawImage(onTop_image,0,0,this.getWidth(),this.getHeight(),this);
        }
    };
    private final JLabel SetUp = new JLabel(){
        @Override
        public void paint(Graphics g) {
            super.paint(g);
            g.setFont(new Font("姚体", Font.PLAIN,14));
            g.setColor(new Color(255,255,255, 128));
            g.drawString("设置",this.getWidth()/2 - 14,this.getHeight()/2 + 7);
        }
    };
    private final JLabel configure_the_hub = new JLabel();
    public static KeyboardConfig keyboardConfig;
    public ConfigPanel() throws InterruptedException, InvocationTargetException, BadLocationException {
//        setCursor(Toolkit.getDefaultToolkit().createCustomCursor(new ImageIcon("src/main/resources/images/cursor.png").getImage(),new Point(0,0),"default"));
//        setIconImage(new ImageIcon("icon/systeTray.png").getImage());
        setUndecorated(true);
        setSize(IGlobalUtil.WIDTH/4,(int)(IGlobalUtil.HEIGHT/1.2));
        setLayout(null);
        setOpacity(0);
        setLocationByPlatform(true);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        //设置标签
        SetUp.setSize(littleIcon.getIconWidth(),littleIcon.getIconHeight());
        SetUp.setLocation(0,0);
        add(SetUp);
        //缩小按钮
        shrink.setSize(littleIcon.getIconWidth(), littleIcon.getIconHeight());
        shrink.setLocation(getWidth() - shrink.getWidth() * 2,0);
        shrink.setToolTipText("缩小");
        add(shrink);
        //置顶按钮
        onTop.setSize(littleIcon.getIconWidth(), littleIcon.getIconHeight());
        onTop.setLocation(getWidth() - shrink.getWidth() * 3,0);
        onTop.setToolTipText("置顶");
        add(onTop);
        //退出按钮
        exit.setSize(shrink.getWidth(), shrink.getHeight());
        exit.setLocation(getWidth() - exit.getWidth(),0);
        exit.setToolTipText("隐藏托盘或者关闭");
        add(exit);
        //信息面板
        add(new Msg(this.getSize(),exit));
        //轮播图面板
        add(new Carousel(new Msg(this.getSize(),exit)));
        JComponentUtil componentUtil = Carousel.componentUtil;
        Carousel carousel =JComponentUtil.carousel;
        JLabelUtil labelUtil = new JLabelUtil(carousel,componentUtil,this);
        add(labelUtil);
        //配置中心
        configure_the_hub.setSize(getWidth(),getHeight()-labelUtil.getY()-labelUtil.getHeight());
        configure_the_hub.setBorder(BorderFactory.createLineBorder(new Color(255, 255, 255,50),3,true));
        configure_the_hub.setBorder(BorderFactory.createMatteBorder(3,0,0,0,new Color(255, 255, 255,50)));
        configure_the_hub.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        configure_the_hub.setLocation(0,labelUtil.getY()+labelUtil.getHeight());
        configure_the_hub.setForeground(new Color(0,255,0,50));
        configure_the_hub.setFont(new Font(FontUtil.font1,Font.BOLD,60));
        configure_the_hub.setText("配 置 中 心");
        configure_the_hub.setHorizontalAlignment(SwingConstants.CENTER);
        keyboardConfig = new KeyboardConfig(configPanel);//键盘配置
        configure_the_hub.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (e.getButton()==MouseEvent.BUTTON1){
                    if (ExitDialog.flag_str==null||ExitDialog.flag_str.equalsIgnoreCase("")){
                        try {
                            new Tray(configPanel).getTray();
                            ExitDialog.flag_str = "Tray";
                            exit_str = "src/main/resources/images/exit_normal.png";//退出
                            exit.repaint();
                            dispose();
                            keyboardConfig = new KeyboardConfig(configPanel);//键盘配置
                            keyboardConfig.setVisible(true);
                            KeyboardConfig.keyboardConfig = keyboardConfig;
                        } catch (AWTException ex) {
                            throw new RuntimeException(ex);
                        }
                    }
                    else{
                        exit_str = "src/main/resources/images/exit_normal.png";//退出
                        exit.repaint();
                        dispose();
                        keyboardConfig = new KeyboardConfig(configPanel);//键盘配置
                        keyboardConfig.setVisible(true);
                        KeyboardConfig.keyboardConfig = keyboardConfig;
                    }
                }
            }
        });
        add(configure_the_hub);
        //主面板
        add(new Back(getSize()));
        shrink.addMouseListener(new ComponentEvent(shrink,"little"));
        shrink.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (e.getButton() == MouseEvent.BUTTON1)
                    setState(ICONIFIED);
            }
        });
        exit.addMouseListener(new ComponentEvent(exit,"exit"));
        exit.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (e.getButton()==MouseEvent.BUTTON1){
                        if (ExitDialog.flag_str==null||ExitDialog.flag_str.equalsIgnoreCase("")){
                            new ExitDialog(configPanel,keyboardConfig,new ImageIcon(Back.interface_str),new ImageIcon(exit_str));
                        }
                        else{
                            exit_str = "src/main/resources/images/exit_normal.png";//退出
                            exit.repaint();
                            dispose();
                        }

                    }
            }
        });
        onTop.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (e.getButton() == MouseEvent.BUTTON1){
                    if (on_top_flag == 1){
                        setAlwaysOnTop(true);
                        onTop.setToolTipText("取消置顶");
                        onTop_str = "src/main/resources/images/OnTop_over.png";
                        onTop.repaint();
                        on_top_flag = 0;
                    }
                    else{
                        setAlwaysOnTop(false);
                        onTop.setToolTipText("置顶");
                        onTop_str = "src/main/resources/images/OnTop_normal.png";
                        onTop.repaint();
                        on_top_flag = 1;
                    }
                }
            }
        });

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                int c = e.getKeyChar();
                if (e.isAltDown() && c == 'c'){
                    setLocationRelativeTo(null);//居中
                }

            }
        });
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                oldPoint.x = e.getX();
                oldPoint.y = e.getY();
            }
        });
        addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                Point newPoint = getLocation();//记录新的位置
                setLocation(newPoint.x+e.getX()-oldPoint.x,newPoint.y+e.getY()-oldPoint.y);//窗体当前位置+拖动时的位置-按下时的位置
            }
        });
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowOpened(WindowEvent e) {
                super.windowOpened(e);
                Opacity_vis_timer = new Timer(1, e1 -> {
                    float op = getOpacity();
                    if (op<0.95f){
                        op +=0.05f;
                        setOpacity(op);
                    }
                    if (op>=0.95f&&op<=1.0f)
                    {
                        op=1;
                        setOpacity(op);
                        setVisible(true);
                        Opacity_vis_timer.stop();
                    }
                });
                    Opacity_vis_timer.start();
            }
        });
        setVisible(true);
    }

//    public static void main(String[] args) {
//
//        SwingUtilities.invokeLater(()->{
//            try {
//                configPanel = new ConfigPanel();
//                configPanel.setVisible(true);
//            } catch (InterruptedException | InvocationTargetException | BadLocationException e) {
//                throw new RuntimeException(e);
//            }
//        });
//    }
}
