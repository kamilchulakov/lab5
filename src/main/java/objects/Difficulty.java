package objects;

public enum Difficulty {
    EASY,
    IMPOSSIBLE,
    TERRIBLE;

    public static Difficulty get(String var) throws NoDifficultyFoundException {
        var = var.toLowerCase();
        if (var.equals("easy")) return EASY;
        else if (var.equals("impossible")) return IMPOSSIBLE;
        else if (var.equals("terrible")) return TERRIBLE;
        else throw new NoDifficultyFoundException();
    }
}
