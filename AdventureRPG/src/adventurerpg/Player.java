/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adventurerpg;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

/**
 *
 * @author storm
 */
public class Player 
{
    private double x;
    private double y;
    private BufferedImage[] playerSpriteSheets;
    private BufferedImage fighterPlayer;
    private BufferedImage wizardPlayer;
    private BufferedImage thiefPlayer;
    
    public Player(double x, double y, AdventureRPG game)
    {
        this.x = x;
        this.y = y;
        playerSpriteSheets = game.getSpriteSheet();
        
        SpriteSheet ss1 = new SpriteSheet(playerSpriteSheets[0]);
        SpriteSheet ss2 = new SpriteSheet(playerSpriteSheets[1]);
        SpriteSheet ss3 = new SpriteSheet(playerSpriteSheets[2]);
        
        fighterPlayer = ss1.grabImage(2, 3, 48, 64);
        wizardPlayer = ss2.grabImage(2, 3, 48, 64);
        thiefPlayer = ss3.grabImage(2, 3, 48, 64);
    }
    
    public void tick()
    {
        
    }
    
    public void render(Graphics g)
    {
        g.drawImage(fighterPlayer, (int)x, (int)y, null);
        g.drawImage(wizardPlayer, (int)x - 50, (int)y, null);
        g.drawImage(thiefPlayer, (int)x + 50, (int)y, null);
    }
    
}
