import greenfoot.*;

public class Cell extends Actor
{
    private int value;
    private boolean fixed;
    private boolean selected = false;

    private int row;
    private int col;

    public Cell(int value, boolean fixed, int row, int col)
    {
        this.value = value;
        this.fixed = fixed;
        this.row = row;
        this.col = col;

        draw();
    }

    public void act()
    {
        if(Greenfoot.mouseClicked(this))
        {
            if(MyWorld.selectedCell != null)
            {
                MyWorld.selectedCell.selected = false;
                MyWorld.selectedCell.draw();
            }

            selected = true;
            MyWorld.selectedCell = this;

            draw();
        }
    }

    public void setValue(int v)
    {
        if(!fixed)
        {
            value = v;
            draw();
        }
    }

    public int getValue()
    {
        return value;
    }

    public int getRow()
    {
        return row;
    }

    public int getCol()
    {
        return col;
    }

    private void draw()
    {
        GreenfootImage img = new GreenfootImage(60, 60);

        if(selected)
            img.setColor(new Color(200, 220, 255));
        else
            img.setColor(Color.WHITE);

        img.fillRect(0, 0, 60, 60);

        img.setColor(Color.GRAY);
        img.drawRect(0, 0, 59, 59);

        if(value != 0)
        {
            if(fixed)
                img.setColor(new Color(0, 70, 180));
            else
                img.setColor(Color.BLACK);

            img.setFont(new Font("Arial", false, false, 28));
            img.drawString("" + value, 24, 38);
        }

        setImage(img);
    }
}