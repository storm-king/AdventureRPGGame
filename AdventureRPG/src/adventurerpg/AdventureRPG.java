
package adventurerpg;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Objects;
import java.util.PriorityQueue;
import java.util.Random;
import javax.swing.Timer;
import javax.swing.JFrame;
import sun.audio.AudioData;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;
import sun.audio.ContinuousAudioDataStream;

/**
 *
 * @author storm
 */
public class AdventureRPG extends Canvas implements Runnable 
{
    public static final int WIDTH = 639;
    public static final int HEIGHT = 306;
    public static final int SCALE = 2;
    public static final double SCALELOWRES = 0.68;
    
    public static final String TITLE = "Conqueror";
    
    private boolean running = false;
    private Thread thread;
    
    public static Image menuImage = null;
    private BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
    private BufferedImage spriteSheetFighter = null;
    private BufferedImage spriteSheetWizard = null;
    private BufferedImage spriteSheetThief = null;
    private BufferedImage fighterCharacterSelect = null;
    private BufferedImage wizardCharacterSelect = null;
    private BufferedImage thiefCharacterSelect = null;
    private BufferedImage selectionBox = null;
    private BufferedImage thiefAbility = null;
    private BufferedImage fighterAbility = null;
    private BufferedImage wizardAbility = null;
    static BufferedImage playerSelectionCircle = null;
    
    public static Player p1;
    public static Player p2;
    public static Player p3;
    public static Player p4;
    
    public static NPC n1;
    public static NPC n2;
    public static NPC n3;
    public static NPC n4;
    public static NPC n5;
    public static NPC n6;
    
    
    private DrawMenuPanel drawGIFMain;
    private DrawMenuPanel drawGIFPlayerSelect;
    private DrawMenuPanel drawCharacterSelect;
    private DrawMenuPanel drawCharacterSelect2;
    private DrawMenuPanel drawCharacterSelect3;
    private DrawMenuPanel drawCharacterSelect4;
    private DrawMenuPanel drawGameScreen;
    
    static int characterSelected;
    static int numberOfPlayers = 0;
    static int numberOfNPCs = 0;
    static int[] characters = new int[4];
    static int[] npcs = new int[6];
    
    static PlayerStats getStatsPlayer1 = new PlayerStats(1);
    static PlayerStats getStatsPlayer2 = new PlayerStats(2);
    static PlayerStats getStatsPlayer3 = new PlayerStats(3);
    static PlayerStats getStatsPlayer4 = new PlayerStats(4);
    static Integer[] statsPlayer1 = new Integer[5];
    static Integer[] statsPlayer2 = new Integer[5];
    static Integer[] statsPlayer3 = new Integer[5];
    static Integer[] statsPlayer4 = new Integer[5];
    
    static NPCStats getStatsNPC1;
    static NPCStats getStatsNPC2;
    static NPCStats getStatsNPC3;
    static NPCStats getStatsNPC4;
    static NPCStats getStatsNPC5;
    static NPCStats getStatsNPC6;
    static Integer[] statsNPC1 = new Integer[5];
    static Integer[] statsNPC2 = new Integer[5];
    static Integer[] statsNPC3 = new Integer[5];
    static Integer[] statsNPC4 = new Integer[5];
    static Integer[] statsNPC5 = new Integer[5];
    static Integer[] statsNPC6 = new Integer[5];
    
    static Integer[] playerToHit = new Integer[5];
    
    static PriorityQueue<Integer[]> characterStats;
    static PriorityQueue<Integer[]> enemyStats;
    static PriorityQueue<Player> players;
    static PriorityQueue<NPC> enemies;
    static boolean[] playerAlive = new boolean[5];
    static boolean[] npcAlive = new boolean[7];
    
    
    
    static boolean roundOver = false;
    static boolean npcTurn = false;
    static boolean playerTurn = false;
    static int characterIdentifier = 0;
    static Integer[] characterAttackingStats;
    static Integer[] npcAttackingStats;
    static int damage = 0;
    static int playerAttacked;
    static Player playerToAttack = null;
    static NPC enemyToAttack = null;
    int delayTime;
    ActionListener listener = new TimeListener();
    Timer timer = new Timer(1000, listener);
    Timer musicTimer = new Timer(29000, listener);
    int[] healthBarLength;
    
    static boolean playerWin = false;
    static boolean npcWin = false;
    
    Projectiles projectile;
    boolean hit = true;
    public static boolean[] isHiding = new boolean[5];
    public static int[] rooms = new int[6];
    public static int roomNumber = 0;
    
    
  
    public static enum STATE{
        MENU,
        NUMBER_PLAYERS,
        CHARACTER_SELECT1,
        CHARACTER_SELECT2,
        CHARACTER_SELECT3,
        CHARACTER_SELECT4,
        PREGAME_INITIALIZATION_SCREEN,
        GAME,
        IN_GAME_INITIALIZATION_SCREEN
    };
    
    public static STATE state = STATE.MENU;
    public static String button = "";
    public static String buttonInterface = "";
    public static String select = "";
    public static ArrayList<Item> bag;
    public static String itemsFound = "";
    public static int notificationTimer = 0;
    public static int sleepTimer = 0;
    public static int endingTimer = 3000;
    public static int attackNumber = 0;
    public static String attackType = "";
    public static boolean runAway = false;
    public static boolean hideOptionSelected = false;
    public static boolean proceedToAnotherRoom = false;
    public static int roomCount = 1;
    public static boolean roomSleep = false;
    public static boolean roomChest1 = false;
    public static boolean roomChest2 = false;
    public static boolean victory = false;
    public static boolean drawBackground = true;
    
    public static ArrayList<NPC> npcObjects;
    public static ArrayList<Player> playerObjects;
    public static int totalDamage = 0;
    public static Player playerDamaged = null;
    public static NPC npcDamaged = null;
    
    
    public static Item itemTaken;
    
    
    public void init()
    {
        BufferedImageLoader loader = new BufferedImageLoader();
        loadAllImages(loader);
        this.addMouseListener(new MouseInput());
        this.addMouseMotionListener(new MouseInput());
        npcAlive[0] = false;
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
            populateScreenImages();
            
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
            tickAllCharacters();
        }
        if(Projectiles.projectileFired == true)
        {
            projectile.tick();
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
        
        if(state != null)
        switch (state) 
        {
            case MENU:
            {
                //startOpeningMusic();
                drawBackground = true;
                drawGIFMain.paintComponent(g);
                drawBackground = false;
                drawGIFMain.paintComponent(g);                
                break;
            }
            case NUMBER_PLAYERS:
            {
                drawBackground = true;
                drawGIFPlayerSelect.paintComponent(g);
                drawBackground = false;
                drawGIFPlayerSelect.paintComponent(g);
                break;
            }
            case CHARACTER_SELECT1:
            {
                drawBackground = true;
                drawCharacterSelect.paintComponent(g);
                drawBackground = false;
                drawCharacterSelect.paintComponent(g);
                getStatsPlayer1.paintComponent(g);
                if(characterSelected != 0 )
                {
                    statsPlayer1 = decisionTreeCharacterSelect(getStatsPlayer1, g);
                }   
                break;
            }
            case CHARACTER_SELECT2:
            {
                drawBackground = true;
                drawCharacterSelect2.paintComponent(g);
                drawBackground = false;
                drawCharacterSelect2.paintComponent(g);
                getStatsPlayer2.paintComponent(g);
                if(characterSelected != 0)
                {
                    statsPlayer2 = decisionTreeCharacterSelect(getStatsPlayer2, g);
                }   
                break;
            }
            case CHARACTER_SELECT3:
            {
                drawBackground = true;
                drawCharacterSelect3.paintComponent(g);
                drawBackground = false;
                drawCharacterSelect3.paintComponent(g);
                getStatsPlayer3.paintComponent(g);
                if(characterSelected != 0)
                {
                    statsPlayer3 = decisionTreeCharacterSelect(getStatsPlayer3, g);
                }   
                break;
            }
            case CHARACTER_SELECT4:
            {
                drawBackground = true;
                drawCharacterSelect4.paintComponent(g);
                drawBackground = false;
                drawCharacterSelect4.paintComponent(g);
                getStatsPlayer4.paintComponent(g);
                if(characterSelected != 0)
                {
                    statsPlayer4 = decisionTreeCharacterSelect(getStatsPlayer4, g);
                }   
                break;
            }
            case PREGAME_INITIALIZATION_SCREEN:
            {
                createPlayers();
                getStatsPlayer1.updateStats(characters[0]);
                if(characters[1] != 0)
                {
                    getStatsPlayer2.updateStats(characters[1]);
                }
                if(characters[2] != 0)
                {
                    getStatsPlayer3.updateStats(characters[2]);
                }
                if(characters[3] != 0)
                {
                    getStatsPlayer4.updateStats(characters[3]);
                }
                retrieveNPCStats();
                createNPCs(); 
                addStatsToPriorityQueues();
                printStats();
                addStatsToPriorityQueues();
                state = STATE.GAME;
                break;
            }
            case GAME:
            {
                drawBackground = true;
                drawGameScreen.paintComponent(g);
                if(Projectiles.projectileFired)
                {
                    projectile.render(g);
                    renderAllCharacters(g);
                    createHealthBar(g);
                    hit = true;
                }
                else
                {
                    createHealthBar(g);
                    if(runAway && roomNumber > 0 && !playerWin)
                    {
                        if(!characterStats.isEmpty())
                        {
                            characterStats.clear();
                        }
                        playerTurn = false;
                        determineOrderOfCharacterTurn(g);
                        if(enemyStats.isEmpty() && hit)
                        {
                            roomNumber--;
                            roomCount--;
                            Projectiles.projectileFired = false;
                            state = STATE.IN_GAME_INITIALIZATION_SCREEN;
                            break;
                        }
                    }
                    if(playerWin)
                    {
                        renderAllCharacters(g);
                        if(proceedToAnotherRoom)
                        {
                            for (Player p : playerObjects)
                            {
                                p.setSearched(false);
                            }
                            roomSleep = false;
                            roomChest1 = false;
                            roomChest2 = false;
                            roomCount++;
                            itemsFound = "";
                            state = STATE.IN_GAME_INITIALIZATION_SCREEN;
                        }
                        if(roomSleep)
                        {
                            Random rand = new Random();
                            int chanceOfNPCReenter = rand.nextInt(6);
                            if(chanceOfNPCReenter == 1)
                            {
                                clearNPCStats();
                                retrieveNPCStats();
                                createNPCs();
                                addStatsToPriorityQueues();
                            }
                            roomSleep = false;
                        }
                    }
                    else
                    {
                        if(!npcWin && !playerWin)
                        {
                            determineOrderOfCharacterTurn(g);
                        }
                        renderAllCharacters(g);
                        if(characterStats.isEmpty() && enemyStats.isEmpty() && Player.readyToFight && !runAway)
                        {
                            addStatsToPriorityQueues();
                            if( !characterStats.isEmpty() && !enemyStats.isEmpty() )
                            {
                                roundOver = false;
                            }
                            else if(characterStats.isEmpty())
                            {
                                npcWin = true;
                            }
                            else if(enemyStats.isEmpty())
                            {
                                playerWin = true;
                                stopHidingForAllPlayers();
                            }

                        }
                        
                    }
                }
                drawBackground = false;
                drawGameScreen.paintComponent(g);
                break;
            }
            case IN_GAME_INITIALIZATION_SCREEN:
            {
                playerWin = false;
                proceedToAnotherRoom = false;
                while(!characterStats.isEmpty())
                {
                    characterStats.remove();
                }
                while(!players.isEmpty())
                {
                    players.remove();
                }
                resetPlayerPositions();
                resetAnimations();
                Player.readyToFight = false;
                
                if(!runAway)
                {
                    clearNPCStats();
                    retrieveNPCStats();
                    createNPCs(); 
                    addStatsToPriorityQueues();
                    roomNumber++;
                }
                else
                {
                    runAway = false;
                    clearNPCStats();
                    enemyStats.clear();
                    enemies.clear();
                    players.clear();
                }
 
                state = STATE.GAME;
                
                break;
            }
            default:
            {
                break;
            }
            
        }

        
        g.dispose();
        bs.show();    
    }
    
    public static void main(String args[])
    {
        Comparator<Integer[]> comparatorStats = new AgilityStatComparator();
        Comparator<Player> comparatorPlayer = new AgilityStatComparatorForPlayers();
        Comparator<NPC> comparatorNPC = new AgilityStatComparatorForNPCs();
        characterStats = new PriorityQueue<>(4, comparatorStats);
        enemyStats = new PriorityQueue<>(6, comparatorStats);
        players = new PriorityQueue<>(4, comparatorPlayer);
        enemies = new PriorityQueue<>(6, comparatorNPC);
        bag = new ArrayList();
        npcObjects = new ArrayList<>();
        playerObjects = new ArrayList<>();
        
        determineRoomSequence();
        
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
    
    
    private void printStats()
    {
        System.out.println("Player 1 STATS: ");
            for(int stat : statsPlayer1)
            {
                System.out.print(stat + " ");
            }
            System.out.println();
            players.remove();
            
            if(statsPlayer2[0] != null)
            {
                System.out.println("Player 2 STATS: ");
                for(int stat : statsPlayer2)
                {
                    System.out.print(stat + " ");
                }
                System.out.println();
                players.remove();
                
            }
            
            if(statsPlayer3[0] != null)
            {
                System.out.println("Player 3 STATS: ");
                for(int stat : statsPlayer3)
                {
                    System.out.print(stat + " ");
                }
                System.out.println();
                players.remove();
            }
            
            if(statsPlayer4[0] != null)
            {
                System.out.println("Player 4 STATS: ");
                for(int stat : statsPlayer4)
                {
                    System.out.print(stat + " ");
                }
                System.out.println();
                players.remove();
            }
            
            System.out.println("NPC 1 STATS: ");
            for(int stat : statsNPC1)
            {
                System.out.print(stat + " ");
            }
            System.out.println();
            
            System.out.println("NPC 2 STATS: ");
            for(int stat : statsNPC2)
            {
                System.out.print(stat + " ");
            }
            System.out.println();
            System.out.println("NPC 3 STATS: ");
            for(int stat : statsNPC3)
            {
                System.out.print(stat + " ");
            }
            System.out.println();
            System.out.println("NPC 4 STATS: ");
            for(int stat : statsNPC4)
            {
                System.out.print(stat + " ");
            }
            System.out.println();
            System.out.println("NPC 5 STATS: ");
            for(int stat : statsNPC5)
            {
                System.out.print(stat + " ");
            }
            System.out.println();
            System.out.println("NPC 6 STATS: ");
            for(int stat : statsNPC6)
            {
                System.out.print(stat + " ");
            }
            System.out.println();
            
            System.out.println("Order of Players: ");
            while (characterStats.size() != 0)
            {
                for(Integer stat : characterStats.remove())
                {
                    System.out.print(stat + " ");
                }
                System.out.println();
            }
                
            System.out.println("Order of Enemies: ");
            while (enemyStats.size() != 0)
            {
                for(Integer stat : enemyStats.remove())
                {
                    System.out.print(stat + " ");
                }
                System.out.println();
            }
            while(enemies.size() != 0)
            {
                enemies.remove();
            }
    }
    

    private void retrieveNPCStats()
    {
        Random rand = new Random();
        npcs = new int[6];
        numberOfNPCs = rand.nextInt(6) + 1;
        for (int npcIndex = 0; npcIndex < numberOfNPCs; npcIndex++) 
        {
            npcs[npcIndex] = rand.nextInt(6) + 1;
        }
    
        getStatsNPC1 = new NPCStats(npcs[0], 1);
        getStatsNPC2 = new NPCStats(npcs[1], 2);
        getStatsNPC3 = new NPCStats(npcs[2], 3);
        getStatsNPC4 = new NPCStats(npcs[3], 4);
        getStatsNPC5 = new NPCStats(npcs[4], 5);
        getStatsNPC6 = new NPCStats(npcs[5], 6);
            
        statsNPC1 = getStatsNPC1.baseStats();
        statsNPC2 = getStatsNPC2.baseStats();
        statsNPC3 = getStatsNPC3.baseStats();
        statsNPC4 = getStatsNPC4.baseStats();
        statsNPC5 = getStatsNPC5.baseStats();
        statsNPC6 = getStatsNPC6.baseStats();
    }
    
    private void createNPCs()
    {
        n1 = new NPC(1065, 290, npcs[0], statsNPC1, this, " (e1)");
        //Sprite.spriteSheet = null;
        n2 = new NPC(1065, 410, npcs[1], statsNPC2, this, " (e2)");
        //Sprite.spriteSheet = null;
        n3 = new NPC(1065, 114, npcs[2], statsNPC3, this, " (e3)");
        //Sprite.spriteSheet = null;
        n4 = new NPC(1065, 230, npcs[3], statsNPC4, this, " (e4)");
        //Sprite.spriteSheet = null;
        n5 = new NPC(1065, 168, npcs[4], statsNPC5, this, " (e5)");
        //Sprite.spriteSheet = null;
        n6 = new NPC(1065, 350, npcs[5], statsNPC6, this, " (e6)");
        //Sprite.spriteSheet = null;
        
        npcObjects.add(n1);
        npcObjects.add(n2);
        npcObjects.add(n3);
        npcObjects.add(n4);
        npcObjects.add(n5);
        npcObjects.add(n6);
        
    }
    
    private void createPlayers()
    {
        p1 = new Player((int)(125 * SCALELOWRES), (int)(300 * SCALELOWRES), characters[0], statsPlayer1, this, " (p1)");
        //Sprite.spriteSheet = null;
        p2 = new Player((int)(125 * SCALELOWRES), (int)(400 * SCALELOWRES), characters[1], statsPlayer2, this, " (p2)");
        //Sprite.spriteSheet = null;
        p3 = new Player((int)(125 * SCALELOWRES), (int)(500 * SCALELOWRES), characters[2], statsPlayer3, this, " (p3)");
        //Sprite.spriteSheet = null;
        p4 = new Player((int)(125 * SCALELOWRES), (int)(600 * SCALELOWRES), characters[3], statsPlayer4, this, " (p4)");
        //Sprite.spriteSheet = null;
        
        playerObjects.add(p1);
        playerObjects.add(p2);
        playerObjects.add(p3);
        playerObjects.add(p4);
    }

    private void drawWizardSelect(Graphics g)
    {
        g.drawImage(wizardCharacterSelect, (int)(840 * SCALELOWRES), (int)(565 * SCALELOWRES), this);
        g.drawImage(selectionBox, (int)Coordinates.BUTTON_SELECT_WIZARD_X, (int)Coordinates.BUTTON_SELECT_Y, this); 
        g.drawImage(wizardAbility, (int)(1332 * SCALELOWRES), (int)(203* SCALELOWRES), this);
    }
    
    private void drawFighterSelect(Graphics g)
    {
        g.drawImage(fighterCharacterSelect, (int)(840 * SCALELOWRES), (int)(565 * SCALELOWRES), this);
        g.drawImage(selectionBox, (int)Coordinates.BUTTON_SELECT_FIGHTER_X, (int)Coordinates.BUTTON_SELECT_Y, this);
        g.drawImage(fighterAbility, (int)(1332 * SCALELOWRES), (int)(203* SCALELOWRES), this);
    }
    
    private void drawThiefSelect(Graphics g)
    {
        g.drawImage(thiefCharacterSelect, (int)(840 * SCALELOWRES), (int)(565 * SCALELOWRES), this);
        g.drawImage(selectionBox, (int)Coordinates.BUTTON_SELECT_THIEF_X, (int)Coordinates.BUTTON_SELECT_Y, this);
        g.drawImage(thiefAbility, (int)(1332 * SCALELOWRES), (int)(203* SCALELOWRES), this);
    }
    
    private void drawSelectionCircle(Player p, Graphics g)
    {
        g.drawImage(playerSelectionCircle, (int)p.getX() - 10, (int)p.getY() + 35, AdventureRPG.this);
        
    }
    
    private void drawSelectionCircle(NPC n, Graphics g)
    {
        g.drawImage(playerSelectionCircle, (int)n.getX() - 10, (int)n.getY() + 35, this);
    }
    
    private Integer[] decisionTreeCharacterSelect(PlayerStats currentPlayer, Graphics g)
    {
        Integer[] charactersStats = new Integer[4];
        if(characterSelected == 1)
        {
            drawWizardSelect(g);
            charactersStats = currentPlayer.wizardStats();
        }
        else if(characterSelected == 2)
        {
            drawFighterSelect(g);  
            charactersStats = currentPlayer.fighterStats();   
        }
        else if(characterSelected == 3)
        {
            drawThiefSelect(g);   
            charactersStats = currentPlayer.thiefStats(); 
        }  
        return charactersStats;
    }
    
    private void renderAllCharacters(Graphics g)
    {
        if (endingTimer > 1500)
        {
            p1.render(g);
            p2.render(g);
            p3.render(g);
            p4.render(g);
            if(n1 != null)
            {
                n1.render(g);
            }
            if(n2 != null)
            {
                n2.render(g);
            }
            if(n3 != null)
            {
                n3.render(g);
            }
            if(n4 != null)
            {
                n4.render(g);
            }
            if(n5 != null)
            {
                n5.render(g);
            }
            if(n6 != null)
            {
                n6.render(g); 
            }
        }
        
//        if(Projectiles.projectileFired && !Projectiles.projectileHit)
//        {
//            projectile.render(g);
//        }
    }
    
    private void tickAllCharacters()
    {
       p1.tick();
       p2.tick();
       p3.tick();
       p4.tick();
       if(n1 != null)
       {
            n1.tick();
       }
       if(n2 != null)
       {
            n2.tick();
       }
       if(n3 != null)
       {
            n3.tick();
       }
       if(n4 != null)
       {
            n4.tick();
       }
       if(n5 != null)
       {
            n5.tick();
       }
       if(n6 != null)
       {
            n6.tick();
       }
       
//       if(Projectiles.projectileFired && !Projectiles.projectileHit)
//        {
//            projectile.tick();
//        }
    }
    
    private void loadAllImages(BufferedImageLoader loader)
    {
        try
        {
            spriteSheetFighter = loader.loadBufferedImage("/Fighter.png");
            spriteSheetWizard = loader.loadBufferedImage("/Wizard.png");
            spriteSheetThief = loader.loadBufferedImage("/Thief.png");  
            fighterCharacterSelect = loader.loadBufferedImage("/FighterSelectSCALED.png");
            wizardCharacterSelect = loader.loadBufferedImage("/WizardSelectSCALED.png");
            thiefCharacterSelect = loader.loadBufferedImage("/ThiefSelectSCALED.png");
            selectionBox = loader.loadBufferedImage("/selection_character.png");
            thiefAbility = loader.loadBufferedImage("/ThiefAbilitySCALED.png");
            fighterAbility = loader.loadBufferedImage("/FighterAbilitySCALED.png");
            wizardAbility = loader.loadBufferedImage("/WizardAbilitySCALED.png");
            playerSelectionCircle = loader.loadBufferedImage("/CharacterTurnCircle.png");
        }catch(IOException e)
        {
            e.printStackTrace();
        }
        drawGIFMain = new DrawMenuPanel(0);
        drawGIFPlayerSelect = new DrawMenuPanel(1);
        drawCharacterSelect = new DrawMenuPanel(2);
        drawCharacterSelect2 = new DrawMenuPanel(3);
        drawCharacterSelect3 = new DrawMenuPanel(4);
        drawCharacterSelect4 = new DrawMenuPanel(5);
        drawGameScreen = new DrawMenuPanel(6);
    }
    
    private void populateScreenImages()
    {
        if(AdventureRPG.state != null)
        switch (AdventureRPG.state) {
            case NUMBER_PLAYERS:
                drawGIFPlayerSelect = new DrawMenuPanel(1);
                break;
            case CHARACTER_SELECT1:
                drawCharacterSelect = new DrawMenuPanel(2);
                break;
            case CHARACTER_SELECT2:
                drawCharacterSelect2 = new DrawMenuPanel(3);
                break;
            case CHARACTER_SELECT3:
                drawCharacterSelect3 = new DrawMenuPanel(4);
                break;
            case CHARACTER_SELECT4:
                drawCharacterSelect4 = new DrawMenuPanel(5);
                break;
            case GAME:
                drawGameScreen = new DrawMenuPanel(6);
                break;
            default:
                break;
        }
    }
    
    private void addStatsToPriorityQueues()
    {
        if(statsPlayer1[0] != null)
        {
            if(statsPlayer1[0] > 0)
            {
                characterStats.add(statsPlayer1);
                players.add(p1);
            }
            else
            {
                p1.changeAnimationHiding(false);
            }
        }
        if(statsPlayer2[0] != null)
        {
            if(statsPlayer2[0] > 0)
            {
                characterStats.add(statsPlayer2);
                players.add(p2);
            }
            else
            {
                p2.changeAnimationHiding(false);
            }
        }
        if(statsPlayer3[0] != null)
        {
            if(statsPlayer3[0] > 0)
            {
                characterStats.add(statsPlayer3);
                players.add(p3);
            }
            else
            {
                p3.changeAnimationHiding(false);
            }
        }
        if(statsPlayer4[0] != null)
        {
            if(statsPlayer4[0] > 0)
            {
                characterStats.add(statsPlayer4);
                players.add(p4);
            }
            else
            {
                p4.changeAnimationHiding(false);
            }
        }
        
        if(statsNPC1 != null && statsNPC1[0] > 0)
        {
            enemyStats.add(n1.getStats());
            enemies.add(n1);
            npcAlive[1] = true;
        }
        if(statsNPC2 != null && statsNPC2[0] > 0)
        {
            enemyStats.add(n2.getStats());
            enemies.add(n2);
            npcAlive[2] = true;
        }
        if(statsNPC3 != null && statsNPC3[0] > 0)
        {
            enemyStats.add(n3.getStats());
            enemies.add(n3);
            npcAlive[3] = true;
        }
        if(statsNPC4 != null && statsNPC4[0] > 0)
        {
            enemyStats.add(n4.getStats());
            enemies.add(n4);
            npcAlive[4] = true;
        }
        if(statsNPC5 != null && statsNPC5[0] > 0)
        {
            enemyStats.add(n5.getStats());
            enemies.add(n5);
            npcAlive[5] = true;
        }
        if(statsNPC6 != null && statsNPC6[0] > 0)
        {
            enemyStats.add(n6.getStats());
            enemies.add(n6);
            npcAlive[6] = true;
        }
    }
    
    private void determineOrderOfCharacterTurn(Graphics g)
    {
        if (Player.readyToFight) 
        {
            
            while(!roundOver && !playerTurn && !npcTurn) 
            {
                Integer[] tempCharacter = characterStats.peek();
                Integer[] tempEnemy = enemyStats.peek();
                
                if (tempCharacter == null && tempEnemy != null) 
                {
                    npcTurn = true;
                    playerTurn = false;
                    npcAttackingStats = enemyStats.remove();
                    characterIdentifier = npcAttackingStats[4];
                    enemyToAttack = enemies.remove();
                    timer.restart();
                    timer.start();
                } 
                else if (tempEnemy == null && tempCharacter != null) 
                {
                    playerTurn = true;
                    npcTurn = false;
                    characterAttackingStats = characterStats.remove();
                    characterIdentifier = characterAttackingStats[4];
                    playerToAttack = players.remove();
                } 
                else if (tempEnemy == null && tempCharacter == null) 
                {
                    playerTurn = false;
                    npcTurn = false;
                    characterIdentifier = 0;
                    characterAttackingStats = null;
                    roundOver = true;
                    enemyToAttack = null;
                    playerToAttack = null;
                } 
                else if (tempCharacter[3] > tempEnemy[3] || (int) tempCharacter[3] == (int) tempEnemy[3]) 
                {
                    playerTurn = true;
                    npcTurn = false;
                    characterAttackingStats = characterStats.remove();
                    characterIdentifier = characterAttackingStats[4];
                    playerToAttack = players.remove();
                } 
                else if (tempCharacter[3] < tempEnemy[3])
                {
                    npcTurn = true;
                    playerTurn = false;
                    npcAttackingStats = enemyStats.remove();
                    characterIdentifier = npcAttackingStats[4];
                    enemyToAttack = enemies.remove();
                    timer.restart();
                    timer.start();
                }
            }
            if(playerTurn && determineIfSpecificCharacterIsAlive(playerToAttack.playerStats))
            {
                boolean npcsAvailable = false;
                for(int npcIndex = 0; npcIndex < npcAlive.length; npcIndex++)
                {
                    if(npcAlive[npcIndex] == true)
                    {
                        npcsAvailable = true;
                        break;
                    }
                }
                if(npcsAvailable)
                {
                    drawSelectionCircle(playerToAttack, g);
                    if(isHiding[characterIdentifier])
                    {
                        playerToAttack.changeAnimationHiding(false);
                        isHiding[characterIdentifier] = false;
                    }
                    if(itemTaken != null)
                    {
                        if(itemTaken.isWeapon())
                        {
                            determineStatsOfPlayerTurn(characterIdentifier).setWeapon(itemTaken.convertItemToWeapon());
                            itemTaken = null;
                        }
                        else if(itemTaken.isArmor())
                        {
                            determineStatsOfPlayerTurn(characterIdentifier).setArmor(itemTaken.convertItemToArmor());
                            itemTaken = null;
                        }
                         else if(itemTaken.isPotion())
                        {
                            int hpToRestore = itemTaken.usePotion();
                            if((characterAttackingStats[0] + hpToRestore) < 20)
                            {
                                characterAttackingStats[0] += hpToRestore;
                            }
                            else if(characterAttackingStats[0] < 20)
                            {
                                int hpToDeduct = (characterAttackingStats[0] + hpToRestore) - 20;
                                characterAttackingStats[0] = characterAttackingStats[0] + hpToRestore - hpToDeduct;
                            }
                            
                            itemTaken = null;
                        }
                        else
                        {
                            itemTaken = null;
                        }
                    }
                    else if(hideOptionSelected)
                    {
                        isHiding[characterIdentifier] = true;
                        playerToAttack.changeAnimationHiding(true);

                        hideOptionSelected = false;
                        playerTurn = false;
                    }
                    else if (attackType.equals("Normal"))
                    {
                        int typeOfPlayer = playerToAttack.getPlayerType();
                        switch (attackNumber) {
                            case 1:
                                playerAttack(characterIdentifier, n1, getStatsNPC1);
                                playerTurn = false;
                                projectile = new Projectiles(typeOfPlayer, playerToAttack.getX(), playerToAttack.getY(), n1.getX(), n1.getY(), "right");
                                Projectiles.projectileFired = true;
                                hit = false;
                                if(!determineIfSpecificCharacterIsAlive(statsNPC1))
                                {
                                    n1.changeAnimationDead(true);
                                    n1.changeAnimationStunned(false);
                                    npcAlive[1] = false;
                                }
                                else
                                {
                                    npcAlive[1] = true;
                                }
                                attackNumber = 0;
                                attackType = "";
                                n1.setTargeted(false);
                                break;
                            case 2:
                                playerAttack(characterIdentifier, n2, getStatsNPC2);
                                playerTurn = false;
                                projectile = new Projectiles(typeOfPlayer, playerToAttack.getX(), playerToAttack.getY(), n2.getX(), n2.getY(), "right");
                                Projectiles.projectileFired = true;
                                hit = false;
                                if(!determineIfSpecificCharacterIsAlive(statsNPC2))
                                {
                                    n2.changeAnimationDead(true);
                                    n2.changeAnimationStunned(false);
                                    npcAlive[2] = false;
                                }
                                else
                                {
                                    npcAlive[2] = true;
                                }
                                attackNumber = 0;
                                attackType = "";
                                n2.setTargeted(false);
                                break;
                            case 3:
                                playerAttack(characterIdentifier, n3, getStatsNPC3);
                                playerTurn = false;
                                projectile = new Projectiles(typeOfPlayer, playerToAttack.getX(), playerToAttack.getY(), n3.getX(), n3.getY(), "right");
                                Projectiles.projectileFired = true;
                                hit = false;
                                if(!determineIfSpecificCharacterIsAlive(statsNPC3))
                                {
                                    n3.changeAnimationDead(true);
                                    n3.changeAnimationStunned(false);
                                    npcAlive[3] = false;
                                }
                                else
                                {
                                    npcAlive[3] = true;
                                }
                                attackNumber = 0;
                                attackType = "";
                                n3.setTargeted(false);
                                break;
                            case 4:
                                playerAttack(characterIdentifier, n4, getStatsNPC4);
                                playerTurn = false;
                                projectile = new Projectiles(typeOfPlayer, playerToAttack.getX(), playerToAttack.getY(), n4.getX(), n4.getY(), "right");
                                Projectiles.projectileFired = true;
                                hit = false;
                                if(!determineIfSpecificCharacterIsAlive(statsNPC4))
                                {
                                    n4.changeAnimationDead(true);
                                    n4.changeAnimationStunned(false);
                                    npcAlive[4] = false;
                                }
                                else
                                {
                                    npcAlive[4] = true;
                                }
                                attackNumber = 0;
                                attackType = "";
                                n4.setTargeted(false);
                                break;
                            case 5:
                                playerAttack(characterIdentifier, n5, getStatsNPC5);
                                playerTurn = false;
                                projectile = new Projectiles(typeOfPlayer, playerToAttack.getX(), playerToAttack.getY(), n5.getX(), n5.getY(), "right");
                                Projectiles.projectileFired = true;
                                hit = false;
                                if(!determineIfSpecificCharacterIsAlive(statsNPC5))
                                {
                                    n5.changeAnimationDead(true);
                                    n5.changeAnimationStunned(false);
                                    npcAlive[5] = false;
                                }
                                else
                                {
                                    npcAlive[5] = true;
                                }
                                attackNumber = 0;
                                attackType = "";
                                n5.setTargeted(false);
                                break;
                            case 6:
                                playerAttack(characterIdentifier, n6, getStatsNPC6);
                                playerTurn = false;
                                projectile = new Projectiles(typeOfPlayer, playerToAttack.getX(), playerToAttack.getY(), n6.getX(), n6.getY(), "right");
                                Projectiles.projectileFired = true;
                                hit = false;
                                if(!determineIfSpecificCharacterIsAlive(statsNPC6))
                                {
                                    n6.changeAnimationDead(true);
                                    n6.changeAnimationStunned(false);
                                    npcAlive[6] = false;
                                }
                                else
                                {
                                    npcAlive[6] = true;
                                }
                                attackNumber = 0;
                                attackType = "";
                                n6.setTargeted(false);
                                break;
                            default:
                                break;
                        }
                    }
                    else if(attackType.equals("Special"))
                    {
                        int typeOfPlayer = playerToAttack.getPlayerType();
                        switch (attackNumber) 
                        {
                            case 1:
                                playerSpecialAttack(characterIdentifier, n1, getStatsNPC1);
                                if(typeOfPlayer == 2)
                                {
                                    n1.changeAnimationStunned(true);
                                }
                                playerTurn = false;
                                projectile = new Projectiles(4, playerToAttack.getX(), playerToAttack.getY(), n1.getX(), n1.getY(), "right");
                                Projectiles.projectileFired = true;
                                hit = false;
                                if(!determineIfSpecificCharacterIsAlive(statsNPC1))
                                {
                                    n1.changeAnimationDead(true);
                                    n1.changeAnimationStunned(false);
                                    npcAlive[1] = false;
                                }
                                else
                                {
                                    npcAlive[1] = true;
                                }
                                attackNumber = 0;
                                attackType = "";
                                n1.setTargeted(false);
                                break;
                            case 2:
                                playerSpecialAttack(characterIdentifier, n2, getStatsNPC2);
                                if(typeOfPlayer == 2)
                                {
                                    n2.changeAnimationStunned(true);
                                }
                                playerTurn = false;
                                projectile = new Projectiles(4, playerToAttack.getX(), playerToAttack.getY(), n2.getX(), n2.getY(), "right");
                                Projectiles.projectileFired = true;
                                hit = false;
                                if(!determineIfSpecificCharacterIsAlive(statsNPC2))
                                {
                                    n2.changeAnimationDead(true);
                                    n2.changeAnimationStunned(false);
                                    npcAlive[2] = false;
                                }
                                else
                                {
                                    npcAlive[2] = true;
                                }
                                attackNumber = 0;
                                attackType = "";
                                n2.setTargeted(false);
                                break;
                            case 3:
                                playerSpecialAttack(characterIdentifier, n3, getStatsNPC3);
                                if(typeOfPlayer == 2)
                                {
                                    n3.changeAnimationStunned(true);
                                }
                                playerTurn = false;
                                projectile = new Projectiles(4, playerToAttack.getX(), playerToAttack.getY(), n3.getX(), n3.getY(), "right");
                                Projectiles.projectileFired = true;
                                hit = false;
                                if(!determineIfSpecificCharacterIsAlive(statsNPC3))
                                {
                                    n3.changeAnimationDead(true);
                                    n3.changeAnimationStunned(false);
                                    npcAlive[3] = false;
                                }
                                else
                                {
                                    npcAlive[3] = true;
                                }
                                attackNumber = 0;
                                attackType = "";
                                n3.setTargeted(false);
                                break;
                            case 4:
                                playerSpecialAttack(characterIdentifier, n4, getStatsNPC4);
                                if(typeOfPlayer == 2)
                                {
                                    n4.changeAnimationStunned(true);
                                }
                                playerTurn = false;
                                projectile = new Projectiles(4, playerToAttack.getX(), playerToAttack.getY(), n4.getX(), n4.getY(), "right");
                                Projectiles.projectileFired = true;
                                hit = false;
                                if(!determineIfSpecificCharacterIsAlive(statsNPC4))
                                {
                                    n4.changeAnimationDead(true);
                                    n4.changeAnimationStunned(false);
                                    npcAlive[4] = false;
                                }
                                else
                                {
                                    npcAlive[4] = true;
                                }
                                attackNumber = 0;
                                attackType = "";
                                n4.setTargeted(false);
                                break;
                            case 5:
                                playerSpecialAttack(characterIdentifier, n5, getStatsNPC5);
                                if(typeOfPlayer == 2)
                                {
                                    n5.changeAnimationStunned(true);
                                }
                                playerTurn = false;
                                projectile = new Projectiles(4, playerToAttack.getX(), playerToAttack.getY(), n5.getX(), n5.getY(), "right");
                                Projectiles.projectileFired = true;
                                hit = false;
                                if(!determineIfSpecificCharacterIsAlive(statsNPC5))
                                {
                                    n5.changeAnimationDead(true);
                                    n5.changeAnimationStunned(false);
                                    npcAlive[5] = false;
                                }
                                else
                                {
                                    npcAlive[5] = true;
                                }
                                attackNumber = 0;
                                attackType = "";
                                n5.setTargeted(false);
                                break;
                            case 6:
                                playerSpecialAttack(characterIdentifier, n6, getStatsNPC6);
                                if(typeOfPlayer == 2)
                                {
                                    n6.changeAnimationStunned(true);
                                }
                                playerTurn = false;
                                projectile = new Projectiles(4, playerToAttack.getX(), playerToAttack.getY(), n6.getX(), n6.getY(), "right");
                                Projectiles.projectileFired = true;
                                hit = false;
                                if(!determineIfSpecificCharacterIsAlive(statsNPC6))
                                {
                                    n6.changeAnimationDead(true);
                                    n6.changeAnimationStunned(false);
                                    npcAlive[6] = false;
                                }
                                else
                                {
                                    npcAlive[6] = true;
                                }
                                attackNumber = 0;
                                attackType = "";
                                n6.setTargeted(false);
                                break;
                            default:
                                break;
                        }

                    }
                }
                else
                {
                    stopHidingForAllPlayers();
                    playerWin = true;
                }
            }
            else if(npcTurn && determineIfSpecificCharacterIsAlive(enemyToAttack.getStats()) )
            {
                drawSelectionCircle(enemyToAttack, g);
                if(delayTime > 1)
                {
                    NPCStats npcStatsToAttack = determineStatsOfNPCTurn(characterIdentifier);
                    if(!npcStatsToAttack.isStunned())
                    {
                        npcAttack(characterIdentifier);
                        int typeOfEnemy = 0;
                        typeOfEnemy = enemyToAttack.getNPCType();
                        if(playerToHit != null)
                        {
                            Player playerHit = determinePlayerHit(playerToHit[4]);
                            projectile = new Projectiles(typeOfEnemy, enemyToAttack.getX(), enemyToAttack.getY(), playerHit.getX(), playerHit.getY(), "left");
                            Projectiles.projectileFired = true;
                            if(!determineIfSpecificCharacterIsAlive(playerToHit))
                            {
                                playerHit.changeAnimationDead(true);
                            }
                        }
                    }
                    else
                    {
                        npcStatsToAttack.stunNPC(false);
                        enemyToAttack.changeAnimationStunned(false);
                        
                    }
                    timer.stop();
                    delayTime = 0;
                    npcTurn = false;
                }
                
            }
            else
            {
                npcTurn = false;
                playerTurn = false;
            }
            
        }
    }
    
    private int npcAttack(Integer npc)
    {   
        playerToHit = targetPlayer();
        Integer[] updatedHPPlayerStats;
        if(playerToHit != null)
        {
            if(null == npc)
            {
                return 0;
            }
            else switch (npc) 
            {
                case 1:
                    if(getStatsNPC1.isStunned())
                    {
                        getStatsNPC1.stunNPC(false);
                        return 0;
                    }
                    else
                    {
                        updatedHPPlayerStats = getStatsNPC1.attackPlayer(playerToHit, isHiding[(playerToHit[4])]);
                        updatePlayerHP(playerToHit[4], updatedHPPlayerStats);
                        return playerToHit[0] - updatedHPPlayerStats[0];
                    }
                case 2:
                    if(getStatsNPC2.isStunned())
                    {
                        getStatsNPC2.stunNPC(false);
                        return 0;
                    }
                    else
                    {
                        updatedHPPlayerStats = getStatsNPC2.attackPlayer(playerToHit, isHiding[(playerToHit[4])]);
                        updatePlayerHP(playerToHit[4], updatedHPPlayerStats);                
                        return playerToHit[0] - updatedHPPlayerStats[0];
                    }
                case 3:
                    if(getStatsNPC3.isStunned())
                    {
                        getStatsNPC3.stunNPC(false);
                        return 0;
                    }
                    else
                    {
                        updatedHPPlayerStats = getStatsNPC3.attackPlayer(playerToHit, isHiding[(playerToHit[4])]);
                        updatePlayerHP(playerToHit[4], updatedHPPlayerStats);              
                        return playerToHit[0] - updatedHPPlayerStats[0];
                    }
                case 4:
                    if(getStatsNPC4.isStunned())
                    {
                        getStatsNPC4.stunNPC(false);
                        return 0;
                    }
                    else
                    {
                        updatedHPPlayerStats = getStatsNPC4.attackPlayer(playerToHit, isHiding[(playerToHit[4])]);
                        updatePlayerHP(playerToHit[4], updatedHPPlayerStats);
                        return playerToHit[0] - updatedHPPlayerStats[0];
                    }
                case 5:
                    if(getStatsNPC5.isStunned())
                    {
                        getStatsNPC5.stunNPC(false);
                        return 0;
                    }
                    else
                    {
                        updatedHPPlayerStats = getStatsNPC5.attackPlayer(playerToHit, isHiding[(playerToHit[4])]);
                        updatePlayerHP(playerToHit[4], updatedHPPlayerStats);
                        return playerToHit[0] - updatedHPPlayerStats[0];
                    }
                case 6:
                    if(getStatsNPC6.isStunned())
                    {
                        getStatsNPC6.stunNPC(false);
                        return 0;
                    }
                    else
                    {
                        updatedHPPlayerStats = getStatsNPC6.attackPlayer(playerToHit, isHiding[(playerToHit[4])]);
                        updatePlayerHP(playerToHit[4], updatedHPPlayerStats);
                        return playerToHit[0] - updatedHPPlayerStats[0];
                    }
                default:
                    return 0;
            }
        }
        else
        {
            return -1;
        } 
    }
    
    private void updatePlayerHP(int playerIdentifier, Integer[] updatedHP)
    {
       playerAttacked = playerIdentifier; 
        switch (playerIdentifier) {
            case 1:
                statsPlayer1 = updatedHP;
                break;
            case 2:
                statsPlayer2 = updatedHP;
                break;
            case 3:
                statsPlayer3 = updatedHP;
                break;
            case 4:
                statsPlayer4 = updatedHP;
                break;
            default:
                break;
        }
    }
    
    
    private void updateNPCHP(int npcIdentifier, Integer[] updatedHP)
    { 
       if(npcIdentifier == 1)
        {
            statsNPC1 = updatedHP;
        }
        else if(npcIdentifier == 2)
        {
            statsNPC2 = updatedHP;
        }
        else if(npcIdentifier == 3)
        {
            statsNPC3 = updatedHP;
        }
        else if(npcIdentifier == 4)
        {
            statsNPC4 = updatedHP;
        }
        else if(npcIdentifier == 5)
        {
            statsNPC5 = updatedHP;
        }
        else if(npcIdentifier == 6)
        {
            statsNPC6 = updatedHP;
        }
    }
    
    private Integer[] targetPlayer()
    {
        Random rand = new Random();
        boolean targetAcquired = false;
        Integer[] playerToTarget = null;
        boolean targetAlive = false;
        int playerTargeted = 0;
        playerDamaged = null;
        npcDamaged = null;
        totalDamage = 0;
        
        while(!targetAlive && determineIfAPlayerIsStillAlive())
        {
            playerTargeted = rand.nextInt(4) + 1;
            if(playerAlive[playerTargeted])
            {
                targetAlive = true;
            }
        }
        while(!targetAcquired && playerTargeted != 0)
        {
            if (playerTargeted == 1 && statsPlayer1[0] != null) 
            {
                if(statsPlayer1[0] > 0)
                {
                    targetAcquired = true;
                    playerToTarget = statsPlayer1;
                    playerDamaged = p1;
                }
            } 
            else if (playerTargeted == 2 && statsPlayer2[0] != null) 
            {
                if(statsPlayer2[0] > 0)
                {
                    targetAcquired = true;
                    playerToTarget = statsPlayer2;
                    playerDamaged = p2;
                }
            } 
            else if (playerTargeted == 3 && statsPlayer3[0] != null) 
            {
                if(statsPlayer3[0] > 0)
                {
                    targetAcquired = true;
                    playerToTarget = statsPlayer3;
                    playerDamaged = p3;
                }
            } 
            else if (playerTargeted == 4 && statsPlayer4[0] != null) 
            {
                if(statsPlayer4[0] > 0)
                {
                    targetAcquired = true;
                    playerToTarget = statsPlayer4;
                    playerDamaged = p4;
                }
            }
        }
        return playerToTarget;
    }
    

    private Player determinePlayerHit(int playerNumber)
    {
        if(playerNumber == 1)
        {
            return p1;
        }
        else if(playerNumber == 2)
        {
            return p2;
        }
        else if(playerNumber == 3)
        {
            return p3;
        }
        else if(playerNumber == 4)
        {
            return p4;
        }
        else
        {
            return null;
        }
    }
    
    private NPCStats determineStatsOfNPCTurn(int npcNumber)
    {
        if(npcNumber == 1)
        {
            return getStatsNPC1;
        }
        else if(npcNumber == 2)
        {
            return getStatsNPC2;
        }
        else if(npcNumber == 3)
        {
            return getStatsNPC3;
        }
        else if(npcNumber == 4)
        {
            return getStatsNPC4;
        }
        else if(npcNumber == 5)
        {
            return getStatsNPC5;
        }
        else if(npcNumber == 6)
        {
            return getStatsNPC6;
        }
        else
        {
            return null;
        }
    }
    
    private PlayerStats determineStatsOfPlayerTurn(int playerNumber)
    {
        if(playerNumber == 1)
        {
            return getStatsPlayer1;
        }
        else if(playerNumber == 2)
        {
            return getStatsPlayer2;
        }
        else if(playerNumber == 3)
        {
            return getStatsPlayer3;
        }
        else if(playerNumber == 4)
        {
            return getStatsPlayer4;
        }
        else
        {
            return null;
        }
    }
    
    
    private int playerAttack(int attackingPlayer, NPC npc, NPCStats stats)
    {   
        int playerIndexToAttack = attackingPlayer;
        NPC npcBeingAttacked = npc;
        NPCStats statsOfNPCBeingAttacked = stats;
        Integer[] updatedHPNPCStats = null;
        Integer damageBeforeArmor = 0;
        int actualDamage = 0;
        playerDamaged = null;
        npcDamaged = null;
        totalDamage = 0;
        
        if(playerToAttack != null)
        {
            if(playerIndexToAttack == 1)
            {
               updatedHPNPCStats = getStatsPlayer1.attackNormalNPC(npcBeingAttacked.getStats());
               damageBeforeArmor = npcBeingAttacked.getStats()[0] - updatedHPNPCStats[0];
               actualDamage = statsOfNPCBeingAttacked.armorProtection(damageBeforeArmor);
               updatedHPNPCStats[0] = npcBeingAttacked.getStats()[0] - actualDamage;
               updateNPCHP(npcBeingAttacked.getStats()[4], updatedHPNPCStats);
               npcDamaged = npcBeingAttacked;
               return npcBeingAttacked.getStats()[0] - updatedHPNPCStats[0];
            }
            else if(playerIndexToAttack== 2)
            {
               updatedHPNPCStats = getStatsPlayer2.attackNormalNPC(npcBeingAttacked.getStats());
               damageBeforeArmor = npcBeingAttacked.getStats()[0] - updatedHPNPCStats[0];
               actualDamage = statsOfNPCBeingAttacked.armorProtection(damageBeforeArmor);
               updatedHPNPCStats[0] = npcBeingAttacked.getStats()[0] - actualDamage;
               updateNPCHP(npcBeingAttacked.getStats()[4], updatedHPNPCStats);
               npcDamaged = npcBeingAttacked;
               return npcBeingAttacked.getStats()[0] - updatedHPNPCStats[0];

            }
            else if(playerIndexToAttack == 3)
            {
               updatedHPNPCStats = getStatsPlayer3.attackNormalNPC(npcBeingAttacked.getStats());
               damageBeforeArmor = npcBeingAttacked.getStats()[0] - updatedHPNPCStats[0];
               actualDamage = statsOfNPCBeingAttacked.armorProtection(damageBeforeArmor);
               updatedHPNPCStats[0] = npcBeingAttacked.getStats()[0] - actualDamage;
               updateNPCHP(npcBeingAttacked.getStats()[4], updatedHPNPCStats);
               npcDamaged = npcBeingAttacked;
               return npcBeingAttacked.getStats()[0] - updatedHPNPCStats[0];
            }
            else if(playerIndexToAttack == 4)
            {
               updatedHPNPCStats = getStatsPlayer4.attackNormalNPC(npcBeingAttacked.getStats());
               damageBeforeArmor = npcBeingAttacked.getStats()[0] - updatedHPNPCStats[0];
               actualDamage = statsOfNPCBeingAttacked.armorProtection(damageBeforeArmor);
               updatedHPNPCStats[0] = npcBeingAttacked.getStats()[0] - actualDamage;
               updateNPCHP(npcBeingAttacked.getStats()[4], updatedHPNPCStats);
               npcDamaged = npcBeingAttacked;
               return npcBeingAttacked.getStats()[0] - updatedHPNPCStats[0];
            }
            else
            {
                return 0;
            }
        }
        else
        {
            return -1;
        }
               
    }
    
    private int playerSpecialAttack(int attackingPlayer, NPC npc, NPCStats stats)
    {   
        int playerIndexToAttack = attackingPlayer;
        NPC npcBeingAttacked = npc;
        NPCStats statsOfNPCBeingAttacked = stats;
        Integer[] updatedHPNPCStats = null;
        Integer damageBeforeArmor = 0;
        int actualDamage = 0;
        playerDamaged = null;
        npcDamaged = null;  
        totalDamage = 0;
        
        if(playerToAttack != null)
        {
            if(playerIndexToAttack == 1)
            {
               updatedHPNPCStats = getStatsPlayer1.attackSpecialNPC(npcBeingAttacked.getStats(), statsOfNPCBeingAttacked);
               damageBeforeArmor = npcBeingAttacked.getStats()[0] - updatedHPNPCStats[0];
               actualDamage = statsOfNPCBeingAttacked.armorProtection(damageBeforeArmor);
               updatedHPNPCStats[0] = npcBeingAttacked.getStats()[0] - actualDamage;
               updateNPCHP(npcBeingAttacked.getStats()[4], updatedHPNPCStats);
               npcDamaged = npcBeingAttacked;
               return npcBeingAttacked.getStats()[0] - updatedHPNPCStats[0];
            }
            else if(playerIndexToAttack== 2)
            {
               updatedHPNPCStats = getStatsPlayer2.attackSpecialNPC(npcBeingAttacked.getStats(), statsOfNPCBeingAttacked);
               damageBeforeArmor = npcBeingAttacked.getStats()[0] - updatedHPNPCStats[0];
               actualDamage = statsOfNPCBeingAttacked.armorProtection(damageBeforeArmor);
               updatedHPNPCStats[0] = npcBeingAttacked.getStats()[0] - actualDamage;
               updateNPCHP(npcBeingAttacked.getStats()[4], updatedHPNPCStats);
               npcDamaged = npcBeingAttacked;
               return npcBeingAttacked.getStats()[0] - updatedHPNPCStats[0];

            }
            else if(playerIndexToAttack == 3)
            {
               updatedHPNPCStats = getStatsPlayer3.attackSpecialNPC(npcBeingAttacked.getStats(), statsOfNPCBeingAttacked);
               damageBeforeArmor = npcBeingAttacked.getStats()[0] - updatedHPNPCStats[0];
               actualDamage = statsOfNPCBeingAttacked.armorProtection(damageBeforeArmor);
               updatedHPNPCStats[0] = npcBeingAttacked.getStats()[0] - actualDamage;
               updateNPCHP(npcBeingAttacked.getStats()[4], updatedHPNPCStats);
               npcDamaged = npcBeingAttacked;
               return npcBeingAttacked.getStats()[0] - updatedHPNPCStats[0];
            }
            else if(playerIndexToAttack == 4)
            {
               updatedHPNPCStats = getStatsPlayer4.attackSpecialNPC(npcBeingAttacked.getStats(), statsOfNPCBeingAttacked);
               damageBeforeArmor = npcBeingAttacked.getStats()[0] - updatedHPNPCStats[0];
               actualDamage = statsOfNPCBeingAttacked.armorProtection(damageBeforeArmor);
               updatedHPNPCStats[0] = npcBeingAttacked.getStats()[0] - actualDamage;
               updateNPCHP(npcBeingAttacked.getStats()[4], updatedHPNPCStats);
               npcDamaged = npcBeingAttacked;
               return npcBeingAttacked.getStats()[0] - updatedHPNPCStats[0];
            }
            else
            {
                return 0;
            }
        }
        else
        {
            return -1;
        }
               
    }
    
    public boolean determineIfAPlayerIsStillAlive()
    {
        playerAlive[1] = statsPlayer1[0] > 0;
        if(statsPlayer2[0] != null)
        {
            playerAlive[2] = statsPlayer2[0] > 0;
        }
        if(statsPlayer3[0] != null)
        {
            playerAlive[3] = statsPlayer3[0] > 0;
        }
        if(statsPlayer4[0] != null)
        {
            playerAlive[4] = statsPlayer4[0] > 0;
        }
        
        for(boolean anyAlive : playerAlive)
        {
            if(anyAlive)
            {
                return true;
            }
        }
        return false;
    }
    
    public boolean determineIfSpecificCharacterIsAlive(Integer[] characterStats)
    {
        return characterStats[0] > 0;
    }
    
    public void createHealthBar(Graphics g)
    {
        /*if(numberOfPlayers > 0)
                {
                    g.setColor(Color.yellow);
                    Font fnt0 = new Font("Arial", Font.BOLD, 15);
                    g.setFont(fnt0);
                    g.drawString("Player One", 33, 13);
                    
                    g.setColor(Color.black);
                    g.fillRect(15, 15, 110, 25);

                    g.setColor(Color.red);
                    g.fillRect(20, 20, statsPlayer1[0] * 5, 15);
                
                
                
                    if(numberOfPlayers > 1)
                    {
                        g.setColor(Color.yellow);
                        g.drawString("Player Two", 148, 13);
                        g.setColor(Color.black);
                        g.fillRect(130, 15, 110, 25);
                        g.setColor(Color.red);
                        g.fillRect(135, 20, statsPlayer2[0] * 5, 15);
                        if(numberOfPlayers > 2)
                        {
                            g.setColor(Color.yellow);
                            g.drawString("Player Three", 258, 13);
                            g.setColor(Color.black);
                            g.fillRect(245, 15, 110, 25);
                            g.setColor(Color.red);
                            g.fillRect(250, 20, statsPlayer3[0] * 5, 15);
                            if(numberOfPlayers > 3)
                            {
                                g.setColor(Color.yellow);
                                g.drawString("Player Four", 375, 13);
                                g.setColor(Color.black);
                                g.fillRect(360, 15, 110, 25);
                                g.setColor(Color.red);
                                g.fillRect(365, 20, statsPlayer4[0] * 5, 15);
                            }
                        }
                    }
                }*/
                /*if(numberOfNPCs > 0)
                {
                    if(!Player.readyToFight)
                    {
                        healthBarLength = new int[numberOfNPCs];
                        if(healthBarLength.length >= 1)
                        {
                            healthBarLength[0] = statsNPC1[0] * 6 + 7;
                            if(healthBarLength.length >= 2)
                            {
                                healthBarLength[1] = statsNPC2[0] * 6 + 7;
                                if(healthBarLength.length >= 3)
                                {
                                    healthBarLength[2] = statsNPC3[0] * 6 + 7;
                                    if(healthBarLength.length >= 4)
                                    {
                                        healthBarLength[3] = statsNPC4[0] * 6 + 7;
                                        if(healthBarLength.length >= 5)
                                        {
                                            healthBarLength[4] = statsNPC5[0] * 6 + 7;
                                            if(healthBarLength.length >= 6)
                                            {
                                                healthBarLength[5] = statsNPC6[0] * 6 + 7;
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                    else
                    {
                        g.setColor(Color.black);
                        g.fillRect(843, 290, healthBarLength[0], 10);

                        g.setColor(Color.red);
                        g.fillRect(847, 293, (int)(statsNPC1[0] * 6), 5);



                        if(numberOfNPCs > 1)
                        {
                            g.setColor(Color.black);
                            g.fillRect(843, 410, healthBarLength[1], 10);

                            g.setColor(Color.red);
                            g.fillRect(847, 413, statsNPC2[0] * 6, 5);
                            if(numberOfNPCs > 2)
                            {
                                g.setColor(Color.black);
                                g.fillRect(843, 114, healthBarLength[2], 10);

                                g.setColor(Color.red);
                                g.fillRect(847, 117, statsNPC3[0] * 6, 5);
                                if(numberOfNPCs > 3)
                                {
                                    g.setColor(Color.black);
                                    g.fillRect(843, 230, healthBarLength[3], 10);

                                    g.setColor(Color.red);
                                    g.fillRect(847, 233, statsNPC4[0] * 6, 5);
                                
                                    if(numberOfNPCs > 4)
                                    {
                                        g.setColor(Color.black);
                                        g.fillRect(843, 168, healthBarLength[4], 10);

                                        g.setColor(Color.red);
                                        g.fillRect(847, 171, statsNPC5[0] * 6, 5);
                                        if(numberOfNPCs > 5)
                                        {
                                            g.setColor(Color.black);
                                            g.fillRect(843, 350, healthBarLength[5], 10);

                                            g.setColor(Color.red);
                                            g.fillRect(847, 353, statsNPC6[0] * 6, 5);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }*/
    }
    
    public static void itemFound(String name, int total)
    {
        if (!itemsFound.equals(""))
        {
            itemsFound += ", ";
        }
        else
        {
            notificationTimer = 3200;
        }
        itemsFound += name + " (x" + Integer.toString(total) + ")";         
    }    
    
    public static void addToBag(String name, int total)
    {
        for (Item i : bag)
        {
            if (i.getName().equals(name))
            {
                i.setTotal(i.getTotal() + total);
                itemFound(name, total);
                return;
            }
        }
        
        bag.add(new Item(name, total));
        itemFound(name, total);
    }
    
    public static void takeFromBag(Item i, int total)
    { 
        i.setTotal(i.getTotal() - total);

        if (i.getTotal() <= 0)
        {
            bag.remove(i);
        }  
    }   
    
    public static void lootObject(int armorDropRate, int weaponDropRate, int potionDropRate, int coinDropRate, int treasureDropRate, int boneDropRate)
    {
        Random r = new Random();
        
        if (armorDropRate >= 0)
        {
            if (r.nextInt(armorDropRate)+1 == 1)
            {
                int item = r.nextInt(4);

                if (item == 0)
                {
                    AdventureRPG.addToBag(Item.ARMOR_MAGE, 1);
                }
                else if (item == 1)
                {
                    AdventureRPG.addToBag(Item.ARMOR_FIGHTER, 1);
                }                
                else if (item == 2)
                {
                    AdventureRPG.addToBag(Item.ARMOR_THIEF, 1);
                }  
                else if (item == 3)
                {
                    AdventureRPG.addToBag(Item.ARMOR_DRUID, 1);
                }  
                else if (item == 4)
                {
                    AdventureRPG.addToBag(Item.ARMOR_RANGER, 1);
                }              
            }    
        }
        if (weaponDropRate >= 0)
        {
            if (r.nextInt(weaponDropRate)+1 == 1)
            {
                int item = r.nextInt(4);

                if (item == 0)
                {
                    AdventureRPG.addToBag(Item.WEAPON_MAGE, 1);
                }
                else if (item == 1)
                {
                    AdventureRPG.addToBag(Item.WEAPON_FIGHTER, 1);
                }                
                else if (item == 2)
                {
                    AdventureRPG.addToBag(Item.WEAPON_THIEF, 1);
                }  
                else if (item == 3)
                {
                    AdventureRPG.addToBag(Item.WEAPON_WARHAMMER, 1);
                }   
                else if (item == 4)
                {
                    AdventureRPG.addToBag(Item.WEAPON_RANGER, 1);
                } 
            }
        } 
        if (potionDropRate >= 0)
        {
            if (r.nextInt(potionDropRate)+1 == 1)
            {
                int item = r.nextInt(2);

                if (item == 0)
                {
                    AdventureRPG.addToBag(Item.POTION_BASIC, 1);
                }
                else if (item == 1)
                {
                    AdventureRPG.addToBag(Item.POTION_ADVANCED, 1);
                }                
                else if (item == 2)
                {
                    AdventureRPG.addToBag(Item.POTION_SUPREME, 1);
                }  
            }
        }   
        if (coinDropRate >= 0)
        {
            if (r.nextInt(coinDropRate)+1 == 1)
            {
                AdventureRPG.addToBag(Item.COINS, r.nextInt((coinDropRate)*5)+1);
            }
        } 
        if (treasureDropRate >= 0)
        {
            if (r.nextInt(treasureDropRate)+1 == 1)
            {
                AdventureRPG.addToBag(Item.TREASURE, 1);
            }
        }         
        if (boneDropRate >= 0)
        {
            if (r.nextInt(boneDropRate)+1 == 1)
            {
                AdventureRPG.addToBag(Item.BONES, 1);
            }
        }
    }
    
    private void stopHidingForAllPlayers()
    {
        p1.changeAnimationHiding(false);
        p2.changeAnimationHiding(false);
        p3.changeAnimationHiding(false);
        p4.changeAnimationHiding(false);
    }
    
    private static void determineRoomSequence()
    {
        Random rand = new Random();
        rooms[5] = 6;
        ArrayList<Integer> roomOptions = new ArrayList<>(5);
        for(int roomsIndex = 1; roomsIndex < rooms.length; roomsIndex++)
        {
            roomOptions.add(roomsIndex);
        }
        
        for(int roomIndex = 0; roomIndex < rooms.length - 1; roomIndex++)
        {
            int roomToSelect = rand.nextInt(roomOptions.size());
            rooms[roomIndex] = roomOptions.remove(roomToSelect);
        }
    }
    
    public void clearNPCStats()
    {
        statsNPC1 = null;
        statsNPC2 = null;
        statsNPC3 = null;
        statsNPC4 = null;
        statsNPC5 = null;
        statsNPC6 = null;
        getStatsNPC1 = null;
        getStatsNPC2 = null;
        getStatsNPC3 = null;
        getStatsNPC4 = null;
        getStatsNPC5 = null;
        getStatsNPC6 = null;
        n1 = null;
        n2 = null;
        n3 = null;
        n4 = null;
        n5 = null;
        n6 = null;
        numberOfNPCs = 0;
        npcs = null;
        npcObjects = null;
        npcObjects = new ArrayList<>();
        npcAlive = null;
        enemyStats = null;
        enemies = null;
        healthBarLength = null;
        Comparator<Integer[]> comparatorStats = new AgilityStatComparator();
        enemyStats = new PriorityQueue<>(6, comparatorStats);
        Comparator<NPC> comparatorNPC = new AgilityStatComparatorForNPCs();
        enemies = new PriorityQueue<>(6, comparatorNPC);
        npcAlive = new boolean[7];
    }
    
    public void resetPlayerPositions()
    {
        p1.setX(125 * SCALELOWRES);
        p2.setX(125 * SCALELOWRES);
        p3.setX(125 * SCALELOWRES);
        p4.setX(125 * SCALELOWRES);
    }
    
    public void resetAnimations()
    {
        p1.resetToWalkingAnimation();
        p2.resetToWalkingAnimation();
        p3.resetToWalkingAnimation();
        p4.resetToWalkingAnimation();
    }
//   
//    public void startOpeningMusic()
//    {
//        String filename = "src/adventurerpg/res/OPENING_MUSIC.wav";
//        ContinuousAudioDataStream loop = null;
//        InputStream in = null;
//        try {
//            in = new FileInputStream(filename);
//        } catch (FileNotFoundException ex) {
//            System.out.println("File not found");
//        }
//            try {
//                AudioStream s = new AudioStream(in);
//                AudioData MD;
//                AudioPlayer.player.start(s);
//            } catch (IOException ex) {
//                System.out.println(ex.getMessage());
//            }
//
//    }
    
    
    class TimeListener implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e) 
        {
            delayTime++;
        }
        
    }
}
