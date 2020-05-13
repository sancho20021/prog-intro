package expression.parser;

import expression.TripleExpression;
import expression.exceptions.Parser;

import java.util.Scanner;

public class Test {
    public static void main(String[] args) throws Exception{
        Parser parser = new ExpressionParser();
        TripleExpression expr = parser.parse(new Scanner(System.in).nextLine());
        System.out.println(expr.evaluate(Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE));
        System.out.println(expr.toMiniString());
    }
}
