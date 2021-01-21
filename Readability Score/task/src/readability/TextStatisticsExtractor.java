package readability;


import java.util.Arrays;

public class TextStatisticsExtractor {

    private String sentenceEndingRegex = "[!.?]";
    private String wordSeperatorRegex = "[.?!\\s]+";
    private String whitespaceRegex = "\\s+";
    final private int polysyllableSize = 3;

    String content;
    int charCount;
    int wordCount;
    int sentCount;
    int syllableCount;
    int polysyllableCount;

    public TextStatisticsExtractor() {
        this.content = "";
        this.charCount = 0;
        this.wordCount = 0;
        this.sentCount = 0;
        this.syllableCount = 0;
        this.polysyllableCount = 0;
    }

    public void extract(String content) {
        charCount = getNumChar(content);
        wordCount = getNumWord(content);
        sentCount = getNumSentence(content);
        syllableCount = getNumSyllable(content);
        polysyllableCount = getNumPolysyllable(content);
    }

    public void printResult() {
        System.out.printf("Words: %d%nSentences: %d%nCharacters: %d%nSyllables: %d%nPolysyllables: %d%n",
                wordCount, sentCount, charCount, syllableCount, polysyllableCount);
    }

    private int getNumPolysyllable(String content) {
        String[] words = getWords(content);
        int[] syllableCountByWords = new int[words.length];
        for (int i = 0; i < words.length; i++) {
            syllableCountByWords[i] = countSyllable(words[i]) >= polysyllableSize ? 1 : 0;
        }
        return Arrays.stream(syllableCountByWords).sum();
    }

    private int getNumSyllable(String content) {
        String[] words = getWords(content);
        int[] syllableCountByWords = new int[words.length];
        for (int i = 0; i < words.length; i++) {
            syllableCountByWords[i] = countSyllable(words[i]);
        }
        return Arrays.stream(syllableCountByWords).sum();
    }

    private int countSyllable(String word) {
        word = word.toLowerCase();
        if (isWord(word)) {
            int vowelCount = word.replaceAll("[^aeiouy]", "").length();
            vowelCount -= countDoubleVowels(word);
            vowelCount -= hasLastE(word);
            vowelCount = Math.max(vowelCount, 1);
            return vowelCount;
        }
        return 1;
    }

    private int hasLastE(String word) {
        return word.matches(".+e") ? 1 : 0;
    }

    private int countDoubleVowels(String word) {
        String vowel = "aeiouy";
        int count = 0;
        char prev = word.charAt(0);
        for (int i = 1; i < word.length(); i++) {
            if (vowel.contains(""+prev) && vowel.contains(""+ word.charAt(i))) {
                count++;
            }
            prev = word.charAt(i);
        }
        return count;
    }

    private boolean isWord(String word) {
        return word.replaceAll("[^a-zA-Z]", "").length() > 0;
    }

    private int getNumSentence(String content) {
        String[] sentences = getSentences(content);
        return sentences.length;
    }

    private String[] getSentences(String content) {
        return removeHeadTailWhiteSpace(content).split(sentenceEndingRegex);
    }

    private String removeHeadTailWhiteSpace(String content) {
        return content.trim();
    }

    private int getNumWord(String content) {
        String[] words = getWords(content);
        return words.length;
    }

    private String[] getWords(String content) {
        String[] words = removeHeadTailWhiteSpace(content).split(wordSeperatorRegex);
        return words;
    }

    private int getNumChar(String content) {
        String charContent = content.replaceAll(whitespaceRegex, "");
        return charContent.length();
    }
}
