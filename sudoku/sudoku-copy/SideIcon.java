import greenfoot.*;

public class SideIcon extends Actor
{
    public SideIcon(String text)
    {
        GreenfootImage img = new GreenfootImage(70, 70);

        img.setColor(new Color(120, 80, 40));
        img.fillOval(0, 0, 70, 70);

        img.setColor(Color.WHITE);
        img.drawOval(0, 0, 69, 69);

        img.drawString(text, 10, 40);

        setImage(img);
    }
}