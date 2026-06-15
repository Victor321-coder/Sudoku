import greenfoot.*;

public class PencilButton extends UI
{
    private GreenfootImage uiImg = new GreenfootImage("PencilUI.png");
    private ButtonOutline outline;

    private boolean active = false;

    public PencilButton()
    {
        setImage(uiImg);
    }
    
    //When clicked sound effect plays
    public void act()
    {
        if (Greenfoot.mouseClicked(this) ||
            (outline != null && Greenfoot.mouseClicked(outline)))
        {
            Greenfoot.playSound("clicksoundeffect.wav");
            clickButton();
        }
    }
    
    public void clickButton()
    {
        active = !active;

        //Changes visibility of outline
        if (outline != null)
        {
            outline.setVisibility(active);
        }

        MyWorld.setPencilMode(active);
    }
    
    //When added to world, outline is added
    protected void addedToWorld(World world)
    {
        outline = new ButtonOutline();
        world.addObject(outline, getX(), getY());
        outline.setVisibility(active);
    }
}