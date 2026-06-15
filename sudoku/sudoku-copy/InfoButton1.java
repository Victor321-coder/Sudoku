import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class InfoButton1 extends Buttons
{
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
        //Plays sound effect and make infoPage world
        if (Greenfoot.mouseClicked(this))
        {
            Greenfoot.playSound("clicksoundeffect.wav");
            Greenfoot.setWorld(new InfoPage1());
        }
    }
}
