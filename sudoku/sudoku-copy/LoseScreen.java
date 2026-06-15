import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class LoseScreen here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class LoseScreen extends EndScreen
{

    /**
     * Constructor for objects of class LoseScreen.
     * 
     */
    public LoseScreen()
    {
        GreenfootImage bg = new GreenfootImage("lose_page.png");
        bg.scale(1000, 700);
        setBackground(bg);
        
        PlayButton play = new PlayButton("Play",300,100);
        play.getImage().setTransparency(0);
        addObject(play,500,570);
    }
}
