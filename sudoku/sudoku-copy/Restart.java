import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Restart here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Restart extends Actor
{
    public Restart () {
        GreenfootImage img = new GreenfootImage("restart button.png"); 
        setImage(img); 
    }
    
    //If clicked then the world restarts
    public void act()
    {
        if (Greenfoot.mouseClicked(this)) {
            ((MyWorld)getWorld()).restart(); 
        }
    }
}
