package intuit.course;

import java.util.*;

/*
给出一些课程和课程的先修课，每个课程有且只有一门先修课，并且保证学生只有一条path修完所有课程，求修到一半时的课程名称。
{{A, B}, {C, D}, {B, C}, {E, F}, {D, E}, {F, G}}
 A B C D E F G
 graph problem:
    1. construct the graph
        Vertex: each course
        Edge: its pre course
        Map<String, Integer> indegrees
    2. find start course
        indegree is 0 ==> A
        start from the vertex whose indegree == 0
            find all its children
            -- BFS   data structure: queue
                initial state: A
                        poll A from queue. add into current path
                        if A"s children"s indegree == 0, offer into queue
                 continue above steps
                 until queue is empty
    3. get the middle of the path
 */
public class Course2 {
    public static String middleClass(String[][] input) {
        Map<String, String> graph = new HashMap<>();
        Map<String, Integer> indegreeMap = new HashMap<>();
        for (String[] strings : input) {
            graph.putIfAbsent(strings[0], strings[1]);
            indegreeMap.put(strings[1], indegreeMap.getOrDefault(strings[1], 0) + 1);
        }
        String start = null;
        for (String s : graph.keySet()) {
            if (!indegreeMap.containsKey(s)) {
                start = s;
                break;
            }
        }
        String res = null;
        int len = graph.keySet().size() + 1;
        // odd 7 middle = 7 /2
        // even 6 middle = 6 / 2 - 1
        // A B C D E F G H
        int middle = len % 2 == 0 ? len / 2 - 1 : len / 2;
        while (middle > 0) {
            res = graph.get(start);
            start = res;
            middle--;
        }
        return res;
    }

    public static void main(String[] args) {
        String[][] courses = { { "A", "B" }, { "C", "D" }, { "B", "C" }, { "E", "F" }, { "D", "E" }, { "F", "G" }, {"G", "H"}};
        System.out.println(middleClass(courses));
    }


}
