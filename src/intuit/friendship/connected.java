package intuit.friendship;

import java.util.*;

public class connected {

    public static Map<Integer, List<Integer>> constructGraph(List<String> employees, List<int[]> friendships, int[] anyone) {
        Map<Integer, String> employeeMap = new HashMap<>();
        Map<Integer, String> departmentMap = new HashMap<>();
        for (String s : employees) {
            String[] strs = s.split(",");
            Integer id = Integer.valueOf(strs[0]);
            String name = strs[1];
            String department = strs[2];
            employeeMap.put(id, name);
            departmentMap.put(id, department);
        }
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for (Integer key : employeeMap.keySet()) {
            anyone[0] = Math.max(key, anyone[0]);
            graph.putIfAbsent(key, new ArrayList<>());
        }
        for (int[] friendship : friendships) {
            int x = friendship[0];
            int y = friendship[1];
            graph.get(x).add(y);
            graph.get(y).add(x);
        }
        return graph;
    }

    public static void main(String[] args) {
        List<String> employees = new ArrayList<>();
        employees.add("1,Richard,Engineering");
        employees.add("2,Erlich,HR");
        employees.add("3,Monica,Business");
        employees.add("4,Dinesh,Engineering");
        employees.add("6,Carla,Engineering");
        List<int[]> friendships = new ArrayList<>();
        friendships.add(new int[]{1,2});
        friendships.add(new int[]{1,3});
        friendships.add(new int[]{2,4});
        friendships.add(new int[]{6,4});
        int[] anyOne = new int[1];
        Map<Integer, List<Integer>> res = constructGraph(employees, friendships, anyOne);
        for (Integer i : res.keySet()) {
            System.out.print(i + " : ");
            System.out.println(Arrays.toString(res.get(i).toArray()));
        }
        HashSet<Integer> visited = new HashSet<>();
        checkConnected(res, anyOne[0], visited);
        if (visited.size() == res.keySet().size()) {
            System.out.println(true);
        } else {
            System.out.println(false);
        }

    }

    private static void checkConnected(Map<Integer, List<Integer>> graph, int cur, HashSet<Integer> visited) {
        if (!graph.containsKey(cur) || visited.contains(cur)) {
            return;
        }
        visited.add(cur);
        for (Integer f : graph.get(cur)) {
            checkConnected(graph, f, visited);
        }
    }
}
