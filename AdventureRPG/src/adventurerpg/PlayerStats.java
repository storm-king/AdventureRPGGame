
package adventurerpg;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.Random;
import javax.swing.JComponent;

/**
 *
 * @author matthewthayer
 */
public class PlayerStats extends JComponent
{
 Integer hitPoints = 20;
 int strength;
 int wisdom;
 int agility;
 int characterClass = 0;
 Weapon weapon;
 Armor armor;
 Integer[] baseStats = new Integer[5];
 Random rand = new Random();
 private static double SCALELOWRES = .68;
 Integer[] wizardBaseStats = new Integer[5];
 Integer[] thiefBaseStats = new Integer[5];
 Integer[] fighterBaseStats = new Integer[5];
 
 public PlayerStats(int playerNumber)
 {
     weapon = new Weapon(0);
     armor = new Armor(0);
     baseStats[0] = hitPoints;
     baseStats[4] = playerNumber;
     baseStats = baseStats();    
 }
 
 public Integer[] baseStats()
 {
     int totalStrength = 0;
     int totalWisdom = 0;
     int totalAgility = 0;
     
     for(int diceRoll = 0; diceRoll < 3; diceRoll++)
     {
         int strengthDice = rand.nextInt(6) + 1;
         totalStrength = totalStrength + strengthDice;
     }
     baseStats[1] = (Integer)totalStrength;
     
     for(int diceRoll2 = 0; diceRoll2 < 3; diceRoll2++)
     {
         int wisdomDice = rand.nextInt(6) + 1;
         totalWisdom = totalWisdom + wisdomDice;
     }
     baseStats[2] = (Integer)totalWisdom;
     
     for(int diceRoll3 = 0; diceRoll3 < 3; diceRoll3++)
     {
         int agilityDice = rand.nextInt(6) + 1;
         totalAgility = totalAgility + agilityDice;
     }
     baseStats[3] = (Integer)totalAgility;
     
     return baseStats;
 }
 
  public Integer[] wizardStats()
 {
     
     wizardBaseStats[0] = baseStats[0];
     wizardBaseStats[1] = baseStats[1] - 5;
     wizardBaseStats[2] = baseStats[2] + 5;
     wizardBaseStats[3] = baseStats[3] - 3;
     wizardBaseStats[4] = baseStats[4];
     characterClass = 1;
     
     return wizardBaseStats;
 }
 
 public Integer[] fighterStats()
 {
     
     fighterBaseStats[0] = baseStats[0];
     fighterBaseStats[1] = baseStats[1] + 3;
     fighterBaseStats[2] = baseStats[2] - 3;
     fighterBaseStats[3] = baseStats[3];
     fighterBaseStats[4] = baseStats[4];
     characterClass = 2;
     
     return fighterBaseStats;
 }
 
 public Integer[] thiefStats()
 {
     
     thiefBaseStats[0] = baseStats[0];
     thiefBaseStats[1] = baseStats[1] - 2;
     thiefBaseStats[2] = baseStats[2] + 2;
     thiefBaseStats[3] = baseStats[3] + 3;
     thiefBaseStats[4] = baseStats[4];
     characterClass = 3;
     
     return thiefBaseStats;
 }
 
 public void setWeapon(Weapon newWeapon)
 {
     weapon = newWeapon;
 }
 
 public void setArmor(Armor newArmor)
 {
     armor = newArmor;
 }
 
 public Weapon getWeapon()
 {
     return weapon;
 }
 
 public Armor getArmor()
 {
     return armor;
 }
 
 @Override
    public void paintComponent(Graphics g) 
    {
        super.paintComponent(g);
        g.setColor(Color.BLACK);
        Font fnt0 = new Font("Arial", Font.BOLD, 34);
        g.setFont(fnt0);
        String healthDisplay = Integer.toString(baseStats[0]);
        String strengthDisplay = Integer.toString(baseStats[1]);
        String wisdomDisplay = Integer.toString(baseStats[2]);
        String agilityDisplay = Integer.toString(baseStats[3]);
        g.drawString(healthDisplay, (int)(322 * SCALELOWRES), (int)(370 * SCALELOWRES));
        g.drawString(strengthDisplay, (int)(322 * SCALELOWRES), (int)(475 * SCALELOWRES));
        g.drawString(wisdomDisplay, (int)(322 * SCALELOWRES), (int)(590 * SCALELOWRES));
        g.drawString(agilityDisplay, (int)(322 * SCALELOWRES), (int)(695 * SCALELOWRES));
        
        if(AdventureRPG.characterSelected == 1)
        {
            g.setColor(Color.RED);
            g.drawString(" - 5", (int)(392 * SCALELOWRES), (int)(475 * SCALELOWRES));
            g.drawString(" - 3", (int)(392 * SCALELOWRES), (int)(695 * SCALELOWRES));
            g.setColor(Color.GREEN);
            g.drawString(" + 5", (int)(392 * SCALELOWRES), (int)(590 * SCALELOWRES));
            g.setColor(Color.BLACK);
            Font fnt1 = new Font("Arial", Font.PLAIN, 8);
            g.setFont(fnt1);         
        }
        if(AdventureRPG.characterSelected == 2)
        {
            g.setColor(Color.RED);
            g.drawString(" - 3", (int)(392 * SCALELOWRES), (int)(590 * SCALELOWRES));
            g.setColor(Color.GREEN);
            g.drawString(" + 3", (int)(392 * SCALELOWRES), (int)(475 * SCALELOWRES));     
        }
        if(AdventureRPG.characterSelected == 3)
        {
            g.setColor(Color.RED);
            g.drawString(" - 2", (int)(392 * SCALELOWRES), (int)(475 * SCALELOWRES));
            
            g.setColor(Color.GREEN);
            g.drawString(" + 3", (int)(392 * SCALELOWRES), (int)(695 * SCALELOWRES));
            g.drawString(" + 2", (int)(392 * SCALELOWRES), (int)(590 * SCALELOWRES));
        }
    }
    
    public void updateStats(int rank)
    {
        if (rank == 1)
        {
            baseStats = wizardBaseStats;
        }
        else if(rank == 2)
        {
            baseStats = fighterBaseStats;
        }
        else if(rank == 3)
        {
            baseStats = thiefBaseStats;
        }
    }
    
    public Integer[] attackNormalNPC(Integer[] npcStats) 
    {
        boolean hit = false;

        Random random = new Random();
        int dice = random.nextInt(10) + 1;
        int chanceOfHit = baseStats[3] - dice;
        int damageInflicted = 0;
        
        if(chanceOfHit > 0)
        {
           damageInflicted = baseStats[1] / 3; 
           npcStats[0] -= damageInflicted;
           AdventureRPG.totalDamage = damageInflicted;
           hit = true;
        }
        return npcStats;
    }
    

    public Integer[] attackSpecialNPC(Integer[] npcStats, NPCStats npcToAttack)
    {
        int damageInflicted = 0;
        if(characterClass == 1)
        {
            Random random = new Random();
            damageInflicted = random.nextInt(10) + 1;
            npcStats[0] -= damageInflicted;
            AdventureRPG.totalDamage = damageInflicted;
            return npcStats;
        }
        else if(characterClass == 2)
        {
            if (!npcToAttack.isStunned()) 
            {
                npcToAttack.stunNPC(true);
            }
            boolean hit = false;

            Random random = new Random();
            int dice = random.nextInt(10) + 1;
            int chanceOfHit = baseStats[3] - dice;

            if (chanceOfHit > 0) 
            {
                damageInflicted = baseStats[1] / 3;
                npcStats[0] -= damageInflicted;
                AdventureRPG.totalDamage = damageInflicted;
                hit = true;
            }

            return npcStats;
        }
        else if(characterClass == 3)
        {
            if(npcToAttack.hasWeapon() && npcToAttack.getWeapon() != null)
            {
                Random random = new Random();
                int dice = random.nextInt(20) + 1;
                int chanceOfSteal = (baseStats[3] - dice);
                if(chanceOfSteal > 0)
                {
                    Weapon weaponStolen = npcToAttack.getWeapon();
                    Weapon nulledOutWeapon = null;
                    npcToAttack.setWeapon(nulledOutWeapon);
                    Item weaponToAddToBag = weaponStolen.convertWeaponToItem(weaponStolen.getRank());
                    AdventureRPG.addToBag(weaponToAddToBag.getName(), 1);
                    return npcStats;
                }
            }
            
        }
        return npcStats;
    }

}
