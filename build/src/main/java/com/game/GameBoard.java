package com.game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.font.FontRenderContext;


public class GameBoard extends JComponent implements KeyListener,MouseListener,MouseMotionListener {

    private static final String CONTINUE = "Continue";
    private static final String RESTART = "Restart";
    private static final String EXIT = "Exit";
    private static final String PAUSE = "Pause Menu";
    private static final int TEXT_SIZE = 30;
    private static final Color MENU_COLOR = new Color(0,255,0);


    private static final int DEF_WIDTH = 600;
    private static final int DEF_HEIGHT = 450;

    private static final Color BG_COLOR = Color.WHITE;

    private Timer gameTimer;

    private JFrame owner;

    private Wall wall;

    private String message;

    private boolean showPauseMenu;

    private Font menuFont;

    private FontMetrics fm;

    private Rectangle continueButtonRect;
    private Rectangle exitButtonRect;
    private Rectangle restartButtonRect;
    private int strLen;

    private DebugConsole debugConsole;

    private static boolean A_check;
    private static boolean D_check;

    private static int score;
    private static int resetScore;


    public GameBoard(JFrame owner){
        super();

        strLen = 0;
        showPauseMenu = false;

        Highscore highscore = new Highscore();
        highscore.readScores();


        this.owner= owner;

        menuFont = new Font("Monospaced",Font.PLAIN,TEXT_SIZE);
        

        this.initialize();
        message = "PRESS [SPACE] TO START";
        wall = new Wall(new Rectangle(0,0,DEF_WIDTH,DEF_HEIGHT),30,3,6/2,new Point(300,430),this, this.owner);

        debugConsole = new DebugConsole(owner,wall,this);
        
        wall.nextLevel();

        gameTimer = new Timer(1,e ->{
            wall.move();
            wall.findImpacts();
            message = String.format("Bricks: %d     Balls: %d     Score: %d",wall.getBrickCount(),wall.getBallCount(),score);
            if(wall.isBallLost()){
                if(wall.ballEnd()){
                    wall.wallReset();
                    highscore.updateScores(score);
                    message = "Game over";
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
                    message = "ALL WALLS DESTROYED";
                    gameTimer.stop();
                }
            }

            repaint();
        });

    }



    private void initialize(){
        this.setPreferredSize(new Dimension(DEF_WIDTH,DEF_HEIGHT));
        this.setFocusable(true);
        this.requestFocusInWindow();
        this.addKeyListener(this);
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
    }


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

        Toolkit.getDefaultToolkit().sync();
    }

    private void clear(Graphics2D g2d){
        Color tmp = g2d.getColor();
        g2d.setColor(BG_COLOR);
        g2d.fillRect(0,0,getWidth(),getHeight());
        g2d.setColor(tmp);
    }

    private void drawBrick(Brick brick,Graphics2D g2d){
        Color tmp = g2d.getColor();

        g2d.setColor(brick.getInnerColor());
        g2d.fill(brick.getBrick());

        g2d.setColor(brick.getBorderColor());
        g2d.draw(brick.getBrick());


        g2d.setColor(tmp);
    }

    private void drawBall(Ball ball,Graphics2D g2d){
        Color tmp = g2d.getColor();

        Shape s = ball.getBallFace();

        g2d.setColor(ball.getInnerColor());
        g2d.fill(s);

        g2d.setColor(ball.getBorderColor());
        g2d.draw(s);

        g2d.setColor(tmp);
    }

    private void drawPlayer(Player p,Graphics2D g2d){
        Color tmp = g2d.getColor();

        Shape s = p.getPlayerFace();
        g2d.setColor(Player.INNER_COLOR);
        g2d.fill(s);

        g2d.setColor(Player.BORDER_COLOR);
        g2d.draw(s);

        g2d.setColor(tmp);
    }

    private void drawMenu(Graphics2D g2d){
        obscureGameBoard(g2d);
        drawPauseMenu(g2d);
    }

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

    private void drawPauseMenu(Graphics2D g2d){
        Font tmpFont = g2d.getFont();
        Color tmpColor = g2d.getColor();


        g2d.setFont(menuFont);
        g2d.setColor(MENU_COLOR);

        if(strLen == 0){
            FontRenderContext frc = g2d.getFontRenderContext();
            strLen = menuFont.getStringBounds(PAUSE,frc).getBounds().width;
        }

        int x = (this.getWidth() - strLen) / 2;
        int y = this.getHeight() / 10;

        g2d.drawString(PAUSE,x,y);

        x = this.getWidth() / 8;
        y = this.getHeight() / 4;


        if(continueButtonRect == null){
            FontRenderContext frc = g2d.getFontRenderContext();
            continueButtonRect = menuFont.getStringBounds(CONTINUE,frc).getBounds();
            continueButtonRect.setLocation(x,y-continueButtonRect.height);
        }

        g2d.drawString(CONTINUE,x,y);

        y *= 2;

        if(restartButtonRect == null){
            restartButtonRect = (Rectangle) continueButtonRect.clone();
            restartButtonRect.setLocation(x,y-restartButtonRect.height);
        }

        g2d.drawString(RESTART,x,y);

        y *= 3.0/2;

        if(exitButtonRect == null){
            exitButtonRect = (Rectangle) continueButtonRect.clone();
            exitButtonRect.setLocation(x,y-exitButtonRect.height);
        }

        g2d.drawString(EXIT,x,y);



        g2d.setFont(tmpFont);
        g2d.setColor(tmpColor);
    }

    public void setResetscrore(){
        resetScore = score;
    }

    public void resetScore(){
        score = resetScore;
    }

    public void setScore(){
        score+=100;
    }

    @Override
    public void keyTyped(KeyEvent keyEvent) {
    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {
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
                if(!showPauseMenu)
                    if(!gameTimer.isRunning() && wall.hasLevel())
                        gameTimer.start();
                break;
            case KeyEvent.VK_F1:
                debugConsole.setVisible(true);
                break;
            default:
               
        }
    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {
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

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        Point p = mouseEvent.getPoint();
        if(!showPauseMenu)
            return;
        if(continueButtonRect.contains(p)){
            showPauseMenu = false;
            gameTimer.start();
            repaint();
        }
        else if(restartButtonRect.contains(p)){
            message = "Restarting Level, Press [SPACE] to continue";
            wall.ballReset();
            wall.wallReset();
            showPauseMenu = false;
            repaint();
        }
        else if(exitButtonRect.contains(p)){
            System.exit(0);
        }

    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseEntered(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseExited(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseDragged(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseMoved(MouseEvent mouseEvent) {
        Point p = mouseEvent.getPoint();
        if(exitButtonRect != null && showPauseMenu) {
            if (exitButtonRect.contains(p) || continueButtonRect.contains(p) || restartButtonRect.contains(p))
                this.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            else
                this.setCursor(Cursor.getDefaultCursor());
        }
        else{
            this.setCursor(Cursor.getDefaultCursor());
        }
    }

    public void onLostFocus(){
        gameTimer.stop();
        message = "Focus Lost, press [SPACE] to continue";
        repaint();
    }

}
