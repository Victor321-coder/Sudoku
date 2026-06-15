import greenfoot.*;

public class Cell extends Actor
{
    private int value;
    private boolean fixed;
    private boolean selected = false;
    private boolean wrong = false;
    private boolean highlighted = false;

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
    
    //Draws cell when added
    public void addedToWorld(World w){
        draw();
    }
    
    //Sets wrong bool and redraws cell
    public void setWrong(boolean wrong) {
        this.wrong = wrong;
        draw();
    }
    
    //Sets selected bool and redraws cell
    public void setSelected(boolean selected)
    {
        this.selected = selected;
        
        draw();
    }

    //If cell is not fixed then set value and redraw cell
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

    //Toggles pencil mark for the cell
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

    //Clears all pencil marks for the cell
    public void clearPencilMarks()
    {
        for(int i = 1; i <= 9; i++)
        {
            pencilMarks[i] = false;
        }
    }

    //Clears the pencil marks and removes pencil marks
    public void clearCell()
    {
        if(!fixed)
        {
            value = 0;
            clearPencilMarks();
            draw();
        }
    }

    //Returns value of cell
    public int getValue()
    {
        return value;
    }

    //Returns row of cell
    public int getRow()
    {
        return row;
    }

    //Returns column of cell
    public int getCol()
    {
        return col;
    }
    
    //Return fixed bool
    public boolean isFixed(){
        return fixed;
    }
    
    //Sets cell to specific color
    public void setColor(Color color){
        backgroundColor=color;
        draw();
    }
    
    //Sets highlighted bool
    public void setHighlighted(boolean bool){
        highlighted = bool;
        draw();
    }
    
    //Checks selected and sets color of cell
    public void checkSelected(){
        if(selected){
            setColor(new Color(200, 220, 255));
        }else{
            img.setColor(backgroundColor);
        }
    }

    //Draws cell
    private void draw()
    {
        img.clear();

        // Background
        if(selected)
        {
            img.setColor(new Color(200, 220, 255));
        }
        else
        {
            img.setColor(Color.WHITE);
        }
        
        //If higlighted bool then set to specific color
        if(highlighted){
            img.setColor(new Color(226, 235, 243));
        }
        
        img.fillRect(0, 0, size, size);

        // Cell border
        img.setColor(Color.LIGHT_GRAY);
        img.drawRect(0, 0, size - 1, size - 1);

        if(value != 0)
        {
            // Number colors
            if(fixed)
            {
                img.setColor(Color.BLACK);      // Fixed numbers
            }
            else if(wrong)
            {
                img.setColor(Color.RED);        // Wrong user entry
            }
            else
            {
                img.setColor(Color.BLUE);       // Correct user entry
            }

            img.setFont(new Font("Arial", false, false, 28));

            String text = String.valueOf(value);

            int textWidth = text.length() * 15;
            int x = (size - textWidth) / 2;
            int y = size / 2 + 10;

            img.drawString(text, x, y);
        }
        else
        {
            // Pencil marks
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