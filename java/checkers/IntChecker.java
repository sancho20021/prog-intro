package checkers;

public class IntChecker implements Checker {
    public static final Checker INT_CHECKER = new IntChecker();

    public boolean isValid(int c) {
        return !Character.isWhitespace(c) && c!=-1;
    }
}
