package org.MiniGameKeyboard.Dialog;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;

/**
 * @author Sun
 * @apiNote 弹出层
 * &#064;//  TODO: 2023/9/26
 */
public class LayerDialog extends JDialog {
    private final int Width = 200;
    private final int Height = 100;
    public LayerDialog(String msg, JFrame component){
        System.err.println("2222====>"+component);
        setUndecorated(true);
        setSize(Width,Height);
        setShape(new RoundRectangle2D.Double(0,0,Width,Height,20,20));
        setLayout(null);
        setLocation(0,0);
        setVisible(true);
    }

//    public static void main(String[] args) {
//        new LayerDialog("");
//    }
}
