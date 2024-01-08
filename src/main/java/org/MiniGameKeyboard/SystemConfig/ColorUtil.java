package org.MiniGameKeyboard.SystemConfig;

import java.awt.*;
import java.awt.color.ICC_ColorSpace;
import java.util.Random;

/**
 * 颜色工具
 */
public class ColorUtil {
    public static Color getRandomColor(){
        Random random = new Random();
        int R= random.nextInt(255);
        int G= random.nextInt(255);
        int B= random.nextInt(255);
        int A= random.nextInt(255);
        if (R > 10 && G > 10 && B > 10 && A >100) return new Color(R,G,B,A).darker().darker();
        else getRandomColor();
        return new Color(random.nextInt(255),random.nextInt(255),random.nextInt(255)).darker();
    }

    /**
     *
     * @return 返回常规的颜色
     */
    public static Color RoutineColor(){
        Color[] colors = {
                Color.white.darker().darker(),
                Color.red.darker().darker(),
                Color.MAGENTA.darker().darker(),
                Color.green.darker().darker(),
                Color.BLUE.darker().darker(),
                Color.orange.darker().darker(),
                Color.pink.darker().darker(),
                Color.yellow.darker().darker(),
        };
        return colors[new Random().nextInt(colors.length)];
    }
}
