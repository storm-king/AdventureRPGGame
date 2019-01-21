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
public class Weapon 
{
    private int rank;
    private int durability;
    Random rand = new Random();
    
    public Weapon(int rankNumber)
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
    
    public String getWeaponName()
    {
        switch (rank) 
        {
            case 0:
                return "fist";
            case 1:
                return "sword";
            case 2:
                return "staff";
            case 3:
                return "warhammer";
            default:
                break;
        }
        return null;
    }
    
    public void useWeapon()
    {
        if(durability > 0)
        {
            durability -= 1;
        }
        else
        {
            breakWeapon();
        }
    }
    
    public void breakWeapon()
    {
        if(rank != 0)
        {
            rank = 0;
            durability = 0;
        }
    }   
    
    public Item convertWeaponToItem(int rank)
    {
        switch (rank) 
        {
            case 1:
                return new Item("Mage Staff Weapon", 1);
            case 2:
                return new Item("Fighter Sword Weapon", 1);
            case 3:
                return new Item("Warhammer Weapon", 1);
            default:
                return null;
        }
    }
}
