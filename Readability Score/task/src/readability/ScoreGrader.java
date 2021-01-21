package readability;

public class ScoreGrader {

    public static String getAllowAge(double score) {
        int category = (int) Math.ceil(score);
        String[] ages = new String[] {"6", "7", "9", "10", "11", "12", "13",
                "14", "15", "16", "17", "18", "24", "25"};
        category = Math.max(1, Math.min(category, 14));
        return ages[category - 1];
    }
}
