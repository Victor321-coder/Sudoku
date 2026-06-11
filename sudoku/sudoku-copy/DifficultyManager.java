public class DifficultyManager
{
    private static int difficulty = 1;

    public static void setDifficulty(int d)
    {
        difficulty = d;
    }

    public static int getDifficulty()
    {
        return difficulty;
    }
}