package intuit.ancestor;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/*
Clarification:
    if distance is 1 but has more than 1 parents, which one return be return?
    any

    if there the path has a circle?
    return which one?
    assume there is no circle on this path
input
    input[][], x
    output: find x's farest ancestor

    Graph problem directed graph
        1. construct the graph
                Map<Integer, Set<Integer>> graph
                key is all children nodes
                value is the parents set of current child node
        2. find x ancestor
                DFS -- from child to parent
                find the farest ancestor

 */
public class Ancestor3 {
    public static int farestAncestor (int[][] input, int x) {
        Map<Integer, Set<Integer>> graph = new HashMap<>();
        for (int[] i : input) {
            graph.putIfAbsent(i[1], new HashSet<>());
            graph.get(i[1]).add(i[0]);
        }
        int[] res = new int[1];
        int[] maxDis = new int[1];
        HashSet<Integer> visited = new HashSet<>();
        findParents(graph, x, maxDis, 0, res, visited);
        return res[0];
    }

    private static void findParents(Map<Integer, Set<Integer>> graph, int x, int[] maxDis, int dis, int[] res, HashSet<Integer> visited) {
        if (visited.contains(x)) {
            return;
        }
        visited.add(x);
        if (!graph.containsKey(x) || graph.get(x).size() == 0) {
            if (maxDis[0] < dis) {
                maxDis[0] = Math.max(maxDis[0], dis);
                res[0] = x;
            }
            return;
        }
        for (Integer i : graph.get(x)) {
            findParents(graph, i, maxDis, dis + 1, res, visited);
        }
    }

    public static void main(String[] args) {
        int[][] edges = { { 1, 4 }, { 1, 5 }, { 2, 5 }, { 3, 6 }, { 6, 7 }, { 3, 8 } };
        int[][] input = {{1,4}, {1,5}, {2,5}, {3,6}, {6,7}};
        int res = farestAncestor(edges, 7);
        System.out.println(res);
    }


}
