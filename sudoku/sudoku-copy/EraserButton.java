import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class EraserButton extends UI
{
    private GreenfootImage uiImg = new GreenfootImage("EraserUI.png");
    private Cell selectedCell;
    
    public EraserButton()
    {
        setImage(uiImg);
    }
    
    public void act(){
        if(Greenfoot.mouseClicked(this)){
            clickButton();
        }
    }
    
    public void clickButton(){
        selectedCell = MyWorld.selectedCell;
        
        if(selectedCell != null){
            selectedCell.setValue(0);   
        }
    }
}
