import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Border here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Border extends BoardVisual
{
    public Border(int size)
    {
        int thickness = 3;
        int imageSize = size * 9 + thickness;

        GreenfootImage img = new GreenfootImage(imageSize, imageSize);
        img.setColor(Color.BLACK);
        
        //Draws a border around the grid
        for(int i = 0; i <= 9; i += 3)
        {
            int position = i * size;

            img.fillRect(position, 0, thickness, imageSize);
            img.fillRect(0, position, imageSize, thickness);
        }

        setImage(img);
    }
}
