package amazon.oa;

public class Amazonfulfillmentcenters {
    /*
    In one of Amazon fulfillment centers, there are a no. of empty boxes kept in increasing order in a row.
    Kiva robots are designed to put a product in a box. The product size is given.
    Design a program to find the best fit box for given product size.
    First line contains no. of empty boxes and next line contains size of boxes with space.
    The next line contains size of given product. The output shows the best fit box size and -1 otherwise.
    For example, Input: 6
                  2 7 9 11 13 16
                           12
    Output: 13
    goals to find the smallest one which is larger than give product size
    Clarification:
    binary search
     */
    public static int smallestLarger(int num, int[] boxes, int productSize) {
        int left = 0;
        int right = boxes.length - 1;
        int res = -1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (boxes[mid] >= productSize) {
                res = boxes[mid];
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int[] boxes = {2, 7, 9, 11, 13, 16};
        System.out.println(smallestLarger(6, boxes, 12));
    }

}
