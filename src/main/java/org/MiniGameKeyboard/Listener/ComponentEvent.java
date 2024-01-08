package org.MiniGameKeyboard.Listener;

import org.MiniGameKeyboard.MiniPanel.ConfigPanel;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ComponentEvent extends MouseAdapter {
    private final String str;
    private JLabel label;
    public ComponentEvent(JLabel label,String str){
        this.label= label;
        this.str=str;
    }
    @Override
    public void mouseEntered(MouseEvent e) {
        super.mouseEntered(e);
        if (str.contains("little")){
            ConfigPanel.little_str = "src/main/resources/images/little_over.png";
            label.repaint();
        }
        if (str.contains("exit")){
            ConfigPanel.exit_str = "src/main/resources/images/exit_over.png";
            label.repaint();
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        super.mouseExited(e);
        if (str.contains("little")){
            ConfigPanel.little_str = "src/main/resources/images/little_normal.png";
            label.repaint();
        }
        if (str.contains("exit")){
            ConfigPanel.exit_str = "src/main/resources/images/exit_normal.png";
            label.repaint();
        }
    }
}
