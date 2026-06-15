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
        //Plays sound effect when clicked
        if (Greenfoot.mouseClicked(this) || (outline != null && Greenfoot.mouseClicked(outline)))
        {
            Greenfoot.playSound("clicksoundeffect.wav");
            clickButton();
        }
    }

    //When clicked, the value of selected cell becomes the answer
    public void clickButton()
    {
        World world = getWorld();

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