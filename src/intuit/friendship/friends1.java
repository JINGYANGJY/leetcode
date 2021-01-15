package intuit.friendship;

import java.lang.reflect.Array;
import java.util.*;

public class friends1 {
   /* suppose you are creating an internal networking site for your company.
    You have two data sets to work with. The first data set is the employees at your company,
    and the second is all the pairs of employees who are virtually friends so far.
    It does not matter which employee’sID is in which column, the friendships are bidirectional.
    To get started, you need to parse and represent each data set in its entirety in code.
            Furthermore, you also need to implement a function that,
            given the employees and friendships as parameters, returns this data
            in the form of an adjacency list representation. This is a mapping of each employee
            ID to a list of his/her friends on the site.
*/
//    employees.csv:
//employee_id,name,department
//1,Richard,Engineering
//2,Erlich,HR
//3,Monica,Business
//4,Dinesh,Engineering
//6,Carla,Engineering
//friendships.csv:
//    id_1,id_2
//        1,2
//        1,3
//        2,4
//    Expected Output
//        1: 2, 3
//        2: 1, 4
//        3: 1
//        4: 2
//        6: None
/*
        Map<Integer, List<Integer>> graph;

 */
    public static Map<Integer, List<Integer>> constructGraph(List<String> employees, List<int[]> friendships) {
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
        Map<Integer, List<Integer>> res = constructGraph(employees, friendships);
        for (Integer i : res.keySet()) {
            System.out.print(i + " : ");
            System.out.println(Arrays.toString(res.get(i).toArray()));
        }

    }
}
