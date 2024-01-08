package org.MiniGameKeyboard.MiniPanel;

import org.MiniGameKeyboard.Panel.Loading;
import org.MiniGameKeyboard.SystemConfig.IGlobalUtil;

import javax.swing.*;
import java.awt.*;

/**
 * 开始进入的加载界面
 */
public class LoadingPanel extends JDialog{
    public LoadingPanel(){
        setUndecorated(true);//无修饰
        setSize(IGlobalUtil.WIDTH,IGlobalUtil.HEIGHT);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setLayout(null);
        setAlwaysOnTop(true);//总在顶层
        setLocationByPlatform(true);
        setLocationRelativeTo(null);
        setResizable(false);
        setBackground(new Color(0,0,0,0));
        add(new Loading(this.getWidth(),this.getHeight(),this));
        setVisible(true);
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(LoadingPanel::new);

    }
}
