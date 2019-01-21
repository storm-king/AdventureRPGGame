
package adventurerpg;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Random;

/**
 *
 * @author storm
 */
public class MouseInput implements MouseListener, MouseMotionListener
{   

    public static double SCALELOWRES = .68;
    public static int mx;
    public static int my;
    Random r = new Random();
    
    @Override
    public void mouseMoved(MouseEvent e)
    {
        mx = e.getX();
        my = e.getY();
        
        if(AdventureRPG.state == AdventureRPG.STATE.NUMBER_PLAYERS)
        {
            if ( hover(mx, my, Coordinates.BUTTON_1P_X, Coordinates.BUTTON_1P_Y, Coordinates.BUTTON_P_W, Coordinates.BUTTON_P_H) )
            {
                AdventureRPG.button = "1P";
            }
            else if ( hover(mx, my, Coordinates.BUTTON_2P_X, Coordinates.BUTTON_2P_Y, Coordinates.BUTTON_P_W, Coordinates.BUTTON_P_H) )
            {
                AdventureRPG.button = "2P";
            }
            else if ( hover(mx, my, Coordinates.BUTTON_3P_X, Coordinates.BUTTON_3P_Y, Coordinates.BUTTON_P_W, Coordinates.BUTTON_P_H) )
            {
                AdventureRPG.button = "3P";
            }
            else if ( hover(mx, my, Coordinates.BUTTON_4P_X, Coordinates.BUTTON_4P_Y, Coordinates.BUTTON_P_W, Coordinates.BUTTON_P_H) )
            {
                AdventureRPG.button = "4P";
            }  
            else if ( hover(mx, my, Coordinates.BUTTON_LOAD_X, Coordinates.BUTTON_LOAD_Y, Coordinates.BUTTON_CONTINUE_W, Coordinates.BUTTON_CONTINUE_H) )
            {
                AdventureRPG.button = "Load";
            }             
            else
            {
                AdventureRPG.button = "";
            }
        }
        
        else if (AdventureRPG.state == AdventureRPG.STATE.CHARACTER_SELECT1 || AdventureRPG.state == AdventureRPG.STATE.CHARACTER_SELECT2 
                || AdventureRPG.state == AdventureRPG.STATE.CHARACTER_SELECT3 || AdventureRPG.state == AdventureRPG.STATE.CHARACTER_SELECT4)
        {
            if ( hover(mx, my, Coordinates.BUTTON_CONTINUE_X, Coordinates.BUTTON_CONTINUE_Y, Coordinates.BUTTON_CONTINUE_W, Coordinates.BUTTON_CONTINUE_H) )
            {
                AdventureRPG.button = "Continue";
            } 
            else if ( hover(mx, my, Coordinates.BUTTON_SELECT_WIZARD_X, Coordinates.BUTTON_SELECT_Y, Coordinates.BUTTON_SELECT_W, Coordinates.BUTTON_SELECT_H) )
            {
                AdventureRPG.button = "Wizard";
            } 
            else if ( hover(mx, my, Coordinates.BUTTON_SELECT_FIGHTER_X, Coordinates.BUTTON_SELECT_Y, Coordinates.BUTTON_SELECT_W, Coordinates.BUTTON_SELECT_H) )
            {
                AdventureRPG.button = "Fighter";
            }
            else if ( hover(mx, my, Coordinates.BUTTON_SELECT_THIEF_X, Coordinates.BUTTON_SELECT_Y, Coordinates.BUTTON_SELECT_W, Coordinates.BUTTON_SELECT_H) )
            {
                AdventureRPG.button = "Thief";
            }            
            else
            {
                AdventureRPG.button = "";
            }
        }
        
        
        else if (AdventureRPG.state == AdventureRPG.STATE.GAME)
        {
            if ( hover(mx, my, Coordinates.BUTTON_OPTION_FIGHT_X, Coordinates.BUTTON_OPTION_Y, Coordinates.BUTTON_OPTION_W, Coordinates.BUTTON_OPTION_H) 
                    && AdventureRPG.playerTurn && !AdventureRPG.playerWin )
            {
                AdventureRPG.button = "Fight";
            }
            else if ( hover(mx, my, Coordinates.BUTTON_OPTION_HIDE_X, Coordinates.BUTTON_OPTION_Y, Coordinates.BUTTON_OPTION_W, Coordinates.BUTTON_OPTION_H) 
                    && AdventureRPG.playerTurn && !AdventureRPG.playerWin )
            {
                AdventureRPG.button = "Hide";
            }
            else if ( hover(mx, my, Coordinates.BUTTON_OPTION_BAG_X, Coordinates.BUTTON_OPTION_Y, Coordinates.BUTTON_OPTION_W, Coordinates.BUTTON_OPTION_H) 
                    && AdventureRPG.playerTurn && !AdventureRPG.playerWin )
            {
                AdventureRPG.button = "Bag";
            }         
            else if ( hover(mx, my, Coordinates.BUTTON_OPTION_SEARCH_X, Coordinates.BUTTON_OPTION_Y, Coordinates.BUTTON_OPTION_W, Coordinates.BUTTON_OPTION_H) 
                    && AdventureRPG.playerTurn && !AdventureRPG.playerToAttack.getSearched() && !AdventureRPG.playerWin )
            {
                AdventureRPG.button = "Search";
            }              
            else if ( hover(mx, my, Coordinates.BUTTON_OPTION_RUN_X, Coordinates.BUTTON_OPTION_Y, Coordinates.BUTTON_OPTION_W, Coordinates.BUTTON_OPTION_H) 
                    && AdventureRPG.playerTurn && !AdventureRPG.playerWin && AdventureRPG.roomCount != 1 )
            {
                AdventureRPG.button = "Run";
            }          
            else if ( hover(mx, my, Coordinates.BUTTON_SUBOPTION_SAVE_X, Coordinates.BUTTON_SUBOPTION_Y, Coordinates.BUTTON_SUBOPTION_W, Coordinates.BUTTON_OPTION_H) )
            {
                AdventureRPG.button = "Save";
            }             
            else if ( hover(mx, my, Coordinates.BUTTON_SUBOPTION_EXIT_X, Coordinates.BUTTON_SUBOPTION_Y, Coordinates.BUTTON_SUBOPTION_W, Coordinates.BUTTON_OPTION_H) )
            {
                AdventureRPG.button = "Exit";
            } 
            
            else if ( hover(mx, my, Coordinates.BUTTON_ROOM_BED_X, Coordinates.BUTTON_ROOM_BED_Y, Coordinates.BUTTON_ROOM_BED_W, Coordinates.BUTTON_ROOM_BED_H)
                    && AdventureRPG.roomCount != 6 )
            {
                AdventureRPG.button = "Sleep";
            }  
            else if ( hover(mx, my, Coordinates.BUTTON_ROOM_CHEST1_X, Coordinates.BUTTON_ROOM_CHEST1_Y, Coordinates.BUTTON_ROOM_CHEST1_W, Coordinates.BUTTON_ROOM_CHEST1_H)
                    && AdventureRPG.roomCount != 6 )
            {
                AdventureRPG.button = "Open";
            }   
            else if ( hover(mx, my, Coordinates.BUTTON_ROOM_CHEST2_X, Coordinates.BUTTON_ROOM_CHEST2_Y, Coordinates.BUTTON_ROOM_CHEST2_W, Coordinates.BUTTON_ROOM_CHEST2_H)
                    && AdventureRPG.roomCount != 6 )
            {
                AdventureRPG.button = "Loot";
            }   
            else if ( hover(mx, my, Coordinates.BUTTON_ROOM_NORTHSTAIRS_X, Coordinates.BUTTON_ROOM_NORTHSTAIRS_Y, Coordinates.BUTTON_ROOM_STAIRS_W, Coordinates.BUTTON_ROOM_STAIRS_H)
                    && AdventureRPG.roomCount != 6 )
            {
                AdventureRPG.button = "North";
            } 
            else if ( hover(mx, my, Coordinates.BUTTON_ROOM_SOUTHSTAIRS_X, Coordinates.BUTTON_ROOM_SOUTHSTAIRS_Y, Coordinates.BUTTON_ROOM_STAIRS_W, Coordinates.BUTTON_ROOM_STAIRS_H)
                    && AdventureRPG.roomCount != 6 )
            {
                AdventureRPG.button = "South";
            } 
            else if ( hover(mx, my, Coordinates.BUTTON_ROOM_WESTSTAIRS_X, Coordinates.BUTTON_ROOM_WESTSTAIRS_Y, Coordinates.BUTTON_ROOM_STAIRS_W, Coordinates.BUTTON_ROOM_STAIRS_H)
                    && AdventureRPG.roomCount != 6 )
            {
                AdventureRPG.button = "West";
            } 
            else if ( hover(mx, my, Coordinates.BUTTON_ROOM_EASTSTAIRS_X, Coordinates.BUTTON_ROOM_EASTSTAIRS_Y, Coordinates.BUTTON_ROOM_STAIRS_W, Coordinates.BUTTON_ROOM_STAIRS_H)
                    && AdventureRPG.roomCount != 6)
            {
                AdventureRPG.button = "East";
            }   
            else if ( hover(mx, my, Coordinates.BUTTON_ROOM_BOSSCHEST_X, Coordinates.BUTTON_ROOM_BOSSCHEST_Y, Coordinates.BUTTON_ROOM_BOSSCHEST_W, Coordinates.BUTTON_ROOM_BOSSCHEST_H)
                    && AdventureRPG.roomCount == 6)
            {
                AdventureRPG.button = "Victory";
            }              
            else
            {
                AdventureRPG.button = "";
            }
            
            for (Player p : AdventureRPG.playerObjects)
            {
                if (p.getName() != null)
                {
                    if ( hover(mx, my, p.getX(), p.getY(), Coordinates.BUTTON_NPC_W, Coordinates.BUTTON_NPC_H) 
                        && p.getName() != null )
                    {
                        AdventureRPG.button = p.getName();
                    }
                }
            }
            for (NPC n : AdventureRPG.npcObjects)
            {
                if (n.getName() != null)
                {
                    if ( hover(mx, my, n.getX(), n.getY(), Coordinates.BUTTON_NPC_W, Coordinates.BUTTON_NPC_H) 
                        && !n.getLooted())
                    {
                        AdventureRPG.button = n.getName();
                    }
                }
            }       
        }        
    } 
    
    public static boolean hover(int mx, int my, double x, double y, double w, double h)
    {
        return mx >= x && mx <= x + w && my >= y && my <= y + h;
    }
    
    @Override
    public void mouseDragged(MouseEvent e)
    {
        
    }        
    
    @Override
    public void mousePressed(MouseEvent e) {
        
    }

    @Override
    public void mouseReleased(MouseEvent e) 
    { 
        if (!AdventureRPG.buttonInterface.equals(""))
        {
            if (AdventureRPG.select.equals("Bag"))
            {
                for (Item i : AdventureRPG.bag)
                {
                    String iName = i.getName();

                    if (AdventureRPG.buttonInterface.equals(iName))
                    {
                        AdventureRPG.takeFromBag(i, 1);
                        AdventureRPG.itemTaken = i;
                        
                        if (iName.equals(Item.TREASURE))
                        {
                            AdventureRPG.addToBag(Item.COINS, 1000);
                        }                       
                        break;
                    }
                }
            }
            else if (AdventureRPG.select.equals("Fight"))
            {
                if (AdventureRPG.buttonInterface.equals("Normal"))
                {
                    AdventureRPG.attackType = "Normal";
                }
                else  if (AdventureRPG.buttonInterface.equals("Special"))
                {
                    AdventureRPG.attackType = "Special";
                }
                AdventureRPG.select = "";
            }
            else if (AdventureRPG.buttonInterface.equals("Confirm"))
            {
                if (AdventureRPG.select.equals("Hide"))
                {
                    AdventureRPG.hideOptionSelected = true;
                    AdventureRPG.select = "";
                }
                else if (AdventureRPG.select.equals("Search"))
                {
                    AdventureRPG.itemsFound = "";
                    if (r.nextInt(3) == 1)
                    {
                        AdventureRPG.addToBag(Item.POTION_BASIC, 1);
                    }
                    if (r.nextInt(3) > 0)
                    {
                        AdventureRPG.addToBag(Item.COINS, r.nextInt(100)+1);
                    }

                    if (AdventureRPG.itemsFound.equals(""))
                    {
                        AdventureRPG.itemsFound = "Nothing Found";
                        AdventureRPG.notificationTimer = 3200;
                    }
                    AdventureRPG.select = "";
                    AdventureRPG.playerToAttack.setSearched(true);
                }
                else if (AdventureRPG.select.equals("Run"))
                {
                    AdventureRPG.runAway = true;
                    AdventureRPG.select = "";
                }   
                else if (AdventureRPG.select.equals("Save"))
                {
                    
                }                
                else if (AdventureRPG.select.equals("Exit"))
                {
                    AdventureRPG.state = AdventureRPG.STATE.MENU;
                    AdventureRPG.characters[0] = 0;
                    AdventureRPG.characters[1] = 0;
                    AdventureRPG.characters[2] = 0;
                    AdventureRPG.characters[3] = 0;
                    AdventureRPG.numberOfPlayers = 0;
                    AdventureRPG.button = "";
                    AdventureRPG.buttonInterface = "";
                    AdventureRPG.select = "";
                    return;
                }
            }
        }        

        if(AdventureRPG.state == AdventureRPG.STATE.MENU)
        {
            mx = e.getX();
            my = e.getY();
            
            if(mx >= 0 && mx <= (1880 * SCALELOWRES))
            {
                if(my >= 0 && my <= (900 * SCALELOWRES))
                {
                    AdventureRPG.state = AdventureRPG.STATE.NUMBER_PLAYERS;
                }
            }
        }
       
        
        else if (!AdventureRPG.button.equals(""))
        {
            if(AdventureRPG.state == AdventureRPG.STATE.NUMBER_PLAYERS)
            {
                if (AdventureRPG.button.equals("1P"))
                {
                    AdventureRPG.numberOfPlayers = 1;           
                }
                else if (AdventureRPG.button.equals("2P"))
                {
                    AdventureRPG.numberOfPlayers = 2;           
                }
                else if (AdventureRPG.button.equals("3P"))
                {
                    AdventureRPG.numberOfPlayers = 3;           
                }  
                else if (AdventureRPG.button.equals("4P"))
                {
                    AdventureRPG.numberOfPlayers = 4;           
                }            
                else if (AdventureRPG.button.equals("Load"))
                {
                               
                }   
                
                if(AdventureRPG.numberOfPlayers != 0)
                {
                    AdventureRPG.state = AdventureRPG.STATE.CHARACTER_SELECT1;
                }
            }

            else if(AdventureRPG.state == AdventureRPG.STATE.CHARACTER_SELECT1
                    || AdventureRPG.state == AdventureRPG.STATE.CHARACTER_SELECT2
                    || AdventureRPG.state == AdventureRPG.STATE.CHARACTER_SELECT3
                    || AdventureRPG.state == AdventureRPG.STATE.CHARACTER_SELECT4)
            {
                if (AdventureRPG.button.equals("Wizard"))
                {
                    if (AdventureRPG.characterSelected != 1)
                    {
                        AdventureRPG.characterSelected = 1;
                    }
                    else
                    {
                        AdventureRPG.characterSelected = 0;
                    }
                }
                else if (AdventureRPG.button.equals("Fighter"))
                {
                    if (AdventureRPG.characterSelected != 2)
                    {
                        AdventureRPG.characterSelected = 2;
                    }
                    else
                    {
                        AdventureRPG.characterSelected = 0;
                    }
                }  
                else if (AdventureRPG.button.equals("Thief"))
                {
                    if (AdventureRPG.characterSelected != 3)
                    {
                        AdventureRPG.characterSelected = 3;
                    }
                    else
                    {
                        AdventureRPG.characterSelected = 0;
                    }
                } 


                if (AdventureRPG.button.equals("Continue") && AdventureRPG.characterSelected != 0)
                {
                    if (AdventureRPG.state == AdventureRPG.STATE.CHARACTER_SELECT1)
                    {
                        AdventureRPG.characters[0] = AdventureRPG.characterSelected;
                        AdventureRPG.characterSelected = 0;

                        if(AdventureRPG.numberOfPlayers > 1)
                        {
                            AdventureRPG.state = AdventureRPG.STATE.CHARACTER_SELECT2;
                        }
                        else
                        {
                            AdventureRPG.state = AdventureRPG.STATE.PREGAME_INITIALIZATION_SCREEN;
                        }
                    }
                    else if (AdventureRPG.state == AdventureRPG.STATE.CHARACTER_SELECT2)
                    {
                        AdventureRPG.characters[1] = AdventureRPG.characterSelected;
                        AdventureRPG.characterSelected = 0;

                        if(AdventureRPG.numberOfPlayers > 2)
                        {
                            AdventureRPG.state = AdventureRPG.STATE.CHARACTER_SELECT3;
                        }
                        else
                        {
                            AdventureRPG.state = AdventureRPG.STATE.PREGAME_INITIALIZATION_SCREEN;
                        }
                    }                  
                    else if (AdventureRPG.state == AdventureRPG.STATE.CHARACTER_SELECT3)
                    {
                        AdventureRPG.characters[2] = AdventureRPG.characterSelected;
                        AdventureRPG.characterSelected = 0;

                        if(AdventureRPG.numberOfPlayers > 3)
                        {
                            AdventureRPG.state = AdventureRPG.STATE.CHARACTER_SELECT4;
                        }
                        else
                        {
                            AdventureRPG.state = AdventureRPG.STATE.PREGAME_INITIALIZATION_SCREEN;
                        }
                    }                  
                    else if (AdventureRPG.state == AdventureRPG.STATE.CHARACTER_SELECT4)
                    {
                       AdventureRPG.characters[3] = AdventureRPG.characterSelected;

                       AdventureRPG.state = AdventureRPG.STATE.PREGAME_INITIALIZATION_SCREEN;
                    }

                    AdventureRPG.characterSelected = 0;
                }  

            }


            else if(AdventureRPG.state == AdventureRPG.STATE.GAME)
            {
                /*if( mx > 810 && mx < 858)
                {
                    if(my > 226 && my < 290)
                    {
                        AdventureRPG.attackNumber = 1;
                    }
                }*/
                if (AdventureRPG.button.equals("Fight"))
                {
                    if (!AdventureRPG.select.equals("Fight"))
                    {
                        AdventureRPG.select = "Fight";
                    } 
                    else
                    {
                        AdventureRPG.select = "";
                    }
                }
                else if (AdventureRPG.button.equals("Hide"))
                {
                    if (!AdventureRPG.select.equals("Hide"))
                    {
                        AdventureRPG.select = "Hide";
                    } 
                    else
                    {
                        AdventureRPG.select = "";
                    }
                }
                else if (AdventureRPG.button.equals("Bag"))
                {
                    if (!AdventureRPG.select.equals("Bag"))
                    {
                        AdventureRPG.select = "Bag";
                    } 
                    else
                    {
                        AdventureRPG.select = "";
                    }
                }
                else if (AdventureRPG.button.equals("Search"))
                {
                    if (!AdventureRPG.select.equals("Search"))
                    {
                        AdventureRPG.select = "Search";
                    } 
                    else
                    {
                        AdventureRPG.select = "";
                    }
                }                 
                else if (AdventureRPG.button.equals("Run"))
                {
                    if (!AdventureRPG.select.equals("Run"))
                    {
                        AdventureRPG.select = "Run";
                    } 
                    else
                    {
                        AdventureRPG.select = "";
                    }
                }     
                else if (AdventureRPG.button.equals("Save"))
                {
                    if (!AdventureRPG.select.equals("Save"))
                    {
                        AdventureRPG.select = "Save";
                    } 
                    else
                    {
                        AdventureRPG.select = "";
                    }
                }
                else if (AdventureRPG.button.equals("Exit"))
                {
                    if (!AdventureRPG.select.equals("Exit"))
                    {
                        AdventureRPG.select = "Exit";
                    } 
                    else
                    {
                        AdventureRPG.select = "";                   
                    }                   
                } 
                
                else if (AdventureRPG.button.equals("Sleep") && AdventureRPG.playerWin && !AdventureRPG.roomSleep)
                {
                    AdventureRPG.roomSleep = true;
                    AdventureRPG.sleepTimer = 3000;
                    
                    for (Player p : AdventureRPG.playerObjects)
                    {
                        if (p.getName() != null)
                        {
                            if (p.getHp() < 20 && p.getHp() > 0)
                            {
                                p.setHp(p.getHp() + 1);
                            }
                        }
                    }
                }   
                else if (AdventureRPG.button.equals("Open") && AdventureRPG.playerWin && !AdventureRPG.roomChest1)
                {
                    AdventureRPG.roomChest1 = true;
                    AdventureRPG.itemsFound = "";
                    AdventureRPG.lootObject(-1, -1, 2, 5, 8, -1);
                    if (AdventureRPG.itemsFound.equals(""))
                    {
                        AdventureRPG.itemsFound = "Nothing Found";
                        AdventureRPG.notificationTimer = 3200;
                    }                    
                } 
                else if (AdventureRPG.button.equals("Loot") && AdventureRPG.playerWin && !AdventureRPG.roomChest2)
                {
                    AdventureRPG.roomChest2 = true;
                    AdventureRPG.itemsFound = "";
                    AdventureRPG.lootObject(5, 5, 3, 1, 8, 15);
                    if (AdventureRPG.itemsFound.equals(""))
                    {
                        AdventureRPG.itemsFound = "Nothing Found";
                        AdventureRPG.notificationTimer = 3200;
                    }
                }   
                else if (AdventureRPG.button.equals("North") || AdventureRPG.button.equals("South")
                        || AdventureRPG.button.equals("West") || AdventureRPG.button.equals("East")
                        || AdventureRPG.button.equals("Victory"))
                {
                    if (AdventureRPG.sleepTimer <= 0 && AdventureRPG.playerWin)
                    {
                        if (!AdventureRPG.button.equals("Victory"))
                        {
                            AdventureRPG.proceedToAnotherRoom = true;   
                        }
                        else
                        {
                            AdventureRPG.victory = true;
                        }
                    } 
                }
                else if (AdventureRPG.button.equals(AdventureRPG.n1.getName()))
                {
                    npcClicked(AdventureRPG.n1, 1);
                }     
                else if (AdventureRPG.button.equals(AdventureRPG.n2.getName()))
                {
                    npcClicked(AdventureRPG.n2, 2);
                }      
                else if (AdventureRPG.button.equals(AdventureRPG.n3.getName()))
                {
                    npcClicked(AdventureRPG.n3, 3);
                }      
                else if (AdventureRPG.button.equals(AdventureRPG.n4.getName()))
                {
                    npcClicked(AdventureRPG.n4, 4);
                }      
                else if (AdventureRPG.button.equals(AdventureRPG.n5.getName()))
                {
                    npcClicked(AdventureRPG.n5, 5);
                }      
                else if (AdventureRPG.button.equals(AdventureRPG.n6.getName()))
                {
                    npcClicked(AdventureRPG.n6, 6);
                }                      
            }
            
            
        }
    }
    
    public void npcClicked(NPC npc, int attackNumber)
    {
        if (npc.getHp() > 0)
        {
            AdventureRPG.attackNumber = attackNumber;
            npc.setTargeted(true);
        }        
        else if (!npc.getLooted())
        {
            if (AdventureRPG.playerTurn || AdventureRPG.playerWin)
            {
                npc.setLooted(true);
                AdventureRPG.itemsFound = "";
                AdventureRPG.lootObject(8, 8, -1, 3, 12, 1);
            }
        }
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
        
        
    }

    @Override
    public void mouseEntered(MouseEvent e){
        

    }

    @Override
    public void mouseExited(MouseEvent e) {
        
    }
    
    
}
