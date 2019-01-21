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
public class AgilityStatComparatorForNPCs implements Comparator<NPC>
{

    @Override
    public int compare(NPC npc1, NPC npc2) 
    {
        if(npc1.getStats()[3] < npc2.getStats()[3])
        {
            return 1;
        }
        if(npc1.getStats()[3] > npc2.getStats()[3])
        {
            return -1;
        }
        else
        {
            return 0;
        }
           
    }
    
}
