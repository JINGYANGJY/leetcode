package amazon.Tree;

public class Leet572_SubtreeofAnotherTree {
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
    boolean res;
    public boolean isSubtree(TreeNode s, TreeNode t) {
        /*
        1. find a subtree root.val == t.root.val
        2. DFS check if two tree are same
        */
        res = false;
        dfs(s, t);
        return res;
    }

    private void dfs(TreeNode s, TreeNode t) {
        if (s == null) {
            return;
        }
        if (s.val == t.val) {
            res = res || sameTree(s, t);
            if (res) {
                return;
            }
        }
        dfs(s.left, t);
        dfs(s.right, t);
    }

    private boolean sameTree(TreeNode s, TreeNode t) {
        if (s == null && t == null) {
            return true;
        }
        if (s == null || t == null) {
            return false;
        }
        return s.val == t.val && sameTree(s.left, t.left) && sameTree(s.right, t.right);
    }
}
