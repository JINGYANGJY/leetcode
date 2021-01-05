package intuit.ancestor;

import java.util.*;

// find all of the parents of given two nodes
// loop one of the set to find whether there is an overlap
/*
graph problem
    1. construct graph directed graph
    2. DFS
        find all parents of x
        ....                y
    3. check whether has overlap
Time: O(n) + O(n) + O(n) = O(n)




 */
public class Ancestor2 {
    public static boolean Ancestor(int[][] input, int x, int y) {
        Map<Integer, Set<Integer>> graph = new HashMap<>();
        for (int[] i : input) {
            graph.putIfAbsent(i[1], new HashSet<>());
            graph.get(i[1]).add(i[0]);
        }
        Set<Integer> parents1 = new HashSet<>();
        findAllParents(graph, parents1, x);
        Set<Integer> parents2 = new HashSet<>();
        findAllParents(graph, parents2, y);
        for (Integer i : parents1) {
            if (parents2.contains(i)) {
                return true;
            }
        }
        return false;
    }
    private static void findAllParents(Map<Integer, Set<Integer>> graph, Set<Integer> res, int cur) {
        if (!graph.containsKey(cur) || graph.get(cur).size() == 0) {
            return;
        }

        for (Integer p : graph.get(cur)) {
            res.add(p);
            findAllParents(graph, res, p);
        }
    }

    public static void main(String[] args) {
        int[][] input = {{1,4}, {1,5}, {2,5}, {3,6}, {6,7}};
        int[][] edges = { { 1, 4 }, { 1, 5 }, { 2, 5 }, { 3, 6 }, { 6, 7 }, { 3, 8 } };
        System.out.println(Ancestor(edges, 7, 8));
        boolean res = Ancestor(input, 4, 5);
        System.out.println(res);
    }
}
