package amazon.oa;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShoppingPatterns {
    public static int find(int nodes, int edges, int[] from, int[] to) {
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for (int i = 0; i < from.length; i++) {
            graph.computeIfAbsent(from[i],k -> new ArrayList<>()).add(to[i]);
            graph.computeIfAbsent(to[i],k -> new ArrayList<>()).add(from[i]);
        }
        boolean[] visited = new boolean[nodes + 1];
        int[] res = new int[]{nodes};
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < nodes;i ++) {
            list.add(i);
            dfs(graph,i,i,-1,list,res);
            list.remove(0);

        }
        return res[0];
    }
    private static void dfs (Map<Integer,List<Integer>> graph, int start, int cur, int pre, List<Integer> list, int[] res) {
        if (list.size() == 4) {
            if (list.get(3) == start)
                update(res,list,graph);
            return;
        };
        if (graph.containsKey(cur))
            for (int next : graph.get(cur)) {
                if (pre == next) continue;
                list.add(next);
                dfs(graph, start, next, cur,list, res);
                list.remove(list.size() - 1);

            }

    }
    private static void update(int[] res, List<Integer> list, Map<Integer,List<Integer>> graph) {
        int val = 0;
        for(int i = 0; i < 3; i++) {
            val += graph.get(list.get(i)).size() - 2;
        }
        res[0] = Math.min(val,res[0]);
    }

    public static void main(String[] args) {
        int products_nodes = 6;
        int products_edges = 6;
        int[] products_from = {1,2,2,3,4,5};
        int[] products_to = {2,4,5,5,5,6};
        int res = find(products_nodes, products_edges, products_from, products_to);
        System.out.println(res);

        products_nodes = 5;
        products_edges = 6;
        products_from = new int[]{1,1, 2, 2, 3, 4};
        products_to = new int[]{2,3, 3, 4, 4, 5};
        res = find(products_nodes, products_edges, products_from, products_to);
        System.out.println(res);
    }
}
