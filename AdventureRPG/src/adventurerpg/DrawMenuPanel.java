/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adventurerpg;

import java.awt.Graphics;
import java.awt.Image;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

/**
 *
 * @author storm
 */
public class DrawMenuPanel extends JPanel
{
    private Image menuImage;
    

    public DrawMenuPanel(int menuOption) {
        int currentMenu = menuOption;
        BufferedImageLoader loader = new BufferedImageLoader(); 
        if(currentMenu == 0)
        {
            menuImage = loader.loadImage("/BackgroundPlay.gif");
        }
        else if(currentMenu == 1)
        {
            menuImage = loader.loadImage("/PlayerSelectPlay.gif");
        }
        else if(currentMenu == 2)
        {
            menuImage = loader.loadImage("/CharacterSelect.png");
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(menuImage, 0, 0, this);
    }
}

