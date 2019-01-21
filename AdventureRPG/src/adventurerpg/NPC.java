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
public class NPC 
{
    private static int npcType;
    private int type;
    private double x;
    private double y;
    private Integer[] npcStats;
    private Sprite ghost;
    private Sprite wolf;
    private Sprite mage;
    private Sprite scarface;
    private Sprite werewolf;
    private Sprite demon;
    private Sprite skeletonSprite;
    private Sprite playerStunned;
    private Animation walkLeft;
    private Animation standing;
    private Animation dead;
    private Animation pileOfBones;
    private Animation stunned;
    private Animation animation;
    private Animation animation2;
    private String name;
    private String id;
    public boolean looted;
    public boolean targeted;
    public static boolean finalBlow = false;
    private int maxHp;

    
    public NPC(double x, double y, int rank, Integer[] stats, AdventureRPG game, String id)
    {
        npcType = rank;
        this.x = x;
        this.y = y;
        npcStats = stats;
        maxHp = stats[0];
        looted = false;
        targeted = false;
        if(npcType == 1 )
        {
            String f = "src/adventurerpg/res/Ghost.png";
            ghost = new Sprite(f, "character");
            BufferedImage[] ghostWalkingLoop = {ghost.getSprite(0,3,null), ghost.getSprite(1,3,null), ghost.getSprite(2,3,null)};
            BufferedImage[] ghostStandingLoop = {ghost.getSprite(1,3,null)};
            walkLeft = new Animation(ghostWalkingLoop, 12);
            standing = new Animation(ghostStandingLoop, 12);
            animation = walkLeft;
            type = 1;
            name = "Ghost" + id;
            this.id = id;
        }
        else if(npcType == 2)
        {
            String f = "src/adventurerpg/res/Wolf.png";
            wolf = new Sprite(f, "character");
            BufferedImage[] wolfWalkingLoop = {wolf.getSprite(0,3,null), wolf.getSprite(1,3,null), wolf.getSprite(2,3,null)};
            BufferedImage[] wolfStandingLoop = {wolf.getSprite(1,3,null)};
            walkLeft = new Animation(wolfWalkingLoop, 12);
            standing = new Animation(wolfStandingLoop, 12);
            animation = walkLeft;
            type = 2;
            name = "Wolf" + id;
            this.id = id;
        }
        else if(npcType == 3 )
        {
            String f = "src/adventurerpg/res/Mage.png";
            mage = new Sprite(f, "character");
            BufferedImage[] mageWalkingLoop = {mage.getSprite(0,3,null), mage.getSprite(1,3,null), mage.getSprite(2,3,null)};
            BufferedImage[] mageStandingLoop = {mage.getSprite(1,3,null)};
            walkLeft = new Animation(mageWalkingLoop, 12);
            standing = new Animation(mageStandingLoop, 12);
            animation = walkLeft;
            type = 3;
            name = "Mage" + id;
            this.id = id;
        }
        if(npcType == 4 )
        {
            String f = "src/adventurerpg/res/Scarface.png";
            scarface = new Sprite(f, "character");
            BufferedImage[] scarfaceWalkingLoop = {scarface.getSprite(0,3,null), scarface.getSprite(1,3,null), scarface.getSprite(2,3,null)};
            BufferedImage[] scarfaceStandingLoop = {scarface.getSprite(1,3,null)};
            walkLeft = new Animation(scarfaceWalkingLoop, 12);
            standing = new Animation(scarfaceStandingLoop, 12);
            animation = walkLeft;
            type = 4;
            name = "Scarface" + id;
            this.id = id;
        }
        else if(npcType == 5)
        {
            String f = "src/adventurerpg/res/Werewolf.png";
            werewolf = new Sprite(f, "character");
            BufferedImage[] werewolfWalkingLoop = {werewolf.getSprite(0,3,null), werewolf.getSprite(1,3,null), werewolf.getSprite(2,3,null)};
            BufferedImage[] werewolfStandingLoop = {werewolf.getSprite(1,3,null)};
            walkLeft = new Animation(werewolfWalkingLoop, 12);
            standing = new Animation(werewolfStandingLoop, 12);
            animation = walkLeft;
            type = 5;
            name = "Werewolf" + id;
            this.id = id;
        }
        else if(npcType == 6 )
        {
            String f = "src/adventurerpg/res/Demon.png";
            demon = new Sprite(f, "character");
            BufferedImage[] demonWalkingLoop = {demon.getSprite(0,3,null), demon.getSprite(1,3,null), demon.getSprite(2,3,null)};
            BufferedImage[] demonStandingLoop = {demon.getSprite(1,3,null)};
            walkLeft = new Animation(demonWalkingLoop, 12);
            standing = new Animation(demonStandingLoop, 12);
            animation = walkLeft;
            type = 6;
            name = "Demon" + id;
            this.id = id;
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
            
            if(x > 810)
            {
                animation.start();
                g.drawImage(animation.getSprite(), (int)x, (int)y, null);
                 x -= .5;   
            }
            else if(animation != dead && animation != pileOfBones)
            {
                animation.stop();
                animation.reset();
                animation = standing;
                animation.start();
                g.drawImage(animation.getSprite(), (int)x, (int)y, null);
            }
            else if(animation == dead)
            {
                    finalBlow = true;
                    animation.start();
                    g.drawImage(animation.getSprite(), (int)x, (int)y, null);
                    if(animation.getCurrentFrame() == 7)
                    {
                        animation = pileOfBones;
                    }
                     else if(!finalBlow)
                    {
                    animation = standing;
                    animation.start();
                    g.drawImage(animation.getSprite(), (int)x, (int)y, null);
                    animation.stop();
                    animation.reset();
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
        if(animation2 != null)
        {
            animation2.start();
            g.drawImage(animation2.getSprite(), (int)x - 10, (int)y - 20, null);
        }
    }
    
    public void setLooted(boolean l)
    {
        looted = l;
    }
            
    public boolean getLooted()
    {
        return looted;
    }
    
    public void setTargeted(boolean t)
    {
        if (t == true)
        {
            for (NPC n : AdventureRPG.npcObjects)
            {
                n.setTargeted(false);
            }
        }
        
        targeted = t;     
    }
            
    public boolean getTargeted()
    {
        return targeted;
    }    
    
    public int getMaxHp()
    {
        return maxHp;
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
    
    public Integer[] getStats()
    {
        return npcStats;
    }
    
    public int getHp()
    {
        return npcStats[0];
    }
    
    public int getNPCType()
    {
        return type;
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
            dead = new Animation(playerDeadLoop, 25);
            pileOfBones = new Animation(bonePile, 25);
            animation = dead;
        }
    }
    
    public void changeAnimationStunned(boolean isStunned)
    {
        if(isStunned)
        {
            String f = "src/adventurerpg/res/StunnedAnimation.png";
            playerStunned = new Sprite(f, "stun");
            BufferedImage[] lightningLoop = {this.playerStunned.getSprite(0,0,null), this.playerStunned.getSprite(1,0,null), this.playerStunned.getSprite(2,0,null),
                                            this.playerStunned.getSprite(0,1,null), this.playerStunned.getSprite(0,1,null), this.playerStunned.getSprite(0,1,null),
                                            this.playerStunned.getSprite(0,2,null), this.playerStunned.getSprite(0,2,null), this.playerStunned.getSprite(0,2,null),};
            stunned = new Animation(lightningLoop, 1);
            animation2 = stunned;
        }
        if(!isStunned)
        {
            animation2 = null;
        }
    }
}
