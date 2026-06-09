import greenfoot.*;

public class DifficultyButton extends Actor
{
    private int difficulty = 1;

    public DifficultyButton()
    {
        updateImage();
    }

    public void act()
    {
        if(Greenfoot.mouseClicked(this))
        {
            difficulty++;

            if(difficulty > 3)
            {
                difficulty = 1;
            }

            DifficultyManager.setDifficulty(difficulty);

            updateImage();
        }
    }

    private void updateImage()
    {
        String text;

        if(difficulty == 1)
        {
            text = "Easy";
        }
        else if(difficulty == 2)
        {
            text = "Medium";
        }
        else
        {
            text = "Hard";
        }

        GreenfootImage img =
            new GreenfootImage(180, 50);

        img.setColor(new Color(240,240,240));
        img.fillRect(0,0,180,50);

        img.setColor(Color.BLACK);
        img.drawRect(0,0,179,49);

        img.setFont(new Font("Arial", true, false, 22));
        img.drawString("Difficulty: " + text, 10, 30);

        setImage(img);
    }
}