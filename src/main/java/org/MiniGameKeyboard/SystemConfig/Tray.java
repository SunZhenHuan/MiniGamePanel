package org.MiniGameKeyboard.SystemConfig;

import org.MiniGameKeyboard.MiniPanel.ConfigPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.Arrays;

/**
 * @author Sun
 * @apiNote 系统托盘
 */
public class Tray implements ActionListener {
    public static TrayIcon trayIcon;
    private Timer Opacity_vis_timer;
    private ConfigPanel configPanel;
    private MenuItem openItem;

    private MenuItem exitItem;
    public Tray(ConfigPanel configPanel){
        this.configPanel = configPanel;
    }
    public void getTray() throws AWTException {
        SystemTray systemTray = null;
        if (isTray()){
            systemTray = SystemTray.getSystemTray();
            Image icon = Toolkit.getDefaultToolkit().getImage("src/main/resources/icon/systemTray.png");
            PopupMenu popupMenu = new PopupMenu();
            openItem = new MenuItem("打开");   //PopupMenu没有像JPopupMenu一样直接实现设置大小的方法
            openItem.addActionListener(this);
            popupMenu.add(openItem);
            exitItem = new MenuItem("退出");
            exitItem.addActionListener(this);
            popupMenu.add(exitItem);
            trayIcon = new TrayIcon(icon,"系统托盘",popupMenu);
            trayIcon.setImageAutoSize(true);
            trayIcon.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    super.mouseClicked(e);
                    if (e.getButton()==MouseEvent.BUTTON1 && e.getClickCount() ==2){
                        Opacity_vis_timer = new Timer(1, e1 -> {
                            float opacity = configPanel.getOpacity();
                            if (opacity<0.95f){
                                opacity +=0.05f;
                                configPanel.setOpacity(opacity);
                            }
                            if (opacity>=0.95f&&opacity<=1.0f)
                            {
                                opacity=1;
                                configPanel.setOpacity(opacity);
                                configPanel.setVisible(true);
                                Opacity_vis_timer.stop();
                            }
                        });
                    Opacity_vis_timer.start();
                    }
                }
            });
            systemTray.add(trayIcon);
        }
        else;
    }
    public static boolean isTray(){
        return SystemTray.isSupported();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == openItem) { // 显示主界面
            Opacity_vis_timer = new Timer(1, e1 -> {
                float opacity = configPanel.getOpacity();
                if (opacity<0.95f){
                    opacity +=0.05f;
                    configPanel.setOpacity(opacity);
                }
                if (opacity>=0.95f&&opacity<=1.0f)
                {
                    opacity=1;
                    configPanel.setOpacity(opacity);
                    configPanel.setVisible(true);
                    Opacity_vis_timer.stop();
                }
            });
            Opacity_vis_timer.start();
        }
        if (e.getSource() == exitItem) { // 退出系统
            System.exit(0);
        }
    }
}
