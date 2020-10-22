package oracle.hard;

import java.util.ArrayDeque;
import java.util.Deque;

/*
Given n non-negative integers representing an elevation map where the width of each bar is 1,
compute how much water it can trap after raining.
Clarification:
    1. input: int[] arr representing elevation map
    2. output: integer represent water it can trap in these bars.
    Assumption:
       non-negative height of bars
       the water it can trap will not exceed the max value of integer
       the leftmost bound and rightmost bound height is Integer.MIN_VALUE
    Example
       e.g.1 [0 1 2 1 4]
              0 0 0 0 1  return 1
       e.g.2 [0 0 1 0 2 0 1 0 4]
                    1   2 1 2   return 6
       for each position, if we know its left max and right max, we can now current how much water it can trap
       BF:
        for each position
            find its leftmost bound (the height bar on the left)
            find its rightmost bound (the height bar on the right)
            min(left, right) - cur.height
            Time: O(n^2)
       optimization: --> O(1) get left bound and right bound
            dp1[i]: ending at i, the biggest number [0, i]
            dp2[i]: ending at i, the biggest number [i, arr.length - 1]

            induction rule:
                dp1[i]: arr[i] > dp1[i - 1] ? arr[i] : dp1[i - 1] (i > 0)
                dp2[i]: arr[i] > dp2[i + 1] ? arr[i] : dp2[i + 1] (i < arr.length - 1)
            base case:
                dp1[0] = arr[0]
                dp2[arr.length - 1] = arr[i]
            Time: O(n) space: O(2n)

       stack:
            at i, we need to know if bar i as the right bound, how much water will be trapped
            use decreasing monotonic stack record left bounds
            while cur height > stack.peek()
                find the

 */
public class trap {
    public int trap(int[] bars) {
        int res = 0;
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = 0; i < bars.length; i++) {
            while (!stack.isEmpty() && bars[i] > stack.peek()) {
                Integer top = stack.pop();
                if (stack.isEmpty()) break;
                int distance = i - stack.peek() - 1;
                int height = Math.min(bars[i], bars[stack.peek()]) - bars[top];
                res += distance * height;
            }
            stack.push(i);
        }
        return res;
    }
}
