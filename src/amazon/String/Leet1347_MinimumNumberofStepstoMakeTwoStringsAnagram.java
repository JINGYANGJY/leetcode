package amazon.String;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class Leet1347_MinimumNumberofStepstoMakeTwoStringsAnagram {
    public int minSteps(String s, String t) {
        /*
        lowercase character? yes

        -> minimum number of steps
        -> type
            frequency
            - what's frequency of characters in the string t
            int[] charactersInT

            - ....in string s
            int[] charactersInS

            -> how many different characters
                if char in t == char in s && num is equal
                counteract

                s = "bab" <a, 1> <b, 2>
                t = "aba" <a, 2> <b, 1>
                          after conteracted only left <a, 1>
                          -> return 1
                s =  "leetcode" <l, 1> <e, 3>, <t, 1>, <c, 1> <o, 1> <d, 1>
                t =  "praaainm" <p, 1> <r, 1> <c, 1>, <i,1> <a, 1>
        */
        int[] charactersInT = new int[26];
        int[] charactersInS = new int[26];
        for (char c : s.toCharArray()) {
            charactersInS[c - 'a']++;
        }
        for (char c : t.toCharArray()) {
            charactersInT[c - 'a']++;
        }
        int match = 0;
        for (int i = 0; i < 26; i++) {
            if (charactersInT[i] > 0 && charactersInS[i] > 0) {
                if (charactersInT[i] >= charactersInS[i]) {
                    match += charactersInS[i];
                } else {
                    match += charactersInT[i];
                }
            }
        }
        return t.length() - match;
    }
}
