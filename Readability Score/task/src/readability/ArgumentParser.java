package readability;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ArgumentParser {

    final String inFile;

    public ArgumentParser(String[] args) {
        this.inFile = args[0];
    }

    public String getFileContent(boolean verbose) throws FileNotFoundException {
        File file = new File(inFile);
        Scanner scanner = new Scanner(file);
        StringBuilder builder = new StringBuilder();
        while (scanner.hasNext()) {
            builder.append(scanner.nextLine());
        }
        String content = builder.toString();
        if (verbose) {
            System.out.printf("The text is%n%s%n", content);
        }
        return content;
    }
}
