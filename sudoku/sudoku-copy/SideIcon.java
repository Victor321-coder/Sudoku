import greenfoot.*;

public class SideIcon extends Actor
{
    private String type;

    public SideIcon(String text)
    {
        type = text;

        GreenfootImage img = new GreenfootImage(70, 70);

        img.setColor(new Color(120, 80, 40));
        img.fillOval(0, 0, 70, 70);

        img.setColor(Color.WHITE);
        img.drawOval(0, 0, 69, 69);

        img.drawString(text, 10, 40);

        setImage(img);
    }

    //If clicked then a new Rule world is created
    public void act()
    {
        if (Greenfoot.mouseClicked(this))
        {
            if (type.equals("HELP"))
            {
                Greenfoot.setWorld(new Rule());
            }
        }
    }
}