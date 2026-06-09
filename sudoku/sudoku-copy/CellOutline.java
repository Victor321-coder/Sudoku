import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class CellOutline here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class CellOutline extends BoardVisual
{
    /**
     * Act - do whatever the CellOutline wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public CellOutline(int size)
    {
        int thickness = 1;
        int imageSize = size * 9 + thickness;

        GreenfootImage img = new GreenfootImage(imageSize, imageSize);
        img.setColor(Color.GRAY);

        for(int i = 0; i <= 9; i += 1)
        {
            int position = i * size;

            img.fillRect(position, 0, thickness, imageSize);
            img.fillRect(0, position, imageSize, thickness);
        }

        setImage(img);
    }
}
