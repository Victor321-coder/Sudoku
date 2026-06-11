import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class InfoPage1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class InfoPage1 extends InfoPage
{
    
    public InfoPage1(){
        GreenfootImage bg = new GreenfootImage("info_page_1.png");
        bg.scale(1000, 700);
        setBackground(bg);

        
        // Play Sudoku button
        InfoButton2 button = new InfoButton2(300,70);
        button.getImage().setTransparency(0);
        addObject(button, 780, 600);
    }
}
