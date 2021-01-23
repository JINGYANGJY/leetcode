package amazon.String;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Leet49_GroupAnagrams {
    public List<List<String>> groupAnagrams(String[] strs) {
        /*
        anagram: rearranging different word or phrase, typically using all letters exactly once
        -> assume only contains lower case letters
        -> group all anagram
            -> character, frequency

            groups -> Map<List<Integer>, List<String>>
                      key is distince group identifier
                      List<Integer> len == 26
                      0 -> a
            for each string
                get its identifier
                    if existed in the groups
                        add into its group
                    otherwise
                        create a new group
                        add into its group
            Time: O(n * m) n is the length of strs, m is word length
        */

        Map<List<Integer>, List<String>> groups = new HashMap<>();
        for (String s : strs) {
            List<Integer> identifier = getID(s);
            groups.computeIfAbsent(identifier, k -> new ArrayList<>()).add(s);
        }
        return new ArrayList<>(groups.values());
    }

    private List<Integer> getID(String s) {
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < 26; i++) {
            res.add(0);
        }
        for (char c : s.toCharArray()) {
            int index = c - 'a';
            int count = res.get(index);
            res.set(index, count + 1);
        }
        return res;
    }
}
