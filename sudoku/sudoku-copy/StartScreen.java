import greenfoot.*;

public class StartScreen extends World
{
    public StartScreen()
    {
        super(1200, 900, 1);

        // Load and scale background image
        GreenfootImage bg = new GreenfootImage("sudoku_start_screen.png");
        bg.scale(1200, 900);
        setBackground(bg);

        // Profile panel
        addObject(new ProfilePanel(), 170, 70);

        // Currency bar
        addObject(new CurrencyBar(), 650, 50);

        // Play Sudoku button
        PlayButton play = new PlayButton("PLAY SUDOKU", 500,120);
        play.getImage().setTransparency(0);
        addObject(play, 600, 610);

        // Daily Puzzle button
        // PlayButton daily = new PlayButton("DAILY PUZZLE");
        // //daily.getImage().setTransparency(0);
        // addObject(daily, 760, 425);

        // // Practice Mode button
        // PlayButton practice = new PlayButton("PRACTICE MODE");
        // //practice.getImage().setTransparency(0);
        // addObject(practice, 760, 565);

        // // Right-side buttons
        // SideIcon help = new SideIcon("HELP");
        // //help.getImage().setTransparency(0);
        // addObject(help, 1080, 230);

        // SideIcon settings = new SideIcon("SET");
        // //settings.getImage().setTransparency(0);
        // addObject(settings, 1080, 390);

        // SideIcon rank = new SideIcon("RANK");
        // //rank.getImage().setTransparency(0);
        // addObject(rank, 1080, 540);

        // // Bottom buttons
        // BottomButton stats = new BottomButton("STATS");
        // //stats.getImage().setTransparency(0);
        // addObject(stats, 170, 700);

        // BottomButton awards = new BottomButton("AWARDS");
        // //awards.getImage().setTransparency(0);
        // addObject(awards, 400, 700);

        // BottomButton shop = new BottomButton("SHOP");
        // //shop.getImage().setTransparency(0);
        // addObject(shop, 650, 700);

        // BottomButton collect = new BottomButton("COLLECT");
        // //collect.getImage().setTransparency(0);
        // addObject(collect, 930, 700);
    }
}