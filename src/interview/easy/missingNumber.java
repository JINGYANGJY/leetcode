package interview.easy;

public class missingNumber {
        public int solution(int[] A) {
            // write your code in Java SE 8
        /*
        Clarification: the array is sorted or not?

        Assume: the input are valid
                input: int[] A
                output: int the missing number
        BF:
            check 1 - N+1 is existed or not?
            for (1 to N+1)
                for (A)
            Time:O(n^2)
            Space: O(1)
        optimization:
            Time: O(n^2) --> O(n)
            datastructure:
                boolean[] existed len: N + 1
                if A[i] = n,  existed[n] = true
                check existed which one is false
            Time: O(n)
            Space: O(n)

            Bit Manipulation
            Space:O(n) --> O(1)
            index 0 1 2 3
            value 1 2 3 5 ... return 4
            we use XOR to find the missing number
            Time: O(n)
            Space: O(1)
            1 ... N + 1
            1 2 3 4
            1 2 3 5 5
            i: 1 2 3 4
        */
            int missing = A.length + 1;
            for (int i = 0; i < A.length; i++) {
                int cur = (i + 1) ^ A[i];
                missing ^= cur;
            }
            return missing;
        }
}
