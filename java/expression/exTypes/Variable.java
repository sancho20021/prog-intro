package expression.exTypes;

import expression.*;

import java.util.Objects;

public class Variable extends MyExpression {
    private String name;

    public Variable(String name) {
        this.name = name;
    }

    @Override
    public int evaluate(int x) {
        return x;
    }

    @Override
    public int evaluate(int x, int y, int z) {
        switch (name) {
            case "x":
                return x;
            case "y":
                return y;
            default:
                return z;
        }
    }

    @Override
    public boolean equals(Object to) {
        if (to == null || to.getClass() != this.getClass()) {
            return false;
        }
        return name.equals(((Variable) to).name);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(name);
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public String toMiniString() {
        return toString();
    }
}
