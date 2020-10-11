package expression.exTypes;

import expression.MyExpression;

public class Divide extends BinarExp {
    public Divide(MyExpression a, MyExpression b) {
        super(a, b);
    }

    @Override
    protected String getSymbol() {
        return "/";
    }

    @Override
    protected int calculate(int a, int b) {
        return a / b;
    }

    @Override
    protected int firstPriority() {
        return 1;
    }

    @Override
    protected boolean hasEps() {
        return true;
    }

    @Override
    protected boolean isOrderImportant() {
        return true;
    }
}
