import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class InfoButton2 extends Buttons
{
    public InfoButton2(int sizeX, int sizeY)
    {

        GreenfootImage img = new GreenfootImage(sizeX, sizeY);

        img.setColor(new Color(50,50,50));
        img.fillRect(0, 0, sizeX, sizeY);

        img.setColor(Color.WHITE);
        img.drawRect(0, 0, sizeX, sizeY);

        setImage(img);
    }
    public void act()
    {
        //Plays sound effect and makes infoPage2
        if (Greenfoot.mouseClicked(this))
        {
            Greenfoot.playSound("clicksoundeffect.wav");    
            Greenfoot.setWorld(new InfoPage2());
        }
    }
}
