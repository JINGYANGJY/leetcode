package amazon.String;

public class Leet748_ShortestCompletingWord {
    public String shortestCompletingWord(String licensePlate, String[] words) {
        licensePlate = licensePlate.toLowerCase();
        int[] count = getCount(licensePlate);
        String res = null;
        for (String s : words) {
            int[] countOfWord = getCount(s);
            if (canMatch(count, countOfWord)) {
                if (res == null || res != null && s.length() < res.length()) {
                    res = s;
                }
            }
        }
        return res;
    }

    private boolean canMatch(int[] count, int[] countOfWord) {
        for (int i = 0; i < 26; i++) {
            if (count[i] > countOfWord[i]) {
                return false;
            }
        }
        return true;
    }

    private int[] getCount(String s) {
        int[] count = new int[26];
        for (char c : s.toCharArray()) {
            if (Character.isLetter(c)) {
                count[c - 'a']++;
            }
        }
        return count;
    }
}
