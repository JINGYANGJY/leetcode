package oracle.medium;

public class sortColors {
    /*
    Given an array nums with n objects colored red, white, or blue,
    sort them in-place so that objects of the same color are adjacent,
    with the colors in the order red, white, and blue.
    Here, we will use the integers 0, 1, and 2 to represent the color red, white, and blue respectively.
    Follow up:
    Could you solve this problem without using the library's sort function?
    Could you come up with a one-pass algorithm using only O(1) constant space?

     */
    public void sortColors(int[] nums) {
        /*

        we need to divide into three parts
        two border
         0       i           j           nums.length
            0           1          2
         i: elements before i are all 0
         j: elements between i and j is 1
         k: elements after j are all 2
         i = 0, j = 0, k = nums.length - 1
         if (nums[j] == 1) j++
         if (nums[j] == 0) swap(i,j)  i++ j++
         if (nums[j] == 2) swap(j, k) k--
         [2,1,1,0,2,1,0]
         */
        int i = 0, j = 0, k = nums.length - 1;
        while (j <= k) {
            if (nums[j] == 0) {
                swap(nums, i, j);
                i++;
                j++;
            } else if (nums[j] == 2) {
                swap(nums, j, k);
                k--;
            } else if (nums[j] == 1){
                j++;
            }
        }
    }
    private void swap(int[] nums, int i , int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
