package readability;

public class ReadabilityScorer {

    TextStatisticsExtractor extractor;
    ReadabilityFormula[] formulas;
    double[] scoreByFormula;

    public ReadabilityScorer(TextStatisticsExtractor extractor, ScorerType type) {
        this.extractor = extractor;
        setFormula(type);
    }

    private void setFormula(ScorerType type) {
        int numFormula = type == ScorerType.ALL ? 4 : 1;
        formulas = new ReadabilityFormula[numFormula];
        scoreByFormula = new double[numFormula];
        int index = 0;
        for (ScorerType typeObj: ScorerType.values()) {
            if ((type == typeObj || type == ScorerType.ALL) && typeObj != ScorerType.ALL) {
                formulas[index] = ScorerFactory.getReadabilityScorer(typeObj);
                index++;
            }
        }
    }

    public void printReport() {
        double totalScore = 0.0;
        for (int i = 0; i < formulas.length; i++) {
            scoreByFormula[i] = formulas[i].getScore(extractor);
            String grade = ScoreGrader.getAllowAge(scoreByFormula[i]);
            System.out.printf("%s: %.2f (about %s year olds).%n", formulas[i].getScoreType(), scoreByFormula[i], grade);
            totalScore += Double.parseDouble(grade);
        }

        totalScore = Math.round(totalScore / formulas.length * 100) / 100;
        System.out.printf("This text should be understood in average by %.2f year olds.%n", totalScore);
    }
}
