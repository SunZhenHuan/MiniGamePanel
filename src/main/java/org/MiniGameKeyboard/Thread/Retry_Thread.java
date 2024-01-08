package org.MiniGameKeyboard.Thread;

import org.MiniGameKeyboard.Api.IpAddSelect;
import org.MiniGameKeyboard.Panel.Msg;

import javax.swing.*;

public class Retry_Thread extends Thread{
    private JLabel region;

    public Retry_Thread(JLabel label){
        this.region = label;
    }

    @Override
    public void run() {
        super.run();
        while (true){
            try {
                if (Msg.hit.equalsIgnoreCase("定位中")||Msg.hit.equalsIgnoreCase("网络错误")){
                    new SwingWorker<String, Void>() {
                        @Override
                        protected String doInBackground() throws InterruptedException {
                            Msg.hit = IpAddSelect.getArea(region);
                            return null;
                        }

                        @Override
                        protected void done() {
                            super.done();
                            region.repaint();
                        }
                    }.execute();
                }
                else{
                    break;
                }
                Thread.sleep(5*1000);//十分钟更新一次
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
