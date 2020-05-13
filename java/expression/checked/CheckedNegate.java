package expression.checked;

import expression.CommonExpression;
import expression.Negate;

public class CheckedNegate extends Negate {
    public CheckedNegate(CommonExpression a) {
        super(a);
    }

    @Override
    protected int calculate(int a) {
        if (a == Integer.MIN_VALUE) {
            throw overflow(a);
        }
        return -a;
    }
}
