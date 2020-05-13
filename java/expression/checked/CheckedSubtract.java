package expression.checked;

import expression.CommonExpression;
import expression.Subtract;
import expression.exceptions.OverflowException;

public class CheckedSubtract extends Subtract {
    public CheckedSubtract(CommonExpression a, CommonExpression b) {
        super(a, b);
    }

    @Override
    protected int calculate(int a, int b) {
        if (a >= 0) {
            //a-b>intMax
            //a-intMax>b
            if (a - Integer.MAX_VALUE > b) {
                throw overflow(a, b);
            }
        } else {
            //a-b<intMin
            //a-intMin<b
            if (a - Integer.MIN_VALUE < b) {
                throw overflow(a, b);
            }
        }
        return a - b;
    }
}
