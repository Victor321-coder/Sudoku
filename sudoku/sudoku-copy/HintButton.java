import greenfoot.*;

public class HintButton extends UI
{
    private GreenfootImage uiImg = new GreenfootImage("HintUI.png");
    private ButtonOutline outline;

    private boolean active = false;

    public HintButton()
    {
        setImage(uiImg);
    }

    public void act()
    {
        if (Greenfoot.mouseClicked(this) || (outline != null && Greenfoot.mouseClicked(outline)))
        {
            clickButton();
        }
    }

    public void clickButton()
    {
        active = !active;

        if (outline != null)
        {
            outline.setVisibility(active);
        }

        MyWorld.setPencilMode(active);
    }

    protected void addedToWorld(World world)
    {
        outline = new ButtonOutline();
        world.addObject(outline, getX(), getY());
        outline.setVisibility(active);
    }
}