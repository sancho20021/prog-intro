
import Scanner.BetaScanner;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.LinkedHashMap;
import java.util.Map;

import static checkers.WordChecker.WORD_CHECKER;

public class WordStatInput {

    public static void main(String[] args) {
        Map<String, Integer> map = new LinkedHashMap<>();
        try {
            BetaScanner scn = new BetaScanner(new File(args[0]), "utf8", WORD_CHECKER);
            try {
                while (scn.hasNextToken()) {
                    String word = scn.nextToken().toLowerCase();
                    map.put(word, map.getOrDefault(word, 0) + 1);
                }
            } finally {
                scn.close();
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
                for (Map.Entry<String, Integer> entry : map.entrySet()) {
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
