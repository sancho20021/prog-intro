package expression.checked;

import expression.CommonExpression;
import expression.Divide;
import expression.exceptions.DBZException;

public class CheckedDivide extends Divide {
    public CheckedDivide(CommonExpression a, CommonExpression b) {
        super(a, b);
    }

    @Override
    protected int calculate(int a, int b) {
        if (a == Integer.MIN_VALUE && b == -1) {
            throw overflow(a, b);
        }
        if (b == 0) {
            throw new DBZException(a);
        }
        return a / b;
    }
}
