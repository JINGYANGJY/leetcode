package amazon.DFS;

import java.util.*;

public class Leet863_AllNodesDistanceKinBinaryTree {
     public class TreeNode {
          int val;
          TreeNode left;
          TreeNode right;
          TreeNode(int x) { val = x; }
      }
      /*
      M1: recursive
      M2: graph + BFS
       */
    public List<Integer> distanceK(TreeNode root, TreeNode target, int K) {
        List<Integer> res = new ArrayList<>();
        dfs(root, target, res, K);
        return res;
    }

    private int dfs(TreeNode root, TreeNode target, List<Integer> res, int K) {
        if (root == null) {
            return -1;//represents no target
        }
        if (root == target) {
            collect(root, res, K);
            return 1;// represent the distance to the target
        }
        int left = dfs(root.left, target, res, K);
        int right = dfs(root.right, target, res, K);
        if (left > 0) {
            if (left == K) {
                res.add(root.val);
            }
            collect(root.right, res, K - left - 1);
            return left + 1;
        }
        if (right > 0) {
            if (right == K) {
                res.add(root.val);
            }
            collect(root.left, res, K - right - 1);
            return right + 1;
        }
        return -1;
    }

    private void collect(TreeNode root, List<Integer> res, int dis) {
        if (root == null) {
            return;
        }
        if (dis == 0) {
            res.add(root.val);
            return;
        }
        collect(root.left, res, dis - 1);
        collect(root.right, res, dis - 1);
    }

    /*
     M2: parent node + BFS
     */
    public List<Integer> distanceK2(TreeNode root, TreeNode target, int K) {
        /*
        -> from target to find all the node whose distance is K
        -> construct the graph from the tree -> O(n)
        -> do BFS find all the nodes whose distance are K
        */
        Map<TreeNode, TreeNode> parent = new HashMap<>();
        constructGraph(root, parent);
        List<Integer> res = new ArrayList<>();
        bfs(target, parent, res, K);
        return res;
    }

    private void bfs(TreeNode target, Map<TreeNode, TreeNode> parent, List<Integer> res, int K) {
        Set<TreeNode> visited = new HashSet<>();
        Deque<TreeNode> queue = new ArrayDeque<>();
        queue.offer(target);
        visited.add(target);
        while (!queue.isEmpty() && K >= 0) {
            int size = queue.size();
            if (K == 0) {
                break;
            }
            for (int i = 0; i < size; i++) {
                TreeNode cur = queue.poll();
                if (cur.left != null && visited.add(cur.left)) {
                    queue.offer(cur.left);
                }
                if (cur.right != null && visited.add(cur.right)) {
                    queue.offer(cur.right);
                }
                if (parent.get(cur) != null && visited.add(parent.get(cur))) {
                    queue.offer(parent.get(cur));
                }
            }
            K--;
        }
        while (!queue.isEmpty()) {
            res.add(queue.poll().val);
        }
    }

    private TreeNode constructGraph(TreeNode root, Map<TreeNode, TreeNode> parent) {
        if (root == null) {
            return root;
        }
        TreeNode left = constructGraph(root.left, parent);
        TreeNode right = constructGraph(root.right, parent);
        if (left != null) {
            parent.putIfAbsent(left, root);
        }
        if (right != null) {
            parent.putIfAbsent(right, root);
        }
        return root;
    }
}
