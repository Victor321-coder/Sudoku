import greenfoot.*;

public class BackButton extends Actor
{
    public BackButton()
    {
        GreenfootImage img = new GreenfootImage(120, 40);

        img.setColor(Color.WHITE);
        img.fillRect(0, 0, 120, 40);

        img.setColor(Color.BLACK);
        img.drawRect(0, 0, 119, 39);
        img.drawString("BACK", 40, 25);
        img.setTransparency(0);
        setImage(img);
    }

    public void act()
    {
        if (Greenfoot.mouseClicked(this))
        {
            Greenfoot.playSound("clicksoundeffect.wav");
            Greenfoot.setWorld(new StartScreen());
        }
    }
}