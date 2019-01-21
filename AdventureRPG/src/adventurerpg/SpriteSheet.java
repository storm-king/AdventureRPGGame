/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adventurerpg;

import java.awt.image.BufferedImage;

/**
 *
 * @author storm
 */
public class SpriteSheet 
{
    private BufferedImage image;
    
    public SpriteSheet( BufferedImage image)
    {
        this.image = image;
    }
    
    public BufferedImage grabImage(int col, int row, int width, int height)
    {
        BufferedImage img = image.getSubimage((col * 48) - 48, (row * 64) - 64, width, height);
        return img;
    }
    
}
