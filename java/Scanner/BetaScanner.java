package Scanner;

import checkers.Checker;

import java.io.*;
import java.util.NoSuchElementException;

public class BetaScanner {
    private BufferedReader reader;
    private Checker checker;
    private int cache = 0;

    public BetaScanner(InputStream in, Checker checker) throws IOException {
        reader = new BufferedReader(new InputStreamReader(in));
        cache = reader.read();
        this.checker = checker;
    }

    public BetaScanner(InputStream in, String charset, Checker checker) throws IOException {
        try {
            reader = new BufferedReader(new InputStreamReader(in, charset));
            cache = reader.read();
            this.checker = checker;
        } catch (UnsupportedEncodingException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public BetaScanner(File f, Checker checker) throws IOException {
        reader = new BufferedReader(new InputStreamReader(new FileInputStream(f)));
        cache = reader.read();
        this.checker = checker;
    }

    public BetaScanner(File f, String charset, Checker checker) throws IOException {
        try {
            reader = new BufferedReader(new InputStreamReader(new FileInputStream(f), charset));
            cache = reader.read();
            this.checker = checker;
        } catch (UnsupportedEncodingException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public BetaScanner(String source, Checker checker) throws IOException {
        reader = new BufferedReader(new StringReader(source));
        cache = reader.read();
        this.checker = checker;
    }

    public boolean hasInput() {
        return cache != -1;
    }

    public boolean hasNextToken() throws IOException {
        while (hasInput() && !checker.isValid(cache)) {
            cache = reader.read();
        }
        return checker.isValid(cache);
    }

    public String nextToken() throws IOException {
        hasNextToken();
        StringBuilder str = new StringBuilder();
        while (hasInput() && checker.isValid(cache)) {
            str.append((char) cache);
            cache = reader.read();
        }
        return str.toString();
    }

    public int nextInt() throws IOException {
        return Integer.parseInt(nextToken());
    }

    public String nextLine() throws IOException {
        StringBuilder s = new StringBuilder();
        while (isNotEndOfLine()) {
            s.append((char) cache);
            cache = reader.read();
        }
        if (cache == -1 && s.length() == 0) {
            throw new NoSuchElementException("No line found");
        }
        skipEndOfLine();
        return s.toString();
    }

    public void skipLine() throws IOException {
        isEndOfLine();
        skipEndOfLine();
    }

    public boolean isEndOfLine() throws IOException {
        while (isNotEndOfLine() && !checker.isValid(cache)) {
            cache = reader.read();
        }
        return !hasInput() || cache == '\n' || cache == '\r';
    }

    private boolean isNotEndOfLine() {
        return hasInput() && cache != '\n' && cache != '\r';
    }

    private void skipEndOfLine() throws IOException {
        if (cache == '\r') {
            cache = reader.read();
        }
        if (cache == '\n') {
            cache = reader.read();
        }
    }

    public void close() throws IOException {
        reader.close();
    }
}
