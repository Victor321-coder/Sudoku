import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class PencilButton extends UI
{
    private GreenfootImage uiImg = new GreenfootImage("PencilUI.png");
    private ButtonOutline outline;
    private Cell selectedCell;
    
    private boolean active = false;
    
    //THE PENCIL MECHANIC DOES NOT WORK YET (WIP)
    //-Anson Quan
    
    public PencilButton()
    {
        setImage(uiImg);
    }
    
    public void act(){
        if(Greenfoot.mouseClicked(this) || Greenfoot.mouseClicked(outline)){
            clickButton();
        }
    }
    
    public void clickButton(){
        selectedCell = MyWorld.selectedCell;
        
        active = !active;
        outline.setVisibility(active);
        
        MyWorld.setPencilMode(active);
    }
    
    protected void addedToWorld(World world){
        outline = new ButtonOutline();
        getWorld().addObject(outline, getX(), getY());
        outline.setVisibility(active);
    }
}
