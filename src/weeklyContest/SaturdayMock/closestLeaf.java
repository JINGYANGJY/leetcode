package weeklyContest.SaturdayMock;


public class closestLeaf {
    class TreeNode {
        char val;
        TreeNode left;
        TreeNode right;
    }
    /*
    Given an binary tree and a key, return the shortest distance from key to a leaf node
     */
    public int shortestDistanceToLeaf(TreeNode root, TreeNode k) {
        int[] res = new int[1];
        res[0] = Integer.MAX_VALUE;
        dfs(root, k, res);
        return res[0];
    }

    private int dfs(TreeNode root, TreeNode k, int[] res) {
        if (root.left == null && root.right == null) {
            return 1;
        }
        if (root == null) {
            return 0;
        }
        int left = dfs(root.left, k, res);
        int right = dfs(root.right, k, res);
        if (left * right > 0) {
            res[0] = Math.min(res[0], Math.min(left, right)) + 1;
        }
        if (left * right < 0) {
            res[0] = Math.min(res[0], Math.abs(left) + Math.abs(right));
        }
        if (root == k) {
            return -1;
        }
        return Math.min(left, right) + 1;
    }
}
