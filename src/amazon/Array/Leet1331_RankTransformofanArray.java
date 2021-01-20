package amazon.Array;

import java.util.Arrays;

public class Leet1331_RankTransformofanArray {
    public int[] arrayRankTransform(int[] arr) {
        if (arr.length == 0) {
            return arr;
        }
        int[] temperary = Arrays.copyOf(arr, arr.length);
        Arrays.sort(temperary);
        int[] numSmaller = new int[arr.length];
        numSmaller[0] = 1;
        for (int i = 1; i < temperary.length; i++) {
            numSmaller[i] = numSmaller[i - 1];
            if (temperary[i] > temperary[i - 1]) {
                numSmaller[i] = numSmaller[i - 1] + 1;
            }
        }
        int[] res = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            int index = binarySearch(temperary, arr[i]);
            res[i] = numSmaller[index];
        }
        return res;
    }

    private int binarySearch(int[] temperary, int target) {
        int left = 0;
        int right = temperary.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (temperary[mid] == target) {
                return mid;
            } else if (temperary[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return -1;
    }
}
