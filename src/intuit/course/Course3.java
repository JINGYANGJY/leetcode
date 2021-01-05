package intuit.course;

import java.util.*;

public class Course3 {
    //假设每门课程可以有多门先修课,找出所有path修到一半课程的名称
    /*
    graph problem
        1. construct the graph
            vertex: courses
            edges: A -> B
            inDegree: Map<String, Integer>
        2. find all courses which has 0 indegree, start course of each path
        3. DFS
            from each start course
                the middle courses of the paths starting form this course

                        A     B
                          \  /
                            C
                          /   \
                          E   F

                A : ACF,  ACE
                        dfs
                            for each course check if it is middle course
                                    steps1: from start to current
                                    steps2: from the last course to current course

                                    steps1 == step2
                                    add course to the res

                B: BCE, BCF

     */

    public static Set<String> middleCourses (String[][] input) {
        Map<String, Integer> inDegree = new HashMap<>();
        Map<String, Set<String>> graph = constructGraph(input, inDegree);
        List<String> starts = new ArrayList<>();
        for (String key : graph.keySet()) {
            if (!inDegree.containsKey(key) || inDegree.get(key) == 0) {
                starts.add(key);
            }
        }
        Set<String> res = new HashSet<>();
        for (String start : starts) {
            List<String> path = new ArrayList<>();
            path.add(start);
            dfs(start, graph, path, res);
        }
        return res;
    }
    private static void dfs(String cur, Map<String, Set<String>> graph, List<String> path,  Set<String> res) {
        if (!graph.containsKey(cur) || graph.get(cur).size() == 0) {
            //System.out.println(Arrays.deepToString(path.toArray()));
            int len = path.size();
            if (len <= 0) {
                return;
            } else if (len % 2 == 0) {
                res.add(path.get(len / 2 - 1));
            } else {
                res.add(path.get(len / 2));
            }
            return;
        }
        for (String nei : graph.get(cur)) {
            path.add(nei);
            dfs(nei, graph, path, res);
            path.remove(path.size() - 1);
        }
    }
    private static Map<String, Set<String>> constructGraph(String[][] input, Map<String, Integer> inDegree) {
        Map<String, Set<String>> graph = new HashMap<>();
        for (String[] strings : input) {
            graph.putIfAbsent(strings[0], new HashSet<>());
            graph.get(strings[0]).add(strings[1]);
            inDegree.put(strings[1], inDegree.getOrDefault(strings[1], 0) + 1);
        }
        return graph;
    }

    public static void main(String[] args) {
        String[][] all_courses = { { "Logic", "COBOL" }, { "Data Structures", "Algorithms" },
                { "Creative Writing", "Data Structures" }, { "Algorithms", "COBOL" },
                { "Intro to Computer Science", "Data Structures" }, { "Logic", "Compilers" },
                { "Data Structures", "Logic" }, { "Creative Writing", "System Administration" },
                { "Databases", "System Administration" }, { "Creative Writing", "Databases" },
                { "Intro to Computer Science", "Graphics" } };
        Set<String> res = middleCourses(all_courses);
        for (String s : res) {
            System.out.println(s);
        }
        System.out.println("------------------------");
        Set<String> r = middleCourse(all_courses);
        for (String s : r) {
            System.out.println(s);
        }
    }

    public static Set<String> middleCourse(String[][] input) {
        Map<String, List<String>> map = new HashMap<>();
        Map<String, Integer> inDegree = new HashMap<>();
        for (String[] str : input) {
            map.putIfAbsent(str[0], new ArrayList<>());
            map.get(str[0]).add(str[1]);
            inDegree.put(str[1], inDegree.getOrDefault(str[1], 0) + 1);
        }
        List<String> startCourses = new ArrayList<>();
        for (String s : map.keySet()) {
            if (!inDegree.containsKey(s)) {
                startCourses.add(s);
            }
        }
        Set<String> res = new HashSet<>();
        for (String c : startCourses) {
            List<String> path = new ArrayList<>();
            path.add(c);
            dfs(c, map, res, path);
        }
        return res;
    }
    //find the middle of each path
    private static void dfs(String cur, Map<String, List<String>> map, Set<String> res, List<String> path) {
        if (!map.containsKey(cur) || map.get(cur).size() == 0) {
            int size = path.size();
            int middleIndex = size % 2 == 0 ? size / 2 - 1 : size / 2;
            res.add(path.get(middleIndex));
            return;
        }
        for (String n : map.get(cur)) {
            path.add(n);
            dfs(n, map, res, path);
            path.remove(path.size() - 1);
        }
    }

}
