package intuit.course;

import java.util.*;

public class Course1 {
    /*
    第一题：输入学生的ID和他上的课程，找到每两个学生上的相同的课程。
    例如，输入{{"58", "A"},  {"94", "B"},  {"17", "A"},  {"58", "B"},  {"17", "B"},  {"58", "C"}}
````输出:[58, 94]: [B]
        [58, 17]: [A, B]
        [94, 17]: []````

        step 1: each students attend which courses
                Map<String, Set<String>> student, list of course he/she attended
        step 2: find common course of each two students
                each two students
                    for (student1)
                        for (student2)
                            common list of course
                               M1. set -- O(max(s1.list.size(), s2.list.size()))
                               M2: sort first, use two pointers O(nlogn)+ O（mlogm） + O(min(s1.list.size(), s2.list.size()))
                               choose M1
              n -- input.length
              m -- student number

        Time: O(n) + O(m^2 * max(s1.course, s2.course))
     */

    public static Map<String[], String[]> findCommonCourses(String[][] input) {
        Map<String[], String[]> res = new HashMap<>();
        Map<String, Set<String>> graph = new HashMap<>();
        List<String> students = new ArrayList<>();
        for (String[] strings : input) {
            if (!graph.containsKey(strings[0])) {
                graph.put(strings[0], new HashSet<>());
                students.add(strings[0]);
            }
            graph.get(strings[0]).add(strings[1]);
        }
        for (int i = 0; i < students.size(); i++) {
            for (int j = i + 1; j < students.size(); j++) {
                Set<String> course1 = graph.get(students.get(i));
                Set<String> course2 = graph.get(students.get(j));
                List<String> commonCourses = new ArrayList<>();
                for (String c : course1) {
                    if (course2.contains(c)) {
                        commonCourses.add(c);
                    }
                }
                res.put(new String[]{students.get(i), students.get(j)}, commonCourses.toArray(new String[commonCourses.size()]));
            }
        }
        return  res;
    }

    public static void main(String[] args) {
        String[][] coursePairs = { { "58", "Software Design" }, { "58", "Linear Algebra" }, { "94", "Art History" },
                { "94", "Operating Systems" }, { "17", "Software Design" }, { "58", "Mechanics" },
                { "58", "Economics" }, { "17", "Linear Algebra" }, { "17", "Political Science" }, { "94", "Economics" },
                { "25", "Economics" } };
        Map<String[], String[]> result = findCommonCourses(coursePairs);
//        for (String[] s : res.keySet()) {
//            System.out.println("students: " + s[0]  + "  "+ s[1]);
//            for (String a : res.get(s)) {
//                System.out.print(a + " ");
//            }
//            System.out.println();
//        }
        System.out.println("{");
        for (String[] key : result.keySet()) {
            System.out.println("[" + key[0] + ", " + key[1] + "]" + ": [");
            for (String courses : result.get(key)) {
                System.out.println(courses + " ");
            }
            System.out.println("]");
        }
        System.out.println("}");

        Map<String, List<String>> res = commonCourses(coursePairs);
        System.out.println("-----------------------------");
        System.out.println("{");
        for (String key : res.keySet()) {
            System.out.println("[" + key + "]" + ": [");
            for (String s : res.get(key)) {
                System.out.println(s + " ");
            }
            System.out.println("]");
        }
        System.out.println("}");
    }

    public static Map<String, List<String>> commonCourses(String[][] input)  {
        Map<String, Set<String>> courses = new HashMap<>();
        for (String[] sC : input) {
            String stu = sC[0];
            String cours = sC[1];
            courses.putIfAbsent(stu, new HashSet<>());
            courses.get(stu).add(cours);
        }
        Map<String, List<String>>  res = new HashMap<>();
        for (String stu : courses.keySet()) {
            for (String mate : courses.keySet()) {
                if (stu.equals(mate)) continue;
                String key = "";
                if (stu.compareTo(mate) < 0) {
                    key = stu + ","+ mate;
                } else {
                    key = mate + "," + stu;
                }
                if (res.containsKey(key)) continue;
                List<String> commonCourse = getCommon(courses.get(stu), courses.get(mate));
                res.put(key, commonCourse);
            }
        }
        return res;
    }
    // getCommon: time: O(max over {m, n}) space: O(n)
// m == set1.size n ==set2.size()
    private static List<String> getCommon(Set<String> set1, Set<String> set2) {
        List<String> res = new ArrayList<>();
        for (String s : set1) {
            if (set2.contains(s)) {
                res.add(s);
            }
        }
        return res;
    }

}