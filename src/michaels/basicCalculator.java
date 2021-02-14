package michaels;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

public class basicCalculator {
    static class OperatorWithPriority {
        char operator;
        int priority;
        public OperatorWithPriority(char operator, int priority) {
            this.operator = operator;
            this.priority = priority;
        }
    }
    public static String calculator(String input) {
       /*
        "a + (2a + c)"
         3a + c
         parathesis

         - ()
         calculation sequence may be changed due to ()
         priority
         "a + ( 2a + c )"
            0      1
          priority 0   1   0
          operands stack     a    2a + c
          operator stack   <+, 0>


          calculation
                2a                   a
                c                  2a + c
               <+, 1>               <+, 0>
               2a + c              a + 2a + c -> 3a + c
               simplify
                    Map<String, Integer> <representation, number>
        */
        // Assume input only contains digit letters [a - z] [A - Z] [+ - * /]
        int priority = 0;
        Deque<OperatorWithPriority> operators = new ArrayDeque<>();
        Deque<String> operands = new ArrayDeque<>();
        char[] arr = input.toCharArray();
        for (int i = 0; i < arr.length; i++) {
            if (Character.isLetter(arr[i]) || Character.isDigit(arr[i])) {
                StringBuilder operand = new StringBuilder();
                while (i < arr.length && (Character.isLetter(arr[i]) || Character.isDigit(arr[i]))) {
                    operand.append(String.valueOf(arr[i++]));
                }
                i -= 1;
                operands.push(operand.toString());
            } else if (arr[i] == '(' || arr[i] == ')') {
                if (arr[i] == '(') {
                    priority++;
                } else {
                    priority--;
                }
            } else {
                while (!operators.isEmpty() && operators.peek().priority >= priority) {
                    help(operands, operators);
                }
                operators.push(new OperatorWithPriority(arr[i], priority));
            }
        }

        while(!operators.isEmpty()) {
            help(operands, operators);
        }
        return operands.isEmpty() ? null : operands.peek();
    }



    private static void help(Deque<String> operands, Deque<OperatorWithPriority> operators) {
        Map<String, Integer> map = new HashMap<>();
        String rightOperand = operands.isEmpty() ? "" : operands.pop();
        String leftOperand = operands.isEmpty() ? "" : operands.pop();
        char[] left = leftOperand.toCharArray();
        StringBuilder operand = new StringBuilder();
        int factor = 0;
        char op = '+';
        for (int i = 0; i < left.length; i++) {
            if (Character.isLetter(left[i]) || Character.isDigit(left[i])) {
                while (i < left.length && (Character.isLetter(left[i]) || Character.isDigit(left[i]))) {
                    if (Character.isDigit(left[i])) {
                        factor = factor * 10 + left[i++] - '0';
                    } else {
                        operand.append(String.valueOf(left[i++]));
                    }
                }
                factor = factor > 0 ? factor : 1;
                if (op == '+') {
                    map.put(operand.toString(), map.getOrDefault(operand.toString(), 0) + factor);
                } else {
                    map.put(operand.toString(), map.getOrDefault(operand.toString(), 0) - factor);
                }
                i -= 1;
                factor = 0;
                operand = new StringBuilder();
            } else {
                op = left[i];
            }
        }
        char operator = operators.pop().operator;
        char[] right = rightOperand.toCharArray();
        operand = new StringBuilder();
        factor = 0;
        op = operator;
        for (int i = 0; i < right.length; i++) {
            if (Character.isLetter(right[i]) || Character.isDigit(right[i])) {
                while (i < right.length && (Character.isLetter(right[i]) || Character.isDigit(right[i]))) {
                    if (Character.isDigit(right[i])) {
                        factor = factor * 10 + right[i++] - '0';
                    } else {
                        operand.append(String.valueOf(right[i++]));
                    }
                }
                i -= 1;
                factor = factor > 0 ? factor : 1;
                if (op == '+') {
                    map.put(operand.toString(), map.getOrDefault(operand.toString(), 0) + factor);
                } else {
                    map.put(operand.toString(), map.getOrDefault(operand.toString(), 0) - factor);
                }
                factor = 0;
                operand = new StringBuilder();
            } else {
                op = right[i];
                if (operator == '-') {
                  op = op == '-' ? '+' : '-';
                }
            }
        }
        StringBuilder res = new StringBuilder();
        for (String key : map.keySet()) {
            int count = map.get(key);
            if (count >= 0) {
                if (res.length() > 0) {
                    res.append("+");
                }
            } else if (count < 0) {
                res.append("-");
                count = Math.abs(count);
            }
            if (count > 1) {
                res.append(String.valueOf(count));
            }
            res.append(key);
        }
        operands.push(res.toString());
    }

    public static void main(String[] args) {
        String input = "a-(6a-b)";
        System.out.println(calculator(input));
    }
}
