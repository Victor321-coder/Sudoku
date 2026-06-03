import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class ButtonOutline extends UI
{
    private GreenfootImage uiImg = new GreenfootImage("ButtonOutline.png");
    
    public ButtonOutline(){
        setImage(uiImg);
    }
    
    public void setVisibility(boolean active){
        if(!active){
            getImage().setTransparency(0);
        } else{
            getImage().setTransparency(255);
        }
    }
}
