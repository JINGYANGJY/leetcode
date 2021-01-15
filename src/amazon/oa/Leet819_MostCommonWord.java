package amazon.oa;

import java.util.*;

public class Leet819_MostCommonWord {
    public String mostCommonWord(String paragraph, String[] banned) {
        String normalizedStr = paragraph.replaceAll("[^a-zA-Z0-9 ]", " ").toLowerCase();
        Map<String, Integer> frequencyMap = new HashMap<>();
        Set<String> set = new HashSet<>(Arrays.asList(banned));
        int freq = 0;
        String[] strs = normalizedStr.split("\\s+");
        for (String s : strs) {
            StringBuilder cur = new StringBuilder();
            char[] arr = s.toLowerCase().toCharArray();
            for (char c : arr) {
                if (Character.isLetter(c)) {
                    cur.append(String.valueOf(c));
                }
            }
            if (set.contains(cur.toString())) continue;
            int count = frequencyMap.getOrDefault(cur.toString(), 0) + 1;
            frequencyMap.put(cur.toString(), count);
            freq = Math.max(freq, count);
        }

        for (String s : frequencyMap.keySet()) {
            if (frequencyMap.get(s) == freq && !set.contains(s)) {
                return s;
            }
        }
        return null;
    }
}
