package intuit.course;

import java.util.*;

public class course {
    /*
    输入学生的ID和他上的课程，找到每两个学生上的相同的课程。
    第一题：输入学生的ID和他上的课程，找到每两个学生上的相同的课程。
    例如，输入{{"58", "A"},  {"94", "B"},  {"17", "A"},  {"58", "B"},  {"17", "B"},  {"58", "C"}}
````输出:[58, 94]: [B]
        [58, 17]: [A, B]
        [94, 17]: []````

        Clarify:
            each student more than 1 courses?
            s1, s2 no common course?

            --
            every student's courses
                Map<String, Set<String>>
            every two students
                for each key
                    for each key
                        common string of their course lists --

            Time: O(n + n^2 * distinct courses)

         for loop
             Map<String, Set<String>>
             for
                for
                    two students common courses
         Time:O(n + |N| ^ 2 * |courses|)


    */

    public static Map<String[], String[]> findCommonCourses(String[][] input) {
        Map<String[], String[]> res = new HashMap<>();
        Map<String, Set<String>> studentsCourses = new HashMap<>();
        for (String[] str : input) {
            studentsCourses.putIfAbsent(str[0], new HashSet<>());
            studentsCourses.get(str[0]).add(str[1]);
        }
        List<String> students = new ArrayList<>(studentsCourses.keySet());
        for (int i = 0; i < students.size(); i++) {
            for (int j = i + 1; j < students.size(); j++) {
                List<String> commonCourse = getCommonCourse(studentsCourses.get(students.get(i)), studentsCourses.get(students.get(j)));
                res.put(new String[]{students.get(i), students.get(j)}, commonCourse.toArray(new String[commonCourse.size()]));
            }
        }
        return res;
    }

    private static List<String> getCommonCourse(Set<String> set1, Set<String> set2) {
        List<String> res = new ArrayList<>();
        for (String s : set1) {
            if (set2.contains(s)) {
                res.add(s);
            }
        }
        return res;
    }

    /*
    给出一些课程和课程的先修课，每个课程有且只有一门先修课，并且保证学生只有一条path修完所有课程，求修到一半时的课程名称。
     */
    public static String middleCourse(String[][] input) {
        /*
         1-2-3-4-5-6-7
         model graph
         vertex:
         edge: input[i] input[i][0] -> input[i][1]
           Map<vertex, vertex> graph
           indegree: 1
           indegree: 0

         Time: O(n + |V|) -> O(|V|)
           Clarify:
                this path contains all courses in the input?
                -->
                    like an linked list
                the start course of the path?
                -> find one course which does not have prerequisite
                Map<String, String> map -> map course with its prerequisite
                {{A, B}, {C, D}, {B, C}, {E, F}, {D, E}, {F, G}}
                A, B
                A B C D E F G size = 7 middle = 3
                Map<String, String>
                for each pair
                    map
                    prerequisite ->
                    Map<String, Integer> inDegree <B, 1>

                Time: O(n)
         */

        Map<String, String> map = new HashMap<>();
        Map<String, Integer> inDegree = new HashMap<>();
        for (String[] str : input) {
            map.put(str[0], str[1]);
            inDegree.put(str[0], inDegree.getOrDefault(str[0], 0) + 0);
            inDegree.put(str[1], inDegree.getOrDefault(str[1], 0) + 1);
        }
        String start = "";
        for (String key : inDegree.keySet()) {
            if (inDegree.get(key) == 0) {
                start = key;
            }
        }

        int size  = inDegree.size();
        int middle = size % 2 == 0 ? size / 2 - 1 : size / 2;
        while (middle > 0) {
            start = map.get(start);
            middle--;
        }
        return start;
    }

    /*
    假设每门课程可以有多门先修课,找出所有path修到一半课程的名称
                         A
                        / \
                       B   D
                        \ /
                         E
              {B, D}
        DFS
            start courses are without has pre..
            Time: O(|V|^|E|)

            Clarify:
            Graph problem
            vertex:
            edge: input[i]
                   input[i][0] -> input[i][1]

            path start courses:
                vertexes  indegree == 0
            for each of vertexes
                dfs(vertex)
                    find all path
                        record path -> middle course
           O(|number of start course| *  V^E) -> O(v^E)
     */
    public static Set<String> allMiddleCourse(String[][] input) {
        Map<String, Integer> inDegreeMap = new HashMap<>();
        Map<String, List<String>> graph = constructGraph(input, inDegreeMap);
        List<String> coursesWithoutPre = new ArrayList<>();
        for (String course : inDegreeMap.keySet()) {
            if (inDegreeMap.get(course) == 0) {
                coursesWithoutPre.add(course);
            }
        }
        Set<String> res = new HashSet<>();
        for (String start : coursesWithoutPre) {
            Set<String> visited = new HashSet<>();
            List<String> curPath = new ArrayList<>();
            curPath.add(start);
            visited.add(start);
            dfs(start, graph, visited, curPath, res);
        }
        return res;
    }

    private static Map<String, List<String>> constructGraph(String[][] input, Map<String, Integer> inDegreeMap) {
        Map<String, List<String>> graph = new HashMap<>();
        for (String[] str : input) {
            graph.putIfAbsent(str[0], new ArrayList<>());
            graph.get(str[0]).add(str[1]);
            inDegreeMap.put(str[0], inDegreeMap.getOrDefault(str[0], 0) + 0);
            inDegreeMap.put(str[1], inDegreeMap.getOrDefault(str[1], 0) + 1);
        }
        return graph;
    }

    private static void dfs(String cur, Map<String, List<String>> graph, Set<String> visited, List<String> curPath, Set<String> res) {
        if (!graph.containsKey(cur) || graph.get(cur).size() == 0) {
//            for (String s : curPath) {
//                System.out.print(s + " ");
//            }
//            System.out.println();
            int size = curPath.size();
            int middle = size % 2 == 0 ? size / 2 - 1 : size / 2;
            res.add(curPath.get(middle));
            return;
        }
        for (String nei : graph.get(cur)) {
            visited.add(nei);
            curPath.add(nei);
            dfs(nei, graph, visited, curPath, res);
            visited.remove(nei);
            curPath.remove(curPath.size() - 1);
        }
    }





    public static void main(String[] args) {
        System.out.println("----------------------course1----------------------------------");
        String[][] coursePairs = {{"58", "Software Design"}, {"58", "Linear Algebra"}, {"94", "Art History"},
                {"94", "Operating Systems"}, {"17", "Software Design"}, {"58", "Mechanics"},
                {"58", "Economics"}, {"17", "Linear Algebra"}, {"17", "Political Science"}, {"94", "Economics"},
                {"25", "Economics"}};
        Map<String[], String[]> result = findCommonCourses(coursePairs);
        System.out.println("{");
        for (String[] key : result.keySet()) {
            System.out.println("[" + key[0] + ", " + key[1] + "]" + ": [");
            for (String courses : result.get(key)) {
                System.out.println(courses + " ");
            }
            System.out.println("]");
        }
        System.out.println("}");
        System.out.println("----------------------course2----------------------------------");
        String[][] courses = { { "A", "B" }, { "C", "D" }, { "B", "C" }, { "E", "F" }, { "D", "E" }, { "F", "G" }, {"G", "H"}};
        System.out.println(middleCourse(courses));
        System.out.println("----------------------course3----------------------------------");
        String[][] all_courses = { { "Logic", "COBOL" }, { "Data Structures", "Algorithms" },
                { "Creative Writing", "Data Structures" }, { "Algorithms", "COBOL" },
                { "Intro to Computer Science", "Data Structures" }, { "Logic", "Compilers" },
                { "Data Structures", "Logic" }, { "Creative Writing", "System Administration" },
                { "Databases", "System Administration" }, { "Creative Writing", "Databases" },
                { "Intro to Computer Science", "Graphics" } };
        Set<String> res = allMiddleCourse(all_courses);
        for (String s : res) {
            System.out.println(s);
        }
    }




}
