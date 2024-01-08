package org.MiniGameKeyboard.Panel;

import org.MiniGameKeyboard.Api.IpAddSelect;
import org.MiniGameKeyboard.Thread.Retry_Thread;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Msg extends JPanel{
    public static int Y;
    private int enter_flag = 1;
    private int out_flag = 1;
    private int alpha = 255;
    private final JLabel avatar = new JLabel(){
        @Override
        public void paint(Graphics g) {
            super.paint(g);
            Graphics2D graphics2D = (Graphics2D) g;
            Color color = new Color(255, 255, 255, alpha);
            graphics2D.setColor(color);
            graphics2D.setFont(new Font("",Font.PLAIN,30));
            graphics2D.fillOval(this.getWidth() / 2 - 50,this.getHeight() / 2 - 50,100,100);

            Color color_text = new Color(255, 0, 0,128);
            graphics2D.setColor(color_text);
            graphics2D.setFont(new Font("姚体", Font.BOLD,50));
            graphics2D.drawString("+",this.getWidth()/2 - 13,this.getHeight() / 2 +15);
        }
    };//头像
    public static String hit = "定位中";
    private final JLabel region = new JLabel(){
        @Override
        public void paint(Graphics g) {
            super.paint(g);
            ImageIcon icon = new ImageIcon("src/main/resources/images/position.png");
            Graphics2D graphics2D =(Graphics2D) g;
            graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            graphics2D.drawImage(icon.getImage(),0,-5 ,40,40,this);
            graphics2D.setColor(new Color(0,255,0,128));
            graphics2D.setFont(new Font("姚体",Font.BOLD,16));
            graphics2D.drawString(hit,this.getWidth()/2-10,this.getHeight()/2+10);
        }
    };//地区
    private final JTextField signature = new JTextField();//个性签名
    public Msg(Dimension size,JLabel component) throws BadLocationException {
        super(null,true);
        setSize(size.width,size.height/4);
        setLocation(0,component.getHeight());
        setOpaque(false);
//        setBorder(BorderFactory.createLineBorder(new Color(255,255,255,50).brighter().brighter(),3,true));
        //图像
        avatar.setSize((int)(getWidth() / 4.5),(int) (getHeight()/2.2));
        avatar.setOpaque(false);
        avatar.setLocation(this.getWidth()/2 - avatar.getWidth()/2,0);
        //地区
        region.setSize(avatar.getSize().width+15, getHeight()/7);
        region.setToolTipText("位置");
        region.setOpaque(false);
        region.setHorizontalAlignment(SwingConstants.CENTER);
        region.setLocation(this.getWidth()/2 - region.getWidth()/2, avatar.getY()+avatar.getHeight()+10);
        new SwingWorker<String, Void>() {
            @Override
            protected String doInBackground() {
                hit = IpAddSelect.getArea(region);
                return null;
            }

            @Override
            protected void done() {
                super.done();
                region.repaint();
            }
        }.execute();
        new Retry_Thread(region).start();
        add(region);
        signature.setSize(getWidth()/2,getHeight()/4);
        signature.setFont(new Font("姚体",Font.BOLD,20));
        signature.setOpaque(false);
        signature.setEditable(false);
        signature.setDoubleBuffered(true);
        signature.setText("游 客 不 可 设 置 签 名");
        signature.setEnabled(false);
        signature.setBorder(BorderFactory.createMatteBorder(0,0,2,0,new Color(255,255,255,128)));
        signature.setLocation(this.getWidth()/2 - signature.getWidth()/2, region.getY()+region.getHeight()+10);
        signature.setHorizontalAlignment(SwingConstants.CENTER);
        add(signature);
        avatar.addMouseListener(new MouseAdapter() {
            @Override
            @SuppressWarnings(value = "all")
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                enter_flag = 1;
                out_flag = 0;
                if (enter_flag == 1){
                    //当标志为1表示可以移入并且 开启一个线程
                    new Thread(()->{
                        while (alpha >=128){
                            try {
                                alpha-=1;
                                avatar.repaint();
                                Thread.sleep(1);
                            } catch (InterruptedException ex) {
                                throw new RuntimeException(ex);
                            }
                        }
                    }).start();
                }
            }

            @Override
            @SuppressWarnings(value = "all")
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                enter_flag = 0;
                out_flag = 1;
                if (out_flag == 1){
                    new Thread(()->{
                        while (alpha <=254){
                            try {
                                alpha+=1;
                                avatar.repaint();
                                Thread.sleep(1);
                            } catch (InterruptedException ex) {
                                throw new RuntimeException(ex);
                            }
                        }
                    }).start();
                }


            }
        });
        add(avatar);
    }
}

