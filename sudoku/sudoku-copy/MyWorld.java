import greenfoot.*;
import greenfoot.MouseInfo;

public class MyWorld extends World
{
    public static Cell selectedCell;
    public static Cell[][] board = new Cell[9][9];

    private int[][] puzzle;
    private int[][] solution;
    private int startX, startY, size;

    private static boolean pencilMode = false;

    private MouseInfo mouse;

    // Timer variables
    private long startTime;
    private boolean timerRunning;

    public MyWorld()
    {
        super(1000, 700, 1);

        int boardNum = Greenfoot.getRandomNumber(9);

        puzzle = PuzzleSelecter.getBoard(1, boardNum);
        solution = PuzzleSelecter.getSolution(1, boardNum);

        startX = 80;
        startY = 160;

        createBoard();
        createNumberPad();

        Border border = new Border(size);

        int borderX = startX - size / 2 + border.getImage().getWidth() / 2-1;
        int borderY = startY - size / 2 + border.getImage().getHeight() / 2-1;


        addObject(border, borderX, borderY);
        CellOutline cellOutline = new CellOutline(size);
        addObject(cellOutline,borderX,borderY);
        
        addObject(new Restart(), 675, 150);
        addObject(new EraserButton(), 846, 158);
        addObject(new PencilButton(), 948, 158);

        setPaintOrder(CellOutline.class, Border.class, Cell.class, NumberButton.class);

        startTime = System.currentTimeMillis();
        timerRunning = true;
    }

    public void act()
    {
        if(Greenfoot.mouseClicked(null))
        {
            mouse = Greenfoot.getMouseInfo();
            checkCellSelection();
            checkNumberSelection();
        }

        updateTimer();
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
                
                cell.setColor(new Color(168,168,168));
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
                addObject(new NumberButton(num),
                    750 + c * 100,
                    250 + r * 100
                );

                num++;
            }
        }
    }

    public void checkCellSelection()
    {
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
        if(!board[row][col].isFixed()){
            selectCell(board[row][col]);
        }
        
    }

    public void checkNumberSelection()
    {
        if(mouse == null || selectedCell == null)
        {
            return;
        }

        Actor clicked = mouse.getActor();

        if(clicked instanceof NumberButton)
        {
            NumberButton button = (NumberButton) clicked;
            int value = button.getValue();

            selectedCell.setValue(value);

            int correctValue =
                solution[selectedCell.getRow()][selectedCell.getCol()];

            if(value == correctValue)
            {
                selectedCell.setWrong(false);
            }
            else
            {
                selectedCell.setWrong(true);
            }
        }
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

        String time = String.format("%02d:%02d", minutes, seconds);

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



    // public static boolean isValidMove(Cell cell, int number)
    // {
        // int row = cell.getRow();
        // int col = cell.getCol();

        // // Row check
        // for(int c = 0; c < 9; c++)
        // {
            // if(board[row][c] != cell &&
               // board[row][c].getValue() == number)
            // {
                // return false;
            // }
        // }

        // // Column check
        // for(int r = 0; r < 9; r++)
        // {
            // if(board[r][col] != cell &&
               // board[r][col].getValue() == number)
            // {
                // return false;
            // }
        // }

        // // Box check
        // int startRow = (row / 3) * 3;
        // int startCol = (col / 3) * 3;

        // for(int r = startRow; r < startRow + 3; r++)
        // {
            // for(int c = startCol; c < startCol + 3; c++)
            // {
                // if(board[r][c] != cell &&
                   // board[r][c].getValue() == number)
                // {
                    // return false;
                // }
            // }
        // }

        // return true;
    // }


    public void restart()
    {
        resetTimer();

        removeObjects(getObjects(Cell.class));

        selectedCell = null;

        createBoard();
    }

    public static void setPencilMode(boolean state)
    {
        pencilMode = state;
    }

    public static boolean getPencilMode()
    {
        return pencilMode;
    }

    public static boolean isPencilMode()
    {
        return pencilMode;
    }

    public Cell getSelectedCell()
    {
        return selectedCell;
    }
}