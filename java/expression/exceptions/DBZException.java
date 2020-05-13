package expression.exceptions;

public class DBZException extends ExpressionException {
    public DBZException(int a) {
        super("Division by zero: " + a + "/ 0");
    }
}
