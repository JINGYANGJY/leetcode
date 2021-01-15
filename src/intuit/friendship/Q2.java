package intuit.friendship;

import java.util.*;

public class Q2 {
    /*
    2nd Question: 输出每个department里有多少人的朋友是其他部门的 ->也就是遍历一遍就好了
    - Clarify
        depart how many employees
               friendsAreOtherDepartment
        - Map<String, String> departmentMap

           for each employee
                what department of his
                        Map<String, Integer>: how many employees of the department
                    for friend
                        Map<String, Integer> :how many employees has other department's friends
        Time: O(n + |V| + |E|)
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
        System.out.println("--------------");
        List<String> r = departments(employees, friendships);
        for (String s : r) {
            System.out.println(s);
        }
    }

    public static Map<Integer, List<Integer>> constructGraph(List<int[]> friendships) {
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for (int[] fs : friendships) {
            int w = fs[0];
            int v = fs[1];
            graph.putIfAbsent(w, new ArrayList<>());
            graph.putIfAbsent(v, new ArrayList<>());
            graph.get(w).add(v);
            graph.get(v).add(w);
        }
        return graph;
    }


    public static List<String> departments(List<String> employees, List<int[]> friendships) {
        Map<Integer, String> departments = new HashMap<>();
        Map<Integer, List<Integer>> graph = constructGraph(friendships);
        for (String employee : employees) {
            String[] items = employee.split(",");
            Integer id = Integer.valueOf(items[0]);
            String dp = items[2];
            departments.put(id, dp);
        }
        Map<String, Integer> numEmployees = new HashMap<>();
        Map<String, Integer> friends = new HashMap<>();
        for (Integer i : graph.keySet()) {
            String dp = departments.get(i);
            numEmployees.put(dp, numEmployees.getOrDefault(dp, 0) + 1);
            for (Integer f : graph.get(i)) {
                String dpF = departments.get(f);
                if (!dp.equals(dpF)) {
                    friends.put(dp, friends.getOrDefault(dp, 0) + 1);
                }
            }
        }
        List<String> res = new ArrayList<>();
        for (String dp : numEmployees.keySet()) {
            res.add(dp + " num: " + String.valueOf(numEmployees.get(dp))
                    + " friends: " + String.valueOf(friends.get(dp)));
        }
        return res;
    }


}
