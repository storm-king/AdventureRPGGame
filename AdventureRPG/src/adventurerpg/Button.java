/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adventurerpg;

import java.awt.Font;
import java.awt.Image;

/**
 *
 * @author Chuck
 */
public class Button 
{
    private final Image buttonImage;
    private final Image buttonSelectionImage;
    private final Image buttonInterfaceImage;
    private final int buttonX;
    private final int buttonY;
    private final int buttonW;
    private final int buttonH;
    private final String buttonString;
    private final Font buttonFont;
    private String buttonStatus;
    
    public Button()
    {
        buttonImage = null;
        buttonSelectionImage = null;
        buttonInterfaceImage = null;
        buttonX = 0;
        buttonY = 0;
        buttonW = 0;
        buttonH = 0;
        buttonString = "";
        buttonStatus = "";
        buttonFont = null;
    }
    
    public Button(String image, String selectionImage, String interfaceImage, boolean hoverCondition, int x, int y, int w, int h, String string, Font font)
    {
        BufferedImageLoader loader = new BufferedImageLoader();
        buttonStatus = "";
        
        if (!image.equals(""))
        {
            if (AdventureRPG.button.equals(string) && hoverCondition)
            {
                image += "_hover";
                buttonStatus = "hover";
            }
            else if (string.equals("Continue") && !hoverCondition
                    || string.equals("Sleep") && AdventureRPG.button.equals(string)
                    || string.equals("Open") && AdventureRPG.button.equals(string) 
                    || string.equals("Loot") && AdventureRPG.button.equals(string)
                    || string.equals("Victory") && AdventureRPG.button.equals(string))
            {
                image += "_unavailable";
                buttonStatus = "unavailable";
            }
            
            if (string.equals("Fight") || string.equals("Hide") || string.equals("Bag") || string.equals("Search") || string.equals("Run"))
            {
                if (AdventureRPG.playerWin || !AdventureRPG.playerTurn
                        || string.equals("Search") && AdventureRPG.playerToAttack.getSearched()
                        || string.equals("Run") && AdventureRPG.roomCount == 1)
                {
                    image = "/button_option_unavailable";
                }
            }


            buttonImage = loader.loadImage(image + ".png");
        }
        else
        {
            buttonImage = null;
        }
        
        
        if (!selectionImage.equals(""))
        {
            buttonSelectionImage = loader.loadImage(selectionImage);
        }
        else
        {
            buttonSelectionImage = null;
        }
        if (!interfaceImage.equals(""))
        {
            buttonInterfaceImage = loader.loadImage(interfaceImage);  
        }
        else
        {
            buttonInterfaceImage = null;
        }
        
        buttonX = x;
        buttonY = y;
        buttonW = w;
        buttonH = h;
        buttonString = string; 
        buttonFont = font;
    }
    
    
    public String getStatus()
    {
        return buttonStatus;
    }
    
    
    public Image getImage()
    {
        return buttonImage;
    }
    public Image getSelectionImage()
    {
        return buttonSelectionImage;
    }
    public Image getInterfaceImage()
    {
        return buttonInterfaceImage;
    }
    
    public int getX()
    {
        return buttonX;
    }
    public int getY()
    {
        return buttonY;
    }
    public int getW()
    {
        return buttonW;
    } 
    public int getH()
    {
        return buttonH;
    }    
    
    public String getString()
    {
        return buttonString;
    } 
    
    public Font getFont()
    {
        return buttonFont;
    }      
}
