package interview.medium;

import java.util.ArrayDeque;
import java.util.Deque;

/*
Evaluate the value of an arithmetic expression in Reverse Polish Notation.
Valid operators are +, -, *, /. Each operand may be an integer or another expression.
Note:
Division between two integers should truncate toward zero.
The given RPN expression is always valid.
That means the expression would always evaluate to a result and there won't be any divide by zero operation.
 */
public class evalRPN {

    /*
    input: string[] Reverse Polish Notation
    output: result of this reverse polish notation
    Clarification:
        only contains "+ - * /" and integers?
        any empty space?
    assumption:
        RPN expression is valid
        must can get a result
     Example:
        "2 + 3 / 4"
        RPN: 2 3 + 4 /
        Sol: stack
                when meet "+ - / *"
                poll operand from stack
                do "+ - * /"
                offer to stack

            stack    2  3 |   5  4 | 1
                       +        /
                return 1
        Time: O(n)
     */
    public int evalRPN(String[] tokens) {
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = 0; i < tokens.length; i++) {
            if (tokens[i] == null || tokens[i].length() == 0) {
                continue;
            }
            if (tokens[i].equals("+") || tokens[i].equals("-") || tokens[i].equals("*") || tokens[i].equals("/")) {
                Integer second = stack.isEmpty() ? null : stack.pop();
                Integer first = stack.isEmpty() ? null : stack.pop();
                if (first == null || second == null) {
                    continue;
                }
                Integer result = tokens[i].equals("+")? first + second : tokens[i].equals("-") ?
                        first - second : tokens[i].equals("*") ? first * second : second != 0 ? first / second : 0;
                stack.push(result);
            } else {
                Integer operand = Integer.valueOf(tokens[i]);
                stack.push(operand);
            }
        }
        return stack.isEmpty() ? -1: stack.peek();
    }
}
