/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adventurerpg;

import java.util.Random;

/**
 *
 * @author storm
 */
public class Armor 
{
    private int rank;
    private int durability;
    private Random rand = new Random();
    
    public Armor(int rankNumber)
    {
        rank = rankNumber;
        durability = setDurability();
    }
    
    public int setDurability()
    {
        if(rank == 0)
        {
            durability = 0;
        }
        else
        {
            durability = rand.nextInt(20) + 1;
        }
        return durability;
    }
    
    public int getRank()
    {
        return rank;
    }
    
    public int getDurability()
    {
        return durability;
    }
    
   public String getArmorName()
    {
        switch (rank)
        {
            case 0:
                return "cloak";
            case 1:
                return "mage";
            case 2:
                return "thief";
            case 3:
                return "ranger";
            case 4:
                return "druid";
            case 5:
                return "fighter";
            default:
                break;
        }
        return null;
    }
    
    public void useArmor()
    {
        if(durability > 0)
        {
            durability -= 1;
        }
        else
        {
            breakArmor();
        }
    }
    
    public void breakArmor()
    {
        rank = 0;
        durability = 0;
    }   
    
}
