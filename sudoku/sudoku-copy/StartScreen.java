import greenfoot.*;

public class StartScreen extends World
{
    public StartScreen()
    {
        super(1000, 700, 1);

        // Load and scale background image
        GreenfootImage bg = new GreenfootImage("start_screen.png");
        bg.scale(1000, 700);
        setBackground(bg);

        // Profile panel
        addObject(new ProfilePanel(), 170, 70);

        // Currency bar
        addObject(new CurrencyBar(), 650, 50);

        // Play Sudoku button
        InfoButton1 button = new InfoButton1(430,200);
        button.getImage().setTransparency(0);
        addObject(button, 500, 525);

        
    }
}