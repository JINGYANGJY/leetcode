package oracle.medium;
/*
Given a string s and an array of integers cost where cost[i] is the cost of deleting the ith character in s.

Return the minimum cost of deletions such that there are no two identical letters next to each other.

Notice that you will delete the chosen characters at the same time, in other words, after deleting a character,
the costs of deleting other characters will not change.
 */
public class minCost {
    public int minCost(String s, int[] cost) {
        int res = 0;
        char[] arr = s.toCharArray();
        for (int i = 0; i < arr.length; i++) {
            int max = 0, sum = 0;
            while (i + 1 < arr.length && arr[i] == arr[i + 1]) {
                max = Math.max(max, cost[i]);
                sum += cost[i];
                i++;
            }
            if (sum > 0) {
                max = Math.max(max, cost[i]);
                sum += cost[i];
                res += sum - max;
            }
        }
        return res;
    }
}
