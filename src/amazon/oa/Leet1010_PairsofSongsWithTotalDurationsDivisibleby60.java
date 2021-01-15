package amazon.oa;

import java.util.HashMap;
import java.util.Map;

public class Leet1010_PairsofSongsWithTotalDurationsDivisibleby60 {
    public int numPairsDivisibleBy60(int[] time) {
        /*
        （time[i] + time[j]）% 60 == 0
         two sum
            for each time i , check how many numbers
                        (time[j] + time[i]) % 60 = 0
            times are different
                for each time % 60
                time[i] = time[i] % 60
                next step do two sum == target
                Tow sum, use Hashmap to maintain all past values and frequence
         Time: O(n) n is the length of time
         Space: O(n)
        */
        for (int i = 0; i < time.length; i++) {
            time[i] = time[i] % 60;
        }
        int res = 0;
        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < time.length; i++) {
            if (i > 0) {
                if (map.containsKey(60 - time[i])) {
                    res += map.get(60 - time[i]);
                } else if (time[i] == 0) {
                    res += map.getOrDefault(time[i], 0);
                }
            }
            map.put(time[i], map.getOrDefault(time[i], 0) + 1);
        }
        return res;
    }
}
