package amazon.DFS;

import java.util.ArrayList;
import java.util.List;

public class Leet210_CourseScheduleII {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        /*
        directed graph problem
        - vertex: all the courses labelled from 0 to n - 1
        - edge: prerequisites[i] = [ai, bi]
                bi -> ai
        -> valid topological sort
            if impossible, return empty
            -> there is a circle between the course
            otherwise return result
            -> BFS:
                start from the indegree == 0 nodes
                -> neis if indgree == 0 next nodes which can be expand
                until datastructure queue is empty
                -> courses != numCourses return empty
                -> return all courses
            -> DFS:
                find the nodes who doesn't have dependencies, add into result
                if there is a circle in the connected graph -> return empty
                construct directed graph
                e.g.
                prerequisites[i] = [ai, bi]
                ai -> bi
        */
        List<List<Integer>> graph = constructGraph(numCourses, prerequisites);
        List<Integer> res = new ArrayList<>();
        int[] visited = new int[numCourses];// == 1 visiting == 2 visited
        for (int i = 0; i < numCourses; i++) {
            if (visited[i] == 0) {
                if (hasCycle(i, graph, visited, res)) {
                    return new int[0];
                }
            }
        }
        int[] ans = new int[res.size()];
        for (int i = 0; i < res.size(); i++) {
            ans[i] = res.get(i);
        }
        return ans;
    }

    private List<List<Integer>> constructGraph(int numCourses, int[][] prerequisites) {
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < numCourses; i++) {
            graph.add(new ArrayList<>());
        }
        for (int[] pre : prerequisites) {
            graph.get(pre[0]).add(pre[1]);
        }
        return graph;
    }

    private boolean hasCycle(int cur, List<List<Integer>> graph, int[] visited, List<Integer> res) {
        if (visited[cur] == 2) {
            return false;
        }
        if (visited[cur] == 1) {
            return true;
        }
        visited[cur] = 1;
        for (Integer nei : graph.get(cur)) {
            if (hasCycle(nei, graph, visited, res)) {
                return true;
            }
        }
        visited[cur] = 2;
        res.add(cur);
        return false;
    }
}
