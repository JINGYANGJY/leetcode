package oracle.medium;

import DataStructure.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class pathSum2 {
    /*
    Given a binary tree and a sum, find all root-to-leaf paths where each path's sum equals the given sum.
    Note: A leaf is a node with no children.
     */
    public List<List<Integer>> pathSum2(TreeNode root, int target) {
        /*
        input: binary tree, target
        output: List<List<Integer>> res
        root-to-leaf sum equals to given target
        Sol:
            backtracking from root to leaf
            check current path sum equals to target or not
            record current path
            if true add to result
            else return
                    4
                   /  \
                  3   1
                 / \   \
                3   5   5
                target = 20  4 3 3
                             4 3 5 XXXXX
                             4 1 1
            Time: O(n)
         */
        List<List<Integer>> res = new ArrayList<>();
        dfs(root, target, 0, new ArrayList<>(), res);
        return res;
    }

    private void dfs(TreeNode root, int target, int sum, List<Integer> curPath, List<List<Integer>> res) {
        if (root == null) {
            return;
        }
        sum += root.val;
        curPath.add(root.val);
        if (sum == target && root.left == null && root.right == null) {
            res.add(new ArrayList<>(curPath));
        } //!!!!! ❌ leaf node left and right == null
        dfs(root.left, target, sum, curPath, res);
        dfs(root.right, target, sum, curPath, res);
        curPath.remove(curPath.size() - 1);

    }
}
