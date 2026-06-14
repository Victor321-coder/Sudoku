import greenfoot.*;

public class PlayButton extends Buttons
{
    private String text;

    public PlayButton(String text, int sizeX, int sizeY)
    {
        this.text = text;

        GreenfootImage img = new GreenfootImage(sizeX, sizeY);

        img.setColor(new Color(70, 90, 170));
        img.fillRect(0, 0, sizeX, sizeY);

        img.setColor(Color.WHITE);
        img.drawRect(0, 0, sizeX, sizeY);

        img.setFont(new Font("Serif", true, false, 28));
        img.drawString(text, 40, 55);

        setImage(img);
    }

    public void act()
    {
        if (Greenfoot.mouseClicked(this))
        {
            Greenfoot.playSound("clicksoundeffect.wav");
            Greenfoot.setWorld(new MyWorld());
        }
    }
}