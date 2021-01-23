package amazon.BinarySearch;

public class Leet1539_KthMissingPositiveNumber {
    public int findKthPositive(int[] arr, int k) {
        /*
        if not missing
        0 1 2 3 4 5
        1 2 3 4 5 6
        index == arr[index] - 1
        sorted -> binary search
        [2,3,4,7,11]
             mid = 2
             arr[mid] = 4 XXXX arr[mid] should be mid + 1
             therefore
                ends with mid, there are 1 missing
                K - 1 > 0
                left = mid + 1
                7,11
                mid = 3 before 7 arr[mid] = 4
                5 6 7
                k - 3 = 2
                11 arr[mid] = 5
                11 - 2 = 9
          step 1:
                use binary search to find
        */
        int leftmost = -1;
        int left = 0;
        int right = arr.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (k > (arr[mid] - (mid + 1))) {
                leftmost = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        if (leftmost == -1) return k;
        int l = k - (arr[leftmost] - (leftmost + 1));
        return leftmost == -1 ? k : arr[leftmost] + l;
    }
}
