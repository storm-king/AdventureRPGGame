/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adventurerpg;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Sprite {

    public BufferedImage spriteSheet;
    private static final int TILE_SIZEX = 48;
    private static final int TILE_SIZEY = 64;
    private static final int TILE_SIZEXDEAD = 42;
    private static final int TILE_SIZEYDEAD = 77;
    private static final int TILE_SIZEXHIDE = 83;
    private static final int TILE_SIZEYHIDE = 75;
    private static final int TILE_SIZEXSTUN = 86;
    private static final int TILE_SIZEYSTUN = 105;
    private static String filePath;
    private static String type;
    
    public Sprite(String f, String typeOfSpriteSheet)
    {
        filePath = f;
        type = typeOfSpriteSheet;
    }

    public static BufferedImage loadSprite(String file) {

        BufferedImage sprite = null;

        try {
            sprite = ImageIO.read(new File(file));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return sprite;
    }

    public static BufferedImage getSprite(int xGrid, int yGrid, BufferedImage spriteSheet) 
    {
        
        if (spriteSheet == null) {
            spriteSheet = loadSprite(filePath);
        }
        if(type == "character")
        {
            return spriteSheet.getSubimage(xGrid * TILE_SIZEX, yGrid * TILE_SIZEY, TILE_SIZEX, TILE_SIZEY);
        }
        else if(type == "projectile")
        {
            return spriteSheet.getSubimage(xGrid * TILE_SIZEY, yGrid * TILE_SIZEY, TILE_SIZEY, TILE_SIZEY);
        }
        else if(type == "dead")
        {
            return spriteSheet.getSubimage(xGrid * TILE_SIZEXDEAD, yGrid * TILE_SIZEYDEAD, TILE_SIZEXDEAD, TILE_SIZEYDEAD);
        }
        else if(type == "hide")
        {
            return spriteSheet.getSubimage(xGrid * TILE_SIZEXHIDE, yGrid * TILE_SIZEYHIDE, TILE_SIZEXHIDE, TILE_SIZEYHIDE);
        }
        else if(type == "stun")
        {
            return spriteSheet.getSubimage(xGrid * TILE_SIZEXSTUN, yGrid * TILE_SIZEYSTUN, TILE_SIZEXSTUN, TILE_SIZEYSTUN); 
        }
        else
        {
            System.out.println("ERROR: Could not determine type of spritesheet");
            return null;
        }
    }

}
