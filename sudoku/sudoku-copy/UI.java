import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class UI extends Actor
{
    public void act(){
        if(Greenfoot.mouseClicked(this)){

            Greenfoot.playSound("clicksoundeffect.wav");

        }
    }
}
