package expression.checked;

import expression.BinarExp;
import expression.CommonExpression;
import expression.exceptions.PowException;

public class CheckedPow extends BinarExp {
    public CheckedPow(CommonExpression a, CommonExpression b) {
        super(a, b);
    }

    @Override
    protected int calculate(int a, int b) {
        if (a == 0 && b == 0 || b < 0) {
            throw new PowException(a, b);
        }
        int n = b, ans = 1, x = a;
        while (n > 0) {
            if (n % 2 == 1) {
                if (CheckedMultiply.hasOverflow(ans, x)) {
                    throw overflow(a, b);
                }
                ans *= x;
                n--;
            }
            if (n != 0 && CheckedMultiply.hasOverflow(x, x)) {
                throw overflow(a, b);
            }
            x *= x;
            n = n / 2;
        }
        return ans;
    }

    @Override
    protected boolean isOrderImportant() {
        return true;
    }

    @Override
    protected boolean hasEps() {
        return false;
    }

    @Override
    protected String getSymbol() {
        return "**";
    }

    @Override
    protected int firstPriority() {
        return 2;
    }
}
