package expression;

import expression.exceptions.OverflowException;

import java.util.Objects;

public abstract class BinarExp extends UnarBinarExp {
    private CommonExpression a, b;

    protected BinarExp(CommonExpression a, CommonExpression b) {
        this.a = a;
        this.b = b;
    }

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

    protected abstract boolean isOrderImportant();

    protected abstract boolean hasEps();

    protected OverflowException overflow(int a, int b) {
        return new OverflowException(a + " " + getSymbol() + " " + b);
    }
}
