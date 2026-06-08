import greenfoot.*;

public class Rule extends World
{
    public Rule()
    {
        super(1188, 766, 1);

        GreenfootImage bg = new GreenfootImage("rule.png");
        bg.scale(1188, 766);
        setBackground(bg);

        addObject(new BackButton(), 100, 50);
    }
}