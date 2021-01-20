package amazon.Array;
/*
https://leetcode.com/problems/first-unique-character-in-a-string/
 */
public class Leet387_FirstUniqueCharacterinaString {
    public int firstUniqChar(String s) {
        char[] arr = s.toCharArray();
        int[] count = new int[26];
        int slow = 0;
        for (int i = 0; i < arr.length; i++) {
            count[arr[i] -'a']++;
            while(slow < arr.length && count[arr[slow] - 'a'] > 1) {
                slow++;
            }
        }
        return slow == arr.length ? -1 : slow;
    }
}
