package org.MiniGameKeyboard.Tips;


// TODO: 2023/9/18 "面板提示"

import javax.swing.*;
import java.awt.*;

/**
 * @author Sun
 */
public class ConfigTips{
    public static JLabel label = new JLabel();
    public static JLabel Tips(Dimension size,Point pos,boolean visible,StringBuilder tips_msg){
        label.setSize(200,100);
        label.setForeground(Color.BLUE);
        label.setText(String.valueOf(tips_msg));
        label.setLocation(pos);
        label.setBorder(BorderFactory.createLineBorder(new Color(0,0,0,100),5,true));
        label.setVisible(visible);
        return label;
    }
}
