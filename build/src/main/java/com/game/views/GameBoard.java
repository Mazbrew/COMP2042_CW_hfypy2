package com.game.views;

import javax.swing.*;

import com.game.ball.Ball;
import com.game.debug.DebugConsole;
import com.game.highscore.Highscore;
import com.game.player.Player;
import com.game.wall.Brick;
import com.game.wall.Wall;

import java.awt.*;
import java.awt.event.*;
import java.awt.font.FontRenderContext;

/**
 * Class that draws out the game and also handles keypress logic.
 */
public class GameBoard extends JComponent implements KeyListener,MouseListener,MouseMotionListener {

    private static final String CONTINUE = "Continue";
    private static final String RESTART = "Restart";
    private static final String EXIT = "Exit";
    private static final String PAUSE = "Pause Menu";
    private static final String MAINMENU = "Main Menu";
    private static final int TEXT_SIZE = 30;
    private static final Color MENU_COLOR = new Color(0,255,0);


    private static final int DEF_WIDTH = 600;
    private static final int DEF_HEIGHT = 450;

    private static final Color BG_COLOR = Color.WHITE;

    private Timer gameTimer;

    private GameFrame owner;

    private Wall wall;

    private Highscore highscore;

    private String message;

    private boolean showPauseMenu;
    private boolean showEndScreen;
    private boolean stopReceivingInput;

    private Font menuFont;

    private FontMetrics fm;

    private Rectangle continueButtonRect;
    private Rectangle exitButtonRect;
    private Rectangle restartButtonRect;
    private Rectangle mainmenuButtonRect;
    private int strLen;

    private DebugConsole debugConsole;

    private static boolean A_check;
    private static boolean D_check;

    private static int score;
    private static int resetScore;

    public GameBoard(GameFrame owner,Highscore highscore){
        super();

        score = 0;
        strLen = 0;
        showPauseMenu = false;
        showEndScreen = false;
        stopReceivingInput = false;

        highscore.readScores();

        this.owner= owner;
        this.highscore = highscore;

        menuFont = new Font("Monospaced",Font.PLAIN,TEXT_SIZE);
        

        this.initialize();
        message = "PRESS [SPACE] TO START";
        wall = new Wall(new Rectangle(0,0,DEF_WIDTH,DEF_HEIGHT),30,3,6/2,new Point(300,430),this, this.owner);

        debugConsole = new DebugConsole(owner,wall,this);
        
        wall.nextLevel();

        gameTimer = new Timer(1,e ->{
            wall.move();
            wall.findImpacts();
            if(score > highscore.gethighestScore()){
                message = String.format("Bricks: %d     Balls: %d     Score: %d (HS)",wall.getBrickCount(),wall.getBallCount(),score);
            }else{
                message = String.format("Bricks: %d     Balls: %d     Score: %d",wall.getBrickCount(),wall.getBallCount(),score);
            }
            if(wall.isBallLost()){
                if(wall.ballEnd()){
                    highscore.updateScores(score);
                    stopReceivingInput = true;
                    showEndScreen= true;
                    message = "Game over";
                    wall.ballReset();
                }
                wall.ballReset();
                gameTimer.stop();
            }
            else if(wall.isDone()){
                if(wall.hasLevel()){
                    message = "Next Level, press [SPACE] to continue";
                    gameTimer.stop();
                    wall.ballReset();
                    wall.nextLevel();
                    wall.wallReset();
                    
                }
                else{
                    highscore.updateScores(score);
                    message = "ALL WALLS DESTROYED";
                    stopReceivingInput = true;
                    showEndScreen= true;
                    wall.ballReset();
                }
            }

            repaint();
        });

    }

    /**
     * Method to initialize the gameboard.
     * 
     */
    private void initialize(){
        this.setPreferredSize(new Dimension(DEF_WIDTH,DEF_HEIGHT));
        this.setFocusable(true);
        this.requestFocusInWindow();
        this.addKeyListener(this);
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
    }


    
    /** 
     * Method that paints the gameboard.
     * Is invoked everytime there is a need to draw components or when repaint is called.
     * 
     * @param g Graphics information.
     */
    public void paint(Graphics g){
        Graphics2D g2d = (Graphics2D) g;
        fm = g2d.getFontMetrics();

        clear(g2d);

        g2d.setColor(Color.BLUE);
        g2d.drawString(message,owner.getWidth()/2-fm.stringWidth(message)/2,owner.getHeight()/2);

        drawBall(wall.ball,g2d);

        for(Brick b : wall.bricks)
            if(!b.isBroken())
                drawBrick(b,g2d);

        drawPlayer(wall.player,g2d);

        if(showPauseMenu)
            drawMenu(g2d);

        if(showEndScreen)
            drawEndScreen(g2d);

        Toolkit.getDefaultToolkit().sync();
    }

    
    /** 
     * Method that clears the entire screen by painting it the background color.
     * 
     * @param g2d  2D graphics information.
     */
    private void clear(Graphics2D g2d){
        Color tmp = g2d.getColor();
        g2d.setColor(BG_COLOR);
        g2d.fillRect(0,0,getWidth(),getHeight());
        g2d.setColor(tmp);
    }

    
    /** 
     * Method that draws the brick.
     * 
     * @param brick The type of brick to be drawn.
     * @param g2d 2d graphic information.
     */
    private void drawBrick(Brick brick,Graphics2D g2d){
        Color tmp = g2d.getColor();

        g2d.setColor(brick.getInnerColor());
        g2d.fill(brick.getBrick());

        g2d.setColor(brick.getBorderColor());
        g2d.draw(brick.getBrick());


        g2d.setColor(tmp);
    }

    
    /** 
     * Method that draws the brick.
     * 
     * @param ball Instance of the ball class.
     * @param g2d 2D graphics information.
     */
    private void drawBall(Ball ball,Graphics2D g2d){
        Color tmp = g2d.getColor();

        Shape s = ball.getBallFace();

        g2d.setColor(ball.getInnerColor());
        g2d.fill(s);

        g2d.setColor(ball.getBorderColor());
        g2d.draw(s);

        g2d.setColor(tmp);
    }

    
    /** 
     * Method that draws the player.
     * 
     * @param p Instance of the player class.
     * @param g2d 2D graphics information.
     */
    private void drawPlayer(Player p,Graphics2D g2d){
        Color tmp = g2d.getColor();

        Shape s = p.getPlayerFace();
        g2d.setColor(Player.INNER_COLOR);
        g2d.fill(s);

        g2d.setColor(Player.BORDER_COLOR);
        g2d.draw(s);

        g2d.setColor(tmp);
    }

    
    /** 
     * Method that draws out the pause menu.
     * 
     * @param g2d 2D graphics information.
     */
    private void drawMenu(Graphics2D g2d){
        obscureGameBoard(g2d);
        
        g2d.setFont(menuFont);
        g2d.setColor(MENU_COLOR);

        FontRenderContext frc = g2d.getFontRenderContext();
        strLen = menuFont.getStringBounds(PAUSE,frc).getBounds().width;

        int x = (this.getWidth() - strLen) / 2;
        int y = this.getHeight() / 10;
        g2d.drawString(PAUSE,x,y);

        y+=50;
        if(continueButtonRect == null){
            continueButtonRect = menuFont.getStringBounds(CONTINUE,frc).getBounds();
            continueButtonRect.setLocation((int) (this.getWidth()/2-continueButtonRect.getWidth()/2),y-continueButtonRect.height);
        }
        g2d.drawString(CONTINUE,(int) (this.getWidth()/2-continueButtonRect.getWidth()/2),y);

        y+=50;
        if(restartButtonRect == null){
            restartButtonRect = menuFont.getStringBounds(RESTART,frc).getBounds();
            restartButtonRect.setLocation((int) (this.getWidth()/2-restartButtonRect.getWidth()/2),y-restartButtonRect.height);
        }
        g2d.drawString(RESTART,(int) (this.getWidth()/2-restartButtonRect.getWidth()/2),y);

        y+=50;
        if(mainmenuButtonRect == null){
            mainmenuButtonRect = menuFont.getStringBounds(MAINMENU,frc).getBounds();
            mainmenuButtonRect.setLocation((int)(this.getWidth()/2-mainmenuButtonRect.getWidth()/2),y-mainmenuButtonRect.height);
        }
        g2d.drawString(MAINMENU,(int)(this.getWidth()/2-mainmenuButtonRect.getWidth()/2),y);

        y+=50;
        if(exitButtonRect == null){
            exitButtonRect = menuFont.getStringBounds(EXIT,frc).getBounds();
            exitButtonRect.setLocation((int) (this.getWidth()/2-exitButtonRect.getWidth()/2),y-exitButtonRect.height);
        }
        g2d.drawString(EXIT,(int) (this.getWidth()/2-exitButtonRect.getWidth()/2),y);
    }

    
    /** 
     * Method that draws out the End Screen.
     * This method is called when the game ends , either when all the walls have been destroyed and when there are no more levels
     * or when the player loses all of their balls.
     * 
     * @param g2d 2D graphics information.
     */
    private void drawEndScreen(Graphics2D g2d){
        obscureGameBoard(g2d);

        g2d.setFont(menuFont);
        g2d.setColor(MENU_COLOR);  
        FontRenderContext frc = g2d.getFontRenderContext();

        int y = 200;

        if(mainmenuButtonRect == null){
            mainmenuButtonRect = menuFont.getStringBounds(MAINMENU,frc).getBounds();
            mainmenuButtonRect.setLocation((int) (this.getWidth()/2-mainmenuButtonRect.getWidth()/2),y-mainmenuButtonRect.height);
        }
        g2d.drawString(MAINMENU,(int)(this.getWidth()/2-mainmenuButtonRect.getWidth()/2),y);

        y+=50;
        if(exitButtonRect == null){
            exitButtonRect = menuFont.getStringBounds(EXIT,frc).getBounds();
            exitButtonRect.setLocation((int) (this.getWidth()/2-exitButtonRect.getWidth()/2),y-exitButtonRect.height);
        }
        g2d.drawString(EXIT,(int) (this.getWidth()/2-exitButtonRect.getWidth()/2),y);
    }

    
    /** 
     * Method that obscures the gameboard by drawing a 
     * 
     * @param g2d 2D graphics information.
     */
    private void obscureGameBoard(Graphics2D g2d){
        Composite tmp = g2d.getComposite();
        Color tmpColor = g2d.getColor();

        AlphaComposite ac = AlphaComposite.getInstance(AlphaComposite.SRC_OVER,0.55f);
        g2d.setComposite(ac);
        g2d.setColor(Color.BLACK);
        g2d.fillRect(0,0,DEF_WIDTH,DEF_HEIGHT);

        g2d.setComposite(tmp);
        g2d.setColor(tmpColor);
    }

    /**
     * Method that defines the reset score. 
     * The reset score is defined as the maximum score of the previous level or 0.
     */
    public void setResetscrore(){
        resetScore = score;
    }

    /**
     * Method that resets the score to the resetscore.
     * This method is called everytime the player resets the level.
     */
    public void resetScore(){
        score = resetScore;
    }

    /**
     * Method that increases the score by a 100 everytime a brick is broken.
     */
    public void setScore(){
        score+=100;
    }

    
    /** 
     * Unused interface
     * 
     */
    @Override
    public void keyTyped(KeyEvent keyEvent) {
    }

    
    /** 
     * Method that detects when a key on the keyboard is pressed.
     * Controls for the player , displaying the pause menu and as well as starting the game reside here.
     * 
     * @param keyEvent Keyboard information.
     */
    @Override
    public void keyPressed(KeyEvent keyEvent) {
        if(stopReceivingInput){
            return;
        }
        switch(keyEvent.getKeyCode()){
            case KeyEvent.VK_A:
                A_check = true;
                wall.player.moveLeft();
                break;
            case KeyEvent.VK_D:
                D_check = true;
                wall.player.moveRight();
                break;
            case KeyEvent.VK_LEFT:
                A_check = true;
                wall.player.moveLeft();
                break;
            case KeyEvent.VK_RIGHT:
                D_check = true;
                wall.player.moveRight();
                break;
            case KeyEvent.VK_ESCAPE:
                if(showPauseMenu == false){
                    showPauseMenu = !showPauseMenu;
                    repaint();
                    gameTimer.stop();
                }
                else{
                    showPauseMenu = !showPauseMenu;
                    repaint();
                    gameTimer.start();
                }
                break;
            case KeyEvent.VK_SPACE:
                if(!showPauseMenu){
                    if(!gameTimer.isRunning() && !wall.isDone())
                        gameTimer.start();
                }
                break;
            case KeyEvent.VK_F1:
                debugConsole.setVisible(true);
                break;
            default:
        }
    }

    
    /** 
     * Method that detects when a key on the keyboard is released.
     * Player movement logic was added here to improve the smoothness of the player movement.
     * 
     * @param keyEvent Keyboard information.
     */
    @Override
    public void keyReleased(KeyEvent keyEvent) {
        if(stopReceivingInput){
            return;
        }
        switch(keyEvent.getKeyCode()){
            case KeyEvent.VK_A:
                A_check = false;
                break;
            case KeyEvent.VK_D:
                D_check = false;
                break;
            case KeyEvent.VK_LEFT:
                A_check = false;
                break;
            case KeyEvent.VK_RIGHT:
                D_check = false;
                break;
            default:
               
        }

        if(A_check == false  && D_check == false){
            wall.player.stop();
        }

        if(A_check== true && D_check == false){
            wall.player.moveLeft();
        }

        if(D_check== true && A_check == false){
            wall.player.moveRight();
        }
    }

    
    /** 
     * Method that detects whenever the mouse is clicked.
     * The logic for buttons  in the pause menu as well as the end screen menu reside here.
     * 
     * @param mouseEvent Mouse information.
     */
    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        Point p = mouseEvent.getPoint();
        if(showPauseMenu){
            if(continueButtonRect.contains(p)){
                showPauseMenu = false;
                gameTimer.start();
                repaint();
            }
            else if(restartButtonRect.contains(p)){
                message = "Restarting Level, Press [SPACE] to continue";
                wall.resetBallCount();
                wall.ballReset();
                wall.wallReset();
                showPauseMenu = false;
                repaint();
            }
            else if(mainmenuButtonRect.contains(p)){
                highscore.updateScores(score);
                gameTimer.stop();
                owner.revertGameboard();
            }
            else if(exitButtonRect.contains(p)){
                highscore.updateScores(score);
                System.exit(0);
                score=0;
            }
        }
        else if(showEndScreen){ 
            if(mainmenuButtonRect.contains(p)){
                gameTimer.stop();
                owner.revertGameboard();
            }
            else if(exitButtonRect.contains(p)){
                System.exit(0);
                score=0;
            }
        }
    }

    
    /** 
     * Unused interface.
     */
    @Override
    public void mousePressed(MouseEvent mouseEvent) {

    }

    
    /** 
     * Unsused interface.
     */
    @Override
    public void mouseReleased(MouseEvent mouseEvent) {

    }

    
    /** 
     * Unused interface.
     */
    @Override
    public void mouseEntered(MouseEvent mouseEvent) {

    }

    
    /** 
     * Unused interface.
     */
    @Override
    public void mouseExited(MouseEvent mouseEvent) {

    }

    
    /** 
     * Unused interface.
     */
    @Override
    public void mouseDragged(MouseEvent mouseEvent) {

    }

    
    /** 
     * Method that detects the movement of the mouse.
     * When the mouse is moved and if the cursor is within the bounds of a button, the cursor will change. 
     * If the cursor exits the bounds of said button, the cursor will revert back.
     * 
     * @param mouseEvent Mouse Information.
     */
    @Override
    public void mouseMoved(MouseEvent mouseEvent) {
        Point p = mouseEvent.getPoint();
        if(showPauseMenu) {
            if (exitButtonRect.contains(p) || continueButtonRect.contains(p) || restartButtonRect.contains(p) || mainmenuButtonRect.contains(p))
                this.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            else
                this.setCursor(Cursor.getDefaultCursor());
        }
        
        if(showEndScreen) {
            if (mainmenuButtonRect.contains(p) || exitButtonRect.contains(p) )
                this.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            else
                this.setCursor(Cursor.getDefaultCursor());
        }
        else{
            this.setCursor(Cursor.getDefaultCursor());
        }
    }

    /**
     * Method that is called when the game frame is not in focus.
     * Changes the message that is displayed on the gameboard and also stops the game timer.
     */
    public void onLostFocus(){
        gameTimer.stop();
        message = "Focus Lost, press [SPACE] to continue";
        repaint();
    }

}
