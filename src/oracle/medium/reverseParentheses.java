package oracle.medium;

import java.util.ArrayDeque;
import java.util.Deque;
/*
You are given a string s that consists of lower case English letters and brackets.

Reverse the strings in each pair of matching parentheses, starting from the innermost one.

Your result should not contain any brackets.
 */
public class reverseParentheses {
    public String reverseParentheses(String s) {
        Deque<Character> stack = new ArrayDeque<>();
        StringBuilder res = new StringBuilder();
        char[] arr = s.toCharArray();
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == ')') {
                StringBuilder cur = new StringBuilder();
                while (stack.peek() != '(') {
                    cur.append(stack.pop());
                }
                if (stack.peek() == '(') {
                    stack.pop();
                }
                char[] arr2 = cur.toString().toCharArray();
                for (char c : arr2) {
                    stack.push(c);
                }
            } else {
                stack.push(arr[i]);
            }
        }
        while (!stack.isEmpty()) {
            res.append(stack.pop());
        }
        return res.reverse().toString();
    }
}
