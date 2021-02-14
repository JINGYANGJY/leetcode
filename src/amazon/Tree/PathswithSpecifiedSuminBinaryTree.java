package amazon.Tree;

import java.util.ArrayList;
import java.util.List;

public class PathswithSpecifiedSuminBinaryTree {
    /*
    Question: All nodes along children pointers from root to leaf nodes form a path in a binary tree.
    Given a binary tree and a number, please print out all of paths where the sum of all nodes value is same as the given number
     */

    /*
    input: root, number
    output: all paths

    from root to leaf
                         2
                      /     \
                     5       8
                    / \
                   3   3
              [2, 5, 3] [2, 5, 3]
              [2, 8]
           DFS
             Time: O(n)
             Space: O(n)
     */
    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        public TreeNode(int val) {
            this.val = val;
        }
    }
    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        List<List<Integer>> res = new ArrayList<>();
        dfs(root, targetSum, new ArrayList<Integer>(), res);
        return res;
    }
   // !!!!记得去掉当前路径多余的东西
    private void dfs(TreeNode cur, int target, List<Integer> curPath, List<List<Integer>> res) {
        if (cur != null && cur.left == null && cur.right == null && target == cur.val) {
            curPath.add(cur.val); // !!!!
            res.add(new ArrayList<>(curPath));
            curPath.remove(curPath.size() - 1); // !!!
            return;
        }
        if (cur == null) {
            return;
        }
        curPath.add(cur.val);
        dfs(cur.left, target - cur.val, curPath, res);
        dfs(cur.right, target - cur.val, curPath, res);
        curPath.remove(curPath.size() - 1); // !!!!!
    }
}
