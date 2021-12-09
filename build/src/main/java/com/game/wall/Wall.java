package com.game.wall;

import java.awt.*;

import com.game.ball.Ball;
import com.game.player.Player;
import com.game.views.GameBoard;
import com.game.views.GameFrame;

/**
 * Class that handles all of the game logic.
 */
public class Wall {

    private static final int LEVELS_COUNT = 8;

    private static final int CLAY = 1;
    private static final int STEEL = 2;
    private static final int CEMENT = 3;
    private static final int SLIME = 4;
    private static final int GRAVITY = 5;

    private static int bpcheck;

    private Rectangle area;

    public Brick[] bricks;
    public Ball ball;
    public Player player;

    private Brick[][] levels;
    private int level;

    private Point startPoint;
    private int brickCount;
    private int ballCount;
    private boolean ballLost;

    private GameBoard gameboard;

    private GameFrame owner;

    public Wall(Rectangle drawArea, int brickCount, int lineCount, double brickDimensionRatio, Point ballPos, GameBoard gameboard,GameFrame owner){

        this.owner = owner;

        this.startPoint = new Point(ballPos);
        this.gameboard = gameboard;

        levels = makeLevels(drawArea,brickCount,lineCount,brickDimensionRatio);
        level = 0;

        ballCount = 3;
        ballLost = false;

        ball = new Ball(ballPos);

        ball.setSpeed();

        player = new Player((Point) ballPos.clone(),150,10, drawArea);

        area = drawArea;


    }

    
    /** 
     * Makes a level with only a single type of brick.
     * 
     * @param drawArea Defines the area in which the dricks can be drawn on , in this case , the entire board.
     * @param brickCnt The number of bricks.
     * @param lineCnt The number of lines of bricks.
     * @param brickSizeRatio The brick length to width ratio.
     * @param type The tupe of brick to be made.
     * @return Returns a brick array.
     */
    private Brick[] makeSingleTypeLevel(Rectangle drawArea, int brickCnt, int lineCnt, double brickSizeRatio, int type){
        brickCnt -= brickCnt % lineCnt;

        int brickOnLine = brickCnt / lineCnt;

        double brickLen = drawArea.getWidth() / brickOnLine;
        double brickHgt = brickLen / brickSizeRatio;

        brickCnt += lineCnt / 2;

        Brick[] tmp  = new Brick[brickCnt];

        Dimension brickSize = new Dimension((int) brickLen,(int) brickHgt);
        Point p = new Point();

        int i;
        for(i = 0; i < tmp.length; i++){
            int line = i / brickOnLine;
            if(line == lineCnt)
                break;
            double x = (i % brickOnLine) * brickLen;
            x =(line % 2 == 0) ? x : (x - (brickLen / 2));
            double y = (line) * brickHgt;
            p.setLocation(x,y);
            tmp[i] = makeBrick(p,brickSize,type);
        }

        for(double y = brickHgt;i < tmp.length;i++, y += 2*brickHgt){
            double x = (brickOnLine * brickLen) - (brickLen / 2);
            p.setLocation(x,y);
            tmp[i] =  makeBrick(p,brickSize,type);
        }
        return tmp;

    }

    
    /** 
     * Method that makes a level with 2 types of bricks.
     * 
     * @param drawArea Defines the area in which the dricks can be drawn on , in this case , the entire board.
     * @param brickCnt The number of bricks.
     * @param lineCnt The number of lines of bricks.
     * @param brickSizeRatio The brick length to width ratio.
     * @param typeA The first type of brick to be made.
     * @param typeB The second type of brick to be made.
     * @return Returns a brick array.
     */
    private Brick[] makeChessboardLevel(Rectangle drawArea, int brickCnt, int lineCnt, double brickSizeRatio, int typeA, int typeB){
        brickCnt -= brickCnt % lineCnt;

        int brickOnLine = brickCnt / lineCnt;

        int centerLeft = brickOnLine / 2 - 1;
        int centerRight = brickOnLine / 2 + 1;

        double brickLen = drawArea.getWidth() / brickOnLine;
        double brickHgt = brickLen / brickSizeRatio;

        brickCnt += lineCnt / 2;

        Brick[] tmp  = new Brick[brickCnt];

        Dimension brickSize = new Dimension((int) brickLen,(int) brickHgt);
        Point p = new Point();

        int i;
        for(i = 0; i < tmp.length; i++){
            int line = i / brickOnLine;
            if(line == lineCnt)
                break;
            int posX = i % brickOnLine;
            double x = posX * brickLen;
            x =(line % 2 == 0) ? x : (x - (brickLen / 2));
            double y = (line) * brickHgt;
            p.setLocation(x,y);

            boolean b = ((line % 2 == 0 && i % 2 == 0) || (line % 2 != 0 && posX > centerLeft && posX <= centerRight));
            tmp[i] = b ?  makeBrick(p,brickSize,typeA) : makeBrick(p,brickSize,typeB);
        }

        for(double y = brickHgt;i < tmp.length;i++, y += 2*brickHgt){
            double x = (brickOnLine * brickLen) - (brickLen / 2);
            p.setLocation(x,y);
            tmp[i] = makeBrick(p,brickSize,typeA);
        }
        return tmp;
    }

    
    /** 
     * Method that makes the levels and stores them within a 2d array.
     * 
     * @param drawArea Defines the area in which the dricks can be drawn on , in this case , the entire board.
     * @param brickCnt The number of bricks.
     * @param lineCnt The number of lines of bricks.
     * @param brickSizeRatio The brick length to width ratio.
     * @return Returns a 2D array, the first index indicates the level, the second holds the bricks.
     */
    private Brick[][] makeLevels(Rectangle drawArea,int brickCount,int lineCount,double brickDimensionRatio){
        Brick[][] tmp = new Brick[LEVELS_COUNT][];
        tmp[0] = makeSingleTypeLevel(drawArea,brickCount,lineCount,brickDimensionRatio,CLAY);
        tmp[1] = makeChessboardLevel(drawArea,brickCount,lineCount,brickDimensionRatio,CLAY,CEMENT);
        tmp[2] = makeSingleTypeLevel(drawArea,brickCount,lineCount,brickDimensionRatio,CEMENT);
        tmp[3] = makeChessboardLevel(drawArea,brickCount,lineCount,brickDimensionRatio,CLAY,STEEL);
        tmp[4] = makeChessboardLevel(drawArea,brickCount,lineCount,brickDimensionRatio,STEEL,CEMENT);
        tmp[5] = makeSingleTypeLevel(drawArea,brickCount,lineCount,brickDimensionRatio,SLIME);
        tmp[6] = makeSingleTypeLevel(drawArea,brickCount,lineCount,brickDimensionRatio,GRAVITY);
        tmp[7] = makeChessboardLevel(drawArea,brickCount,lineCount,brickDimensionRatio,SLIME,GRAVITY);
        return tmp;
    }

    /**
     * Method that moves the player and the ball.
     */
    public void move(){
        player.move();
        ball.move();
    }

    /**
     * Method that finds the impacts and changes the movement of the ball based on the impact.
     */
    public void findImpacts(){
        if(bpcheck!=0){
            bpcheck--;
        }
       
        if(player.impact(ball) && bpcheck==0){
            bpcheck = 10;
            ball.reverseY();
        }
        else if(impactWall()){
            gameboard.setScore();
            brickCount--;
        }
        else if((ball.getPosition().getX() < area.getX()) || (ball.getPosition().getX() > area.getX() + area.getWidth())) {
            ball.reverseX();
        }
        else if(ball.getPosition().getY() < area.getY()){
            ball.reverseY();
        }
        else if(ball.getPosition().getY() > area.getY() + area.getHeight()){
            ballCount--;
            ballLost = true;
        }
    }

    
    /** 
     * Method that finds impact on the wall , i.e. the array of bricks.
     * 
     * @return Returns a false when there has been no impact and true if there has been.
     */
    private boolean impactWall(){
        for(Brick b : bricks){
            switch(b.findBrickImpact(ball,owner)) {
                case Brick.UP_IMPACT:
                    ball.reverseY();
                    return b.setImpact(ball.down, Crack.UP);
                case Brick.DOWN_IMPACT:
                    ball.reverseY();
                    return b.setImpact(ball.up,Crack.DOWN);
                case Brick.LEFT_IMPACT:
                    ball.reverseX();
                    return b.setImpact(ball.right,Crack.RIGHT);
                case Brick.RIGHT_IMPACT:
                    ball.reverseX();
                    return b.setImpact(ball.left,Crack.LEFT);
            }
        }
        return false;
    }

    
    /** 
     * Getter method that returns the remaining number of bricks.
     * 
     * @return Returns the number of bricks.
     */
    public int getBrickCount(){
        return brickCount;
    }

    
    /** 
     * Getter method that returns the remaining number of balls.
     * @return Returns the number of bricks.
     */
    public int getBallCount(){
        return ballCount;
    }

    
    /** 
     * Method that checks if the ball has been lost, i.e. it touches the bottom of the screen.
     * 
     * @return Returns true if the ball is lost else false.
     */
    public boolean isBallLost(){
        return ballLost;
    }

    /**
     * Method that resets the ball and the player to the startpoint.
     * 
     */
    public void ballReset(){
        player.moveTo(startPoint);
        ball.moveTo(startPoint);

        ball.setSpeed();
        ballLost = false;
    }

    /**
     * Method that resets the wall to it's unbroken state.
     */
    public void wallReset(){
        gameboard.resetScore();
        for(Brick b : bricks)
            b.repair();
        brickCount = bricks.length;
    }

    
    /** 
     * Meethod that checks if the balls have been fully depleted.
     * 
     * @return boolean
     */
    public boolean ballEnd(){
        return ballCount == 0;
    }

    
    /** 
     * Method that checks if all the bricks have been broken.
     * 
     * @return boolean
     */
    public boolean isDone(){
        return brickCount == 0;
    }

    /**
     * Method that changes the wall to the wall of the next level.
     */
    public void nextLevel(){
        gameboard.setResetscrore();
        if(level <LEVELS_COUNT){
        bricks = levels[level++];
        this.brickCount = bricks.length;
        }
        ballReset();
    }

    
    /** 
     * Method that checks if a next level exists.
     * 
     * @return Returns true if there exist a level beyond the current one else false.s
     */
    public boolean hasLevel(){
        return level < levels.length;
    }

    
    /** 
     * Method that is used by the debug panel to directly change the X axis speed of the ball.
     * 
     * @param s Desired X axis speed set by the user.
     */
    public void setBallXSpeed(int s){
        ball.setXSpeed(s);
    }

    
    /** 
     * Method that is used by the debug panel to directly change the Y axis speed of the ball.
     * 
     * @param s Desired Y axis speed set by the user.
     */
    public void setBallYSpeed(int s){
        ball.setYSpeed(s);
    }

    /**
     * Method that resets the ball count to 3.
     */
    public void resetBallCount(){
        ballCount = 3;
    }

    
    /** 
     * Method that makes the bricks.
     * 
     * @param point Position of the brick.
     * @param size Size of the brick.
     * @param type Type of the brick.
     * @return Returns the newly created instance of the brick.
     */
    private Brick makeBrick(Point point, Dimension size, int type){
        Brick out;
        switch(type){
            case CLAY:
                out = new ClayBrick(point,size);
                break;
            case STEEL:
                out = new SteelBrick(point,size);
                break;
            case CEMENT:
                out = new CementBrick(point, size);
                break;
            case SLIME:
                out = new SlimeBrick(point,size);
                break;
            case GRAVITY:
                out = new GravityBrick(point,size);
                break;
            default:
                throw  new IllegalArgumentException(String.format("Unknown Type:%d\n",type));
        }
        return  out;
    }
}
