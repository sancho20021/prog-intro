package expression.parser;

import expression.CommonExpression;
import expression.Const;
import expression.Variable;
import expression.checked.*;
import expression.exceptions.Parser;

import java.util.List;
import java.util.Map;


public class ExpressionParser extends BaseParser implements Parser {
    String curOperator;
    private final static List<String> VARS = List.of("x", "y", "z");
    private final static Map<String, Integer> OP_PRIORITIES = Map.of(
            "+", 0,
            "-", 0,
            "*", 1,
            "/", 1,
            "**", 2,
            "//", 2
    );
    private final static List<String> UNARY_OPS = List.of("log2", "pow2");
    private final static int START_LEVEL = 0;
    private final static int LAST_LEVEL = 3;

    @Override
    public CommonExpression parse(String expression) {
        setSource(new StringSource(expression));
        nextChar();
        curOperator = null;
        CommonExpression expr = parseLevel(START_LEVEL);
        if (ch != '\0') {
            throw error("Unexpected ')'");
        }
        return expr;
    }

    private CommonExpression parseLevel(final int level) {
        if (level == LAST_LEVEL) {
            return parseLexem();
        }
        CommonExpression expression = parseLevel(level + 1);
        skipWhitespaces();
        while (curOperator!=null || ch != '\0' && ch != ')') {
            if (curOperator == null) {
                takeOperator();
            }
            if (OP_PRIORITIES.get(curOperator) != level) {
                break;
            }
            String nowOperator = curOperator;
            curOperator = null;
            expression = createExpression(nowOperator, expression, parseLevel(level + 1));
            skipWhitespaces();
        }
        return expression;
    }

    private void takeOperator() {
        char first = ch;
        nextChar();
        if (OP_PRIORITIES.containsKey(Character.toString(first) + ch)) {
            curOperator = Character.toString(first) + ch;
            nextChar();
        } else if (OP_PRIORITIES.containsKey(Character.toString(first))) {
            curOperator = Character.toString(first);
        } else {
            throw error("Unexpected symbol '" + first + "'");
        }
    }

    private CommonExpression parseLexem() {
        skipWhitespaces();
        if (test('(')) {
            CommonExpression expression = parseLevel(START_LEVEL);
            expect(')');
            return expression;
        } else if (test('-')) {
            if (between('0', '9')) {
                return getConst(true);
            } else {
                return new CheckedNegate(parseLexem());
            }
        } else if (between('0', '9')) {
            return getConst(false);
        } else {
            StringBuilder str = new StringBuilder();
            while (between('a', 'z') || between('0', '9')) {
                str.append(ch);
                nextChar();
            }
            String lexem = str.toString();
            if(!lexem.isEmpty()) {
                if (UNARY_OPS.contains(lexem)) {
                    return createExpression(lexem, parseLexem());
                } else {
                    if (VARS.contains(lexem)) {
                        return new Variable(lexem);
                    } else {
                        throw error("Invalid argument or operator \"" + lexem + "\"");
                    }
                }
            } else{
                throw error("Expected argument, found " + (ch!='\0' ? ("'" + ch + "'") : "end of file"));
            }
        }
    }

    private CommonExpression createExpression(String unaryOp, CommonExpression expression) {
        switch (unaryOp) {
            case "log2":
                return new CheckedLog2(expression);
            case "pow2":
                return new CheckedPow2(expression);
            default:
                throw error("Unrecognized operator: " + unaryOp);
        }
    }

    private CommonExpression createExpression(String binaryOp, CommonExpression a, CommonExpression b) {
        switch (binaryOp) {
            case "+":
                return new CheckedAdd(a, b);
            case "-":
                return new CheckedSubtract(a, b);
            case "*":
                return new CheckedMultiply(a, b);
            case "/":
                return new CheckedDivide(a, b);
            case "**":
                return new CheckedPow(a, b);
            case "//":
                return new CheckedLog(a, b);
            default:
                throw error("Unrecognized operator: " + binaryOp);
        }
    }

    private Const getConst(boolean isNegative) {
        StringBuilder str = new StringBuilder();
        if (isNegative) {
            str.append("-");
        }
        while (between('0', '9')) {
            str.append(ch);
            nextChar();
        }
        try {
            return new Const(Integer.parseInt(str.toString()));
        } catch (NumberFormatException e) {
            throw error("Invalid number");
        }
    }

    private boolean isOperator(String op) {
        return OP_PRIORITIES.containsKey(op);
    }

    private void skipWhitespaces() {
        while (Character.isWhitespace(ch)) {
            nextChar();
        }
    }
}
