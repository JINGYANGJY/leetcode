package intuit.friendship;

import java.util.*;

public class Q2 {
    /*
    2nd Question: 输出每个department里有多少人的朋友是其他部门的 ->也就是遍历一遍就好了
     */
    public static Map<String, Integer> Q2(List<String> employees, List<int[]> friendships) {
        // simplify input
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
        // construct adjacent graph
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for (Integer key : employeeMap.keySet()) {
            graph.putIfAbsent(key, new ArrayList<>());
        }
        for (int[] friendship : friendships) {
            int x = friendship[0];
            int y = friendship[1];
            graph.get(x).add(y);
            graph.get(y).add(x);
        }
        Map<String, Integer> res = new HashMap<>();
        for (Integer i : graph.keySet()) {
            String dptm = departmentMap.get(i);
            int count = 0;
            for (Integer f : graph.get(i)) {
                if (!departmentMap.get(f).equals(dptm)) {
                    count++;
                }
            }
            res.put(dptm, res.getOrDefault(dptm, 0) + count);
        }
        return res;
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
        Map<String, Integer> res = Q2(employees, friendships);
        for (String dp : res.keySet()) {
            System.out.print(dp + " : ");
            System.out.println(res.get(dp));
        }
    }

}
