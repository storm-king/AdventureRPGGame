/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adventurerpg;

import java.util.Comparator;

/**
 *
 * @author storm
 */
public class AgilityStatComparatorForPlayers implements Comparator<Player>
{

    @Override
    public int compare(Player player1, Player player2) 
    {
        if(player1.getStats()[3] < player2.getStats()[3])
        {
            return 1;
        }
        if(player1.getStats()[3] > player2.getStats()[3])
        {
            return -1;
        }
        else
        {
            return 0;
        }
    }
    
}
