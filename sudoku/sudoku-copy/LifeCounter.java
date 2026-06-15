import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class LifeBar here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class LifeCounter extends UI
{
    /**
     * Act - do whatever the LifeBar wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    int life;
    public LifeCounter(int life){
        this.life=life;
        updateDisplay();
    }
    public void updateDisplay(){
        GreenfootImage image = new GreenfootImage(
            "Life: " + life,
            30,
            Color.BLACK,
            new Color(0, 0, 0, 0)
        );

        setImage(image);
    }
    public void setLife(int life){
        this.life=life;
        updateDisplay();
    }
    public void act()
    {
        // Add your action code here.
    }
}
