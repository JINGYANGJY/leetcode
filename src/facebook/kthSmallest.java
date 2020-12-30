package facebook;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

public class kthSmallest {
        public static int kthSmallest(int[][] mat, int k) {
        /*
            find the kth smallest array sum
            sum sorted in increasing order
            datastructure: PriorityQueue
            initials state
                int[] : {....mat[i][0]}
             expand from pq
             generate its neigbors into pq
                e.g. [0, 0, 0]   0 -> index
                 [1, 0, 0] [0, 1, 0], [0, 0, 1] into pq
           Time: m * n logn
           n is number of rows in mat
           m is number of columns in mat
           // 记得mark visited 好不好
        */
            if (mat.length == 0) {
                return -1;
            }
            int n = mat.length;
            int m = mat[0].length;
            PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> {
                int r1 = getRes(a, mat);
                int r2 = getRes(b, mat);
                return r1 - r2;
            });
            Set<String> visited = new HashSet<>();
            int[] initial = new int[n];
            Arrays.fill(initial, 0);
            pq.offer(initial);
            visited.add(Arrays.toString(initial));
            while (!pq.isEmpty() && k > 0) {
                int[] cur = pq.poll();
                k--;
                if (k == 0) {
                    return getRes(cur, mat);
                }
                for (int i = 0; i < n; i++) {
                    if (cur[i] + 1 < m) {
                        cur[i] += 1;
                        int[] copy = Arrays.copyOf(cur, n);
                        if (visited.add(Arrays.toString(copy))) {
                            pq.offer(copy);
                        }
                        cur[i] -= 1;
                    } else {
                        continue;
                    }
                }
            }
            return -1;
        }

        private static int getRes(int[] arr, int[][] mat) {
            int res = 0;
            for (int i = 0; i < mat.length; i++) {
                res += mat[i][arr[i]];
            }
            return res;
        }


    public static void main(String[] args) {
        int[][] mat = {
                {1,3,11},
                {2,4,6}
        };
        System.out.println(kthSmallest(mat, 9));
    }
}
