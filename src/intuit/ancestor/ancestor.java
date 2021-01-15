package intuit.ancestor;

import java.util.*;

public class ancestor {
    /*
    讲： 4'34'
    写代码：3'27'

    只有0个parents和只有1个parent的节点
    输入是 int[][] input, input[0]是input[1]的parent，比如 {{1,4}, {1,5}, {2,5}, {3,6}, {6,7}}会形成上面的图
    - construct directed graph
        vertex
        edges
        Map<Integer, List<Integer>> graph
            input[i][1] -> input[i][0]
        Time: O(n + |V|) --> O(n)
      construct directed graph
        vertex: distinct integers
        edges: input[i]
                    input[i][0] <- input[i][1]
        Time:O(n + |V|) -> O(n)
        n is length of the input
     */
    public static int ancestor1(int[][] input) {
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for (int[] i : input) {
            graph.putIfAbsent(i[1], new ArrayList<>());
            graph.get(i[1]).add(i[0]);
        }
        int res = 0;
        for (Integer key : graph.keySet()) {
            if (graph.get(key).size() <= 1) {
                res++;
            }
        }
        return res;
    }
    /*
     find all of the parents of given two nodes
     loop one of the set to find whether there is an overlap
     */
    public static boolean ancestor2(int[][] input, int i, int j) {
        /*
        讲： 4'
        写： 5'
         - directed graph
         - start i to find all parents
         - start j to find all parents
                how?
                DFS
                    Set

             Set
             Set
             for
             Time: O(n + |V| + |E|)

             directed graph
             -> common ancestor
             find all ancestor of these two nodes
                    DFS
                        start one find all reachable nodes
                                         1
                                        / \
                                       2   4
                                      / \  /
                                     5   6
                       set1
                       set2

             common elements in two set
             Time:O(n + |V| + |E| + |V|)
            -> O(|V| + |E|)
         */
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for (int[] arr : input) {
            graph.putIfAbsent(arr[1], new ArrayList<>());
            graph.get(arr[1]).add(arr[0]);
        }
        Set<Integer> set1 = new HashSet<>();
        Set<Integer> visited = new HashSet<>();
        dfs(i, graph, set1, visited);
        Set<Integer> set2 = new HashSet<>();
        visited.clear();
        dfs(j, graph, set2, visited);

        for (Integer n : set1) {
            if (set2.contains(n)) {
                return true;
            }
        }
        return false;
    }

    private static void dfs(int cur, Map<Integer, List<Integer>> graph, Set<Integer> set, Set<Integer> visited) {
        if (visited.contains(cur)) {
            return;
        }
        visited.add(cur);
        set.add(cur);
        if (!graph.containsKey(cur)) {
            return;
        }
        for (Integer nei : graph.get(cur)) {
            dfs(nei, graph, set, visited);
        }
    }

    /*
    find x's farest ancestor
    讲： 5
    写： 10
     */
    public static int ancestor3(int[][] input, int i) {
        /*
            --
                directed graph
                start from i
                DFS
                    int[] dis
                    int i
                    all paths start from i, and get its farthest ancestor

                                    1
                                   / \
                                  2   3
                                 / \ /
                                4   5
                                   / \
                                  6   7
                     mark visited
            Time: O(V^E)

            find all paths in the graph
                  farthest ancestor
            -> DFS
                                1
                               / \
                              2   3
                             / \   \
                             4  5 _ 6
                                /
                                7
                    find all paths
                            for the nodes
                                    dis

            Time: O(|V|^|E|)

         */
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for (int[] arr : input) {
            graph.putIfAbsent(arr[1], new ArrayList<>());
            graph.get(arr[1]).add(arr[0]);
        }
        int[] res = new int[1];
        int[] maxDis = new int[1];
        Set<Integer> visited = new HashSet<>();
        dfs(i, res, 0, maxDis, graph, visited);
        return res[0];
    }

    private static void dfs(int cur, int[] res, int dis, int[] maxDis, Map<Integer, List<Integer>> graph, Set<Integer> visited) {
        if (dis > maxDis[0]) {
            maxDis[0] = dis;
            res[0] = cur;
        }
        if (!graph.containsKey(cur)) {
            return;
        }
        if (visited.contains(cur)) {
            return;
        }
        visited.add(cur);
        for (Integer nei : graph.get(cur)) {
            dfs(nei, res, dis + 1, maxDis, graph, visited);
        }
        visited.remove(cur);
    }
    public static void main(String[] args) {
        System.out.println("---------------ancestor1---------------------");
        int[][] input = {{1,4}, {1,5}, {2,5}, {3,6}, {6,7}};
        int res = ancestor1(input);
        System.out.println(res);

        System.out.println("---------------ancestor2---------------------");
       // int[][] input = {{1,4}, {1,5}, {2,5}, {3,6}, {6,7}};
        int[][] edges = { { 1, 4 }, { 1, 5 }, { 2, 5 }, { 3, 6 }, { 6, 7 }, { 3, 8 } };
        System.out.println(ancestor2(edges,5, 4));

        System.out.println("---------------ancestor3---------------------");
       // int[][] edges = { { 1, 4 }, { 1, 5 }, { 2, 5 }, { 3, 6 }, { 6, 7 }, { 3, 8 } };
       // int[][] input = {{1,4}, {1,5}, {2,5}, {3,6}, {6,7}};
        System.out.println(ancestor3(edges, 7));

    }


}
