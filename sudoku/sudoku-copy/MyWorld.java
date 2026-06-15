import greenfoot.*;
import greenfoot.MouseInfo;
import java.util.*;
import java.util.ArrayList;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;
import java.io.File;
import java.io.FileNotFoundException;

public class MyWorld extends World
{
    public static Cell selectedCell;
    public static Cell[][] board = new Cell[9][9];
    public String[] correctKey = {"1", "2", "3", "4", "5", "6", "7", "8", "9"};

    private static ArrayList<Cell> selectedCells = new ArrayList<Cell>();

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

    // Greenfoot Sound
    private static GreenfootSound backgroundMusic = new GreenfootSound ("background music.wav");
    private static int highScore = 0;
    
    private boolean[] completedRows = new boolean[9];
    private boolean[] completedCols = new boolean[9];
    private boolean[][] completedGrids = new boolean[3][3];

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

        addObject(new DifficultyButton(), 800, 600);

        backgroundMusic.setVolume(100); 
        backgroundMusic.playLoop();

        setPaintOrder(CellOutline.class, Border.class, Cell.class, NumberButton.class);
        startTime = System.currentTimeMillis();
        timerRunning = true;

        try {
            Scanner file = new Scanner(new File("score.txt")); 
            
            if (file.hasNext()) {
                highScore = file.nextInt(); 
            }
            file.close();  
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
        
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

    //Starts music when the user presses the run button
    public void started () {
        backgroundMusic.playLoop();
    }

    //Stops the music when the user presses the pause button
    public void stopped() {
        backgroundMusic.pause();
    }

    public void act()
    {
        if(Greenfoot.mouseClicked(null))
        {
            mouse = Greenfoot.getMouseInfo();
            checkCellSelection();
            checkNumberSelection();
            highlight();
        }
        checkKeyboardInput();
        if(checkWin()){
            Greenfoot.setWorld(new WinScreen());
        }
        

        updateTimer();

        showText("Score: " + score, 800, 100);
        showText("High Score: " + highScore, 800, 70);
        
        try {
            FileWriter out = new FileWriter("score.txt");
            PrintWriter output = new PrintWriter(out);
            
            output.println(highScore); 
            output.close(); 
        } catch (IOException e) {
            System.out.println("Error saving score");
        }
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
                    700 + c * 100,
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

            if(selectedCell.isFixed())
            {
                return;
            }

            int row = selectedCell.getRow();
            int col = selectedCell.getCol();

            int correctValue = solution[row][col];

            // Was this cell already correct before the click?
            boolean alreadyCorrect =
                selectedCell.getValue() == correctValue;

            selectedCell.setValue(value);

            long now = System.currentTimeMillis();
            long timeDiff = now - lastMoveTime;
            lastMoveTime = now;

            if(value == correctValue)
            {
                selectedCell.setWrong(false);

                // Only award points the first time
                if(!alreadyCorrect)
                {
                    int basePoints = 10;

                if(timeDiff < 2000)
                {
                    basePoints += 10;
                }
                else if(timeDiff < 5000)
                {
                    basePoints += 5;
                }

                addScore(basePoints);

                checkRowComplete(row);
                checkColumnComplete(col);
                checkGridComplete(row, col);
            }
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
        if(score < 0) 
        {
            score = 0;
        }
        
        if(score > highScore)
        {
            highScore = score;
        }
    }

    private void checkRowComplete(int row)
    {
        if(completedRows[row]) return;

        for(int c = 0; c < 9; c++)
        {
            if(board[row][c].getValue() == 0)
            {
                return;
            }
        }

        completedRows[row] = true;
        addScore(50);
    }

    private void checkColumnComplete(int col)
    {
        if(completedCols[col]) return;

        for(int r = 0; r < 9; r++)
        {
            if(board[r][col].getValue() == 0)
            {
                return;
            }
        }

        completedCols[col] = true;
        addScore(50);
    }

    private void checkGridComplete(int row, int col)
    {
        int gridRow = row / 3;
        int gridCol = col / 3;

        if(completedGrids[gridRow][gridCol]) return;

        int startRow = gridRow * 3;
        int startCol = gridCol * 3;

        for(int r = startRow; r < startRow + 3; r++)
        {
            for(int c = startCol; c < startCol + 3; c++)
            {
                if(board[r][c].getValue() == 0)
                {
                    return;
                }
            }
        }

        completedGrids[gridRow][gridCol] = true;
        addScore(50);
    }
    private boolean checkWin(){
        for(int i=0;i<9;i++){
            for(int o=0;o<9;o++){
                if(board[i][o].getValue() == 0)
                    return false;
            }
        }
        return true;
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
            if(keyPressed.equals("backspace")){
                eraserButton.clickButton();
            }
        }
    }

    public static void selectCell(Cell cell)
    {
        unselectCells();

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

    // ---------------- HIGHLIGHT ----------------

    private void highlight(){
        if(selectedCell != null){
            int row = selectedCell.getRow();
            int col = selectedCell.getCol();
            int correctValue = solution[row][col];
            boolean alreadyCorrect = selectedCell.getValue() == correctValue;
            int gridX = col/3;
            int gridY = row/3;

            //Gets corners of the grid that selected cell is in
            int startX = gridX * 3;
            int bottomX = gridX * 3 + 2;
            int startY = gridY * 3;
            int bottomY = gridY * 3 + 2;

            //Highlights grid
            for(int r = startX; r < bottomX + 1; r++){
                for(int c = startY; c < bottomY + 1; c++){
                    board[c][r].setHighlighted(true);
                    selectedCells.add(board[c][r]);
                } 
            }

            //Highlights column
            for(int r = 0; r < 9; r++){
                board[r][col].setHighlighted(true);
                selectedCells.add(board[r][col]);
            }

            //Highlights row
            for(int r = 0; r < 9; r++){
                board[row][r].setHighlighted(true);
                selectedCells.add(board[row][r]);
            }

            //Allows the selected cell to be brighter than other highlighted cells
            selectedCell.setHighlighted(false);
        }

    }
    
    private static void unselectCells(){
            //For every cell in arrayList, unhighlight them and remove from list
            if(selectedCells.size() > 0){
                for(Cell highlightedCell : selectedCells){
                    highlightedCell.setHighlighted(false);
                }

                for(int i = selectedCells.size() - 1; i >= 0; i--){
                    selectedCells.remove(i);
                }    
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

        showText("Time: " + time, 800, 40);
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