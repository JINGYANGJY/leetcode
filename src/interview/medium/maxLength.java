package interview.medium;

import java.util.Arrays;
import java.util.List;

class maxLength {
    public static int maxLength(List<String> arr) {
        int[] maxLength = new int[1];
        dfs(arr, 0, 0, maxLength);
        return maxLength[0];
    }
    static boolean[] existed = new boolean[26];
    private static void dfs(List<String> arr, int index, int curLen, int[] maxLength) {
        maxLength[0] = Math.max(maxLength[0], curLen);
        if (index == arr.size()) {
            return;
        }
        for (int i = index; i < arr.size(); i++) {
            //check current arr[i] can be added concatenated and mark the existed character
            boolean canBeAdded = check(arr.get(i));
            if (canBeAdded) {
                dfs(arr, i + 1, curLen + arr.get(i).length(), maxLength);
                uncheck(arr.get(i));
            }
            // used to unmark the existed character

        }
    }
    private static void uncheck(String s) {
        for (char c : s.toCharArray()) {
            existed[c - 'a'] = false;
        }
    }
    private static boolean check(String s) {
        boolean flag = true;
        int end = 0;
        for (int i = 0; i < s.length(); i++) {
            if (existed[s.charAt(i) - 'a']) {
                flag = false;
                end = i;
                break;
            }
            existed[s.charAt(i) - 'a'] = true;
        }
        if (!flag) {
            for (int i = 0; i < end; i++) {
                existed[s.charAt(i) - 'a'] = false;
            }
        }
        return flag;
    }
    public static void main(String[] args) {
        String[] arr = {"cha","r","act","ers"};
        System.out.println(maxLength(Arrays.asList(arr)));
    }

}
