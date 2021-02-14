package amazon.String;

import java.util.*;

public class Leet819_MostCommonWord {
    public String mostCommonWord(String paragraph, String[] banned) {
        Set<String> banSet = new HashSet<>(Arrays.asList(banned));
        Map<String, Integer> freqMap = new HashMap<>();
        char[] arr = paragraph.toCharArray();
        StringBuilder str = new StringBuilder();
        int maxFre = 0;
        String res = "";
        for (int i = 0; i <= arr.length; i++) {
            if (i < arr.length && (arr[i] >= 'a' && arr[i] <= 'z' || arr[i] >= 'A' && arr[i] <= 'Z')) {
                str.append(String.valueOf(Character.toLowerCase(arr[i])));
            } else {
                if (str.length() <= 0) continue;
                String s = str.toString();
                if (banSet.contains(s)) {
                    str = new StringBuilder();
                    continue;
                }
                int count = freqMap.getOrDefault(s, 0) + 1;
                freqMap.put(s, count);
                if (count > maxFre) {
                    maxFre = count;
                    res = s;
                }
                str = new StringBuilder();
            }
        }
        return res;
    }
}
