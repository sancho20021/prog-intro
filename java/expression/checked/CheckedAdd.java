package expression.checked;

import expression.Add;
import expression.CommonExpression;

public class CheckedAdd extends Add {
    public CheckedAdd(CommonExpression a, CommonExpression b) {
        super(a, b);
    }

    @Override
    protected int calculate(int a, int b) {
        if (a > 0) {
            if (b > Integer.MAX_VALUE - a) {
                throw overflow(a, b);
            }
        } else {
            if (b < Integer.MIN_VALUE - a) {
                throw overflow(a, b);
            }
        }
        return a + b;
    }
}
