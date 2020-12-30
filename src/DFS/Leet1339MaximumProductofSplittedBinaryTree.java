package DFS;


import DataStructure.TreeNode;

public class Leet1339MaximumProductofSplittedBinaryTree {
    Integer sum = null;
    long MOD = (long)(Math.pow(10, 9) + 7);
    public int maxProduct(TreeNode root) {
        long[] res = new long[1];
        sum = dfs(root, res);
        res[0] = Long.MIN_VALUE;
        dfs(root, res);
        return (int)(res[0] % MOD);
    }

    private int dfs(TreeNode root, long[] res) {
        if (root == null) {
            return 0;
        }
        int left = dfs(root.left, res);
        int right = dfs(root.right, res);
        int subTree = left + right + root.val;
        if (sum != null) {
            res[0] = Math.max(res[0], (((long)sum - subTree) * subTree));
        }
        return subTree;
    }
}
