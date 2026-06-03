import greenfoot.*;

public class BottomButton extends Actor
{
    public BottomButton(String text)
    {
        GreenfootImage img = new GreenfootImage(150, 60);

        img.setColor(new Color(170, 120, 60));
        img.fillRect(0, 0, 150, 60);

        img.setColor(Color.WHITE);
        img.drawRect(0, 0, 149, 59);

        img.drawString(text, 25, 35);

        setImage(img);
    }
}