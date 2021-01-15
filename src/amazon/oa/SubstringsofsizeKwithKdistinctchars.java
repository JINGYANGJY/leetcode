package amazon.oa;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SubstringsofsizeKwithKdistinctchars {
    /*
    Given a string s and an int k, return all unique substrings of s of size k with k distinct characters.
     */
    public static List<String> SubstringsofsizeKwithKdistinctchars(String s, int k) {
        char[] arr = s.toCharArray();
        List<String> res = new ArrayList<>();
        Set<String> set = new HashSet<>();
        Set<Character> chars = new HashSet<>();
        int slow = 0;
        for (int i = 0; i < arr.length; i++) {
            if (chars.contains(arr[i])) {
                while (slow < i && chars.contains(arr[i])) {
                    chars.remove(arr[slow++]);
                }
            }
            chars.add(arr[i]);
            while (slow < i - k + 1) {
                chars.remove(arr[slow++]);
            }
            if (chars.size() == k) {
                String cnt = new String(arr, slow, k);
                if (set.add(cnt)) {
                    res.add(cnt);
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        List<String> res = SubstringsofsizeKwithKdistinctchars("awaglknagawunagwkwagl", 4);
        for (String s : res) {
            System.out.println(s);
        }

        res = SubstringsofsizeKwithKdistinctchars("abcabc", 3);
        for (String s : res) {
            System.out.println(s);
        }

        res = SubstringsofsizeKwithKdistinctchars("abacab", 3);
        for (String s : res) {
            System.out.println(s);
        }
    }
}
