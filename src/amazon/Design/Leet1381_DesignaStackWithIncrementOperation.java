package amazon.Design;

import java.util.ArrayDeque;
import java.util.Deque;

public class Leet1381_DesignaStackWithIncrementOperation {
    /*
 Stack: FILO
 [1, 2, 3]
 push
 pop
 Deque<Integer> stack
 maxSize
     if stack.size() >= maxSize
         do nothing
 increment
     if k < stack.size()
         [1, 2, 3] ->
         [3, 200, 100]  Deque<Integer> temperaryStack
         -> stack

 */
    class CustomStack {
        Deque<Integer> stack;
        int maxSize;
        int[] increment;

        public CustomStack(int maxSize) {
            this.maxSize = maxSize;
            stack = new ArrayDeque<>();
            increment = new int[maxSize];
        }

        public void push(int x) {
            if (stack.size() >= maxSize) {
                return;
            }
            stack.push(x);
        }

        public int pop() {
            if (stack.isEmpty()) {
                return -1;
            }
            int index = stack.size() - 1;
            int res = stack.pop();
            if (index > 0) {
                increment[index - 1] += increment[index];
            }
            res += increment[index];
            increment[index] = 0;
            return res;
        }

        public void increment(int k, int val) {
            if (stack.isEmpty()) {
                return;
            }
            if (k >= stack.size()) {
                increment[stack.size() - 1] += val;
            } else {
                increment[k - 1] += val;
            }
        }
    }
}
