package expression.exTypes;

import expression.MyExpression;

import java.util.Objects;

public abstract class BinarExp extends MyExpression {
    protected MyExpression a, b;

    protected BinarExp(MyExpression a, MyExpression b) {
        this.a = a;
        this.b = b;
    }

    protected abstract String getSymbol();

    protected abstract int calculate(int a, int b);

    public int evaluate(int x, int y, int z) {
        return calculate(a.evaluate(x, y, z), b.evaluate(x, y, z));
    }

    public int evaluate(int x) {
        return calculate(a.evaluate(x), b.evaluate(x));
    }

    @Override
    public boolean equals(Object to) {
        if (to == null || getClass() != to.getClass()) {
            return false;
        }
        final BinarExp other = (BinarExp) to;
        return Objects.equals(a, other.a)
                && Objects.equals(b, other.b);
    }

    @Override
    public int hashCode() {
        return Objects.hash(a, b, getClass());
    }

    @Override
    public String toString() {
        return "(" + a + " " + getSymbol() + " " + b + ")";
    }

    @Override
    public String toMiniString() {
        int aPr = getPriority(a);
        int bPr = getPriority(b);
        boolean hasBEps = b instanceof BinarExp && ((BinarExp) b).hasEps();
        StringBuilder str = new StringBuilder();
        str.append(withBrackets(aPr < firstPriority(), a.toMiniString()));
        str.append(" ").append(getSymbol()).append(" ");
        str.append(withBrackets(
                bPr < firstPriority() || (bPr == firstPriority()) && (hasBEps || isOrderImportant()),
                b.toMiniString()
        ));
        return str.toString();
    }

    private int getPriority(MyExpression a) {
        return a instanceof BinarExp ? ((BinarExp) a).firstPriority() : Integer.MAX_VALUE;
    }

    private String withBrackets(boolean isNec, String string) {
        return withString(isNec, "(") + string + withString(isNec, ")");
    }

    private String withString(boolean isNec, String string) {
        return isNec ? string : "";
    }

    protected abstract int firstPriority();

    protected abstract boolean hasEps();

    protected abstract boolean isOrderImportant();
}
