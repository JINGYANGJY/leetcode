package intuit.task;

import java.util.*;

public class Q1 {
    /*
    算task和pretask輸出task by level的那題.
是像這樣
input = {
{"cook", "eat"},   // do "cook" before "eat"
{"study", "eat"},
{"sleep", "study"}}

output (steps of a workflow):
{{"sleep", "cook"},.
{"study"},
{"eat"}}

来自：一亩三分地社区
链接：https://www.1point3acres.com/bbs/thread-304476-1-1.html
     */

    public static List<List<String>> tasks(String[][] input) {
        Map<String, List<String>> graph = new HashMap<>();
        Map<String, Integer> inDegree = new HashMap<>();
        for (String[] task : input) {
            String v = task[0];
            String w = task[1];
            graph.putIfAbsent(v, new ArrayList<>());
            graph.putIfAbsent(w, new ArrayList<>());
            graph.get(v).add(w);
            inDegree.put(w, inDegree.getOrDefault(w, 0) +  1);
        }
        Deque<String> queue = new ArrayDeque<>();
        for (String key : graph.keySet()) {
            if (!inDegree.containsKey(key)) {
                queue.offer(key); // indegree == 0
            }
        }
        List<List<String>> res = new ArrayList<>();
        while (!queue.isEmpty()) {
            int size = queue.size();
            List<String> curTasks = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                String cur = queue.poll();
                curTasks.add(cur);
                for (String nei : graph.get(cur)) {
                    Integer indgree = inDegree.get(nei);
                    if (indgree == null) {
                        continue;
                    }
                    if (indgree == 1) {
                        queue.offer(nei);
                    }
                    inDegree.put(nei, indgree - 1);
                }
            }
            res.add(curTasks);
        }
        return res;
    }

    public static void main(String[] args) {
        String[][] input = new String[][] {
                {"cook", "eat"},// do "cook" before "eat"
            {"study", "eat"},
            {"sleep", "study"}};

        List<List<String>> res = tasks(input);
        for (List<String> r : res) {
            for (String s : r) {
                System.out.print(s + " ");
            }
            System.out.println();
        }
    }
}
