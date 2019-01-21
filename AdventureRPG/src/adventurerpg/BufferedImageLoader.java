/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adventurerpg;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

/**
 *
 * @author storm
 */
public class BufferedImageLoader 
{
    
    private BufferedImage image;
    private Image image1;
    
    public BufferedImage loadBufferedImage(String path) throws IOException
    {
        
        image = ImageIO.read(getClass().getResource(path));
        return image;
    }
    
    public Image loadImage(String path)
    {
        image1 = new ImageIcon(getClass().getResource(path)).ge‌​tImage();
        return image1;
    }
    
    
    
}
