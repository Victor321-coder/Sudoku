import greenfoot.*;

public class Cell extends Actor
{
    private int value;
    private boolean fixed;
    private boolean selected = false;

    private int row;
    private int col, size;

    public Cell(int value, boolean fixed, int row, int col, int size)
    {
        this.value = value;
        this.fixed = fixed;
        this.row = row;
        this.col = col;
        this.size=size;

        draw();
    }

    public void act()
    {
        // if(Greenfoot.mouseClicked(this))
        // {
            // MyWorld.selectCell(this);
        // }
    }

    public void setSelected(boolean selected)
    {
        this.selected = selected;
        draw();
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
        GreenfootImage img = new GreenfootImage(size, size);

        if(selected){
            img.setColor(new Color(200, 220, 255));
        }
        else{
            img.setColor(Color.WHITE);
        }

        img.fillRect(0, 0, size, size);

        img.setColor(Color.GRAY);
        img.drawRect(0, 0, size - 1, size - 1);

        if(value != 0)
        {
            if(fixed){
                img.setColor(new Color(0, 70, 180));
            }else{
                img.setColor(Color.BLACK);
            }

            img.setFont(new Font("Arial", false, false, 28));
            img.drawString("" + value, 24, 38);
        }

        setImage(img);
    }
}
