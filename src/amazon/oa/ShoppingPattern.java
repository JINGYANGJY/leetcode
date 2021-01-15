package amazon.oa;

import java.util.*;

public class ShoppingPattern {
    /*
    Amazon is trying to understand customer shopping patterns
    and offer items that are regularly bought together to new customers.
    Each item that has been bought together can be represented as an undirected graph
    where edges join often bundled products. A group of n products is uniquely numbered from 1 of product_nodes.
    A trio is defined as a group of three related products that all connected by an edge.
    Trios are scored by counting the number of related products outside of the trio, this is referred as a product sum.

    Given product relation data, determine the minimum product sum for all trios of related products in the group.
    If no such trio exists, return -1.
     */
    /*
    graph problem
        find all trios
            how? DFS if (prev != cur) && nei is visited -> circle
                trios -> circle && length == 3
                this is a trio, add into list
        for each trio
            for each node in a trio
               number of  neighbors - the other nodes in the trio
             +
     */

    public static int minimumProduct (int products_nodes, int products_edges, int[] products_from, int[] products_to) {
        Map<Integer, Set<Integer>> graph = constructGraph(products_from, products_to);
        int[] minimum = new int[1];
        minimum[0] = Integer.MAX_VALUE;
        boolean[] visited = new boolean[products_nodes + 1];
        List<Integer> path = new ArrayList<>();
        for (int i = 1; i <= products_nodes; i++) {
            if (!visited[i]) {
                dfs(-1, i, graph, visited, path, minimum);
            }
        }
        return minimum[0];
    }

    private static Map<Integer, Set<Integer>> constructGraph(int[] products_from, int[] products_to) {
        Map<Integer, Set<Integer>> graph = new HashMap<>();
        for (int i = 0; i < products_from.length; i++) {
            int v = products_from[i];
            int w = products_to[i];
            graph.putIfAbsent(v, new HashSet<>());
            graph.putIfAbsent(w, new HashSet<>());
            graph.get(v).add(w);
            graph.get(w).add(v);
        }
        return graph;
    }

    private static int relatedProducts(List<Integer> path, Map<Integer, Set<Integer>> graph) {
        int size = path.size();
        int v = path.get(size - 1);
        int w = path.get(size - 2);
        int e = path.get(size - 3);
        System.out.println(v + "," + w + "," + e );
        int p1 = helper(graph, v, w, e);
        int p2 = helper(graph, w, v, e);
        int p3 = helper(graph, e, v, w);
        return p1 + p2 + p3;
    }

    private static int helper(Map<Integer, Set<Integer>> graph, int v1, int v2, int v3) {
        Set<Integer> neis = graph.get(v1);
        int size = neis.size();
        if (neis.contains(v2)) {
            size--;
        }
        if (neis.contains(v3)) {
            size--;
        }
        return size;
    }

    private static void dfs(int prev, int cur, Map<Integer, Set<Integer>> graph, boolean[] visiting, List<Integer> path, int[] minimum) {
        if (visiting[cur]) {
            int size = path.size();
            if (size > 2) {
                if (path.get(size - 3) == cur) {
                    int products = relatedProducts(path, graph);
                    if (products < minimum[0]) {
                        minimum[0] = products;
                    }
                }
            }
            return;
        }
        visiting[cur] = true;
        path.add(cur);
        for (Integer nei : graph.get(cur)) {
            if (nei == prev) continue;
            dfs(cur, nei, graph, visiting, path, minimum);
        }
        path.remove(path.size() - 1);
    }

    public static void main(String[] args) {
        int products_nodes = 6;
        int products_edges = 6;
        int[] products_from = {1,2,2,3,4,5};
        int[] products_to = {2,4,5,5,5,6};
        int res = minimumProduct(products_nodes, products_edges, products_from, products_to);
        System.out.println(res);

        products_nodes = 5;
        products_edges = 6;
        products_from = new int[]{1,1, 2, 2, 3, 4};
        products_to = new int[]{2,3, 3, 4, 4, 5};
        res = minimumProduct(products_nodes, products_edges, products_from, products_to);
        System.out.println(res);
    }
}
