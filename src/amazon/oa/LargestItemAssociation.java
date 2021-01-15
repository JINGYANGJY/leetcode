package amazon.oa;

import java.util.*;

public class LargestItemAssociation {
    /*
    In order to improve customer experience,
    Amazon has developed a system to provide recommendations to the customer regarding
    the item they can purchase. Based on historical customer purchase information,
    an item association can be defined as - If an item A is ordered by a customer,
    then item B is also likely to be ordered by the same customer
    (e.g. Book 1 is frequently orderered with Book 2).
    All items that are linked together by an item association can be considered to be in the same group.
    An item without any association to any other item can be considered to be in
    its own item association group of size 1.

    Given a list of item association relationships(i.e.
    group of items likely to be ordered together),
    write an algorithm that outputs the largest item association group.
    If two groups have the same number of items then
    select the group which contains the item that appears first in lexicographic order.

    Input
    The itput to the function/method consists of an argument - itemAssociation,
    a list containing paris of string representing the items that are ordered together.

    Output
    Return a list of strings representing the largest association group sorted lexicographically.
     */
    /*
    model as a undirected graph problem
    1. construct graph
    2. goals to find the largest component
            if several component has same size, return whose lexicographic order is smallest
     */

    public static String[] largestcomponent(String[][] input) {
        Map<String, List<String>> graph = constructGraph(input);
        List<String> res = new ArrayList<>();
        Set<String> visited = new HashSet<>();
        for (String key : graph.keySet()) {
            if (!visited.contains(key)) {
                List<String> cnt = new ArrayList<>();
                dfs(key, cnt, graph, visited);
                if (cnt.size() > res.size()) {
                    res = cnt;
                } else if (cnt.size() == res.size()) {
                    if (compare(cnt, res)) {
                        res = cnt;
                    }
                }
            }
        }
        return res.toArray(new String[res.size()]);
    }

    private static boolean compare(List<String> cnt, List<String> res) {
        Collections.sort(cnt);
        Collections.sort(res);
        for (int i = 0; i < cnt.size(); i++) {
            int cmp = cnt.get(i).compareTo(res.get(i));
            if (cmp == 0) {
                continue;
            } else if (cmp < -1) {
                return true;
            } else {
                return false;
            }
        }
        return false;
    }

    private static void dfs(String key, List<String> cnt, Map<String, List<String>> graph, Set<String> visited) {
        if (visited.contains(key)) {
            return;
        }
        visited.add(key);
        cnt.add(key);
        for (String nei : graph.get(key)) {
            dfs(nei, cnt, graph, visited);
        }
    }

    private static Map<String, List<String>> constructGraph(String[][] input) {
        Map<String, List<String>> graph = new HashMap<>();
        for (String[] pair : input) {
            String v = pair[0];
            String w = pair[1];
            graph.putIfAbsent(v, new ArrayList<>());
            graph.putIfAbsent(w, new ArrayList<>());
            graph.get(v).add(w);
            graph.get(w).add(v);
        }
        return graph;
    }

    public static void main(String[] args) {
        String[][] itemAssociation = {
                            {"Item1", "Item2"},
                            {"Item2", "Item6"},
                            {"Item3", "Item4"},
                            {"Item4", "Item5"}
                            };
        String[] res = largestcomponent(itemAssociation);
        for (String s : res) {
            System.out.println(s);
        }
    }
}
