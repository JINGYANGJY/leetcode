package amazon.Stack;

import java.util.ArrayDeque;
import java.util.Deque;

public class Leet856_ScoreofParentheses {
    public int scoreOfParentheses(String S) {
       /*
       ( ( ) ( ( ) ) )
       -> match right par,,, with left par...
       -> stack FILO
          Deque<Character> stack: maintain '('

          ( 1 2
             (2 + 1) * 2
            case 1: (
                    push into stack
            case 2: )
                    case 2.1
                        peek is (
                        pop, add 1 into stack
                    case 2.2
                        peek is number
                            cur += stack.pop()
                        until (
                            push cur * 2 into stack
       */
        Deque<Integer> stack = new ArrayDeque<>();
        char[] arr = S.toCharArray();
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == '(') {
                stack.push(-1);
            } else if (arr[i] == ')') {
                int cur = 0;
                while (!stack.isEmpty() && stack.peek() != -1) {
                    cur += stack.pop();
                }
                stack.pop();
                cur = cur == 0 ? 1 : cur * 2;
                stack.push(cur);
            }
        }
        int res = 0;
        while (!stack.isEmpty()) {
            res += stack.pop();
        }
        return res;
    }
}
