import greenfoot.*;

public class CurrencyBar extends Actor
{
    public CurrencyBar()
    {
        GreenfootImage img = new GreenfootImage(450, 60);

        img.setColor(new Color(80, 80, 140));
        img.fillRect(0, 0, 450, 60);

        img.setColor(Color.WHITE);
        img.drawRect(0, 0, 449, 59);

        img.drawString("Coins: 1200", 20, 35);
        img.drawString("Hints: 5", 180, 35);
        img.drawString("Wins: 25", 320, 35);
        
        img.setTransparency(0);
        
        setImage(img);
    }
}