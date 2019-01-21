/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adventurerpg;

import java.awt.Image;

/**
 *
 * @author Chuck
 */


public class Item 
{
    public final static String COINS = "Coins";
    public final static String POTION_BASIC = "Basic Potion";
    public final static String POTION_ADVANCED = "Advanced Potion";
    public final static String POTION_SUPREME = "Supreme Potion";
    public final static String ARMOR_MAGE = "Mage Robes Armor";
    public final static String ARMOR_FIGHTER = "Fighter Plate Armor";
    public final static String ARMOR_THIEF = "Thief Garment Armor";
    public final static String ARMOR_RANGER = "Ranger Padding Armor";
    public final static String ARMOR_DRUID = "Druid Garbs Armor";
    public final static String WEAPON_MAGE = "Mage Staff Weapon";
    public final static String WEAPON_FIGHTER = "Fighter Sword Weapon";
    public final static String WEAPON_THIEF = "Thief Dagger Weapon";
    public final static String WEAPON_RANGER = "Ranger Bow Weapon";
    public final static String WEAPON_WARHAMMER = "Warhammer Weapon";
    public final static String TREASURE = "Treasure Chest";
    public final static String BONES = "Bones";
    
    private final String itemName;
    private Image itemImage;
    private int itemTotal;
    
    public Item()
    {
        itemName = "";
        itemImage = null;
        itemTotal = 0;
    }
    
    public Item(String name, int total)
    {
        BufferedImageLoader loader = new BufferedImageLoader();
        String image;
        
        switch (name)
        {
            case COINS:
            {
                image = "/item_coins_low.png";
                break;
            }
            case POTION_BASIC: 
            {
                image = "/item_potion_basic.png";
                break;
            }
            case POTION_ADVANCED: 
            {
                image = "/item_potion_advanced.png";
                break;
            }
            case POTION_SUPREME: 
            {
                image = "/item_potion_supreme.png";
                break;
            }            
            case ARMOR_MAGE: 
            {
                image = "/item_armor_mage.png";
                break;
            }
            case ARMOR_FIGHTER: 
            {
                image = "/item_armor_fighter.png";
                break;
            }          
            case ARMOR_THIEF: 
            {
                image = "/item_armor_thief.png";
                break;
            }  
            case ARMOR_RANGER: 
            {
                image = "/item_armor_ranger.png";
                break;
            } 
            case ARMOR_DRUID: 
            {
                image = "/item_armor_druid.png";
                break;
            }            
            case WEAPON_MAGE: 
            {
                image = "/item_weapon_mage.png";
                break;
            }
            case WEAPON_FIGHTER: 
            {
                image = "/item_weapon_fighter.png";
                break;
            }          
            case WEAPON_THIEF: 
            {
                image = "/item_weapon_thief.png";
                break;
            }  
            case WEAPON_RANGER: 
            {
                image = "/item_weapon_ranger.png";
                break;
            } 
            case WEAPON_WARHAMMER: 
            {
                image = "/item_weapon_warhammer.png";
                break;
            }   
            case TREASURE: 
            {
                image = "/item_treasure.png";
                break;
            } 
            case BONES: 
            {
                image = "/item_bones.png";
                break;
            }             
            default:
            {
                image = "";
                break;
            }
        }
        
        itemName = name;
        itemImage = loader.loadImage(image);
        itemTotal = total;
    }
    
    public void setTotal(int total)
    {
        itemTotal = total;
    }
    
    public String getName()
    {
        return itemName;
    }
    
    public Image getImage()
    {
        if (itemName.equals(COINS))
        {
            BufferedImageLoader loader = new BufferedImageLoader();
            
            if (itemTotal < 50)
            {
                itemImage = loader.loadImage("/item_coins_low.png");
            }
            else if (itemTotal < 1000)
            {
                itemImage = loader.loadImage("/item_coins_mid.png");
            }
            else
            {
                itemImage = loader.loadImage("/item_coins_high.png");
            }
        }
        
        return itemImage;
    }
    
    public int getTotal()
    {
        return itemTotal;
    }
    
    public boolean isWeapon()
    {
        if(itemName.contains("Weapon"))
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    
    public boolean isArmor()
    {
        if(itemName.contains("Armor"))
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    public boolean isPotion()
    {
        if(itemName.contains("Potion"))
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    
    public int usePotion()
    {
        int hpToRestore = 0;
        if(itemName.equals(POTION_BASIC))
        {
            hpToRestore = 5;
        }
        else if(itemName.equals(POTION_ADVANCED))
        {
            hpToRestore = 10;
        }
        else if(itemName.equals(POTION_SUPREME))
        {
            hpToRestore = 20;
        }
        return hpToRestore;
    }
    
    public Weapon convertItemToWeapon()
    {
        Weapon itemAsWeapon = null;
        if(itemName.equals(WEAPON_FIGHTER))
        {
            itemAsWeapon = new Weapon(1);
        }
        else if(itemName.equals(WEAPON_MAGE))
        {
            itemAsWeapon = new Weapon(2);
        }
        else if(itemName.equals(WEAPON_WARHAMMER))
        {
            itemAsWeapon = new Weapon(3);
        }
        return itemAsWeapon;
    }
    
    
    public Armor convertItemToArmor()
    {
        Armor itemAsArmor = null;
            if(itemName.equals(ARMOR_MAGE))
        {
            itemAsArmor = new Armor(1);
        }
        else if(itemName.equals(ARMOR_THIEF))
        {
            itemAsArmor = new Armor(2);
        }
        else if(itemName.equals(ARMOR_RANGER))
        {
            itemAsArmor = new Armor(3);
        }
        else if(itemName.equals(ARMOR_DRUID))
        {
            itemAsArmor = new Armor(4);
        }
        else if(itemName.equals(ARMOR_FIGHTER))
        {
            itemAsArmor = new Armor(5);
        }
        return itemAsArmor;
    }
}
