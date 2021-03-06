package intuit.calculator;

import java.util.ArrayDeque;
import java.util.Deque;

public class Calculator2 {
    public static int solution(String expression) {
        Deque<Integer> operands = new ArrayDeque<>();
        Deque<OperatorLevel> operator = new ArrayDeque<>();
        int level = 0;
        ExpressionTokenizer expressionTokenizer = new ExpressionTokenizer(expression);
        for (Token t = expressionTokenizer.nextToken(); t != null; t = expressionTokenizer.nextToken()) {
            if (t.getType() == Token.Type.NUMBER) {
                operands.push(t.intValue());
            } else if (t.getType() == Token.Type.PARENTHESIS) {
                if (t.charValue() == '(') {
                    level++;
                } else {
                    level--;
                }
            } else {
                while (!operator.isEmpty() && operator.peek().level >= level) {
                    calculate(operands, operator);
                }
                operator.push(new OperatorLevel(t.charValue(), level));
            }
        }
        while (!operator.isEmpty()) {
            calculate(operands, operator);
        }
        return operands.peek();
    }
    private static void calculate(Deque<Integer> operands, Deque<OperatorLevel> operator) {
        char c = operator.pop().operator;
        int rightOperand = operands.isEmpty() ? 0 : operands.pop();
        int leftOperand = operands.isEmpty() ? 0 : operands.pop();
        if (c == '+') {
            operands.push(leftOperand + rightOperand);
        } else {
            operands.push(leftOperand - rightOperand);
        }
    }
    static class OperatorLevel {
        char operator;
        int level;
        public OperatorLevel(char operator, int level) {
            this.operator = operator;
            this.level = level;
        }
    }
    static class ExpressionTokenizer {
        int start;
        char[] arr;
        public ExpressionTokenizer(String express) {
            start = 0;
            arr = express.toCharArray();
        }
        public Token nextToken() {
            int res = 0;
            if (start >= arr.length) return null;
            if (Character.isDigit(arr[start])) {
                while (start < arr.length && Character.isDigit(arr[start])) {
                    res *= 10;
                    res += arr[start] - '0';
                    start++;
                }
                return new Token(Token.Type.NUMBER, res, ' ');
            } else if (arr[start] == ')' || arr[start] == '(') {
                Token r = new Token(Token.Type.PARENTHESIS, 0, arr[start]);
                start++;
                return r;
            } else {
                Token r = new Token(Token.Type.OPERATOR, 0, arr[start]);
                start++;
                return r;
            }
        }
    }
    static class Token {
        enum Type {
            NUMBER,
            OPERATOR,
            PARENTHESIS
        }
        public Type type;
        public int value;
        public char c;
        public Token(Type type, int value, char c) {
            this.type = type;
            this.value = value;
            this.c = c;
        }
        public Type getType() {
            return type;
        }
        public int intValue() {
            return value;
        }
        public char charValue() {
            return c;
        }
    }

    public static void main(String[] args) {
        String s = "2+(1-2+7-(5-2))";
        System.out.println(solution(s));
        System.out.println(calculator2(s));
    }

    public static int calculator2(String input) {
        Deque<Integer> operands = new ArrayDeque<>();
        Deque<OperatorLevel> operators = new ArrayDeque<>();
        char[] arr = input.toCharArray();
        int priority = 0;
        for (int i = 0; i < arr.length; i++) {
            if (Character.isDigit(arr[i])) {
                int operand = 0;
                while(i < arr.length && (Character.isDigit(arr[i]))) {
                    operand = operand * 10 + arr[i++] - '0';
                }
                i -= 1;
                operands.push(operand);
            } else if (arr[i] == '(' || arr[i] == ')') {
                if (arr[i] == '(') {
                    priority++;
                } else {
                    priority--;
                }
            } else {
                while (!operators.isEmpty() && operators.peek().level >= priority) {
                    helper(operands, operators);
                }
                operators.push(new OperatorLevel(arr[i], priority));
            }
        }
        while (!operators.isEmpty()) {
            helper(operands, operators);
        }
        return operands.peek();
    }

    private static void helper(Deque<Integer> operands, Deque<OperatorLevel> operators) {
        char operator = operators.pop().operator;
        Integer rightOperand = operands.isEmpty() ? 0 : operands.pop();
        Integer leftOperand = operands.isEmpty() ? 0 : operands.pop();
        if (operator == '+') {
            operands.push(leftOperand + rightOperand);
        } else {
            operands.push(leftOperand - rightOperand);
        }
    }


}
