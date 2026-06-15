import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class EraserButton extends UI
{
    private GreenfootImage uiImg = new GreenfootImage("EraserUI.png");
    private Cell selectedCell;
    
    public EraserButton()
    {
        setImage(uiImg);
    }
    
    //Plays sound effect when clicked
    public void act(){
        if(Greenfoot.mouseClicked(this)){
            Greenfoot.playSound("clicksoundeffect.wav");
            clickButton();
        }
    }
    
    //Sets the value of selected cell to zero
    public void clickButton(){
        selectedCell = MyWorld.selectedCell;
        
        if(selectedCell != null){
            selectedCell.setValue(0);   
        }
    }
}
