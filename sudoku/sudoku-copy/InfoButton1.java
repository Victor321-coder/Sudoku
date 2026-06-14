import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class InfoButton here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class InfoButton1 extends Buttons
{
    /**
     * Act - do whatever the InfoButton wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public InfoButton1(int sizeX, int sizeY)
    {

        GreenfootImage img = new GreenfootImage(sizeX, sizeY);

        img.setColor(new Color(70, 90, 170));
        img.fillRect(0, 0, sizeX, sizeY);

        img.setColor(Color.WHITE);
        img.drawRect(0, 0, sizeX, sizeY);

        setImage(img);
    }
    public void act()
    {
        if (Greenfoot.mouseClicked(this))
        {
            Greenfoot.playSound("clicksoundeffect.wav");
            Greenfoot.setWorld(new InfoPage1());
        }
    }
}
