package expression;

public class Subtract extends BinarExp {
    public Subtract(CommonExpression a, CommonExpression b) {
        super(a, b);
    }

    @Override
    protected String getSymbol() {
        return "-";
    }

    @Override
    protected int calculate(int a, int b) {
        return a - b;
    }

    @Override
    protected int firstPriority() {
        return 0;
    }

    @Override
    protected boolean hasEps() {
        return false;
    }

    @Override
    protected boolean isOrderImportant() {
        return true;
    }
}
