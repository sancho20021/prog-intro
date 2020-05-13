package expression;

import expression.exceptions.OverflowException;

import java.util.Objects;

public abstract class UnarExp extends UnarBinarExp {
    private CommonExpression a;

    protected UnarExp(CommonExpression a) {
        this.a = a;
    }

    protected abstract int calculate(int a);

    public int evaluate(int x, int y, int z) {
        return calculate(a.evaluate(x, y, z));
    }

    public int evaluate(int x) {
        return calculate(a.evaluate(x));
    }

    @Override
    public boolean equals(Object to) {
        if (to == null || getClass() != to.getClass()) {
            return false;
        }
        final UnarExp other = (UnarExp) to;
        return Objects.equals(a, other.a);
    }

    @Override
    public int hashCode() {
        return Objects.hash(a, getClass());
    }

    @Override
    public String toString() {
        return "(" + getSymbol() + a + ")";
    }

    @Override
    public String toMiniString() {
        int aPr = getPriority(a);
        return getSymbol() + withBrackets(aPr < firstPriority(), a.toMiniString());
    }

    protected OverflowException overflow(int a){
        return new OverflowException(getSymbol() + " " + a);
    }
}
