package oracle.hard;

// you can also use imports, for example:
// import java.util.*;

// you can write to stdout for debugging purposes, e.g.
// System.out.println("this is a debug message");
//https://app.codility.com/programmers/lessons/9-maximum_slice_problem/max_double_slice_sum/
/*
A non-empty array A consisting of N integers is given.

A triplet (X, Y, Z), such that 0 ≤ X < Y < Z < N, is called a double slice.

The sum of double slice (X, Y, Z) is the total of A[X + 1] + A[X + 2] + ... + A[Y − 1] + A[Y + 1] + A[Y + 2] + ... + A[Z − 1].

For example, array A such that:

    A[0] = 3
    A[1] = 2
    A[2] = 6
    A[3] = -1
    A[4] = 4
    A[5] = 5
    A[6] = -1
    A[7] = 2
contains the following example double slices:

double slice (0, 3, 6), sum is 2 + 6 + 4 + 5 = 17,
double slice (0, 3, 7), sum is 2 + 6 + 4 + 5 − 1 = 16,
double slice (3, 4, 5), sum is 0.
The goal is to find the maximal sum of any double slice.
 */
class maxDoubleSlice {
    public int solution(int[] A) {
        // write your code in Java SE 8
        /*
            X Y Z
            BF:
            find X Y Z
                make sum(X, Y) + sum (Y, Z) is the maximal
            for
                for
                    for
                         + prefix sum, global max
                         ==> res
            Time: O(n^3)

            dp1[i]: the maximal sum of subarray(0, i)
            dp2[j]: the maximal sum of subarray(j, array.length - 1)
            induction rule:
            dp1[i] = {
                dp1[i] = Math.max(0, dp1[i - 1] + A[i])
            }
            dp2[j] = {
                dp2[j] = Math.max(0, dp2[j + 1] + A[j])
            }
            max = Math.max(max, dp1[i - 1] + dp2[i + 1])
        */
        int res = 0;
        int[] dp1 = new int[A.length];
        int[] dp2 = new int[A.length];

        for (int i = 1; i < A.length; i++) {
            dp1[i] = Math.max(dp1[i - 1] + A[i], 0) ;
        }
        for (int j = A.length - 2; j >= 0; j--) {
            dp2[j] = Math.max(dp2[j + 1] + A[j], 0);
        }
        for (int i = 1; i < A.length - 1; i++) {
            res = Math.max(res, dp1[i - 1] + dp2[i + 1]);
        }
        return res;
    }
}
