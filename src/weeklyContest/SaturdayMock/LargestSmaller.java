package weeklyContest.SaturdayMock;

public class LargestSmaller {
    public static int largestSmaller(int n) {
        /*
        - 12354321  -> 12454321
        - 12312345  -> 12254331

        - 54321 -> 54312
        - 12345 -> -1
          XXXXA
          XXXXB should be decreasing  -> largest one

          the largest one B < A
          largest one which are smaller than target

          step 1: find the cnt C which can be replaced so that we can get a smaller number
                  start from the end, find the increasing number at least two number
          step 2: find the one which is the largest one which is smaller than  this cnt C
                  swap
                  -> largest
                  -> decreasing
                      swap [cnt + 1, tail]
         */

         char[] arr = String.valueOf(n).toCharArray();
         int larger = -1;
         for (int i = arr.length - 2; i >= 0; i--) {
             if (arr[i] > arr[i + 1]) {
                 larger = i;
                 break;
             }
         }
         // from left to right increasing
         if (larger == -1) {
             return -1;
         }
         int smaller = -1;
         //只能从最后向前， 因为想要知道比他小的最大的，从后往前如果是increasing， 可以找到，如果是decreasing，如果从前往后则可能提前跳出，
        //并不是最大的
         for (int i = arr.length - 1; i >= 0; i--) {
             if (arr[i] < arr[larger]) {
                 smaller = i;
                 break;
             }
         }
         swap(arr, larger, smaller);
         int left = larger + 1;
         int right = arr.length - 1;
         while (left <= right) {
             swap(arr, left++, right--);
         }
         return Integer.valueOf(new String(arr));
    }

    private static void swap(char[] arr, int left, int right) {
        char temp = arr[left];
        arr[left] = arr[right];
        arr[right] = temp;
    }

    public static void main(String[] args) {
        System.out.println(largestSmaller(12312345));
    }
}
