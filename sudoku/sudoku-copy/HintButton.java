import greenfoot.*;

public class HintButton extends UI
{
    private GreenfootImage uiImg = new GreenfootImage("HintUI.png");
    private ButtonOutline outline;
    private Cell selectedCell;
    private boolean active = false;

    public HintButton()
    {
        setImage(uiImg);
    }

    public void act()
    {
        if (Greenfoot.mouseClicked(this) || (outline != null && Greenfoot.mouseClicked(outline)))
        {
            Greenfoot.playSound("clicksoundeffect.wav");
            clickButton();
        }
    }

    public void clickButton()
    {
        World world = getWorld(); 
        active = !active;

        if (outline != null)
        {
            outline.setVisibility(active);
        }

        selectedCell = MyWorld.selectedCell;
        if(selectedCell != null){
            selectedCell.setValue(((MyWorld)world).getHintCell());   
        }
    }

    protected void addedToWorld(World world)
    {
        outline = new ButtonOutline();
        world.addObject(outline, getX(), getY());
        outline.setVisibility(active);
    }
}