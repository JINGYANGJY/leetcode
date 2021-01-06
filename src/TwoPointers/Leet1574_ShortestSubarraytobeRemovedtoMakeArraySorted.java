package TwoPointers;

public class Leet1574_ShortestSubarraytobeRemovedtoMakeArraySorted {
    // 及其麻烦的方法
        public int findLengthOfShortestSubarray(int[] arr) {
        /*
        M1: array + binary search
        M2: array + two points
        remove a subarry
        subarray
            middle:
                    _________|______________|_________

                    non decreasing                   non increasing

            leftside:
                        |_________________|__________
                                            non increasing
            rightside:
                        __________________|___________|
                      non decreasing

            int nonDecreasing
            int nonIncreasing

        */
            int res = arr.length;
            int leftIndex = 0;
            boolean flag = true;
            for (int i = 0; i < arr.length; i++) {
                if (i + 1 < arr.length && arr[i] > arr[i + 1]) {
                    leftIndex = i;
                    flag = false;
                    break;
                }
            }
            if (flag) {
                return 0;
            }
            int rightIndex = arr.length - 1;
            for (int i = arr.length - 1; i >= 0; i--) {
                if (i - 1 >= 0 && arr[i- 1] > arr[i]) {
                    rightIndex = i;
                    break;
                }
            }

            if (arr[leftIndex] <= arr[rightIndex]) {
                return rightIndex - leftIndex - 1;
            } else {
                for (int i = 0; i <= leftIndex; i++) {
                    int first = firstOccurance(arr, rightIndex, arr.length - 1, arr[i]); // -1
                    if (first != -1) {
                        res = Math.min(res, first - i - 1);
                    } else {
                        res = Math.min(res, arr.length - i - 1);
                    }
                }
                for (int i = rightIndex; i < arr.length; i++) {
                    int last = lastOccurance(arr, 0, leftIndex, arr[i]);
                    if (last != -1) {
                        res = Math.min(res, i - last - 1);
                    } else if (last == -1) {
                        res = Math.min(res, i);
                    }
                }
            }
            return res;
        }

        private int firstOccurance(int[] arr, int left, int right, int target) {
            int res = -1;
            while (left <= right) {
                int mid = left + (right - left) / 2;
                if (arr[mid] >= target) {
                    res = mid;
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            }
            return res;
        }

        private int lastOccurance(int[] arr, int left, int right, int target) {
            int res = -1;
            while (left <= right) {
                int mid = left + (right - left) / 2;
                if (arr[mid] == target) {
                    res = mid;
                    left = mid + 1;
                }
                if (arr[mid] > target) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            }
            return res;
        }

        // method 2
        public int findLengthOfShortestSubarrayTwoPointers(int[] arr) {
            int left = 0;
            while (left + 1 < arr.length && arr[left] <= arr[left + 1]) {
                left++;
            }
            if(left == arr.length - 1) return 0;

            int right = arr.length - 1;
            while (left < right && arr[right] >= arr[right - 1]) {
                right--;
            }
            int res = Math.min(right, arr.length - left - 1);
            int i = 0;
            int j = right;
            while (i <= left && j < arr.length) {
                if (arr[j] >= arr[i]) {
                    res = Math.min(res, j - i - 1);
                    i++;
                } else {
                    j++;
                }
            }
            return res;
        }
}
