/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adventurerpg;

/**
 *
 * @author Chuck
 */

public class Coordinates 
{
    public final static double SCALELOWRES = .68;
    
    public final static double BUTTON_P_W = 267 * SCALELOWRES;
    public final static double BUTTON_P_H = 50 * SCALELOWRES;
    
    public final static double BUTTON_1P_X = 536 * SCALELOWRES;
    public final static double BUTTON_1P_Y = 450 * SCALELOWRES;
    public final static double BUTTON_2P_X = 1121 * SCALELOWRES;
    public final static double BUTTON_2P_Y = BUTTON_1P_Y;
    public final static double BUTTON_3P_X = BUTTON_1P_X;
    public final static double BUTTON_3P_Y = 613 * SCALELOWRES;
    public final static double BUTTON_4P_X = BUTTON_2P_X;
    public final static double BUTTON_4P_Y = BUTTON_3P_Y;  
    
    public final static double BUTTON_LOAD_X = BUTTON_1P_X + ((BUTTON_2P_X - BUTTON_1P_X)/2.175);
    public final static double BUTTON_LOAD_Y = 776 * SCALELOWRES;    
    
    public final static double BUTTON_CONTINUE_X = 1408 * SCALELOWRES;
    public final static double BUTTON_CONTINUE_Y = 833 * SCALELOWRES;
    public final static double BUTTON_CONTINUE_W = 314 * SCALELOWRES;
    public final static double BUTTON_CONTINUE_H = 78 * SCALELOWRES;    
    
    public final static double BUTTON_SUBOPTION_W = 48;
    public final static double BUTTON_SUBOPTION_H = 48;    
    public final static double BUTTON_SUBOPTION_EXIT_X = 140 * SCALELOWRES;     
    public final static double BUTTON_SUBOPTION_SAVE_X = BUTTON_SUBOPTION_EXIT_X + BUTTON_SUBOPTION_W;
    public final static double BUTTON_SUBOPTION_Y = 812 * SCALELOWRES;     
    
    public final static double BUTTON_OPTION_W = 64;
    public final static double BUTTON_OPTION_H = 64;    
    
    public final static double BUTTON_OPTION_FIGHT_X = BUTTON_SUBOPTION_SAVE_X + (BUTTON_OPTION_W*1.25);
    public final static double BUTTON_OPTION_HIDE_X = BUTTON_OPTION_FIGHT_X + BUTTON_OPTION_W;   
    public final static double BUTTON_OPTION_BAG_X = BUTTON_OPTION_HIDE_X + BUTTON_OPTION_W;
    public final static double BUTTON_OPTION_SEARCH_X = BUTTON_OPTION_BAG_X + BUTTON_OPTION_W;
    public final static double BUTTON_OPTION_RUN_X = BUTTON_OPTION_SEARCH_X + BUTTON_OPTION_W;
    public final static double BUTTON_OPTION_Y = 804 * SCALELOWRES;
    
    
    public final static double BUTTON_NPC_W = 52;
    public final static double BUTTON_NPC_H = 60;     
    
    
    public final static double BUTTON_ROOM_BOSSCHEST_X = 1012;
    public final static double BUTTON_ROOM_BOSSCHEST_Y = 293;
    public final static double BUTTON_ROOM_BOSSCHEST_W = 68;
    public final static double BUTTON_ROOM_BOSSCHEST_H = 77;
    
    public final static double BUTTON_ROOM_BED_X = 1071;
    public final static double BUTTON_ROOM_BED_Y = 432;
    public final static double BUTTON_ROOM_BED_W = 165;
    public final static double BUTTON_ROOM_BED_H = 144;
    
    public final static double BUTTON_ROOM_CHEST1_X = 1061;
    public final static double BUTTON_ROOM_CHEST1_Y = 574;
    public final static double BUTTON_ROOM_CHEST1_W = 78;
    public final static double BUTTON_ROOM_CHEST1_H = 56;    
    public final static double BUTTON_ROOM_CHEST2_X = 649;
    public final static double BUTTON_ROOM_CHEST2_Y = 302;
    public final static double BUTTON_ROOM_CHEST2_W = 48;
    public final static double BUTTON_ROOM_CHEST2_H = 36; 
    
    public final static double BUTTON_ROOM_STAIRS_W = 100;
    public final static double BUTTON_ROOM_STAIRS_H = 100; 
    
    public final static double BUTTON_ROOM_NORTHSTAIRS_X = 631;
    public final static double BUTTON_ROOM_NORTHSTAIRS_Y = 0;    
    public final static double BUTTON_ROOM_SOUTHSTAIRS_X = BUTTON_ROOM_NORTHSTAIRS_X;
    public final static double BUTTON_ROOM_SOUTHSTAIRS_Y = 528;  
    public final static double BUTTON_ROOM_WESTSTAIRS_X = -5;
    public final static double BUTTON_ROOM_WESTSTAIRS_Y = 239;  
    public final static double BUTTON_ROOM_EASTSTAIRS_X = 1184;
    public final static double BUTTON_ROOM_EASTSTAIRS_Y = BUTTON_ROOM_WESTSTAIRS_Y;  
    
    public final static double BUTTON_SELECT_WIZARD_X = 637 * SCALELOWRES;
    public final static double BUTTON_SELECT_FIGHTER_X = 876 * SCALELOWRES;
    public final static double BUTTON_SELECT_THIEF_X = 1113 * SCALELOWRES;
    public final static double BUTTON_SELECT_Y = 330 * SCALELOWRES;
    public final static double BUTTON_SELECT_W = 116; 
    public final static double BUTTON_SELECT_H = 116;   
}
