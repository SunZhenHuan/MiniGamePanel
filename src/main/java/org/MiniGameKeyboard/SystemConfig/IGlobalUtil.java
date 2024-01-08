package org.MiniGameKeyboard.SystemConfig;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

/**
 * @author Sun
 */
// TODO: 2023/9/17  全局的工具接口
public interface IGlobalUtil {
    Toolkit toolkit = Toolkit.getDefaultToolkit();//工具
    int WIDTH = toolkit.getScreenSize().width;//宽度
    int HEIGHT = toolkit.getScreenSize().height;//高度
}
