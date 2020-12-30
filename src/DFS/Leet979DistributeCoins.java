package DFS;

public class Leet979DistributeCoins {
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    static class ReturnType {
        int coins;
        int steps;

        public ReturnType(int coins, int steps) {
            this.coins = coins;
            this.steps = steps;
        }
    }

    public int distributeCoins(TreeNode root) {
        return dfs(root).steps;
    }

    private ReturnType dfs(TreeNode root) {
        if (root == null) {
            return new ReturnType(0, 0);
        }

        ReturnType left = dfs(root.left);
        ReturnType right = dfs(root.right);
        int coins = left.coins + right.coins + root.val - 1;
        int steps = left.steps + right.steps + Math.abs(coins);
        return new ReturnType(coins, steps);
    }
}
