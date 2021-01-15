package amazon.oa;

import java.util.ArrayDeque;
import java.util.Deque;

public class Leet682_BaseballGame {
    public int calPoints(String[] ops) {
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = 0; i < ops.length; i++) {
            if (ops[i].equals("C")) {
                if (stack.isEmpty()) continue;
                stack.pop();
            } else if (ops[i].equals("D")) {
                if (stack.isEmpty()) continue;
                stack.push(stack.peek() * 2);
            } else if (ops[i].equals("+") || ops[i].equals("-")) {
                String operator = ops[i];
                Integer rightOperand = stack.isEmpty() ? 0 : stack.pop();
                Integer leftOperand = stack.isEmpty() ? 0 : stack.peek();
                stack.push(rightOperand);
                if (operator.equals("+")) {
                    stack.push(leftOperand + rightOperand);
                } else {
                    stack.push(leftOperand - rightOperand);
                }
            } else {
                stack.push(Integer.valueOf(ops[i]));
            }
        }
        int res = 0;
        while (!stack.isEmpty()) {
            res += stack.pop();
        }
        return res;
    }
}
