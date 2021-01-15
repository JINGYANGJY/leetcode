package intuit.calculator;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;
import com.google.common.collect.ImmutableMap;

public class calculator {
    /*
    输入string 只有加减和数字，如"1+2-3"。 用一个整数存数字前面的符号。
    Clarify:
        no unary operand

       - left to right
       intially res = 0 operator = '+'

        1 + 2 - 3
 res    1
 op       +
            3
              -
                3  0
         Time: O(n) n

      ()

      Clarify:  ( )

      - left to right XXXXXX
        operator has priority because of '('
        operatorLevel
             operator
             priority
        1   +   (   2   -   3   )
           + 0         '-' 1         0

        if same priority
            left to right
        otherwise
            high priority first

        Deque<Integer> operands    0

        Deque<operatorLevel> operators
        current prior                               0


                firstly check
                        if stack.peek priority >= current priority
                            calculate
                        add current operator
        priority
            '(' p + 1
            ')' p -
       Time: O(N) N is the length of the input

       Deque<String> operands
       Deque<operatorLevel> operators
                calculation
                    separate
                        digits
                            do calculation as Q2
                        string
                            append it after expression


    */
    public static String calculator1(String input) {
        /*
        Clarification: no unary operand
        left to right
            1 + 2 - 3
            1
                3
                  -
                    3
                    res = 0
            initially
               operator = '+'
           for each operand
                    calculate the current res
                    operator
                       update the operator
           Time: O(n)
           n is length of the expression
           讲：3'
           写：6'
         */
        char[] arr = input.toCharArray();
        int res = 0;
        char operator = '+';
        for(int i = 0; i < arr.length; i++) {
            if (Character.isDigit(arr[i])) {
                int operand = 0;
                while (i < arr.length && Character.isDigit(arr[i])) {
                    operand = operand * 10 + arr[i] - '0';
                    i++;
                }
                i -= 1;
                if (operator == '+') {
                    res += operand;
                } else {
                    res -= operand;
                }
            } else {
                operator = arr[i];
            }
        }
        return String.valueOf(res);
    }

    static class OperatorLevel {
        char operator;
        int priority;
        public OperatorLevel(char operator, int priority) {
            this.priority = priority;
            this.operator = operator;
        }
    }
    public static int calculator2(String input) {
        /* 2+(1-2+7-(5-2))
        * not from left to right
        *  2 + ( 1 - 2 + 7 - ( 5 - 2 ) )
        *
         * */
        char[] arr = input.toCharArray();
        Deque<Integer> operands = new ArrayDeque<>();
        Deque<OperatorLevel> operators = new ArrayDeque<>();
        int priority = 0;
        for (int i = 0; i < arr.length; i++) {
            if (Character.isDigit(arr[i])) {
                int operand = 0;
                while (i < arr.length && Character.isDigit(arr[i])) {
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
                while (!operators.isEmpty() && operators.peek().priority >= priority) {
                    helper(operands, operators);
                }
                operators.push(new OperatorLevel(arr[i], priority));
            }
        }
        while (!operators.isEmpty()) {
            helper(operands, operators);
        }
        return operands.pop();
    }

    private static void helper(Deque<Integer> operands, Deque<OperatorLevel> operators) {
        char c = operators.pop().operator;
        Integer rightOperand = operands.isEmpty() ? null : operands.pop();
        Integer leftOperand = operands.isEmpty() ? null : operands.pop();
        if (leftOperand != null && rightOperand != null) {
            if (c == '+') {
                operands.push(leftOperand + rightOperand);
            } else {
                operands.push(leftOperand - rightOperand);
            }
        }
    }

    public static String calculator3(String input, Map<String, Integer> map) {
        char[] arr = input.toCharArray();
        Deque<String> operands = new ArrayDeque<>();
        Deque<OperatorLevel> operators = new ArrayDeque<>();
        int priority = 0;
        for (int i = 0; i < arr.length; i++) {
            if (Character.isDigit(arr[i])) {
                int operand = 0;
                while (i < arr.length && Character.isDigit(arr[i])) {
                    operand = operand * 10 + arr[i++] - '0';
                }
                i -= 1;
                operands.push(String.valueOf(operand));
            } else if (Character.isLetter(arr[i])) {
                StringBuilder operand = new StringBuilder();
                while (i < arr.length && Character.isLetter(arr[i])) {
                    operand.append(String.valueOf(arr[i++]));
                }
                i -= 1;
                if (map.containsKey(operand.toString())) {
                    operands.push(String.valueOf(map.get(operand.toString())));
                } else {
                    operands.push(String.valueOf(operand));
                }
            } else if (arr[i] == '(' || arr[i] == ')') {
                if (arr[i] == '(') {
                    priority++;
                } else {
                    priority--;
                }
            } else {
                while (!operators.isEmpty() && operators.peek().priority >= priority) {
                    helper2(operands, operators);
                }
                operators.push(new OperatorLevel(arr[i], priority));
            }
        }

        while (!operators.isEmpty()) {
            helper2(operands, operators);
        }
        return operands.peek();
    }

    private static void helper2(Deque<String> operands, Deque<OperatorLevel> operators) {
        char operator = operators.pop().operator;
        int[] res = new int[1];
        StringBuilder variables = new StringBuilder();
        String rightOperand = operands.isEmpty() ? "" : operands.pop();
        String leftOperand = operands.isEmpty() ? "" : operands.pop();
        organize(res, variables, leftOperand, '+');
        organize(res, variables, rightOperand, operator);
        if (res[0] == 0) {
            operands.push(variables.toString());
        } else {
            operands.push(String.valueOf(res[0]) + variables.toString());
        }
    }

    private static void organize(int[] res, StringBuilder variables, String expression, char operator) {
        char op = '+';
        char[] arr = expression.toCharArray();
        for (int i = 0; i < arr.length; i++) {
            if (Character.isDigit(arr[i])) {
                int operand = 0;
                while (i < arr.length && Character.isDigit(arr[i])) {
                    operand = operand * 10 + arr[i++] - '0';
                }
                i -= 1;
                if (operator == '-') {
                    op = op == '+' ? '-' : '+';
                }
                if (op == '-') {
                    res[0] -= operand;
                } else {
                    res[0] += operand;
                }
            } else if (arr[i] == '+' || arr[i] == '-') {
                op = arr[i];
            } else {
                StringBuilder operand = new StringBuilder();
                while (i < arr.length && Character.isLetter(arr[i])) {
                    operand.append(String.valueOf(arr[i++]));
                }
                i -= 1;
                if (operator == '-') {
                    op = op == '+' ? '-' : '+';
                }
                if (op == '-') {
                    variables.append("-");
                    variables.append(operand);
                } else {
                    variables.append("+");
                    variables.append(operand);
                }
            }
        }
    }

    public static void main(String[] args) {
        System.out.println("----------------------calculator1------------------------------");
        System.out.println(calculator1("2+3-999"));
        System.out.println("----------------------calculator2------------------------------");
        System.out.println(calculator2("2+(1-2+7-(5-2))"));
        System.out.println("----------------------calculator3------------------------------");
        Map<String, Integer> map = new HashMap<>();
        map.put("a", 1);
        map.put("b", 2);;
        map.put("c", 3);
        System.out.println(calculator3("a-((b-c)-ded)", map));

        Map<String, String> mutableMap = new HashMap<>();
        mutableMap.put("United States", "Washington D.C.");

        // ImmutableMap.copyOf() creates a copy of the mutable map
        ImmutableMap<String, String> immutableMap = ImmutableMap
                .copyOf(mutableMap);
        System.out.println(immutableMap.get("United States"));
    }
}
