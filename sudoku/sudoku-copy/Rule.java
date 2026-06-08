import greenfoot.*;

public class Rule extends World
{
    public Rule()
    {
        super(1000, 700, 1);

        GreenfootImage bg = new GreenfootImage("new_rule.png");
        bg.scale(1000, 700);
        setBackground(bg);

        addObject(new BackButton(), 100, 50);
    }
}