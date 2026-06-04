import greenfoot.*;

public class MyWorld extends World
{
    public static Cell selectedCell;

    public static Cell[][] board = new Cell[9][9];

    private int[][] puzzle;
    private int startX, startY, size;

    private static boolean pencilMode = false;
    
    public MyWorld()
    {
        super(1000, 700, 1);
        startX=80;
        startY=160;
        
        puzzle=PuzzleSelecter.getBoard(1);
        createBoard();
        createNumberPad();
        
        Border border=new Border(size);
        int borderX = startX - size / 2 + border.getImage().getWidth() / 2;
        int borderY = startY - size / 2 + border.getImage().getHeight() / 2;
        addObject(border, borderX, borderY);
        addObject(new EraserButton(), 860, 158);
        addObject(new PencilButton(), 948, 158);
        addObject(new RestartButton(), 675, 158); 
        addObject(new HintButton(), 770, 158); 
        setPaintOrder(Border.class, Cell.class, NumberButton.class);
        
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
                    c
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
    public void makeMove(){
        
    }
    public void act()
    {
        
    }
    
    public static boolean isValidMove(Cell cell, int number)
    {
        int row = cell.getRow();
        int col = cell.getCol();

        // Row check
        for(int c = 0; c < 9; c++)
        {
            if(board[row][c] != cell && board[row][c].getValue() == number)
            {
                return false;
            }
        }

        // Column check
        for(int r = 0; r < 9; r++)
        {
            if(board[r][col] != cell && board[r][col].getValue() == number)
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
                if(board[r][c] != cell && board[r][c].getValue() == number)
                {
                    return false;
                }
            }
        }

        return true;
    } 
    
    public void restart () {
        createBoard();
    }
    
    public boolean getPencilMode(){
        return pencilMode;
    }
    
    public static void setPencilMode(boolean state){
        pencilMode = state;
    }
    
    public void giveHint() {
        
    }
}
