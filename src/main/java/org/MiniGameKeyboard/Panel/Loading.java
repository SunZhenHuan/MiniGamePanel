package org.MiniGameKeyboard.Panel;

import org.MiniGameKeyboard.MiniPanel.ConfigPanel;
import org.MiniGameKeyboard.MiniPanel.LoadingPanel;
import org.MiniGameKeyboard.SystemConfig.ColorUtil;
import org.MiniGameKeyboard.SystemConfig.FontUtil;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import java.awt.*;
import java.lang.reflect.InvocationTargetException;

/**
 * @author Sun
 * @apiNote 面板集合
 */
public class Loading extends JPanel implements Runnable{
    private StringBuilder stringBuilder = new StringBuilder("Loading");
    private LoadingPanel loadingPanel;
    private long time=0L;
    @Override
    public void paint(Graphics g) {
        Graphics2D graphics2D= (Graphics2D) g;
        graphics2D.setColor(ColorUtil.getRandomColor());
        graphics2D.setFont(new Font(FontUtil.font,Font.BOLD,100));
        graphics2D.drawString(String.valueOf(stringBuilder),this.getWidth()/2-150,this.getHeight()/2);
    }
    public Loading(int width,int height,LoadingPanel loadingPanel){
        super(null,true);
        this.loadingPanel = loadingPanel;
        setSize(width,height);
        setLocation(0,0);
        Thread loading_thread = new Thread(this);
        loading_thread.start();
        }

    @Override
    public void run() {
        while (true){
            long startMill=System.currentTimeMillis();
            try {
                this.stringBuilder.append(".");//加点
                repaint();//重画
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            long endMill = System.currentTimeMillis();
            time += (endMill-startMill);
            if (time > 4000){
                loadingPanel.dispose();
                SwingUtilities.invokeLater(()->{
                    try {
                        ConfigPanel.configPanel = new ConfigPanel();
                    } catch (InterruptedException | InvocationTargetException | BadLocationException e) {
                        throw new RuntimeException(e);
                    }
                });
                break;
            }
        }
    }
}
