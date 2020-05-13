package expression.checked;

import expression.CommonExpression;
import expression.UnarExp;
import expression.exceptions.LogException;

public class CheckedLog2 extends UnarExp {
    public CheckedLog2(CommonExpression a) {
        super(a);
    }

    @Override
    protected int calculate(int a) {
        if (a <= 0) {
            throw new LogException(a, 2);
        }
        return CheckedLog.calculateLog(a, 2);
    }

    @Override
    protected String getSymbol() {
        return "log2";
    }

    @Override
    protected int firstPriority() {
        return 2;
    }

}
