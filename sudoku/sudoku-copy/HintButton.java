import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class hint here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class HintButton extends UI
{
    public HintButton() {
        GreenfootImage img = new GreenfootImage("HintUI.png"); 
        setImage(img); 
    }
    /**
     * Act - do whatever the hint wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        if (Greenfoot.mouseClicked(this)) {
            
        }
    }
}
