package amazon.Tree;

public class Leet1448_CountGoodNodesinBinaryTree {
      public class TreeNode {
          int val;
          TreeNode left;
          TreeNode right;
          TreeNode() {}
          TreeNode(int val) { this.val = val; }
          TreeNode(int val, TreeNode left, TreeNode right) {
              this.val = val;
              this.left = left;
              this.right = right;
          }
      }
    public int goodNodes(TreeNode root) {
        int[] res = new int[1];
        dfs(root, root.val, res);
        return res[0];
    }

    private void dfs(TreeNode root, int curMax, int[] res) {
        if (root == null) {
            return;
        }
        curMax = Math.max(curMax, root.val);
        if (curMax <= root.val) {
            res[0] += 1;
        }
        dfs(root.left, curMax, res);
        dfs(root.right, curMax, res);
    }
}
