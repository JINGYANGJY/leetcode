package amazon.Design;

import java.util.Arrays;
import java.util.Random;

public class Leet384_ShuffleanArray {
    int[] nums;
    int[] shuffled;
    Random rand;
    int size;
    public Leet384_ShuffleanArray(int[] nums) {
        this.nums = nums;
        this.size = nums.length;
        this.shuffled = Arrays.copyOf(nums, size);
        rand = new Random();

    }

    /** Resets the array to its original configuration and return it. */
    public int[] reset() {
        return nums;
    }

    /** Returns a random shuffling of the array. */
    public int[] shuffle() {
        for (int i = 0; i < nums.length; i++) {
            swap(shuffled, i, randomIndex(i));
        }
        return shuffled;
    }

    private int randomIndex(int start) {
        return start + rand.nextInt(size - start);
    }

    private void swap(int[] shuffled, int left, int right) {
        int temp = shuffled[left];
        shuffled[left] = shuffled[right];
        shuffled[right] = temp;
    }
}
