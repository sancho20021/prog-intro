package expression.checked;

import expression.CommonExpression;
import expression.Multiply;

public class CheckedMultiply extends Multiply {
    public CheckedMultiply(CommonExpression a, CommonExpression b) {
        super(a, b);
    }

    @Override
    protected int calculate(int a, int b) {
        if (hasOverflow(a, b)) {
            throw overflow(a, b);
        }
        return a * b;
    }

    static boolean hasOverflow(int a, int b) {
        int r = a * b;
        return ((b != 0) && (r / b != a)) || (a == Integer.MIN_VALUE && b == -1);
    }
}
