/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adventurerpg;

import java.awt.image.BufferedImage;
import java.util.Random;

/**
 *
 * @author storm
 */
public class NPCStats
{
    int npcType;
    int hitPoints;
    int strength;
    int wisdom;
    int agility;
    int weaponRank;
    int armorRank;
    Weapon weapon;
    Armor armor;
    Integer[] baseStats = new Integer[5];
    Random rand = new Random();
    boolean stunned = false;
    
    
    public NPCStats(int npcRank, int npcNumber)
    {
        npcType = npcRank;
        baseStats[4] = npcNumber;
        weaponRank = rand.nextInt(4);
        weapon = new Weapon(weaponRank);
        armorRank = rand.nextInt(5);
        armor = new Armor(armorRank);

    }

    public Integer[] baseStats()
    {
        int totalHP = 0;
        int totalStrength = 0;
        int totalWisdom = 0;
        int totalAgility = 0;
        
        for(int diceRoll1 = 0; diceRoll1 < npcType; diceRoll1++)
        {
            int healthDice = rand.nextInt(3) + 1;
            totalHP = totalHP + healthDice;
        }
        baseStats[0] = totalHP;
        for(int diceRoll2 = 0; diceRoll2 < npcType; diceRoll2++)
        {
            int strengthDice = rand.nextInt(3) + 1;
            totalStrength = totalStrength + strengthDice;
        }
        baseStats[1] = totalStrength;

        for(int diceRoll3 = 0; diceRoll3 < npcType; diceRoll3++)
        {
            int wisdomDice = rand.nextInt(3) + 1;
            totalWisdom = totalWisdom + wisdomDice;
        }
        baseStats[2] = totalWisdom;

        for(int diceRoll4 = 0; diceRoll4 < npcType; diceRoll4++)
        {
            int agilityDice = rand.nextInt(3) + 1;
            totalAgility = totalAgility + agilityDice;
        }
        baseStats[3] = totalAgility;

        return baseStats;
    }
    
    public boolean hasArmor()
    {
        if(armor.getRank() > 0 && armor.getDurability() > 0)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    
    public boolean hasWeapon()
    {
        if(weapon != null && weapon.getRank() > 0 && weapon.getDurability() > 0)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    
    public Weapon getWeapon()
    {
     return weapon;
    }
    
    public void setWeapon(Weapon newWeapon)
    {
        weapon = newWeapon;
    }
    
    public Integer[] attackPlayer(Integer[] playerStats, boolean isHiding) 
    {
        boolean hit = false;

        Random random = new Random();
        double dice = random.nextInt(10) + 1;
        int damageInflicted = 0;
        
        if(isHiding)
        {
            baseStats[3] = (int)(baseStats[3] * .25);
        }
        
        double chanceOfHit = baseStats[3] - dice;
        
        if(chanceOfHit > 0)
        {
            if(weapon != null && weapon.getDurability() > 0)
            {
                damageInflicted = baseStats[1] / 3 + weapon.getRank(); 
                weapon.useWeapon();
            }
            else
            {
                damageInflicted = baseStats[1] / 3;
            }
                AdventureRPG.totalDamage = damageInflicted;
                playerStats[0] -= damageInflicted;
                hit = true;
            
        }
        return playerStats;
    }
    
    public void stunNPC(boolean stun)
    {
        if(stun)
        {
            stunned = true;
        }
        else
        {
            stunned = false;
        }
    }
    
    public boolean isStunned()
    {
        return stunned;
    }
    
    public int armorProtection(int damageTaken)
    {
        int actualDamage = damageTaken - armor.getRank();
        if(actualDamage > 0)
        {
            return actualDamage;
        }
        else
        {
            return 0;
        }
    }
 
}
