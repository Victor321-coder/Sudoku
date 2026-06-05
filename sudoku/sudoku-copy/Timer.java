import greenfoot.*;

public class Timer extends Actor
{
    private long startTime;
    private boolean running;

    public Timer()
    {
        startTime = System.currentTimeMillis();
        running = true;
        updateImage();
    }

    public void act()
    {
        if (running)
        {
            updateImage();
        }
    }

    private void updateImage()
    {
        long elapsed = (System.currentTimeMillis() - startTime) / 1000;

        int minutes = (int)(elapsed / 60);
        int seconds = (int)(elapsed % 60);

        String timeText = String.format("%02d:%02d", minutes, seconds);

        setImage(new GreenfootImage("Time: " + timeText, 32, Color.BLUE, new Color(0,0,0,0)));
    }

    public void stopTimer()
    {
        running = false;
    }

    public void resetTimer()
    {
        startTime = System.currentTimeMillis();
        running = true;
    }

    public long getElapsedSeconds()
    {
        return (System.currentTimeMillis() - startTime) / 1000;
    }
}