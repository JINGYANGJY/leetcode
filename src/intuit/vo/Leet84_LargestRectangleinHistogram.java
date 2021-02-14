package intuit.vo;

import java.util.ArrayDeque;
import java.util.Deque;

public class Leet84_LargestRectangleinHistogram {
    /*
    https://leetcode.com/problems/largest-rectangle-in-histogram/
     */
    public int largestRectangleArea(int[] heights) {
        /*
            2,1,5,6,2,3

        for each rectangle,
            find the leftmost bound >= its height
            find the righmost bound >= its height

            TC: O(n^2)
     ------------------------------------------------------
        -> monotonic stack increasing order
                    2,  1,  5,  6,  2,  3
           stack        1           2   3
                    2   6   10  6   8   3
        */

        Deque<Integer> stack = new ArrayDeque<>();
        int[] res = new int[heights.length];
        for (int i = 0; i <= heights.length; i++) {
            while (!stack.isEmpty() && (i < heights.length && heights[stack.peek()] > heights[i]
                    || i == heights.length)) {
                int index = stack.pop();
                res[index] += (i - index) * heights[index]; // its self + right side
            }
            if (i < heights.length) {

                res[i] += stack.isEmpty() ? heights[i] * i : heights[i] * (i - 1 - stack.peek()); //left not include its self area
                stack.push(i);
            }
        }
        int max = res[0];
        for (int i : res) {
            max = Math.max(max, i);
        }
        return max;
    }
}
