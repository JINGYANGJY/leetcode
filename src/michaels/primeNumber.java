package michaels;

import java.util.Arrays;

public class primeNumber {
    /*
        the first pair which sums to be equaled with the prime number
        target = 8
        two prime whose sums to be target
        < 8
        1. which numbers are prime number < target
        2. sum of two numbers == target
        Clarification:
            target no same number sum to a prime number
        data structure
            list -> maintain all primes
        for i = 2, i < target; i++
            check if it is a prime
                - true add into list

       sorted ________________________
       -> two sum == target
              p                      q

              p + q == target
              if > target
                q--;
              < target
                p++;
         target = n
         Time: O(n * n + n) -> O(n ^ 2)
         Space: O(n)
         -----------------------------------
         optimizations:
         -> prime -> O(n)
         -> space O(n) -> O(1)
            p = 2;
            q = target - 1;

            if (p + q && isprime(p) && isprime(q))
                return;
         Time: O(n^2)
     */

    public static int[] twoPRimesToTarget(int n) {
        boolean[] isPrime = new boolean[n];
        Arrays.fill(isPrime, true);
        for (int i = 2; i < n; i++) {
            for (int j = 2; j * i < n; j++) {
                isPrime[j * i] = false;
            }
        }
        int left = 2;
        int right = n - 2;
        while (left < right) {
            if (left + right == n) {
                if (isPrime[left] && isPrime[right]) {
                    return new int[]{left, right};
                }
                left++;
                right--;
            } else if (left + right < n) {
                left++;
            } else {
                right--;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        int target = 8;
        int[] res = twoPRimesToTarget(target);
        if (res != null) {
            System.out.println(res[0] + "," + res[1]);
        }

    }
}
