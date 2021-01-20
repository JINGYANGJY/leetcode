package amazon.Array;

import java.util.Arrays;

public class Leet1465_MaximumAreaofaPieceofCakeAfterHorizontalandVerticalCuts {
    public int maxArea(int h, int w, int[] horizontalCuts, int[] verticalCuts) {
        /*
        two array
            horizontal
                0 .... h
            vertical
                0      w
            find max interval from horizontal and vertical
                - sort
                for loop from horizontal
                for loop from vertical
                    max horizontal * max vertical
        */
        Arrays.sort(horizontalCuts);
        Arrays.sort(verticalCuts);
        long maxHorizontal = 0;
        long prev = 0;
        for (int i = 0; i < horizontalCuts.length; i++) {
            maxHorizontal = Math.max(maxHorizontal, horizontalCuts[i] - prev);
            prev = horizontalCuts[i];
        }
        maxHorizontal = Math.max(maxHorizontal, h - prev);
        prev = 0;
        long maxVertical = 0;
        for (int i = 0; i < verticalCuts.length; i++) {
            maxVertical = Math.max(maxVertical, verticalCuts[i] - prev);
            prev = verticalCuts[i];
        }
        maxVertical = Math.max(maxVertical, w - prev);
        return (int)((maxHorizontal * maxVertical) % 1000000007);
    }
}
