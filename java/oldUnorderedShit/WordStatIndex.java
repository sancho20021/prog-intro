import IntList.IntList;
import Scanner.BetaScanner;

import java.io.*;
import java.util.LinkedHashMap;
import java.util.Map;

import static checkers.WordChecker.WORD_CHECKER;

public class WordStatIndex {

    public static void main(String[] args) {
        Map<String, IntList> map = new LinkedHashMap<>();
        int count = 0;
        try {
            BetaScanner scn = new BetaScanner(new File(args[0]), WORD_CHECKER);
            try {
                while (scn.hasNextToken()) {
                    String word = scn.nextToken().toLowerCase();
                    IntList list = map.get(word);
                    if (list == null) {
                        map.put(word, new IntList(++count));
                    } else {

                        list.add(++count);
                    }
                }
            } finally {
                try {
                    scn.close();
                } catch (IOException e) {
                    System.err.println("I/O error occurred while closing Scanner: " + e.getMessage());
                }
            }
        } catch (FileNotFoundException e) {
            System.err.println("Input file not found: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Input error: " + e.getMessage());
        }
        try {
            BufferedWriter out = new BufferedWriter(
                    new OutputStreamWriter(new FileOutputStream(new File(args[1])), "utf8")
            );
            try {
                for (Map.Entry<String, IntList> entry : map.entrySet()) {
                    out.write(entry.getKey() + " " + entry.getValue().size() + " " + entry.getValue());
                    out.newLine();
                }
            } catch (IOException e) {
                System.err.println("Output error: " + e.getMessage());
            } finally {
                out.close();
            }
        } catch (FileNotFoundException e) {
            System.err.println("Output file cannot be opened or created: " + e.getMessage());
        } catch (UnsupportedEncodingException e) {
            System.err.println("Encoding is unsupported");
        } catch (IOException e) {
            System.err.println("Output exception" + e.getMessage());
        }
    }
}
