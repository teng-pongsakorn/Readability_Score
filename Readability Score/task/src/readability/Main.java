package readability;


import java.io.FileNotFoundException;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        try {
            ArgumentParser parser = new ArgumentParser(args);
            String content = parser.getFileContent(true);
            TextStatisticsExtractor extractor = new TextStatisticsExtractor();
            extractor.extract(content);
            extractor.printResult();

            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter the score you want to calculate (ARI, FK, SMOG, CL, all): ");
            ScorerType type = ScorerType.valueOf(scanner.nextLine().toUpperCase());
            System.out.println("");

            ReadabilityScorer scorer = new ReadabilityScorer(extractor, type);
            scorer.printReport();

        } catch (FileNotFoundException e) {
            System.out.println("Invalid File");
        }
    }
}
