import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class WinScreen here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class WinScreen extends EndScreen
{

    /**
     * Constructor for objects of class WinScreen.
     * 
     */
    public WinScreen()
    {
        GreenfootImage bg = new GreenfootImage("win_page.png");
        bg.scale(1000, 700);
        setBackground(bg);
        
        PlayButton play = new PlayButton("Play",300,100);
        play.getImage().setTransparency(0);
        addObject(play,500,570);
    }
}
