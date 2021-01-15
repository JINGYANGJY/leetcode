package amazon.oa;

import java.util.Arrays;

public class PrimeAirRoute {
    /*
    Given max. travel distance and forward and backward route list,
    return pair of ids of forward and backward routes that optimally utilized the max travel distance.:
    eg: max travel distance is : 11000
    forward rou te list : [1,3000],[2,5000],[3,4000],[4,10000]
    backward route list : [1,2000],[2,3000],[3,4000]
    Result : [2,3] ...2 is from forward and 3 is from backward...total distance is
    9000...no other combination is there which is >9000 and <=11,000.......0(n^2)
    solution is straight forward, thinking that sorting both might help.
     */
    /*
    Clarification:
        if there are more than one such pair?
    goals to find two number max sum <= 11000
    Time: O(nlogn + mlogm + n + m)
    n is forward.length
    m is backward.length
     */
    public static int[] findPair(int[][] forward, int[][] backward, int target) {
        Arrays.sort(forward, (a, b) -> {
            return a[1] - b[1];
        });
        Arrays.sort(backward, (a, b) -> {
            return a[1] - b[1];
        });
        int p = 0;
        int q = backward.length - 1;
        int[] res = new int[2];
        int max = 0;
        while(p < forward.length && q >= 0) {
            int sum = forward[p][1] + backward[q][1];
            if (sum <= target) {
                if (sum > max) {
                    max = sum;
                    res[0] = forward[p][0];
                    res[1] = backward[q][0];
                }
                p++;
            } else {
                q--;
            }
        }
        return res;
    }
    
    public static void main(String[] args) {
        int[][] forward = {{1,1000},{2,5000},{3,4000},{4,10000}};
        int[][] backward = {{1,2000},{2,3000},{3,9000}};
        int[] res = findPair(forward, backward, 11000);
        System.out.println(res[0]);
        System.out.println(res[1]);
        
    }
}
