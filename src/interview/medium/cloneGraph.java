package interview.medium;

import java.util.*;

public class cloneGraph {
    static class Node {
        int value;
        List<Node> neighbors;
        public Node(int value) {
            this.value = value;
        }
    }
    /*
        connected graph?
        static class node {
            int value;
            List<Node> neighbors;
        }
        input: List<Node> graph
        output: List<Node> res
        Time: O(N) N # of nodes of graph
     */
    Map<Node, Node> visited;
    public List<Node> cloneGraph(List<Node> graph) {
        List<Node> res = new ArrayList<>();
        visited = new HashMap<>();
        for (Node node : graph) {
            if (!visited.containsKey(node)) {
                bfs(node);
            }
        }
        for (Node node : visited.keySet()) {
            res.add(visited.get(node));
        }
        return res;
     }

     private void bfs(Node root) {
        Deque<Node> queue = new ArrayDeque<>();
        Node newNode = new Node(root.value);
        visited.put(root, newNode);
        while (!queue.isEmpty()) {
            Node top = queue.poll();
            for (Node node : top.neighbors) {
                Node child = visited.get(node);
                if (child == null) {
                    child = new Node(node.value);
                    visited.put(node, child);
                    queue.offer(node);
                }
                visited.get(top).neighbors.add(child);
            }
        }
     }
}
