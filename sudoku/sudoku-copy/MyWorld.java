import greenfoot.*;
import greenfoot.MouseInfo;
import java.util.*;
public class MyWorld extends World
{
    public static Cell selectedCell;
    public static Cell[][] board = new Cell[9][9];
    public String[] correctKey = {"1", "2", "3", "4", "5", "6", "7", "8", "9"};

    private int[][] puzzle;
    private int[][] solution;
    private int startX, startY, size;

    private static boolean pencilMode = false;

    private MouseInfo mouse;
    private EraserButton eraserButton=new EraserButton();

    // Timer variables
    private long startTime;
    private boolean timerRunning;

    // SCORE SYSTEM
    private int score = 0;
    private long lastMoveTime = System.currentTimeMillis();

    public MyWorld()
    {
        super(1000, 700, 1);

        int boardNum = Greenfoot.getRandomNumber(10);

        int difficulty = DifficultyManager.getDifficulty();

        puzzle = PuzzleSelecter.getBoard(difficulty, boardNum);
        solution = PuzzleSelecter.getSolution(difficulty, boardNum);

        startX = 80;
        startY = 160;

        createBoard();
        createNumberPad();

        Border border = new Border(size);

        int borderX = startX - size / 2 + border.getImage().getWidth() / 2 - 1;
        int borderY = startY - size / 2 + border.getImage().getHeight() / 2 - 1;

        addObject(border, borderX, borderY);

        CellOutline cellOutline = new CellOutline(size);
        addObject(cellOutline, borderX, borderY);

        addObject(new Restart(), 650, 158);
        addObject(new HintButton(), 750, 158);
        addObject(eraserButton, 846, 158);
        addObject(new PencilButton(), 948, 158);
        
        addObject(new DifficultyButton(), 850, 600);
        
        
        

        setPaintOrder(CellOutline.class, Border.class, Cell.class, NumberButton.class);

        startTime = System.currentTimeMillis();
        timerRunning = true;
        
        if(Greenfoot.mouseClicked(this))
        {
            difficulty++;

        if(difficulty > 3)
        {
            difficulty = 1;
        }

        DifficultyManager.setDifficulty(difficulty);

        

        Greenfoot.setWorld(new MyWorld());
        }
    }

    public void act()
    {
        if(Greenfoot.mouseClicked(null))
        {
            mouse = Greenfoot.getMouseInfo();
            checkCellSelection();
            checkNumberSelection();
        }
        checkKeyboardInput();

        updateTimer();
        showText("Score: " + score, 850, 120);
    }

    // ---------------- BOARD ----------------

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

    // ---------------- INPUT ----------------

    public void checkCellSelection()
    {
        if(mouse == null) return;

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

    public void checkNumberSelection()
    {
        if(mouse == null || selectedCell == null) return;

        Actor clicked = mouse.getActor();

        if(clicked instanceof NumberButton)
        {
            NumberButton button = (NumberButton) clicked;
            int value = button.getValue();
            
            if(selectedCell.isFixed()==false){
                selectedCell.setValue(value);
            }
            

            int correctValue =
                solution[selectedCell.getRow()][selectedCell.getCol()];

            // SPEED SYSTEM
            long now = System.currentTimeMillis();
            long timeDiff = now - lastMoveTime;
            lastMoveTime = now;

            if(value == correctValue)
            {
                selectedCell.setWrong(false);

                int basePoints = 10;

                if(timeDiff < 2000) basePoints += 10;
                else if(timeDiff < 5000) basePoints += 5;

                addScore(basePoints);

                int row = selectedCell.getRow();
                int col = selectedCell.getCol();

                checkRowComplete(row);
                checkColumnComplete(col);
                checkGridComplete(row, col);
            }
            else
            {
                selectedCell.setWrong(true);
                addScore(-5);
            }
        }
    }

    // ---------------- SCORE SYSTEM ----------------

    private void addScore(int amount)
    {
        score += amount;
        if(score < 0) score = 0;
    }

    private void checkRowComplete(int row)
    {
        for(int c = 0; c < 9; c++)
        {
            if(board[row][c].getValue() == 0)
                return;
        }

        addScore(50);
    }

    private void checkColumnComplete(int col)
    {
        for(int r = 0; r < 9; r++)
        {
            if(board[r][col].getValue() == 0)
                return;
        }

        addScore(50);
    }

    private void checkGridComplete(int row, int col)
    {
        int startRow = (row / 3) * 3;
        int startCol = (col / 3) * 3;

        for(int r = startRow; r < startRow + 3; r++)
        {
            for(int c = startCol; c < startCol + 3; c++)
            {
                if(board[r][c].getValue() == 0)
                    return;
            }
        }

        addScore(50);
    }

    public void checkKeyboardInput(){
        String keyPressed = Greenfoot.getKey();
        if(keyPressed!=null){
            
            if(Arrays.asList(correctKey).contains(keyPressed)){
                if(selectedCell.isFixed()==false){
                    selectedCell.setValue(Integer.parseInt(keyPressed));
                }
            
                int correctValue =
                solution[selectedCell.getRow()][selectedCell.getCol()];

                if(Integer.parseInt(keyPressed) == correctValue)
                {
                    selectedCell.setWrong(false);
                }
                else
                {
                    selectedCell.setWrong(true);
                }
            }
            if(keyPressed=="backspace"){
                eraserButton.clickButton();
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

    // ---------------- TIMER ----------------

    private void updateTimer()
    {
        if(!timerRunning) return;

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

    // ---------------- RESTART ----------------

    public void restart()
    {
        resetTimer();
        score = 0;

        removeObjects(getObjects(Cell.class));
        selectedCell = null;

        createBoard();
    }

    // ---------------- MODES ----------------

    public static void setPencilMode(boolean state)
    {
        pencilMode = state;
    }

    public static boolean isPencilMode()
    {
        return pencilMode;
    }

    public Cell getSelectedCell()
    {
        return selectedCell;
    }
    
    public int getHintCell() {
        return solution[selectedCell.getRow()][selectedCell.getCol()];
    }
}