package readability;

enum ScorerType {
    ARI("Automated Readability Index"),
    FK("Flesch–Kincaid readability tests"),
    SMOG("Simple Measure of Gobbledygook"),
    CL("Coleman–Liau index"),
    ALL("All");

    private String fullname;
    ScorerType(String fullname) {
        this.fullname = fullname;
    }

    @Override
    public String toString() {
        return fullname;
    }
}

public class ScorerFactory {

    public static ReadabilityFormula getReadabilityScorer(ScorerType type) {
        switch (type) {
            case ARI:
                return new AutomatedReadabilityIndex();
            case FK:
                return new FleschKincaidReadabilityTests();
            case SMOG:
                return new SimpleMeasureOfGobbledygook();
            case CL:
                return new ColemanLiauIndex();
        }
        return null;
    }
}
