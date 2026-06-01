import greenfoot.*;

public class MyWorld extends World
{
    public static Cell selectedCell;

    private int[][] puzzle = {
        {3,0,0,0,4,9,0,0,0},
        {0,0,0,6,0,0,5,0,1},
        {7,5,2,0,0,1,0,0,0},
        {0,0,1,0,0,0,7,0,0},
        {5,0,0,3,9,6,0,0,0},
        {0,0,8,1,5,0,0,9,6},
        {0,0,3,0,1,0,0,6,0},
        {0,0,4,0,0,0,1,0,0},
        {0,0,0,0,2,8,0,0,0}
    };

    public MyWorld()
    {
        super(1000, 700, 1);

        createBoard();
        createNumberPad();
    }

    private void createBoard()
    {
        int startX = 80;
        int startY = 80;
        int size = 60;

        for(int r=0;r<9;r++)
        {
            for(int c=0;c<9;c++)
            {
                Cell cell = new Cell(
                    puzzle[r][c],
                    puzzle[r][c] != 0
                );

                addObject(
                    cell,
                    startX + c*size,
                    startY + r*size
                );
            }
        }
    }

    private void createNumberPad()
    {
        int num = 1;

        for(int r=0;r<3;r++)
        {
            for(int c=0;c<3;c++)
            {
                addObject(
                    new NumberButton(num),
                    750 + c*100,
                    250 + r*100
                );

                num++;
            }
        }
    }
    
    public void act()
    {
        GreenfootImage bg = getBackground();

        bg.setColor(Color.BLACK);

        int startX = 50;
        int startY = 50;

        for(int i=0;i<=9;i++)
        {
            if(i % 3 == 0)
            {
                bg.fillRect(startX+i*60, startY, 3, 540);
                bg.fillRect(startX, startY+i*60, 540, 3);
            }
        }
    }
}