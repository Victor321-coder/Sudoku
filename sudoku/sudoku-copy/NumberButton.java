import greenfoot.*;

public class NumberButton extends Actor
{
    private int number;

    public NumberButton(int n)
    {
        number = n;

        GreenfootImage img = new GreenfootImage(80,80);

        img.setColor(new Color(235,240,250));
        img.fillRect(0,0,80,80);

        img.setColor(Color.BLUE);
        img.setFont(new Font("Arial", false, false, 40));

        img.drawString("" + n, 30, 50);

        setImage(img);
    }

    public void act()
    {
        if(Greenfoot.mouseClicked(this))
        {
            if(MyWorld.selectedCell != null)
            {
                MyWorld.selectedCell.setValue(number);
            }
        }
    }
}