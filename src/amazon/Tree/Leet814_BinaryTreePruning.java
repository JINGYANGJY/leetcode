package amazon.Tree;

public class Leet814_BinaryTreePruning {
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
    public TreeNode pruneTree(TreeNode root) {
        /*
        recursion
           boolean dfs(root)
           represent if contains node whose value is 1
           left = dfs(root.left)
           right = dfs(root.right)

           left = false
           root.left = null
           right = false
           root.right = null

           return left || right || root.val == 1
        */

        dfs(root);
        if (root.val == 0 && root.left == null && root.right == null) {
            return null;
        }
        return root;
    }

    private boolean dfs(TreeNode root) {
        if (root == null) {
            return false;
        }
        boolean left = dfs(root.left);
        boolean right = dfs(root.right);
        if (!left) {
            root.left = null;
        }
        if (!right) {
            root.right = null;
        }
        return left || right || root.val == 1;
    }
}
