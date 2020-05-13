package expression;

public class Negate extends UnarExp {

    public Negate(CommonExpression a) {
        super(a);
    }

    @Override
    protected int calculate(int a) {
        return -a;
    }

    @Override
    protected String getSymbol() {
        return "-";
    }

    @Override
    protected int firstPriority() {
        return 1;
    }

}
