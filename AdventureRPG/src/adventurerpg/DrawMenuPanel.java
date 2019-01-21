/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adventurerpg;


import java.awt.AlphaComposite;
import java.awt.Graphics;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.util.ArrayList;
import javax.swing.JPanel;

/**
 *
 * @author storm
 */

public class DrawMenuPanel extends JPanel
{
    private final Image borderImage;
    private final Image bagImage;
    private Image menuImage;
    private Image sleepImage;
    private Image interfaceFightNormalImage;
    private Image interfaceFightSpecialImage;
    private Image interfaceButtonImage;
    private Image interfaceButtonImageHover;
    
    
    private final ArrayList<Button> buttons;
    private final Font fnt0 = new Font("Times New Roman", Font.BOLD, 14);
    private final Font fnt1 = new Font("Arial", Font.BOLD, 18);
    private final Font fntInfo = new Font("Arial", Font.BOLD, 20);
    private final Font fntMainButton = new Font("Times New Roman", Font.BOLD, 32);
    private final Font fntButton = new Font("Arial", Font.BOLD, 14);
    private final Font fntNpc = new Font("Arial", Font.BOLD, 16);
    private final Font fntPlayer = new Font("Arial", Font.BOLD, 16);
    private final Font fntBag = new Font("Arial", Font.BOLD, 12);
    private final Font fntHp = new Font("Arial", Font.BOLD, 10);
    private final Font fntDmg = new Font("Arial", Font.BOLD, 14);
    
    public DrawMenuPanel(int menuOption) 
    {
        int currentMenu = menuOption;
        BufferedImageLoader loader = new BufferedImageLoader(); 
        
        buttons = new ArrayList();
        
        sleepImage = loader.loadImage("/sleeping.png");
        bagImage = loader.loadImage("/button_bag_hover.png");
        borderImage = loader.loadImage("/screen_border.png");
        
        
        if(currentMenu == 0)
        {
            menuImage = loader.loadImage("/BackgroundPlaySCALED.gif");
        }
        else if(currentMenu == 1)
        {
            menuImage = loader.loadImage("/PlayerSelectPlaySCALED.gif");

            buttons.add(new Button("/button_1p", "", "",  
                    true,
                    (int)Coordinates.BUTTON_1P_X, (int)Coordinates.BUTTON_1P_Y,
                    (int)Coordinates.BUTTON_P_W, (int)Coordinates.BUTTON_P_H,
                    "1P", null));            
            buttons.add(new Button("/button_2p", "", "",  
                    true,
                    (int)Coordinates.BUTTON_2P_X, (int)Coordinates.BUTTON_2P_Y,
                    (int)Coordinates.BUTTON_P_W, (int)Coordinates.BUTTON_P_H,
                    "2P", null));                        
            buttons.add(new Button("/button_3p", "", "", 
                    true,
                    (int)Coordinates.BUTTON_3P_X, (int)Coordinates.BUTTON_3P_Y,
                    (int)Coordinates.BUTTON_P_W, (int)Coordinates.BUTTON_P_H,
                    "3P", null));             
            buttons.add(new Button("/button_4p", "", "",  
                    true,
                    (int)Coordinates.BUTTON_4P_X, (int)Coordinates.BUTTON_4P_Y,
                    (int)Coordinates.BUTTON_P_W, (int)Coordinates.BUTTON_P_H,
                    "4P", null));    
            buttons.add(new Button("/button_x", "", "",  
                    true,
                    (int)Coordinates.BUTTON_LOAD_X, (int)Coordinates.BUTTON_LOAD_Y,
                    (int)Coordinates.BUTTON_CONTINUE_W, (int)Coordinates.BUTTON_CONTINUE_H,
                    "Load", fntMainButton));             
        }
        
        else if (currentMenu >= 2 && currentMenu <= 5)
        {   
            if(currentMenu == 2)
            {
                menuImage = loader.loadImage("/CharacterSelectSCALED.png");
            }
            else if(currentMenu == 3)
            {
                menuImage = loader.loadImage("/CharacterSelectTwoSCALED.png");
            }
            else if(currentMenu == 4)
            {
                menuImage = loader.loadImage("/CharacterSelectThreeSCALED.png");
            }
            else if(currentMenu == 5)
            {
                menuImage = loader.loadImage("/CharacterSelectFourSCALED.png");
            }            
            
            buttons.add(new Button("/button_continue", "", "", 
                    AdventureRPG.characterSelected != 0,
                    (int)Coordinates.BUTTON_CONTINUE_X, (int)Coordinates.BUTTON_CONTINUE_Y,
                    (int)Coordinates.BUTTON_CONTINUE_W, (int)Coordinates.BUTTON_CONTINUE_H,
                    "Continue", null));
            
            buttons.add(new Button("/button_select_wizard", "", "",
                    AdventureRPG.characterSelected != 1,
                    (int)Coordinates.BUTTON_SELECT_WIZARD_X, (int)Coordinates.BUTTON_SELECT_Y,
                    (int)Coordinates.BUTTON_SELECT_W, (int)Coordinates.BUTTON_SELECT_H,
                    "Wizard", fnt0)); 
            buttons.add(new Button("/button_select_fighter", "", "",  
                    AdventureRPG.characterSelected != 2,
                    (int)Coordinates.BUTTON_SELECT_FIGHTER_X, (int)Coordinates.BUTTON_SELECT_Y,
                    (int)Coordinates.BUTTON_SELECT_W, (int)Coordinates.BUTTON_SELECT_H,
                    "Fighter", fnt0));             
            buttons.add(new Button("/button_select_thief", "", "", 
                    AdventureRPG.characterSelected != 3,
                    (int)Coordinates.BUTTON_SELECT_THIEF_X, (int)Coordinates.BUTTON_SELECT_Y,
                    (int)Coordinates.BUTTON_SELECT_W, (int)Coordinates.BUTTON_SELECT_H,
                    "Thief", fnt0));                                 
        }  
            
        //GAMEPLAY
        else if(currentMenu == 6)
        {
            determineRoomToDraw(loader);
            
            buttons.add(new Button("/button_option_fight", "/selection_option.png", "/interface_fight.png",
                    !AdventureRPG.select.equals("Fight"),
                    (int)Coordinates.BUTTON_OPTION_FIGHT_X, (int)Coordinates.BUTTON_OPTION_Y,
                    (int)Coordinates.BUTTON_OPTION_W, (int)Coordinates.BUTTON_OPTION_H,
                    "Fight", fnt0));            
            buttons.add(new Button("/button_option_hide", "/selection_option.png", "/interface_hide.png", 
                    !AdventureRPG.select.equals("Hide"),
                    (int)Coordinates.BUTTON_OPTION_HIDE_X, (int)Coordinates.BUTTON_OPTION_Y,
                    (int)Coordinates.BUTTON_OPTION_W, (int)Coordinates.BUTTON_OPTION_H,
                    "Hide", fnt0));
            buttons.add(new Button("/button_option_bag", "/selection_option.png", "/interface_bag.png",
                    !AdventureRPG.select.equals("Bag"),
                    (int)Coordinates.BUTTON_OPTION_BAG_X, (int)Coordinates.BUTTON_OPTION_Y,
                    (int)Coordinates.BUTTON_OPTION_W, (int)Coordinates.BUTTON_OPTION_H,
                    "Bag", fnt0));            
            buttons.add(new Button("/button_option_search", "/selection_option.png", "/interface_search.png",
                    !AdventureRPG.select.equals("Search"),
                    (int)Coordinates.BUTTON_OPTION_SEARCH_X, (int)Coordinates.BUTTON_OPTION_Y,
                    (int)Coordinates.BUTTON_OPTION_W, (int)Coordinates.BUTTON_OPTION_H,
                    "Search", fnt0));            
            buttons.add(new Button("/button_option_run", "/selection_option.png", "/interface_run.png", 
                    !AdventureRPG.select.equals("Run"),
                    (int)Coordinates.BUTTON_OPTION_RUN_X, (int)Coordinates.BUTTON_OPTION_Y,
                    (int)Coordinates.BUTTON_OPTION_W, (int)Coordinates.BUTTON_OPTION_H,
                    "Run", fnt0));
            
            buttons.add(new Button("/button_option_save", "/selection_suboption.png", "/interface_suboption.png", 
                    !AdventureRPG.select.equals("Save"),
                    (int)Coordinates.BUTTON_SUBOPTION_SAVE_X, (int)Coordinates.BUTTON_SUBOPTION_Y,
                    (int)Coordinates.BUTTON_SUBOPTION_W, (int)Coordinates.BUTTON_SUBOPTION_H,
                    "Save", fnt0));                        
            buttons.add(new Button("/button_option_exit", "/selection_suboption.png", "/interface_suboption.png", 
                    AdventureRPG.select.equals("Exit"),
                    (int)Coordinates.BUTTON_SUBOPTION_EXIT_X, (int)Coordinates.BUTTON_SUBOPTION_Y,
                    (int)Coordinates.BUTTON_SUBOPTION_W, (int)Coordinates.BUTTON_SUBOPTION_H,
                    "Exit", fnt0));    
            
            if (AdventureRPG.roomCount != 6)
            {
                buttons.add(new Button("/button_room_bed", "", "", 
                        AdventureRPG.playerWin && !AdventureRPG.roomSleep,
                        (int)Coordinates.BUTTON_ROOM_BED_X, (int)Coordinates.BUTTON_ROOM_BED_Y,
                        (int)Coordinates.BUTTON_ROOM_BED_W, (int)Coordinates.BUTTON_ROOM_BED_H,
                        "Sleep", fnt0));    
                buttons.add(new Button("/button_room_chest1", "", "", 
                        AdventureRPG.playerWin && !AdventureRPG.roomChest1,
                        (int)Coordinates.BUTTON_ROOM_CHEST1_X, (int)Coordinates.BUTTON_ROOM_CHEST1_Y,
                        (int)Coordinates.BUTTON_ROOM_CHEST1_W, (int)Coordinates.BUTTON_ROOM_CHEST1_H,
                        "Open", fnt0));  
                buttons.add(new Button("/button_room_chest2", "", "", 
                        AdventureRPG.playerWin && !AdventureRPG.roomChest2,
                        (int)Coordinates.BUTTON_ROOM_CHEST2_X, (int)Coordinates.BUTTON_ROOM_CHEST2_Y,
                        (int)Coordinates.BUTTON_ROOM_CHEST2_W, (int)Coordinates.BUTTON_ROOM_CHEST2_H,
                        "Loot", fnt0));    

                buttons.add(new Button("/button_room_northstairs", "", "", 
                        true,
                        (int)Coordinates.BUTTON_ROOM_NORTHSTAIRS_X, (int)Coordinates.BUTTON_ROOM_NORTHSTAIRS_Y,
                        (int)Coordinates.BUTTON_ROOM_STAIRS_W, (int)Coordinates.BUTTON_ROOM_STAIRS_H,
                        "North", fnt0)); 
                buttons.add(new Button("/button_room_southstairs", "", "", 
                        true,
                        (int)Coordinates.BUTTON_ROOM_SOUTHSTAIRS_X, (int)Coordinates.BUTTON_ROOM_SOUTHSTAIRS_Y,
                        (int)Coordinates.BUTTON_ROOM_STAIRS_W, (int)Coordinates.BUTTON_ROOM_STAIRS_H,
                        "South", fnt0)); 
                buttons.add(new Button("/button_room_weststairs", "", "", 
                        true,
                        (int)Coordinates.BUTTON_ROOM_WESTSTAIRS_X, (int)Coordinates.BUTTON_ROOM_WESTSTAIRS_Y,
                        (int)Coordinates.BUTTON_ROOM_STAIRS_W, (int)Coordinates.BUTTON_ROOM_STAIRS_H,
                        "West", fnt0)); 
                buttons.add(new Button("/button_room_eaststairs", "", "", 
                        true,
                        (int)Coordinates.BUTTON_ROOM_EASTSTAIRS_X, (int)Coordinates.BUTTON_ROOM_EASTSTAIRS_Y,
                        (int)Coordinates.BUTTON_ROOM_STAIRS_W, (int)Coordinates.BUTTON_ROOM_STAIRS_H,
                        "East", fnt0));  
            }
            else
            {
                buttons.add(new Button("/button_room_bosschest", "", "", 
                        AdventureRPG.playerWin,
                        (int)Coordinates.BUTTON_ROOM_BOSSCHEST_X, (int)Coordinates.BUTTON_ROOM_BOSSCHEST_Y,
                        (int)Coordinates.BUTTON_ROOM_BOSSCHEST_W, (int)Coordinates.BUTTON_ROOM_BOSSCHEST_H,
                        "Victory", fnt0));                  
            }
            
            if (AdventureRPG.state == AdventureRPG.STATE.GAME)
            {
                for ( NPC n : AdventureRPG.npcObjects)
                {
                    if (n.getName() != null && !n.getTargeted() && n.getHp() > 0)
                    {
                        buttons.add(new Button("", "", "", 
                                true,
                                (int)n.getX(), (int)n.getY(),
                                (int)Coordinates.BUTTON_NPC_W, (int)Coordinates.BUTTON_NPC_H,
                                n.getName(), fntNpc)); 
                    }
                } 
                for ( Player p : AdventureRPG.playerObjects)
                {
                    if (p.getName() != null)
                    {
                        buttons.add(new Button("", "", "", 
                                true,
                                (int)p.getX(), (int)p.getY(),
                                (int)Coordinates.BUTTON_NPC_W, (int)Coordinates.BUTTON_NPC_H,
                                p.getName(), fntPlayer)); 
                    }
                }                  
            }
            
            if (!AdventureRPG.select.equals(""))
            {
                if (AdventureRPG.select.equals("Fight"))
                {
                    interfaceFightNormalImage = loader.loadImage("/interface_fight_normal.png"); 
                    interfaceFightSpecialImage = loader.loadImage("/interface_fight_special.png");        
                }
                else if (!AdventureRPG.select.equals("bag"))
                {
                    interfaceButtonImage = loader.loadImage("/button_interface.png");   
                    interfaceButtonImageHover = loader.loadImage("/button_interface_hover.png");
                }
            }
        }   

    }
    

    @Override
    public void paintComponent(Graphics g) 
    {
        boolean hovering = false;
        
        super.paintComponent(g);
        if (AdventureRPG.drawBackground)
        {
            g.drawImage(menuImage, 0, 0, this);
        }
        else if (AdventureRPG.endingTimer > 1500)
        {
            for (Button b : buttons)
            {
                Image img = b.getImage();
                String str = b.getString();
                int x = b.getX();
                int y = b.getY();
                int w = b.getW();
                int h = b.getH();
                Font font = b.getFont();


                if (img != null)
                {
                    g.drawImage(img, x, y, this);
                }

                if (font != null && !b.getStatus().equals("unavailable"))
                {
                    drawStringExt(g, str, x, y, w, h, font, Color.WHITE, Color.BLACK, 1);            
                }

                if (str.equals(AdventureRPG.select))
                {
                    g.drawImage(b.getSelectionImage(), x, y, this);

                    if (b.getInterfaceImage() != null)
                    {
                        int interface_x = x + (w/2) - 115;
                        int interface_y = y - 228;
                        Image interfaceImage = b.getInterfaceImage();

                        if (str.equals("Exit") || str.equals("Save"))
                        {
                            interface_y += 68;
                        }

                        if (str.equals("Fight"))
                        {
                            if (MouseInput.hover(MouseInput.mx, MouseInput.my, interface_x + 60, interface_y + 35, 110, 82))
                            {
                                interfaceImage = interfaceFightNormalImage;
                                AdventureRPG.buttonInterface = "Normal";
                                hovering = true;
                            }
                            else if (MouseInput.hover(MouseInput.mx, MouseInput.my, interface_x + 60, interface_y + 118, 110, 82))
                            {
                                interfaceImage = interfaceFightSpecialImage;
                                AdventureRPG.buttonInterface = "Special";
                                hovering = true;
                            }                          
                        }

                        g.drawImage(interfaceImage, interface_x, interface_y, this);
                        drawStringExt(g, str, interface_x, interface_y, 230, 236, fnt1, Color.YELLOW, Color.BLACK, 1);


                        if (str.equals("Exit") || str.equals("Save") || str.equals("Hide") || str.equals("Run") || str.equals("Search") )
                        {
                            interface_x += 24;
                            interface_y += 75;
                            Image interfaceButton = interfaceButtonImage;

                            if (MouseInput.hover(MouseInput.mx, MouseInput.my, interface_x, interface_y, 184, 43))
                            {
                                interfaceButton = interfaceButtonImageHover;
                                AdventureRPG.buttonInterface = "Confirm";
                                hovering = true;
                            }

                            if (interfaceButtonImage != null)
                            {
                                drawStringExt(g, "Confirm " + str + ":", interface_x, interface_y, 184, 43, fnt0, Color.YELLOW, Color.BLACK, 1);
                                g.drawImage(interfaceButton, interface_x, interface_y, this);
                                drawStringExt(g, "Confirm", interface_x, interface_y, 184, 43, fntButton, Color.WHITE, Color.BLACK, 1);
                            }
                        }
                        else if (str.equals("Bag") && !AdventureRPG.bag.isEmpty())
                        {
                            int bag_x = interface_x + 36;
                            int bag_y = interface_y + 48;
                            int bag_w = 40;
                            int bag_h = 38;
                            int bag_slot = 1;


                            for (int itm = 0; itm < AdventureRPG.bag.size(); itm++)
                            {
                                Item i = AdventureRPG.bag.get(itm);
                                String iName = i.getName();

                                if (!hovering && MouseInput.hover(MouseInput.mx, MouseInput.my, bag_x, bag_y, bag_w, bag_h))
                                {
                                    AdventureRPG.buttonInterface = iName;
                                    hovering = true;
                                }

                                if (AdventureRPG.buttonInterface.equals(iName))
                                {
                                    g.drawImage(bagImage, bag_x, bag_y, this);
                                    drawStringExt(g, iName, bag_x, bag_y - 4, bag_w, bag_h, fnt1, Color.YELLOW, Color.BLACK, 1);
                                }

                                g.drawImage(i.getImage(), bag_x, bag_y, this);
                                drawStringExt(g, Integer.toString(i.getTotal()), bag_x, bag_y, bag_w, bag_h, fntBag, Color.YELLOW, Color.BLACK, 1);


                                bag_x += bag_w;
                                bag_slot++;
                                if (bag_slot > 4)
                                {
                                    bag_x -= (bag_w*4);
                                    bag_y += bag_h;
                                    bag_slot = 1;
                                }
                            }

                        }

                    }

                }

                if (!hovering && !AdventureRPG.buttonInterface.equals(""))
                {
                    AdventureRPG.buttonInterface = "";
                }
            }

            if (AdventureRPG.state == AdventureRPG.STATE.GAME)
            {
                String turn = "Turn: ";

                if (AdventureRPG.npcTurn)
                {
                    turn += "Enemy - " + AdventureRPG.enemyToAttack.getName();
                }
                else if (AdventureRPG.playerTurn)
                {
                    turn += "Player - " + AdventureRPG.playerToAttack.getName();
                }

                if (AdventureRPG.playerWin)
                {
                    turn = "Room Cleared!!!";
                    if (AdventureRPG.totalDamage != -1)
                    {
                        AdventureRPG.totalDamage = -1;
                        AdventureRPG.playerDamaged = null;
                        AdventureRPG.npcDamaged = null;
                    }
                }
                else if (AdventureRPG.npcWin)
                {
                    turn = "You lose...";
                }

                if (!turn.equals("Turn: "))
                {
                    drawStringExt(g, "[ROOM " + Integer.toString(AdventureRPG.roomCount) + "] " + turn, 20, 80, 0, 0, fntInfo, Color.YELLOW, Color.BLACK, 1);
                }

                if (!AdventureRPG.itemsFound.equals("") && AdventureRPG.notificationTimer > 0)
                {
                    drawStringExt(g, "Items Found: " + AdventureRPG.itemsFound, 20, 104, 0, 0, fntButton, Color.WHITE, Color.BLACK, 1);
                    AdventureRPG.notificationTimer--;

                    if (AdventureRPG.notificationTimer <= 0)
                    {
                        AdventureRPG.itemsFound = "";
                    }
                }

                if (AdventureRPG.playerTurn || AdventureRPG.playerWin)
                {
                    for (NPC n : AdventureRPG.npcObjects)
                    {
                        String action = "";
                        if (n.getName() != null)
                        {
                            if (n.getTargeted() && !AdventureRPG.playerWin)
                            {
                                action = "TARGETED: ";
                            }
                            else if (n.getHp() <= 0 && !n.getLooted() && AdventureRPG.button.equals(n.getName()))
                            {
                                action = "LOOT: ";
                            }
                        }
                        if (!action.equals(""))
                        {
                            drawStringExt(g, action + n.getName(), (int)n.getX(), (int)n.getY(), (int)Coordinates.BUTTON_NPC_W, (int)Coordinates.BUTTON_NPC_H, 
                                    fntNpc, Color.YELLOW, Color.BLACK, 1);  
                        }
                    }
                }

                int hpX = 45;
                int hpY = 23;
                int barX = 15;
                int barY = 25;
                int count = 1;
                for (Player p : AdventureRPG.playerObjects)
                {   
                    if (p.getName() != null)
                    {
                        if (AdventureRPG.playerTurn)
                        {
                            if (AdventureRPG.playerToAttack.getName().equals(p.getName()) && !AdventureRPG.attackType.equals(""))
                            {
                                drawStringExt(g, "ATTACK: " + AdventureRPG.attackType, (int)p.getX(), (int)p.getY(), (int)Coordinates.BUTTON_NPC_W, (int)Coordinates.BUTTON_NPC_H, 
                                        fntPlayer, Color.YELLOW, Color.BLACK, 1);                            
                            }
                        }
                        if (AdventureRPG.playerDamaged != null)
                        {
                            if (p.getName().equals(AdventureRPG.playerDamaged.getName()))
                            {
                                drawStringExt(g, Integer.toString(AdventureRPG.totalDamage) + " DMG", (int)p.getX() + 48, (int)p.getY() + ((int)Coordinates.BUTTON_NPC_H/2), 0, 0, 
                                            fntDmg, Color.RED, Color.BLACK, 1);                             
                            }
                        }                        
                                     
                        drawStringExt(g, "Player " + Integer.toString(count), hpX, hpY, 0, 0, 
                                    fnt0, Color.YELLOW, Color.BLACK, 1);

                        g.setColor(Color.black);
                        g.fillRect(barX, barY, 110, 25);

                        g.setColor(Color.red);
                        if (p.getHp() <= 20 && p.getHp() > 0)
                        {
                            g.fillRect(barX + 5, barY + 5, p.getHp() * 5, 15);
                        }
                        else if ( p.getHp() > 20)
                        {
                            g.fillRect(barX + 5, barY + 5, 100, 15);
                        }

                        drawStringExt(g, Integer.toString(p.getHp()) + " HP", hpX, hpY, 0, 25, 
                                    fntHp, Color.WHITE, Color.BLACK, 1);                    
                    }

                    count++;
                    hpX += 115;
                    barX += 115;
                }
                for (NPC n : AdventureRPG.npcObjects)
                {   
                    if (n.getName() != null && !n.getLooted())
                    {
                        int x = (int)n.getX() + (int)Coordinates.BUTTON_NPC_W;
                        int y = (int)n.getY() + 8;
                        int hp = n.getHp();
                        int maxHp = n.getMaxHp();
                        
                        g.setColor(Color.black);
                        g.fillRect(x, y, (maxHp * 5) + 6, 16);

                        g.setColor(Color.red);
                        if (hp <= maxHp && hp > 0)
                        {
                            g.fillRect(x + 3, y + 3, hp * 5, 10);
                        }

                        drawStringExt(g, Integer.toString(n.getHp()) + " HP", x + 8, y - 7, 0, 25, 
                                    fntHp, Color.WHITE, Color.BLACK, 1);   
                        
                        if (AdventureRPG.npcDamaged != null)
                        {
                            if (n.getName().equals(AdventureRPG.npcDamaged.getName()))
                            {
                                drawStringExt(g, Integer.toString(AdventureRPG.totalDamage) + " DMG", x - 48 - (int)Coordinates.BUTTON_NPC_W, y + ((int)Coordinates.BUTTON_NPC_H/2), 0, 0, 
                                            fntDmg, Color.RED, Color.BLACK, 1);                             
                            }
                        }
                        
                    }
                }
                
                
                if (AdventureRPG.sleepTimer > 0)
                {
                    AdventureRPG.sleepTimer--;
                    if (AdventureRPG.sleepTimer > 1500)
                    {
                        drawTranslucentImage(g, sleepImage, (3000-AdventureRPG.sleepTimer)/1500f);
                    }
                    else
                    {
                        drawTranslucentImage(g, sleepImage, AdventureRPG.sleepTimer/1500f);
                    }
                }

            }

            g.drawImage(borderImage, 0, 0, this);
        }
        
        if (AdventureRPG.endingTimer > 0)
        {
            if (AdventureRPG.endingTimer > 1500)
            {
                drawTranslucentImage(g, sleepImage, (3000-AdventureRPG.endingTimer)/1500f);
            }
            else
            {
                drawTranslucentImage(g, sleepImage, AdventureRPG.endingTimer/1500f);
            }
        }        
    }
    
    public void drawTranslucentImage(Graphics g, Image img, float alpha)
    {
        super.paintComponent(g);
        float f = alpha;

        Graphics2D g2d = (Graphics2D)g.create();
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, f));
        g2d.drawImage(img, 0, 0, null);

        g2d.dispose();
    }   
    
    public void drawStringExt(Graphics g, String str, int x, int y, int imageWidth, int imageHeight, Font fnt, Color mainColor, Color borderColor, int border) 
    {    
        if (AdventureRPG.button.equals(str) && !AdventureRPG.select.equals(str)
                || str != null && mainColor == Color.YELLOW
                || fnt == fntMainButton || fnt == fntButton || fnt == fntInfo || fnt == fntHp || fnt == fntDmg)
        {
            g.setFont(fnt);
            FontMetrics fm = g.getFontMetrics();
            
            int stringWidth = fm.stringWidth(str);
            int stringHeight = fm.getHeight();
            
            if (fnt != fntNpc && imageWidth != 0)
            {
                x -= (stringWidth/2) - (imageWidth/2);
            }
            
            if (str.equals(AdventureRPG.select) || str.equals("Confirm"))
            {
                y += stringHeight + 4;
            }
            else if (fnt == fntMainButton)
            {
                y += (imageHeight/2) + (stringHeight/2.5);
            }            
            else if (str.equals("North") || str.equals("South") 
                    || str.equals("West") || str.equals("East"))
            {
                y += imageHeight/2;
            }
            else if (fnt == fntBag)
            {
                y += imageHeight - (stringHeight/1.5);
                x += (imageWidth/2) - (stringWidth/1.5);
            }
            else if (fnt == fntPlayer)
            {
                x -= (stringWidth/2) + imageWidth;
                y += (imageHeight/1.5);               
            }              
            else if (fnt == fntNpc)
            {
                x += imageWidth;
                y += (imageHeight/1.5);               
            }            
            else if (fnt == fntHp)
            {
                y += (imageHeight/2) + (stringHeight/2);               
            } 
            
            if (border > 0)
            {
                g.setColor(borderColor);
                g.drawString(str, x-border, y-border);
                g.drawString(str, x-border, y+border);
                g.drawString(str, x+border, y-border);
                g.drawString(str, x+border, y+border);
            }
            
            if (fnt == fntDmg)
            {
                if (str.equals("0 DMG"))
                {
                    g.setColor(Color.WHITE);
                }
                else
                {
                    g.setColor(mainColor);
                }
            }             
            else
            {
                g.setColor(mainColor);
            }
            
            g.drawString(str, x, y);
        }
    }

    private void determineRoomToDraw(BufferedImageLoader loader) 
    {
        if (AdventureRPG.endingTimer > 0)
        {
            if (AdventureRPG.npcWin || AdventureRPG.victory)
            {
                AdventureRPG.endingTimer--;
            }
        }
        
        if (AdventureRPG.endingTimer <= 1500)
        {
            if (AdventureRPG.npcWin)
            {
                menuImage = loader.loadImage("/EndLose.png");
        
            }
            else
            {
                menuImage = loader.loadImage("/EndWin.png");
            }
            return;
        }
        
        switch (AdventureRPG.rooms[AdventureRPG.roomNumber]) 
            {
                case 1:
                    menuImage = loader.loadImage("/GhostRoom.png");
                    break;
                case 2:
                    menuImage = loader.loadImage("/littleWolfRoom.png");
                    break;
                case 3:
                    menuImage = loader.loadImage("/mageTower.png");
                    break;
                case 4:
                    menuImage = loader.loadImage("/Room1SCALED.png");
                    break;
                case 5:
                    menuImage = loader.loadImage("/WereWolfRoom.png");
                    break;
                case 6:
                    menuImage = loader.loadImage("/BossRoom.png");
                    break;
                default:
                    break;
            }
    }

}