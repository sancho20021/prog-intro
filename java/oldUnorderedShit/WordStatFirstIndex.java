import Scanner.BetaScanner;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.LinkedHashMap;
import java.util.Map;

import static checkers.WordChecker.WORD_CHECKER;

public class WordStatFirstIndex {

    public static void main(String[] args) {
        Map<String, WordStatList> map = new LinkedHashMap<>();
        try {
            BetaScanner in = new BetaScanner(new File(args[0]), "utf8", WORD_CHECKER);
            try {
                int lineCounter = 1;
                int wordCounter = 1;
                while (in.hasNextToken()) {
                    String word = in.nextToken().toLowerCase();
                    map.computeIfAbsent(word, k -> new WordStatList()).add(lineCounter, wordCounter);
                    wordCounter++;
                    if (in.isEndOfLine()) {
                        wordCounter = 1;
                        lineCounter++;
                    }
                }
            } finally {
                in.close();
            }
        } catch (FileNotFoundException e) {
            System.err.println("Input file not found: " + e.getMessage());
            return;
        } catch (IOException e) {
            System.err.println("Input error: " + e.getMessage());
            return;
        }
        try {
            BufferedWriter out = new BufferedWriter(
                    new OutputStreamWriter(new FileOutputStream(new File(args[1])), StandardCharsets.UTF_8)
            );
            try {
                for (Map.Entry<String, WordStatList> entry : map.entrySet()) {
                    out.write(entry.getKey() + " " + entry.getValue());
                    out.newLine();
                }
            } finally {
                out.close();
            }
        } catch (FileNotFoundException e) {
            System.err.println("Output file cannot be opened or created: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Output error" + e.getMessage());
        }
    }
}
