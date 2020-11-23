package interview.easy;

import java.util.ArrayDeque;
import java.util.Deque;

public class isValidParentheses {
    /*
    Given a string s containing just the characters '(', ')', '{', '}', '[' and ']',
    determine if the input string is valid.
    An input string is valid if:
    Open brackets must be closed by the same type of brackets.
    Open brackets must be closed in the correct order.
    Clarification:
        if the string is valid?
        string only contains '(', ')', '{', '}', '[' and ']' ?
     */
    public boolean isValidParentheses(String s) {
        Deque<Integer> stack = new ArrayDeque<>();
        for (char c : s.toCharArray()) {
            if (c == '(') {
                stack.push(1);
            } else if (c == '{') {
                stack.push(2);
            } else if (c == '[') {
                stack.push(3);
            } else if (c == ')') {
                return stack.isEmpty() ? false : stack.pop() + -1 == 0;
            } else if (c == '}') {
                return stack.isEmpty() ? false : stack.pop() + -2 == 0;
            } else if (c == ']') {
                return stack.isEmpty() ? false : stack.pop() + -3 == 0;
            }
        }
        return stack.isEmpty();
    }
}
