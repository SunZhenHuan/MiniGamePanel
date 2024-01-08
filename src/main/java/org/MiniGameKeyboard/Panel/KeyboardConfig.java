package org.MiniGameKeyboard.Panel;

import org.MiniGameKeyboard.Enum.KeyEnum;
import org.MiniGameKeyboard.MiniPanel.ConfigPanel;
import org.MiniGameKeyboard.MiniPanel.MainPanel;
import org.MiniGameKeyboard.SystemConfig.ColorUtil;
import org.MiniGameKeyboard.SystemConfig.FontUtil;
import org.MiniGameKeyboard.SystemConfig.IGlobalUtil;
import org.w3c.dom.css.RGBColor;

import javax.swing.*;
import java.awt.*;
import java.awt.color.ICC_ColorSpace;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.*;
import java.util.List;

/**
 * 键盘配置中心
 */
public class KeyboardConfig extends JDialog {
    public static KeyboardConfig keyboardConfig;
    public static ConfigPanel configPanel;
    private Point point=new Point();
    private ImageIcon back = new ImageIcon("src/main/resources/images/interface.png");
    private final JPanel back_panel = new JPanel(){
        @Override
        public void paint(Graphics g) {
            super.paint(g);
            Graphics2D graphics2D =(Graphics2D) g;
            graphics2D.drawImage(back.getImage(),0,0,this.getWidth(),this.getHeight(),this);
            graphics2D.setColor(new Color(255,255,255,128));
            graphics2D.setFont(new Font(FontUtil.font1,Font.BOLD,20));
            graphics2D.drawString("配置中心",0,20);
            graphics2D.drawLine(0,30,this.getWidth(),30);
        }
    };
    private ImageIcon  exit_icon = new ImageIcon("src/main/resources/images/exit_normal.png");
    private JLabel exit = new JLabel();
    private JPanel contentPanel = new JPanel();
    private JLabel[]keyLabels = new JLabel[KeyEnum.values().length];
    private boolean[] labelFlag = new boolean[KeyEnum.values().length];
    private final JLabel confirm = new JLabel();//确认
    private final JLabel cancel = new JLabel();//取消
    private final Map<Integer,String>allName = new HashMap<>();

    public KeyboardConfig(ConfigPanel configPanel){
        this.configPanel = configPanel;
        setUndecorated(true);
        setSize(IGlobalUtil.WIDTH/2,(int)(IGlobalUtil.HEIGHT/1.5));
        setLocationByPlatform(true);
        setLocationRelativeTo(null);
        setLayout(null);
        back_panel.setSize(getSize());
        back_panel.setLayout(null);
        back_panel.setDoubleBuffered(true);
        back_panel.setLocation(0,0);

        exit.setSize(exit_icon.getIconWidth(),exit_icon.getIconHeight());
        exit.setLocation(this.getWidth()-exit.getWidth(),0);
        exit.setIcon(exit_icon);


        //主体面板
        contentPanel.setSize(getWidth(),this.getHeight() - exit.getHeight());
        contentPanel.setLocation(0,exit.getHeight());
        contentPanel.setLayout(new FlowLayout(FlowLayout.CENTER,20,40));
        contentPanel.setDoubleBuffered(true);
        contentPanel.setOpaque(false);
        for (KeyEnum keyEnum:KeyEnum.values()){
            int index = keyEnum.ordinal();//下标
            String KeyName = keyEnum.name();
            keyLabels[index] = new JLabel();
            keyLabels[index].setName(KeyName);
            keyLabels[index].setBorder(BorderFactory.createLineBorder(new Color(255,255,255,100),5,true));
            keyLabels[index].setText(KeyName);
            keyLabels[index].setOpaque(true);
            keyLabels[index].setBackground(ColorUtil.RoutineColor().darker());
            keyLabels[index].setFont(new Font(FontUtil.font1,Font.BOLD,30));
            keyLabels[index].setForeground(Color.WHITE.darker());
            keyLabels[index].addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    super.mouseClicked(e);
                    if (e.getButton() == MouseEvent.BUTTON1 && e.getClickCount() == 2 && !labelFlag[index]){
                        keyLabels[index].setBackground(keyLabels[index].getBackground().brighter());
                        keyLabels[index].setForeground(Color.WHITE.brighter());
                        labelFlag[index] = true;
                        allName.put(index,keyLabels[index].getName());
                    }
                    if (labelFlag[index] && e.getButton() == MouseEvent.BUTTON3){
                        keyLabels[index].setBackground(keyLabels[index].getBackground().darker());
                        keyLabels[index].setForeground(Color.WHITE.darker());
                        labelFlag[index] = false;
                        allName.remove(index);
                    }
                }
            });
            contentPanel.add(keyLabels[index]);
        }
        confirm.setSize(100,50);
        confirm.setBackground(Color.BLUE);
        confirm.setOpaque(true);
        confirm.setBackground(new Color(0,255,0, 128));
        confirm.setBorder(BorderFactory.createLineBorder(Color.WHITE.darker().darker(),3,true));
        confirm.setLocation(getWidth()/2-confirm.getWidth()*2,getHeight()-confirm.getHeight()-20);
        confirm.setHorizontalAlignment(SwingConstants.CENTER);
        confirm.setForeground(Color.WHITE);
        confirm.setFont(new Font(FontUtil.font1,Font.BOLD,30));
        confirm.setText("确定");

        cancel.setSize(100,50);
        cancel.setBackground(Color.BLUE);
        cancel.setOpaque(true);
        cancel.setBackground(new Color(0,255,0, 128));
        cancel.setBorder(BorderFactory.createLineBorder(Color.WHITE.darker().darker(),3,true));
        cancel.setLocation(getWidth()/2+cancel.getWidth(),getHeight()-cancel.getHeight()-20);
        cancel.setHorizontalAlignment(SwingConstants.CENTER);
        cancel.setForeground(Color.WHITE);
        cancel.setFont(new Font(FontUtil.font1,Font.BOLD,30));
        cancel.setText("取消");

        confirm.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (e.getButton() == MouseEvent.BUTTON1 && !(allName.isEmpty())){
                    //把选好的交给mini面板
                    dispose();
                    SwingUtilities.invokeLater(() -> {
                        new MainPanel(keyboardConfig,allName).setVisible(true);
                    });
                }
            }
        });
        cancel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (e.getButton() == MouseEvent.BUTTON1){
                    if (!allName.isEmpty()){
                        Set<Integer>key = allName.keySet();
                        for (Integer index:key){
                            keyLabels[index].setBackground(keyLabels[index].getBackground().darker());
                            keyLabels[index].setForeground(Color.WHITE.darker());
                            labelFlag[index] = false;
                        }
                        allName.clear();
                    }
                }
            }
        });

        add(cancel);
        add(confirm);
        add(contentPanel);
        add(exit);
        add(back_panel);
        exit.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (e.getButton() == MouseEvent.BUTTON1){
                    dispose();
                    configPanel.setVisible(true);
                }
            }
        });
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                point.x = e.getX();
                point.y = e.getY();
            }
        });
        addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                super.mouseDragged(e);
                Point p = getLocation();
                setLocation(p.x+e.getX() - point.x,p.y+e.getY()-point.y);
            }
        });
    }
//    public static void main(String[] args) {
//        SwingUtilities.invokeLater(()-> new KeyboardConfig().setVisible(true));
//    }
}