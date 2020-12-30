package intuit.ancestor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Ancestor1 {
    //输入是 int[][] input, input[0]是input[1]的parent，比如 {{1,4}, {1,5}, {2,5}, {3,6}, {6,7}}会形成上面的图
    //第一问是只有0个parents和只有1个parent的节点
    public static int solution(int[][] input) {
        int res = 0;
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for (int[] i : input) {
            graph.putIfAbsent(i[1], new ArrayList<>());
            graph.get(i[1]).add(i[0]);
        }
        for (Integer i : graph.keySet()) {
            int numOfParents = graph.get(i).size();
            if (numOfParents == 0 || numOfParents == 1) {
                res++;
            }
        }
        return res;
    }
    public static void main(String[] args) {
        int[][] input = {{1,4}, {1,5}, {2,5}, {3,6}, {6,7}};
        int res = solution(input);
        System.out.println(res);
    }

    /*
        construct graph
        goals to find children has how many parents
        Graph <Integer, List<Integer>> key is child, value is list of parents
        traverse the graph to get result

        Time: O(N) N == input.length
        Space: O(N)
         */

}
