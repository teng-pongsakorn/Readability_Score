package readability;

public abstract class ReadabilityFormula {

    public abstract double getScore(TextStatisticsExtractor extractor);

    public abstract ScorerType getScoreType();
}

class AutomatedReadabilityIndex extends ReadabilityFormula {

    @Override
    public double getScore(TextStatisticsExtractor extractor) {
        double score = 4.71 * extractor.charCount / extractor.wordCount + 0.5 * extractor.wordCount / extractor.sentCount - 21.43;
        return score;
    }

    @Override
    public ScorerType getScoreType() {
        return ScorerType.ARI;
    }
}

class FleschKincaidReadabilityTests extends ReadabilityFormula {


    @Override
    public double getScore(TextStatisticsExtractor extractor) {
        double score = 0.39 * extractor.wordCount / extractor.sentCount + 11.8 * extractor.syllableCount / extractor.wordCount - 15.59;
        return score;
    }

    @Override
    public ScorerType getScoreType() {
        return ScorerType.FK;
    }
}

class SimpleMeasureOfGobbledygook extends ReadabilityFormula {


    @Override
    public double getScore(TextStatisticsExtractor extractor) {
        double score = 1.043 * Math.sqrt(extractor.polysyllableCount * 30.0 / extractor.sentCount) + 3.1291;
        return score;
    }

    @Override
    public ScorerType getScoreType() {
        return ScorerType.SMOG;
    }
}

class ColemanLiauIndex extends ReadabilityFormula {


    @Override
    public double getScore(TextStatisticsExtractor extractor) {
        double L = calculateCharPerHundredWords(extractor);
        double S = calculateNumSentencesPerHundredWords(extractor);
        double score = 0.0588 * L - 0.296 * S - 15.8;
        return score;
    }

    @Override
    public ScorerType getScoreType() {
        return ScorerType.CL;
    }

    private double calculateNumSentencesPerHundredWords(TextStatisticsExtractor extractor) {
        return 100.0 * extractor.sentCount / extractor.wordCount;
    }

    private double calculateCharPerHundredWords(TextStatisticsExtractor extractor) {
        return 100.0 * extractor.charCount / extractor.wordCount;
    }
}
