import greenfoot.*;

public class MyWorld extends World
{
    public static Cell selectedCell;
<<<<<<< HEAD
    public static Cell[][] board = new Cell[9][9];
=======

    private static Cell[][] board = new Cell[9][9];
    private static Cell[][] boardOverlay = new Cell[9][9];
>>>>>>> 61cc2c654cd47ee6639d1d540d375bb35dbe39e6

    private int[][] puzzle;
    private int startX, startY, size;

    private static boolean pencilMode = false;

    // Timer variables
    private long startTime;
    private boolean timerRunning;

    public MyWorld()
    {
        super(1000, 700, 1);

        startX = 80;
        startY = 160;

        puzzle = PuzzleSelecter.getBoard(1);

        createBoard();
        createNumberPad();

        Border border = new Border(size);
        int borderX = startX - size / 2 + border.getImage().getWidth() / 2;
        int borderY = startY - size / 2 + border.getImage().getHeight() / 2;

        addObject(border, borderX, borderY);

        addObject(new Restart(), 675, 150);
        addObject(new EraserButton(), 846, 158);
        addObject(new PencilButton(), 948, 158);
<<<<<<< HEAD

        setPaintOrder(Border.class, Cell.class, NumberButton.class);

        // Start timer
        startTime = System.currentTimeMillis();
        timerRunning = true;
=======
        
        
        
        
        setPaintOrder(Border.class, Cell.class, NumberButton.class);
        
        
        
        
>>>>>>> 61cc2c654cd47ee6639d1d540d375bb35dbe39e6
    }
    public void act()
    {
        checkUserInput();
    }
    
    private void createBoard()
    {
        size = 60;

        for(int r = 0; r < 9; r++)
        {
            for(int c = 0; c < 9; c++)
            {
                Cell cell = new Cell(
                    puzzle[r][c],
                    puzzle[r][c] != 0,
                    r,
                    c,
                    size
                );

                board[r][c] = cell;
                

                addObject(
                    cell,
                    startX + c * size,
                    startY + r * size
                );
            }
        }
    }
    

    private void createNumberPad()
    {
        int num = 1;

        for(int r = 0; r < 3; r++)
        {
            for(int c = 0; c < 3; c++)
            {
                addObject(
                    new NumberButton(num),
                    750 + c * 100,
                    250 + r * 100
                );

                num++;
            }
        }
    }
<<<<<<< HEAD

    public void act()
    {
        updateTimer();
    }

    private void updateTimer()
    {
        if(!timerRunning)
        {
            return;
        }

        long elapsedSeconds =
            (System.currentTimeMillis() - startTime) / 1000;

        int minutes = (int)(elapsedSeconds / 60);
        int seconds = (int)(elapsedSeconds % 60);

        String time =
            String.format("%02d:%02d", minutes, seconds);

        showText("Time: " + time, 850, 80);
    }

    public void stopTimer()
    {
        timerRunning = false;
    }

    public void resetTimer()
    {
        startTime = System.currentTimeMillis();
        timerRunning = true;
    }

    public void makeMove()
    {

    }

=======
    public void checkUserInput(){
        if(!Greenfoot.mouseClicked(null))
        {
            return;
        }

        MouseInfo mouse = Greenfoot.getMouseInfo();
        if(mouse == null)
        {
            return;
        }

        int boardX = startX - size / 2;
        int boardY = startY - size / 2;
        int mouseX = mouse.getX();
        int mouseY = mouse.getY();

        if(mouseX < boardX || mouseX >= boardX + size * 9 ||
           mouseY < boardY || mouseY >= boardY + size * 9)
        {
            return;
        }

        int col = (mouseX - boardX) / size;
        int row = (mouseY - boardY) / size;

        selectCell(board[row][col]);
    }

    public static void selectCell(Cell cell)
    {
        if(selectedCell != null)
        {
            selectedCell.setSelected(false);
        }

        selectedCell = cell;

        if(selectedCell != null)
        {
            selectedCell.setSelected(true);
        }
    }
    
    
    
>>>>>>> 61cc2c654cd47ee6639d1d540d375bb35dbe39e6
    public static boolean isValidMove(Cell cell, int number)
    {
        int row = cell.getRow();
        int col = cell.getCol();

        // Row check
        for(int c = 0; c < 9; c++)
        {
            if(board[row][c] != cell &&
               board[row][c].getValue() == number)
            {
                return false;
            }
        }

        // Column check
        for(int r = 0; r < 9; r++)
        {
            if(board[r][col] != cell &&
               board[r][col].getValue() == number)
            {
                return false;
            }
        }

        // 3x3 box check
        int startRow = (row / 3) * 3;
        int startCol = (col / 3) * 3;

        for(int r = startRow; r < startRow + 3; r++)
        {
            for(int c = startCol; c < startCol + 3; c++)
            {
                if(board[r][c] != cell &&
                   board[r][c].getValue() == number)
                {
                    return false;
                }
            }
        }

        return true;
<<<<<<< HEAD
    }

    public void restart()
    {
        resetTimer();

        for(int r = 0; r < 9; r++)
        {
            for(int c = 0; c < 9; c++)
            {
                if(board[r][c] != null)
                {
                    removeObject(board[r][c]);
                }
            }
        }

=======
    } 
    
    public void restart () {
        removeObjects(getObjects(Cell.class));
        selectedCell = null;
>>>>>>> 61cc2c654cd47ee6639d1d540d375bb35dbe39e6
        createBoard();
    }

    public boolean getPencilMode()
    {
        return pencilMode;
    }

    public static void setPencilMode(boolean state)
    {
        pencilMode = state;
    }
<<<<<<< HEAD
}
=======
    public Cell getSelectedCell(){
        return selectedCell;
    }
}
>>>>>>> 61cc2c654cd47ee6639d1d540d375bb35dbe39e6
