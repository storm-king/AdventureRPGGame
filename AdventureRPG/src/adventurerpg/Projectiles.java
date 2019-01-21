/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adventurerpg;

import static adventurerpg.Player.SCALELOWRES;
import static adventurerpg.Player.readyToFight;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

/**
 *
 * @author storm
 */
public class Projectiles 
{
    int rank;
    double startingX;
    double startingY;
    double updatedX;
    double updatedY;
    double stopX;
    double stopY;
    String travelDirection;
    private Sprite projectile;
    static boolean projectileHit = false;
    static boolean projectileFired = false;
    
    private Animation shoot;
    
    
    public Projectiles(int rankOfCharacter, double characterAttackingX, double characterAttackingY, double destinationX, double destinationY, String directionOfTravel)
    {
        rank = rankOfCharacter;
        startingX = characterAttackingX;
        startingY = characterAttackingY;
        stopX = destinationX;
        stopY = destinationY;
        updatedX = characterAttackingX;
        updatedY = characterAttackingY;
        travelDirection = directionOfTravel;
        String f = "src/adventurerpg/res/CircularProjectiles.png";
        projectile = new Sprite(f, "projectile");
        
        if(travelDirection.equals("left"))
        {
            if(rank == 1)
            { 
                BufferedImage[] projectileLoop = {projectile.getSprite(0,0,null), projectile.getSprite(1,0,null), projectile.getSprite(2,0,null), projectile.getSprite(3,0,null),
                                                  projectile.getSprite(4,0,null), projectile.getSprite(5,0,null), projectile.getSprite(6,0,null), projectile.getSprite(7,0,null),
                                                  projectile.getSprite(0,1,null), projectile.getSprite(1,1,null), projectile.getSprite(2,1,null), projectile.getSprite(3,1,null),
                                                  projectile.getSprite(4,1,null), projectile.getSprite(5,1,null), projectile.getSprite(6,1,null), projectile.getSprite(7,1,null)};
                shoot = new Animation(projectileLoop, 1); 
            }
            else if(rank == 2 || rank == 5)
            {
                BufferedImage[] projectileLoop = {projectile.getSprite(8,12,null), projectile.getSprite(9,12,null), projectile.getSprite(10,12,null), projectile.getSprite(11,12,null),
                                                  projectile.getSprite(12,12,null), projectile.getSprite(13,12,null), projectile.getSprite(14,12,null), projectile.getSprite(15,12,null),
                                                  projectile.getSprite(8,13,null), projectile.getSprite(9,13,null), projectile.getSprite(10,13,null), projectile.getSprite(11,13,null),
                                                  projectile.getSprite(12,13,null), projectile.getSprite(13,13,null), projectile.getSprite(14,13,null), projectile.getSprite(15,13,null)};
                shoot = new Animation(projectileLoop, 1); 
            }
            else if(rank == 3)
            { 
                BufferedImage[] projectileLoop = {projectile.getSprite(0,4,null), projectile.getSprite(1,4,null), projectile.getSprite(2,4,null), projectile.getSprite(3,4,null),
                                                  projectile.getSprite(4,4,null), projectile.getSprite(5,4,null), projectile.getSprite(6,4,null), projectile.getSprite(7,4,null),
                                                  projectile.getSprite(0,5,null), projectile.getSprite(1,5,null), projectile.getSprite(2,5,null), projectile.getSprite(3,5,null),
                                                  projectile.getSprite(4,5,null), projectile.getSprite(5,5,null), projectile.getSprite(6,5,null), projectile.getSprite(7,5,null)};
                shoot = new Animation(projectileLoop, 1); 
            }
            else if(rank == 4)
            {
                BufferedImage[] projectileLoop = {projectile.getSprite(8,8,null), projectile.getSprite(9,8,null), projectile.getSprite(10,8,null), projectile.getSprite(11,8,null),
                                                  projectile.getSprite(12,8,null), projectile.getSprite(13,8,null), projectile.getSprite(14,8,null), projectile.getSprite(15,8,null),
                                                  projectile.getSprite(8,9,null), projectile.getSprite(9,9,null), projectile.getSprite(10,9,null), projectile.getSprite(11,9,null),
                                                  projectile.getSprite(12,9,null), projectile.getSprite(13,9,null), projectile.getSprite(14,9,null), projectile.getSprite(15,9,null)};
                shoot = new Animation(projectileLoop, 1); 
            }
            else if(rank == 6)
            { 
                BufferedImage[] projectileLoop = {projectile.getSprite(0,12,null), projectile.getSprite(1,12,null), projectile.getSprite(2,12,null), projectile.getSprite(3,12,null),
                                                  projectile.getSprite(4,12,null), projectile.getSprite(5,12,null), projectile.getSprite(6,12,null), projectile.getSprite(7,12,null),
                                                  projectile.getSprite(0,13,null), projectile.getSprite(1,13,null), projectile.getSprite(2,13,null), projectile.getSprite(3,13,null),
                                                  projectile.getSprite(4,13,null), projectile.getSprite(5,13,null), projectile.getSprite(6,13,null), projectile.getSprite(7,13,null)};
                shoot = new Animation(projectileLoop, 1); 
            }
        }
        else if(travelDirection.equals("right"))
        {
            if(rank == 1)
            { 
                BufferedImage[] projectileLoop = {projectile.getSprite(0,8,null), projectile.getSprite(1,8,null), projectile.getSprite(2,8,null), projectile.getSprite(3,8,null),
                                                  projectile.getSprite(4,8,null), projectile.getSprite(5,8,null), projectile.getSprite(6,8,null), projectile.getSprite(7,8,null),
                                                  projectile.getSprite(0,9,null), projectile.getSprite(1,9,null), projectile.getSprite(2,9,null), projectile.getSprite(3,9,null),
                                                  projectile.getSprite(4,9,null), projectile.getSprite(5,9,null), projectile.getSprite(6,9,null), projectile.getSprite(7,9,null)};
                shoot = new Animation(projectileLoop, 1); 
            }
            else if(rank == 2)
            {
                BufferedImage[] projectileLoop = {projectile.getSprite(8,4,null), projectile.getSprite(9,4,null), projectile.getSprite(10,4,null), projectile.getSprite(11,4,null),
                                                  projectile.getSprite(12,4,null), projectile.getSprite(13,4,null), projectile.getSprite(14,4,null), projectile.getSprite(15,4,null),
                                                  projectile.getSprite(8,5,null), projectile.getSprite(9,5,null), projectile.getSprite(10,5,null), projectile.getSprite(11,5,null),
                                                  projectile.getSprite(12,5,null), projectile.getSprite(13,5,null), projectile.getSprite(14,5,null), projectile.getSprite(15,5,null)};
                shoot = new Animation(projectileLoop, 1); 
            }
            else if(rank == 3)
            { 
                BufferedImage[] projectileLoop = {projectile.getSprite(8,0,null), projectile.getSprite(9,0,null), projectile.getSprite(10,0,null), projectile.getSprite(11,0,null),
                                                  projectile.getSprite(12,0,null), projectile.getSprite(13,0,null), projectile.getSprite(14,0,null), projectile.getSprite(15,0,null),
                                                  projectile.getSprite(8,1,null), projectile.getSprite(9,1,null), projectile.getSprite(10,1,null), projectile.getSprite(11,1,null),
                                                  projectile.getSprite(12,1,null), projectile.getSprite(13,1,null), projectile.getSprite(14,1,null), projectile.getSprite(15,1,null)};
                shoot = new Animation(projectileLoop, 1); 
            }
            else if(rank == 4)
            {
                BufferedImage[] projectileLoop = {projectile.getSprite(0,12,null), projectile.getSprite(1,12,null), projectile.getSprite(2,12,null), projectile.getSprite(3,12,null),
                                                  projectile.getSprite(4,12,null), projectile.getSprite(5,12,null), projectile.getSprite(6,12,null), projectile.getSprite(7,12,null),
                                                  projectile.getSprite(0,13,null), projectile.getSprite(1,13,null), projectile.getSprite(2,13,null), projectile.getSprite(3,13,null),
                                                  projectile.getSprite(4,13,null), projectile.getSprite(5,13,null), projectile.getSprite(6,13,null), projectile.getSprite(7,13,null)};
                shoot = new Animation(projectileLoop, 1);
            }
        }
        else
        {
            shoot = null;
        }
    }
    
     public void tick()
    {
        if(shoot != null)
        {
            shoot.update();
        }
    }
    
    public void render(Graphics g)
    {
        
        if(shoot != null)
        {
            projectileHit = false;
            shoot.start();
            projectileFired = true;
            

            g.drawImage(shoot.getSprite(), (int)updatedX, (int)updatedY, null );
            if(updatedX < stopX && travelDirection == "right")
            {
                 updatedX += 4;
                 if(projectileHit != false)
                 {
                     projectileHit = false;
                 }
                 
                 if(updatedY < stopY)
                 {
                     updatedY += 3.5; 
                 }
                 else if(updatedY > stopY)
                 {
                     updatedY -= 3.5;
                 }
                 else
                 {
                     projectileFired = true;
                 }
            }
            else if(updatedX > stopX && travelDirection == "left")
            {
                updatedX -= 4;
                projectileFired = true;
                if(projectileHit != false)
                 {
                     projectileHit = false;
                 }
                
                 if(updatedY < stopY)
                 {
                     updatedY += 3.5; 
                 }
                 else if(updatedY > stopY)
                 {
                     updatedY -= 3.5;
                 }
            }
            else
            {
                shoot.stop();
                shoot.reset();
                
                if(projectileHit != true)
                {
                    projectileHit = true;
                }
                projectileFired = false;
                g.drawImage(shoot.getSprite(),  (int)updatedX, (int)updatedY, null);
            }
        }
    }
    
}
