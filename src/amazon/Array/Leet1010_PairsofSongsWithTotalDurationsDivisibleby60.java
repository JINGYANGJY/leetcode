package amazon.Array;

import java.util.HashMap;
import java.util.Map;

public class Leet1010_PairsofSongsWithTotalDurationsDivisibleby60 {
    public int numPairsDivisibleBy60(int[] time) {
        /*
        goals to find how many pairs which (time[i] + time[j]) % 60 == 0
        two sum problems
        - all pairs
            check if sum % 60 == 0?

        Time:O(n^2)
        ------------------------------------------------------------------
        optimizations:
        Map<Integer, Integer> <value, frequency>
        [30,    20,   150,    100,    40]
         30     20     30      40     40
         time[i] + time[j] == 60
         Time: O(n)
         Space: O(n)
        */
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < time.length; i++) {
            time[i] %= 60;
        }
        int res = 0;
        for (int i = 0; i < time.length; i++) {
            if (time[i] > 0 && map.containsKey(60 - time[i])) {
                res += map.get(60 - time[i]);
            } else if (map.containsKey(0) && time[i] == 0){
                res += map.get(0);
            }
            map.put(time[i], map.getOrDefault(time[i], 0) + 1);
        }
        return res;
    }
}
