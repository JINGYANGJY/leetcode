package amazon.Stack;

import java.util.ArrayDeque;
import java.util.Deque;

public class Leet772_BasicCalculatorIII {
    class OperatorWithLevel {
        char operator;
        int priority;
        public OperatorWithLevel(char operator, int priority) {
            this.operator = operator;
            this.priority = priority;
        }
    }
    public int calculate(String s) {
        Deque<Integer> operands = new ArrayDeque<>();
        Deque<OperatorWithLevel> operators = new ArrayDeque<>();
        char[] arr = s.toCharArray();
        int priority = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == ' ') {
                continue;
            } else if (Character.isDigit(arr[i])) {
                int operand = 0;
                while (i < arr.length && Character.isDigit(arr[i])) {
                    operand = operand * 10 + arr[i++] - '0';
                }
                i -= 1;
                operands.push(operand);
            } else if (arr[i] == '(' || arr[i] == ')') {
                if (arr[i] == '(') {
                    priority += 2;
                } else {
                    priority -= 2;
                }
            } else {
                int curPriority = (arr[i] == '*' || arr[i] =='/') ? priority + 1 : priority;
                while (!operators.isEmpty() && operators.peek().priority >= curPriority) {
                    calculator(operands, operators);
                }
                if (arr[i] == '*' || arr[i] == '/') {
                    operators.push(new OperatorWithLevel(arr[i], priority + 1));
                } else {
                    operators.push(new OperatorWithLevel(arr[i], priority));
                }

            }
        }
        while (!operators.isEmpty()) {
            calculator(operands, operators);
        }
        return operands.peek();
    }

    private void calculator(Deque<Integer> operands, Deque<OperatorWithLevel> operators) {
        char operator = operators.pop().operator;
        Integer rightOperand = operands.isEmpty() ? 0 : operands.pop();
        Integer leftOperand = operands.isEmpty() ? 0 : operands.pop();
        if (operator == '*') {
            operands.push(leftOperand * rightOperand);
        } else if (operator == '/') {
            operands.push(leftOperand / rightOperand);
        } else if (operator == '+') {
            operands.push(leftOperand + rightOperand);
        } else {
            operands.push(leftOperand - rightOperand);
        }
    }
}
