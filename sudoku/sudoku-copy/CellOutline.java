import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class CellOutline extends BoardVisual
{
    public CellOutline(int size)
    {
        int thickness = 1;
        int imageSize = size * 9 + thickness;

        GreenfootImage img = new GreenfootImage(imageSize, imageSize);
        img.setColor(Color.GRAY);
        
        //Draws an outline around each cell
        for(int i = 0; i <= 9; i += 1)
        {
            int position = i * size;

            img.fillRect(position, 0, thickness, imageSize);
            img.fillRect(0, position, imageSize, thickness);
        }

        setImage(img);
    }
}
