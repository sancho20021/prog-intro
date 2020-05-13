package expression.checked;

import expression.BinarExp;
import expression.CommonExpression;
import expression.exceptions.LogException;

public class CheckedLog extends BinarExp {
    public CheckedLog(CommonExpression a, CommonExpression b) {
        super(a, b);
    }

    @Override
    protected int calculate(int a, int b) {
        if (a <= 0 || b <= 0 || b == 1) {
            throw new LogException(a, b);
        }
        return calculateLog(a, b);
    }

    @Override
    protected boolean isOrderImportant() {
        return true;
    }

    @Override
    protected boolean hasEps() {
        return true;
    }

    @Override
    protected String getSymbol() {
        return "//";
    }

    @Override
    protected int firstPriority() {
        return 2;
    }

    static int calculateLog(int a, int b) {
        int log = 0;
        while (a >= b) {
            log++;
            a /= b;
        }
        return log;
    }
}
