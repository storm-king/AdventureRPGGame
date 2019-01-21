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
    Integer[] playerStats;
    public static double SCALELOWRES = .68;
    
    private Sprite fighter;
    private Sprite wizard;
    private Sprite thief;
    private Sprite skeletonSprite;
    private Sprite hideSprite;
    private Animation walkRight;
    private Animation standing;
    private Animation dead;
    private Animation pileOfBones;
    private Animation hidingAnimation;
    private int currentCharacter;
    public static boolean readyToFight = false;
    private int typeOfPlayer = 0;
    
    private String name;
    private String id;
    private boolean searched;
    
    private Animation animation;
    private Animation animation2;
 
    
    public Player(double x, double y, int characterSelect, Integer[] stats, AdventureRPG game, String id)
    {
        this.x = x;
        this.y = y;
        playerStats = stats;
        currentCharacter = characterSelect;
        searched = false;
        if(currentCharacter == 1 )
        {
            String f = "src/adventurerpg/res/Wizard.png";
            wizard = new Sprite(f, "character");
            BufferedImage[] wizardPlayerWalkingLoop = {wizard.getSprite(0,1,null), wizard.getSprite(1,1,null), wizard.getSprite(2,1,null)};
            BufferedImage[] wizardPlayerStandingLoop = {wizard.getSprite(1,1,null)};
            walkRight = new Animation(wizardPlayerWalkingLoop, 12);
            standing = new Animation(wizardPlayerStandingLoop, 12);
            animation = walkRight;
            typeOfPlayer = 1;
            name = "Wizard" + id;
        }
        else if(currentCharacter == 2)
        {
            String f = "src/adventurerpg/res/Fighter.png";
            fighter = new Sprite(f, "character");
            BufferedImage[] fighterPlayerWalkingLoop = {fighter.getSprite(0,1,null), fighter.getSprite(1,1,null), fighter.getSprite(2,1,null)};
            BufferedImage[] fighterPlayerStandingLoop = {fighter.getSprite(1,1,null)};
            walkRight = new Animation(fighterPlayerWalkingLoop, 12);
            standing = new Animation(fighterPlayerStandingLoop, 12);
            animation = walkRight;
            typeOfPlayer = 2;
            name = "Fighter" + id;
        }
        else if(currentCharacter == 3 )
        {
            String f = "src/adventurerpg/res/Thief.png";
            thief = new Sprite(f, "character");
            BufferedImage[] thiefPlayerWalkingLoop = {thief.getSprite(0,1,null), thief.getSprite(1,1,null), thief.getSprite(2,1,null)};
            BufferedImage[] thiefPlayerStandingLoop = {thief.getSprite(1,1,null)};
            walkRight = new Animation(thiefPlayerWalkingLoop, 12);
            standing = new Animation(thiefPlayerStandingLoop, 12);
            animation = walkRight;
            typeOfPlayer = 3;
            name = "Thief" + id;
        }
        
        

    }
    
    
    public void tick()
    {
        if(animation != null)
        {
            animation.update();
        }
        if(animation2 != null)
        {
            animation2.update();
        }
    }
    
    public void render(Graphics g)
    {
        if(animation != null)
        {
            
            if(x < (750 * SCALELOWRES))
            {
                animation.start();
                g.drawImage(animation.getSprite(), (int)x, (int)y, null);
                 x += .5; 
                 if(readyToFight != false)
                 {
                     readyToFight = false;
                 }
            }
            else if(animation != dead && animation != pileOfBones)
            {
                animation.stop();
                animation.reset();
                animation = standing;
                animation.start();
                
                if(readyToFight != true)
                {
                    readyToFight = true;
                }
                g.drawImage(animation.getSprite(), (int)x, (int)y, null);
            }
            else if(animation == dead)
            {
                
                animation.start();
                g.drawImage(animation.getSprite(), (int)x, (int)y, null);
                if(animation.getCurrentFrame() == 7)
                {
                    animation = pileOfBones;
                }
            }
            else
            {
                animation.stop();
                animation.reset();
                animation.start();
                g.drawImage(animation.getSprite(), (int)x, (int)y, null);
            }
        }
        if(animation2 != null )
        {
            animation2.start();
            g.drawImage(animation2.getSprite(), (int)x - 25, (int)y - 10, null);
        }
        
    }
    
    public void setSearched(boolean searched)
    {
        this.searched = searched;
    }
    
    public boolean getSearched()
    {
        return searched;
    }    
    
    public String getName()
    {
        return name;
    }
    
    public double getX()
    {
        return x;
    }
    
    public double getY()
    {
        return y;
    }
    
    public void setX(double newX)
    {
        if(animation != pileOfBones)
        {
            x = newX;
        }
    }
    
    public void setY(double newY)
    {
        y = newY;
    }
    
    public Integer[] getStats()
    {
        return playerStats;
    }
    
    public int getHp()
    {
        return playerStats[0];
    }
    
    public void setHp(int hp)
    {
        playerStats[0] = hp;
    }
    
    public int getPlayerType()
    {
        return typeOfPlayer;
    }
    
    public void changeAnimationDead(boolean isDead)
    {
        if(isDead)
        {
            String f = "src/adventurerpg/res/SkeletonSpriteSheet.png";
            skeletonSprite = new Sprite(f, "dead");
            BufferedImage[] playerDeadLoop = {this.skeletonSprite.getSprite(0,0,null), this.skeletonSprite.getSprite(1,0,null), this.skeletonSprite.getSprite(2,0,null), this.skeletonSprite.getSprite(3,0,null),
                                             this.skeletonSprite.getSprite(4,0,null), this.skeletonSprite.getSprite(5,0,null), this.skeletonSprite.getSprite(6,0,null), this.skeletonSprite.getSprite(7,0,null)};
            BufferedImage[] bonePile = {this.skeletonSprite.getSprite(7,0,null)};
            dead = new Animation(playerDeadLoop, 20);
            pileOfBones = new Animation(bonePile, 20);
            animation.stop();
            animation.reset();
            animation = dead;
        }
    }
    
    public void changeAnimationHiding(boolean isHiding)
    {
        if(isHiding)
        {
            String f = "src/adventurerpg/res/Hide.png";
            hideSprite = new Sprite(f, "hide");
            BufferedImage[] hidingCircle = {this.hideSprite.getSprite(0,0,null), this.hideSprite.getSprite(1,0,null), this.hideSprite.getSprite(2,0,null), this.hideSprite.getSprite(3,0,null),
                                             this.hideSprite.getSprite(4,0,null), this.hideSprite.getSprite(5,0,null)};
            hidingAnimation = new Animation(hidingCircle, 12);
            animation2 = hidingAnimation;
        }
        if(!isHiding)
        {
            animation2 = null;
        }
    }
    
    public void resetToWalkingAnimation()
    {
        if(animation != pileOfBones)
        {
            animation = walkRight;
        }
        else
        {
            animation = null;                                                                                                                                                                                                    
        }
    }
    
}
