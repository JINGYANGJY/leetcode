package amazon.Array;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
/*
https://leetcode.com/problems/sequential-digits/
 */
public class Leet1291_SequentialDigits {
    public List<Integer> sequentialDigits(int low, int high) {
        List<Integer> res = new ArrayList<>();
        for (int digit = 1; digit < 9; ++digit) {
            int next = digit, n = next;
            while (n <= high && next < 10) {
                if (n >= low) {
                    res.add(n);
                }
                n = n * 10 + ++next;
            }
        }
        Collections.sort(res);
        return res;
    }
}
