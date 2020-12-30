package oa.tiktok;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class commonElements {
    /*
    Given two arrays containing unsorted integers(within range [0, 1000]),
    write a function to return all the common elements(each number in one array can
    be used only once). Array size N: 0 <= N <= 10000.sort output from smallest to biggest.
     */
    public static void main(String[] args) {
        int[] arr1 = {2, 1};
        int[] arr2 = {};
        List<Integer> res = commonElements(arr1, arr2);
        for (Integer i : res) {
            System.out.println(i);
        }
    }

    public static List<Integer> commonElements(int[] arr1, int[] arr2) {
        List<Integer> res = new ArrayList<>();
        if (arr1 == null || arr2 == null) {
            return res;
        }
        Arrays.sort(arr1);
        Arrays.sort(arr2);
        int p1 = 0;
        int p2 = 0;
        while (p1 < arr1.length && p2 < arr2.length) {
            if (arr1[p1] == arr2[p2]) {
                res.add(arr1[p1]);
                p1++;
                p2++;
            } else if (arr1[p1] < arr2[p2]) {
                p1++;
            } else {
                p2++;
            }
        }
        return res;
    }
}
