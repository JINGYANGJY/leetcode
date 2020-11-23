package interview.medium;

import DataStructure.TreeNode;

import java.util.HashMap;
import java.util.Map;

public class pathSum3 {
    /*
    You are given a binary tree in which each node contains an integer value.
    Find the number of paths that sum to a given value.
    The path does not need to start or end at the root or a leaf,
    but it must go downwards (traveling only from parent nodes to child nodes).
    The tree has no more than 1,000 nodes and the values are in the range -1,000,000 to 1,000,000.
    input: binary tree, target
    output: number of paths sum to the target

    Assume： sum of tree nodes on one path will not exceed integer range
    path: from parent to child
          no need start or end at root or a leaf
                 1
              /     \
              2     3
            / \     /
           5  7     2
                    \
                     7
          target: 7
          return 3
                   2 + 5, 7, 7
         backtracking
         record the sum on one path
            Map<Integer, Integer>  <sum, frequency>
            1 3 8 check map.containsKey(8 - target) res += 1

     */
    public static int pathSum(TreeNode root, int target) {
        Map<Integer, Integer> preMap = new HashMap<>();
        preMap.put(0, 1); /// !!!!
        int[] res = new int[1];
        dfs(root, target, 0, preMap, res);
        return res[0];
    }

    private static void dfs(TreeNode root, int target, int sum, Map<Integer, Integer> preMap, int[] res) {
        if (root == null) {
            return;
        }
        sum += root.val;
        if (preMap.containsKey(sum - target)) {
            res[0] += preMap.get(sum - target);
        }
        preMap.put(sum, preMap.getOrDefault(sum, 0) + 1);
        dfs(root.left, target, sum, preMap, res);
        dfs(root.right, target, sum, preMap, res);
        Integer count = preMap.get(sum) - 1;
        if (count == 0) {
            preMap.remove(sum);
        } else {
            preMap.put(sum, count);
        }
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(4);
        root.right = new TreeNode(8);
        root.left.left = new TreeNode(11);
        root.left.left.left = new TreeNode(7);
        root.left.left.right = new TreeNode(2);
        root.right.left = new TreeNode(13);
        root.right.right = new TreeNode(4);
        root.right.right.left = new TreeNode(5);
        root.right.right.right =new TreeNode(1);
        System.out.println(pathSum(root, 22));
    }
}
