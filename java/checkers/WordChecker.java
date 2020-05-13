package checkers;

public class WordChecker implements Checker {
    public static final Checker WORD_CHECKER = new WordChecker();

    public boolean isValid(int c) {
        return Character.getType(c) == Character.DASH_PUNCTUATION
                || Character.isAlphabetic(c) || c == '\'';
    }
}
