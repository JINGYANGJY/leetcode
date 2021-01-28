package amazon.DP;

import java.util.ArrayDeque;
import java.util.Deque;

public class Leet42_TrappingRainWater {
    public int trap(int[] height) {
        /*
        -> goals to get how much water can be trapped
           where will have water?
                -   -
                 |_|
           for each bar, we see if there is a area it as a right bar can trap water
           -> for each bar, we find all left bars, which can trap water with this bar
           -> monotonic stack to maintain all possible left bar's index for current bar
           -> for each bar
                stack.peek < cur bar
                -> how much water

        */
        int res = 0;
        Deque<Integer> stack = new ArrayDeque<>();
        int baseHeight = 0;
        for (int i = 0; i < height.length; i++) {
            while (!stack.isEmpty() && height[stack.peek()] <= height[i]) {
                baseHeight = height[stack.pop()];
                if (stack.isEmpty()) break;
                res += (Math.min(height[stack.peek()], height[i]) - baseHeight) * (i - stack.peek() - 1);
            }
            stack.push(i);
        }
        return res;
    }
}
