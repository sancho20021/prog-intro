package expression.checked;

import expression.CommonExpression;
import expression.UnarExp;
import expression.exceptions.PowException;

public class CheckedPow2 extends UnarExp {
    public CheckedPow2(CommonExpression a) {
        super(a);
    }

    @Override
    protected int calculate(int a) {
        if (a > 31) {
            throw overflow(a);
        }
        if (a < 0) {
            throw new PowException(2, a);
        }
        return 1 << a;
    }

    @Override
    protected String getSymbol() {
        return "pow2";
    }

    @Override
    protected int firstPriority() {
        return 2;
    }

}
