import java.io.*;
import java.util.*;

public class WordStatInputSort {
    static boolean isPartOfWord(char c) {
        return Character.isAlphabetic(c) || c == '\'' || Character.getType(c) == Character.DASH_PUNCTUATION;
    }

    public static void main(String[] args) {
        NavigableMap<String, Integer> map = new TreeMap<>();
        try {
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(new FileInputStream(new File(args[0])), "utf8")
            );
            String s;
            try {
                while ((s = in.readLine()) != null) {
                    for (int l = 0; l < s.length(); l++) {
                        if (!isPartOfWord(s.charAt(l))) {
                            continue;
                        }
                        int r = l + 1;
                        while (r < s.length() && isPartOfWord(s.charAt(r))) {
                            r++;
                        }
                        String word = s.substring(l, r).toLowerCase();
                        map.put(word, map.getOrDefault(word, 0) + 1);
                        l = r;
                    }
                }
            } finally {
                in.close();
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
                for (Map.Entry entry : map.entrySet()) {
                    out.write(entry.getKey() + " " + entry.getValue());
                    out.newLine();
                }
            } catch (IOException e) {
                System.err.println("Output error: " + e.getMessage());
            } finally {
                out.close();
            }
        } catch (FileNotFoundException e){
            System.err.println("Output file cannot be opened or created: " + e.getMessage());
        } catch (UnsupportedEncodingException e) {
            System.err.println("Encoding is unsupported");
        } catch (IOException e) {
            System.err.println("Output exception" + e.getMessage());
        }
    }
}
