package expression;

public abstract class UnarBinarExp implements CommonExpression {
    protected abstract String getSymbol();

    protected int getPriority(CommonExpression a) {
        return a instanceof UnarBinarExp ? ((UnarBinarExp) a).firstPriority() : Integer.MAX_VALUE;
    }

    protected String withBrackets(boolean isNec, String string) {
        return withString(isNec, "(") + string + withString(isNec, ")");
    }

    protected String withString(boolean isNec, String string) {
        return isNec ? string : "";
    }

    protected abstract int firstPriority();
}
