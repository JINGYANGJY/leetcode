package DFS;

import java.util.HashSet;
import java.util.Set;

public class Leet1079LetterTilePossibilities {
    /*
    Given a string, return the number of sequence like following
    Example:
    "tiles = "AAB"
    return 8 because "A", "B", "AA", "AB", "BA", "AAB", "ABA", "BAA"
    Solution:
        len from 1 - tiles.length
        substring and its permutations
                            "AAB"
                            /   \
        level1              A    B          {A, B} res += 2
                           /\    /
                           A B   A          {AA, AB, BA} res += 3
                           / \   \
                           B A   A          {AAB, ABA, BAA} res += 3
                           --> return == 8
        --> DFS
            find the permutations of input
            for each level  put distinct characters on the position of current level
              -> current string.len == level sub permutations add number of sub permutations into result
            --> result
     */
    public static int numTilePossibilities(String tiles) {
        int[] res = new int[1];
        char[] arr = tiles.toCharArray();
        permutations(0, arr, res);
        return res[0] - 1; // exclude " "
    }
    private static void swap(char[] arr, int i, int j) {
        char temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
    private static void permutations(int level, char[] arr, int[] res) {
        res[0] += 1;
        if (level == arr.length) {
            return;
        }
        Set<Character> set = new HashSet<>();
        for (int i = level; i < arr.length; i++) {
            if (set.add(arr[i])) {
                swap(arr, i, level);
                permutations(level + 1, arr, res);
                swap(arr, i, level);
            }
        }
    }

    public static void main(String[] args) {
        System.out.println(numTilePossibilities("AAB"));
    }
}
