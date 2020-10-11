package expression.exTypes;

import expression.MyExpression;

import java.util.Objects;

public class Const extends MyExpression {
    private int value;

    public Const(int value) {
        this.value = value;
    }

    @Override
    public int evaluate(int x) {
        return value;
    }

    @Override
    public int evaluate(int x, int y, int z) {
        return value;
    }

    @Override
    public boolean equals(Object to) {
        if (to == null || getClass() != to.getClass()) {
            return false;
        }
        return value == ((Const) to).value;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(value);
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }

    @Override
    public String toMiniString() {
        return toString();
    }
}
