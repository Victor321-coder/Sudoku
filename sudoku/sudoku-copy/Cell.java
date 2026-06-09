import greenfoot.*;

public class Cell extends Actor
{
    private int value;
    private boolean fixed;
    private boolean selected = false;
    private boolean wrong = false;

    private int row;
    private int col;
    private int size;
    GreenfootImage img;
    private Color backgroundColor = Color.WHITE;

    // Pencil marks (1-9)
    private boolean[] pencilMarks = new boolean[10];

    public Cell(int value, boolean fixed, int row, int col, int size)
    {
        this.value = value;
        this.fixed = fixed;
        this.row = row;
        this.col = col;
        this.size = size;
        img = new GreenfootImage(size, size);

        
    }
    public void addedToWorld(World w){
        draw();
    }
    public void act()
    {
    }
    
    public void setWrong(boolean wrong) {
        this.wrong = wrong;
        draw();
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

            // Remove all pencil marks when a real number is placed
            clearPencilMarks();

            draw();
        }
    }

    public void togglePencilMark(int num)
    {
        if(fixed)
        {
            return;
        }

        if(value != 0)
        {
            return;
        }

        pencilMarks[num] = !pencilMarks[num];

        draw();
    }

    public void clearPencilMarks()
    {
        for(int i = 1; i <= 9; i++)
        {
            pencilMarks[i] = false;
        }
    }

    public void clearCell()
    {
        if(!fixed)
        {
            value = 0;
            clearPencilMarks();
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
    public boolean isFixed(){
        return fixed;
    }
    public void setColor(Color color){
        backgroundColor=color;
        draw();
    }
    public void checkSelected(){
        if(selected){
            setColor(new Color(200, 220, 255));
        }else{
            img.setColor(backgroundColor);
        }
    }

    private void draw()
    {
        
        
        if(selected)
        {
            img.setColor(new Color(200, 220, 255));
    
        }
        else if(fixed)
        {
            img.setColor(new Color(255, 171, 171));
    
        }
        else
        {
            img.setColor(Color.WHITE);

        }
        
        img.fillRect(0, 0, size, size);
        img.drawRect(0, 0, size - 1, size - 1);

        
        if(value != 0)
        {

            if(fixed)
            {
                img.setColor(new Color(0, 70, 180));
            }
            else
            {
                if(wrong)
                { 
                    img.setColor(Color.RED);
                }
                else if(fixed)
                {
                    img.setColor(new Color(0, 70, 180));
                }
                else
                {
                    img.setColor(Color.BLACK);
                }
            }

            img.setFont(new Font("Arial", false, false, 28));
            img.drawString("" + value, 24, 38);
        }else{
            // Draw pencil marks
            img.setColor(Color.GRAY);
            img.setFont(new Font("Arial", false, false, 12));

            for(int n = 1; n <= 9; n++)
            {
                if(pencilMarks[n])
                {
                    int miniRow = (n - 1) / 3;
                    int miniCol = (n - 1) % 3;

                    int x = 6 + miniCol * 18;
                    int y = 14 + miniRow * 18;

                    img.drawString("" + n, x, y);
                }
            }
        }

        setImage(img);
    }
}