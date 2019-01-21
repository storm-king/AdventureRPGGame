
package adventurerpg;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 *
 * @author storm
 */
public class MouseInput implements MouseListener
{   
    public int numberOfPlayers = 0;

    @Override
    public void mouseClicked(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mousePressed(MouseEvent e) 
    {
        int mx = e.getX();
        int my = e.getY();
        
        if(AdventureRPG.state == AdventureRPG.STATE.MENU)
        {
            if(mx >= 0 && mx <= 1880)
            {
                if(my >= 0 && my <= 900)
                {
                    AdventureRPG.state = AdventureRPG.STATE.NUMBER_PLAYERS;
                }
            }
        }
        else if(AdventureRPG.state == AdventureRPG.STATE.NUMBER_PLAYERS)
        {
            if(mx >= 536 && mx <= 803)
            {
                if(my >= 450 && my <= 500)
                {
                    numberOfPlayers = 1;
                }
                else if(my >= 613 && my <= 663)
                {
                    numberOfPlayers = 3;
                }
            }
            else if(mx >= 1121 && mx <= 1388)
            {
                if(my >= 450 && my <= 500)
                {
                    numberOfPlayers = 2;
                }
                else if(my >= 613 && my <= 663)
                {
                    numberOfPlayers = 4;
                }
            }
            
            if(numberOfPlayers != 0)
            {
                AdventureRPG.state = AdventureRPG.STATE.CHARACTER_SELECT;
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseExited(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
