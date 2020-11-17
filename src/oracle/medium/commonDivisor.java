package oracle.medium;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class commonDivisor {
        public static int solution(int[] A, int[] B) {
            // write your code in Java SE 8
            int res = 0;
            for (int i = 0; i < A.length; i++) {
                List<Integer> listA = getDivisor(A[i]);
                List<Integer> listB = getDivisor(B[i]);
                if (common(listA, listB)) {
                    res++;
                }
            }
            return res;
        }

        private static List<Integer> getDivisor(int n) {
            List<Integer> res = new ArrayList<>();
            for (int i = 2; i <= n / 2; i++) {
                if (n % i == 0) {
                    res.add(i);
                }
            }
            return res;
        }
        // 3 5
        // 3 5 15 25
        private static boolean common(List<Integer> list1, List<Integer> list2) {
            int i = 0, j = 0;
            boolean res = false;
            while (i < list1.size() && j < list2.size()) {
                int divisor1 = list1.get(i);
                int divisor2 = list2.get(i);
                if (divisor1 == divisor2 && isPrime(divisor1)) {
                    res = true;
                    i++;
                    j++;
                } else if (isPrime(divisor1) || isPrime(divisor2)) {
                    return false;
                }

                if (divisor1 < divisor2) {
                    i++;
                } else if (divisor1 > divisor2){
                    j++;
                }

            }
            return res;
        }
        private static boolean isPrime(int n) {
            if (n <= 2) return true;
            for (int i = 3; i < n; i++) {
                if (n % i == 0) {
                    return false;
                }
            }
            return true;
        }
        Deque<Integer> queue = new ArrayDeque<>();

        public static void main(String[] args) {
            int[] A = {15, 10, 9};
            int[] B = {75, 30, 5};
            System.out.println(solution(A, B));
        }
}
