package oa.tiktok;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class minimumTransformation {
    /*
    A character transformation T(char1, char2) of a string is defined
    as converting all char1's within that string into char2's.
    For example, T(t', s') for string "tiktok" will result in
    "siksok" by replacing all letter 't' with letter 's'
    You were given 2 strings,
     */
    public static void main(String[] args) {
  //      System.out.println(minimumTransformation("abcdefghijk", "bcdefghijkl"));
//        System.out.println(minimumTransformation("abc", "bcd"));
//        System.out.println(minimumTransformation("bc", "aa"));
//        System.out.println(minimumTransformation("ababcc", "cccccc"));
//        System.out.println(minimumTransformation("ab", "ba"));
        System.out.println(minimumTransformation("abcd", "dabc"));
    }
    static int[] visited = new int[26];

    public static int minimumTransformation(String source, String target) {
        /*
        source --> target
        tiktok --> sikaok --> return -1, not possible
        dependency: s-->t, a-->t
        source --> target
        ababcc --> cccccc
        a --> c
        b --> c
        c --> c
         */
       Character[] map = new Character[26];
       int res = 0;
       for (int i = 0; i < source.length(); i++) {
           Character key = source.charAt(i);
           Character value = target.charAt(i);
           if (map[key - 'a'] != null && map[key - 'a'] != value) {
               return -1;
           } else if (key == value) {
               continue;
           }
           map[key - 'a'] = value;
       }
       for (int i = 0; i < 26; i++) {
           if (map[i] != null && visited[i] == 0) {
               int[] partialRes = new int[1];
               if (hasCycle(i, map, partialRes)) {
                   res++;
               }
               res += partialRes[0];
           }
       }
       return res;
    }

    public static boolean hasCycle(int i, Character[] map, int[] res) {
        if (map[i] == null) {
            return false;
        }
        if (visited[i] == 1) { //visiting
            return true;
        }
        if (visited[i] == 2) { //visited
            return false;
        }
        visited[i] = 1;
        res[0]++;
        if (map[i] != null && hasCycle(map[i] - 'a', map, res)) {
            return true;
        }
        visited[i] = 2;
        return false;
    }

    public int getMin (String one, String two) {
        char[] org = one.toCharArray();
        char[] target = two.toCharArray();
        Integer[] graph = new Integer[26];
        boolean[] flag = new boolean[26];
        for (int i = 0; i < target.length; i++) {
            int j = target[i] - 'a';
            int k = org[i] - 'a';
            if (graph[j] != null && Integer.compare(graph[j],k) != 0) {
                return -1;
            }
            graph[j] = k;
        }
        int res = 0;
        for (int i = 0; i <target.length; i++) {
            if (target[i] == org[i] || flag[target[i] - 'a']) continue;
            Integer next = target[i] - 'a';
            boolean[] visited = new boolean[26];
            while (graph[next] != null && !visited[next] && Integer.compare(graph[next],next) != 0) {
                visited[next] = flag[next] = true;
                next = graph[next];
                res++;
            }
            flag[next] = true;
            res += visited[next] ? 1 : 0;
        }
        return res;
    }
}
