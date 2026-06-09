public class DifficultyManager
{
    private static int difficulty = 1; // 1=Easy, 2=Medium, 3=Hard

    public static void setDifficulty(int d)
    {
        difficulty = d;
    }

    public static int getDifficulty()
    {
        return difficulty;
    }
}