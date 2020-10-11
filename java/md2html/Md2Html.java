package md2html;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class Md2Html {
    private final static ToHtmlConverter TO_HTML_CONVERTER = new ToHtmlConverter();

    public static void main(String[] args) {
        //args = new String[]{"src/input.txt", "src/output.txt"};
        if (args.length != 2) {
            System.err.println("Error: 2 arguments required");
            return;
        }

        try {
            try (BufferedReader in = new BufferedReader(new FileReader(args[0], StandardCharsets.UTF_8))) {
                try (PrintWriter out = new PrintWriter(args[1], StandardCharsets.UTF_8)) {
                    StringBuilder currentParagraph = new StringBuilder();
                    String line;
                    while ((line = in.readLine()) != null) {
                        if (line.isEmpty()) {
                            out.print(TO_HTML_CONVERTER.getHtml(currentParagraph));
                            currentParagraph.setLength(0);
                        } else {
                            currentParagraph.append(line).append("\n");
                        }
                    }
                    out.print(TO_HTML_CONVERTER.getHtml(currentParagraph));
                } catch (FileNotFoundException e) {
                    System.err.println("Output file cannot be opened or created: " + e.getMessage());
                }
            } catch (FileNotFoundException e) {
                System.err.println("Input file not found: " + e.getMessage());
            }
        } catch (IOException e) {
            System.err.println("Input/Output exception: " + e.getMessage());
        }
    }
}
