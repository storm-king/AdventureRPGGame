
package adventurerpg;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.swing.JFrame;

/**
 *
 * @author storm
 */
public class AdventureRPG extends Canvas implements Runnable 
{
    public static final int WIDTH = 940;
    public static final int HEIGHT = 450;
    public static final int SCALE = 2;
    public static final String TITLE = "Dungeon Exploration";
    
    private boolean running = false;
    private Thread thread;
    
    public static Image menuImage = null;
    private BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
    private BufferedImage spriteSheetFighter = null;
    private BufferedImage spriteSheetWizard = null;
    private BufferedImage spriteSheetThief = null;
    
    private Player p;
    private Menu menu;
    private DrawMenuPanel drawGIFMain;
    private DrawMenuPanel drawGIFPlayerSelect;
    private DrawMenuPanel drawCharacterSelect;
    
    public static enum STATE{
        MENU,
        NUMBER_PLAYERS,
        CHARACTER_SELECT,
        GAME
    };
    
    public static STATE state = STATE.MENU;
    
    
    public void init()
    {
        BufferedImageLoader loader = new BufferedImageLoader();
        try{
            
            spriteSheetFighter = loader.loadBufferedImage("/Fighter.png");
            spriteSheetWizard = loader.loadBufferedImage("/Wizard.png");
            spriteSheetThief = loader.loadBufferedImage("/Thief.png");            
        }catch(IOException e)
        {
            e.printStackTrace();
        }
        
        p = new Player(940, 450, this);
        menu = new Menu();
        drawGIFMain = new DrawMenuPanel(0);
        drawGIFPlayerSelect = new DrawMenuPanel(1);
        drawCharacterSelect = new DrawMenuPanel(2);
        
        this.addMouseListener(new MouseInput());
   
    }
    
    private synchronized void start()
    {
        if(running)
        {
            return;
        }
        running = true;
        thread = new Thread(this);
        thread.start(); 
    }
    
    private synchronized void stop()
    {
        if(!running)
        {
            return;
        }
        running = false;
        thread = new Thread(this);
        try 
        {
            thread.join();
        } 
        catch (InterruptedException ex) 
        {
            ex.printStackTrace();
        }
        System.exit(1);
    }
    
    
    @Override
    public void run() 
    {
        init();
        long lastTime = System.nanoTime();
        final double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        int updates = 0;
        int frames = 0;
        long timer = System.currentTimeMillis();
        
        while(running)
        {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            if(delta >= 1)
            {
                tick();
                updates++;
                delta--;
            }
            render();
            frames++;
            
            if(System.currentTimeMillis() - timer > 1000)
            {
                timer += 1000;
                System.out.println(updates + "TICKS, " + frames + " FPS");
                updates = 0;
                frames = 0;
            }
        }
        stop();
    }
    
    private void tick()
    {
        if(state == STATE.GAME)
        {
            p.tick();
        }
    }
    
    private void render()
    {
        BufferStrategy bs = this.getBufferStrategy();
        if(bs == null)
        {
            createBufferStrategy(3);
            return;
        }
        
        Graphics g = bs.getDrawGraphics();
        
        
        
        if(state == STATE.GAME)
        {
            g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
            p.render(g);     
        }
        else if(state == STATE.MENU)
        {
            drawGIFMain.paintComponent(g);
            menu.render(g);
        }
        else if(state == STATE.NUMBER_PLAYERS)
        {
            drawGIFPlayerSelect.paintComponent(g);
        }
        else if(state == STATE.CHARACTER_SELECT)
        {
            drawCharacterSelect.paintComponent(g);
        }
        
        g.dispose();
        bs.show();
        
        
    }
    
    public static void main(String args[])
    {
        AdventureRPG game = new AdventureRPG();
        game.setPreferredSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
        game.setMaximumSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
        game.setMinimumSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
        
        
        JFrame frame = new JFrame(game.TITLE);
        frame.add(game);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        
        game.start();
    }
    
    
public BufferedImage[] getSpriteSheet()
{
    BufferedImage[] playerSpriteSheets = {spriteSheetFighter, spriteSheetWizard, spriteSheetThief};
    return playerSpriteSheets;
}
    


    
}
