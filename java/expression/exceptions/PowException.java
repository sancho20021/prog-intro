package expression.exceptions;

public class PowException extends ExpressionException {
    public PowException(int a, int b) {
        super("Invalid pow arguments: " + a + ", " + b);
    }
}
