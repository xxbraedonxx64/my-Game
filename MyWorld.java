import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class MyWorld here.
 * 
 * @author Braedon Harper 
 * @version 1/26/2018
 */
public class MyWorld extends World
{
    private final int MAX_PARTS = (680*600)/(40*40);
    private int[] x = new int[MAX_PARTS];
    private int[] y = new int[MAX_PARTS];
    private int parts = 4;
    private Snake body;
    private boolean startGame = false;

    /**
     * Constructor for objects of class MyWorld.
     * 
     * @param There are no parameteres
     * @return Nothing is returned
     */
    public MyWorld()
    {
        //Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(680, 680, 1);
        setPaintOrder( Snake.class, Food.class );

        for( int i = 0;i < parts; i++ )
        {
            x[i] = 200 - i*40;
            y[i] = 40;
        }

        populate();
        prepare();
    }

    /**
     * randomFood spawns food into your world when the snake eats
     * 
     * @param numbering howMany
     * @return Nothing is returned
     */
    public void randomFood( int howMany )
    {
        Food newFood;
        int x;
        int y;
        for( int i = 0; i < howMany; i++ )
        {
            newFood = new Food();
            x = Greenfoot.getRandomNumber( getWidth() );
            y = Greenfoot.getRandomNumber( getHeight() );
            
            addObject( newFood, x, y );
        }
    }

    /**
     * populate adds a other snake to your body when eaten the food class
     * 
     * @param There are no parameteres
     * @return Nothing is return
     */
    private void populate()
    {
        for( int i = 0; i < parts; i++ )
        {
            body = new Snake(i);
            addObject( body, x[i], y[i] );
        }
        randomFood(5);
    }

    /**
     * act does actions to the world
     * 
     * @param There are no parameteres
     * @return Nothing is return
     */
    public void act()
    {
        if( startGame == false )
        {
            checkKeyPress();
        }
        else
        {
            for( int i = parts; i > 0; i-- )
            {
                x[i] = x[i-1];
                y[i] = y[i-1];
            }
        }
    }

    /**
     * checkKeyPress start the game when Run is pressed
     * 
     * @param There are no parameteres
     * @return Nothing is return
     */
    private void checkKeyPress()
    {
        startGame = true;
    }

    /**
     * startGame checking if the game is starting or not
     * 
     * @param There are no parameteres
     * @return the game when resets
     */
    public boolean startGame()
    {
        return startGame;
    }

    /**
     * setSX checking how many body parts are on the x axis
     * 
     * @param s is for snake
     * @param sx is for snake in the X axis
     * @return Nothing is returned
     */
    public void setSX( int s, int sx )
    {
        x[s] = sx;
    }

    /**
     * setSY checking how many body parts are on the y axis
     * 
     * @param s is for snake
     * @param sy is for for snake in the Y axis
     * @return Nothing is returned
     */
    public void setSY( int s, int sy )
    {
        y[s] = sy;
    }

    /**
     * getMyX checking where the snakes is on the x axis
     * 
     * @param s is for snake
     * @return returning the x axis
     */
    public int getMyX( int s )
    {
        return x[s];
    }

    /**
     * getMyY checking where the snakes is on the y axis
     * 
     * @param s is for snake
     * @return returning the y axis
     */
    public int getMyY( int s )
    {
        return y[s];
    }

    /**
     * addSnake adds a other snake to your body
     * 
     * @param There are no parameteres
     * @return Nothing is return
     */
    public void addSnake()
    {
        int parentX = x[parts-1];
        int parentY = y[parts-1];

        body = new Snake(parts);
        addObject(body, parentX, parentY );
        parts++;
    }

    /**
     * Prepare the world for the start of the program.
     * That is: create the initial objects and add them to the world.
     * 
     * @param There are no parameteres
     * @return Nothing is returned
     */
    private void prepare()
    {
        Food food = new Food();
        addObject(food,98,571);
        Food food2 = new Food();
        addObject(food2,214,453);
        Food food3 = new Food();
        addObject(food3,522,632);
        Food food4 = new Food();
        addObject(food4,572,378);
        Food food5 = new Food();
        addObject(food5,339,276);
    }
}
