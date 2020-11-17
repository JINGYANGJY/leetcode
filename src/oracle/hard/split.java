package oracle.hard;
class split {
    public static int solution(int K, int M, int[] A) {
        // write your code in Java SE 8
        int left = Integer.MIN_VALUE, right = 0;
        for (int i : A) {
            right += i;
            left = Math.max(i, left);
        }
        int res = left;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            int part = split(A, mid);
            if (part > K) {
                left = mid + 1;
            } else {
                res = mid;
                right = mid - 1;
            }
        }
        return res;
    }
    private static int split(int[] A, int mid) {
        int cur = 0;
        int res = 0;
        for (int i = 0; i < A.length; i++) {
            cur += A[i];
            if (cur >= mid) {
                if (cur == mid) {
                    cur = 0;
                } else {
                    cur = A[i];
                }
                res++;
            }
        }
        return cur == 0 ? res : res + 1;
    }

    public static void main(String[] args) {
        int[] A = {2, 1, 5, 1, 2, 2, 2};
        System.out.println(solution(3, 5, A));
    }
}
