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
public class AgilityStatComparator implements Comparator<Integer[]>
{

    @Override
    public int compare(Integer[] statCharacter1, Integer[] statCharacter2) 
    {
        if(statCharacter1[3] < statCharacter2[3])
        {
            return 1;
        }
        if(statCharacter1[3] > statCharacter2[3])
        {
            return -1;
        }
        else
        {
            return 0;
        }
    }
    
}
