package amazon.Greedy;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Leet1647_MinimumDeletionstoMakeCharacterFrequenciesUnique {
    public int minDeletions(String s) {
        /*
        -> minimum deletion
        Clarification?
            lowercase?
            ->
                get each character's frequency
                sort
                for each duplicate frequency, get the largest frequency < cur
        */
        int[] freq = new int[26];
        for (char c : s.toCharArray()) {
            freq[c - 'a']++;
        }
        Arrays.sort(freq);
        Set<Integer> freqSet = new HashSet<>();
        int res = 0;
        for (int i = 25; i >= 0; i--) {
            if (freq[i] == 0) continue;
            if (freqSet.contains(freq[i])) {
                int initial = freq[i];
                while (initial >= 0 && freqSet.contains(initial)) {
                    initial--;
                }
                if (initial > 0) freqSet.add(initial);
                res += freq[i] - initial;
            } else {
                freqSet.add(freq[i]);
            }
        }
        return res;
    }
}
