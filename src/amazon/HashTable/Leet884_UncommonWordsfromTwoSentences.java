package amazon.HashTable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Leet884_UncommonWordsfromTwoSentences {
    public String[] uncommonFromSentences(String A, String B) {
        /*
        calculator the frequency of each word in A and B
        */
        Map<String, Integer> freMap = new HashMap<>();
        String[] arr = A.split(" ");
        for (String s : arr) {
            freMap.put(s, freMap.getOrDefault(s, 0) + 1);
        }
        arr = B.split(" ");
        for (String s : arr) {
            freMap.put(s, freMap.getOrDefault(s, 0) + 1);
        }
        List<String> res = new ArrayList<>();
        for (String s : freMap.keySet()) {
            if (freMap.get(s) == 1) {
                res.add(s);
            }
        }
        return res.toArray(new String[res.size()]);
    }
}
