import greenfoot.*;

public class NumberButton extends Actor
{
    private int number;

    public NumberButton(int n)
    {
        number = n;

        GreenfootImage img = new GreenfootImage(80, 80);

        img.setColor(new Color(235, 240, 250));
        img.fillRect(0, 0, 80, 80);

        img.setColor(Color.BLUE);
        img.setFont(new Font("Arial", false, false, 40));

        img.drawString("" + n, 30, 50);

        setImage(img);
    }

    public void act()
    {
        // userNumberSelect();
    }

    
    public int getValue(){
        return number;
    }

    // public void userNumberSelect()
    // {
        // if(Greenfoot.mouseClicked(this))
        // {
            // Cell cell = MyWorld.selectedCell;

            // if(cell == null)
            // {
                // return;
            // }

            // // Pencil mode ON
            // if(MyWorld.isPencilMode())
            // {
                // cell.togglePencilMark(number);
                // return;
            // }

<<<<<<< HEAD
            // cell.setValue(number);
            
        }
    }
=======
            // // Normal mode
            // if(MyWorld.isValidMove(cell, number))
            // {
                // cell.setValue(number);
            // }
            // else
            // {
                // System.out.println(
                    // "Invalid move! " +
                    // number +
                    // " already exists in that row, column, or box."
                // );
            // }
        // }
    // }
>>>>>>> a306f159a7ff701987dc1b58a05e549e206bd9c0
    
    
}
