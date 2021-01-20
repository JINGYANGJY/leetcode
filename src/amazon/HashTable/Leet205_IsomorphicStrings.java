package amazon.HashTable;

import java.util.Arrays;

public class Leet205_IsomorphicStrings {
    /*
    ASCII 第一位 ' '
    case 1:
        没有对应的 && 对应的没有并用过
    case 2：
        有对应的 && 对应的和要对应的一致
     */
    public boolean isIsomorphic(String s, String t) {
        int[] pattern = new int[128];
        boolean[] used = new boolean[128];
        Arrays.fill(pattern, -1);
        char[] arrS = s.toCharArray();
        char[] arrT = t.toCharArray();
        for (int i = 0; i < arrS.length; i++) {
            if (pattern[arrS[i] - ' '] == -1 && !used[arrT[i] - ' '] || pattern[arrS[i] - ' '] == arrT[i] - ' ') {
                pattern[arrS[i] - ' '] = arrT[i] - ' ';
                used[arrT[i] - ' '] = true;
            } else {
                return false;
            }
        }
        return true;
    }
}
