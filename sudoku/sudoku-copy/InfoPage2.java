import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class InfoPage2 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class InfoPage2 extends InfoPage
{

    /**
     * Constructor for objects of class InfoPage2.
     * 
     */
    public InfoPage2(){
    
        GreenfootImage bg = new GreenfootImage("info_page_2.png");
        bg.scale(1000, 700);
        setBackground(bg);

        
        // Play Sudoku button
        InfoButton1 button1=new InfoButton1(200,60);
        button1.getImage().setTransparency(0);
        addObject (button1,630,625);
        
        PlayButton play = new PlayButton("Play",200,60);
        play.getImage().setTransparency(0);
        addObject(play,850,625);

    }
}
