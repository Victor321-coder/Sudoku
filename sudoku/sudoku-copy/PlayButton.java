import greenfoot.*;

public class PlayButton extends Actor
{
    private String text;

    public PlayButton(String text)
    {
        this.text = text;

        GreenfootImage img = new GreenfootImage(350, 90);

        img.setColor(new Color(70, 90, 170));
        img.fillRect(0, 0, 350, 90);

        img.setColor(Color.WHITE);
        img.drawRect(0, 0, 349, 89);

        img.setFont(new Font("Serif", true, false, 28));
        img.drawString(text, 40, 55);

        setImage(img);
    }

    public void act()
    {
        if (Greenfoot.mouseClicked(this))
        {
            Greenfoot.setWorld(new MyWorld());
        }
    }
}