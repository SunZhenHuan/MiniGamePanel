package org.MiniGameKeyboard.Panel;

import javax.swing.*;
import java.awt.*;

public class Back extends JPanel {
    public static String interface_str = "src/main/resources/images/interface.png";//界面图片
    private  final ImageIcon ConfigPanelImage = new ImageIcon(interface_str);

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Image interface_image = ConfigPanelImage.getImage();
        Graphics2D graphics2D = (Graphics2D) g;
        //画界面
        graphics2D.drawImage(interface_image,0,0,this);
    }
    public Back(Dimension size){
        super(null,true);
        setSize(size);
        setLocation(0,0);
    }
}
