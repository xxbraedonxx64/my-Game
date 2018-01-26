import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.Color;
/**
 * Write a description of class Player here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Snake extends Actor
{
    private int x;
    private int y;
    private int s;
    private String upKey;
    private String downKey;
    private String leftKey;
    private String rightKey;
    private final int SIZE = 40;
    /**
     * Act - do whatever the Player wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     * 
     * @param There are no parameteres
     * @return Nothing is returned
     */
    public void act() 
    {
        MyWorld myWorld = (MyWorld)getWorld();
        if( myWorld.startGame() == true )
        {
            if( s == 0)
            {
                lead();
                lookForFood();
                lookForEdge();
                lookForTail();
            }
            else
            {
                follow();
            }
        }
    }    
    
    /**
     * lead Moves the snake and the tail
     * 
     * @param There are no parameteres
     * @return Nothing is returned
     */
    private void lead()
    {
        double angle;
        MyWorld myWorld = (MyWorld) getWorld();
        x = getX();
        y = getY();
        
        if( Greenfoot.isKeyDown(leftKey) )
        {
            setRotation(180);
        }
        else if( Greenfoot.isKeyDown(rightKey) )
        {
            setRotation(0);
        }
        else if( Greenfoot.isKeyDown(upKey) )
        {
            setRotation(270);
        }
        else if( Greenfoot.isKeyDown(downKey) )
        {
            setRotation(90);
        }
        
        angle = Math.toRadians( getRotation() );
        x = (int) Math.round( getX() + Math.cos(angle) * SIZE );
        y = (int) Math.round( getY() + Math.sin(angle) * SIZE );
        
        setLocation(x, y);
        myWorld.setSX(s, x );
        myWorld.setSY(s, y );
    }
    
    /**
     * lookForFood when it touchs the food it makes it disappear then adds other food appear, and adds a tail to your snake
     * 
     * @param There are no parameteres
     * @return Nothing is returned
     */
    private void lookForFood()
    {
        MyWorld myWorld = (MyWorld)getWorld();
        
        if( isTouching( Food.class ) )
        {    
            removeTouching( Food.class);
            myWorld.randomFood(1);
            growTail();
        }       
    }
    
    /**
     * lookForEdge shows a text and stops the game when you hit the edge
     * 
     * @param There are no parameteres
     * @return Nothing is returned
     */
    private void lookForEdge()
    {
        if( isAtEdge() )
        {
            getWorld() .showText("YOU SUCK SO MUCH LOL ( ͡° _ʖ ͡°) !", getWorld().getWidth()/2, getWorld().getHeight()/2 );
            Greenfoot.stop();
        }
    }
    
    /**
     * lookForTail shows a text and stops the game when you hit your tail
     * 
     * @param There are no parameteres
     * @return Nothing is returned
     */
    private void lookForTail()
    {
        if( isTouching(Snake.class) )
        {
            getWorld() .showText("YOU SUCK SO MUCH LOL ( ͡° _ʖ ͡°) !", getWorld().getWidth()/2, getWorld().getHeight()/2 );
            Greenfoot.stop();
        }
    }
    
    /**
     * follow makes the tail attached to the snake
     * 
     * @param There are no parameteres
     * @return Nothing is returned
     */
    private void follow()
    {
        MyWorld myWorld = (MyWorld)getWorld();
        x = myWorld.getMyX(s);
        y = myWorld.getMyY(s);
        setLocation(x, y);
    }
    
    /**
     * growTail adds an additional tail to the snake player Model
     * 
     * @param There are no parameteres
     * @return Nothing is returned
     */
    private void growTail()
    {
        MyWorld myWorld = (MyWorld)getWorld();
        myWorld.addSnake();
    }
    
    /**
     * Snake the player model
     * 
     * @param numbering the part
     * @return Nothing is rerturned
     */
    public Snake( int part )
    {
        GreenfootImage snakeImage = new GreenfootImage(40, 40);
        s = part;
        if( s == 0 )
        {
            snakeImage.setColor( Color.BLACK );
            snakeImage.fillRect( 0, 0, 40, 40);
        }
        else 
        {
            snakeImage.setColor( Color.GREEN );
            snakeImage.fillRect( 0, 0, 40, 40);
        }
        setImage(snakeImage);
        upKey = "up";
        downKey = "down";
        leftKey = "left";
        rightKey = "right";
    }
    
    /**
     * movement moves the snake 
     * 
     * @param There are no parameteres
     * @return Nothing is returned
     */
    public void movement()
    {
        if (Greenfoot.isKeyDown("up") )
        {
            setLocation( getX(), getY() - 1 );
        }
        
        if (Greenfoot.isKeyDown("down") )
        {
            setLocation( getX(), getY() + 1 );
        }
        
        if (Greenfoot.isKeyDown("left") ) 
        {
            setLocation( getX() -1, getY() );
        }
        
        if (Greenfoot.isKeyDown("right") ) 
        {
            setLocation( getX() +1, getY() );
        }
         
    }
}
