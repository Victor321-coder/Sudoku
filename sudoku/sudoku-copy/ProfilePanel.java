import greenfoot.*;

public class ProfilePanel extends Actor
{
    public ProfilePanel()
    {
        GreenfootImage img = new GreenfootImage(300, 120);

        img.setColor(new Color(40, 60, 120));
        img.fillRect(0, 0, 300, 120);

        img.setColor(Color.WHITE);
        img.drawRect(0, 0, 299, 119);

        img.drawString("NOVICE", 20, 30);
        img.drawString("Player", 20, 60);
        img.drawString("Best: 0", 20, 90);

        img.setTransparency(0);

        setImage(img);
    }
}