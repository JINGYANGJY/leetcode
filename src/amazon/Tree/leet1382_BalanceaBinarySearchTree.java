package amazon.Tree;

import java.util.ArrayList;
import java.util.List;

public class leet1382_BalanceaBinarySearchTree {
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
    public TreeNode balanceBST(TreeNode root) {
        /*
            step 1: get the inorder tranversal nodes of the tree
            step 2: get the root of balanced binary search tree
            step 3: construct balanced BST
        */

        List<TreeNode> inorder = new ArrayList<>();
        dfs(root, inorder);
        return constructBST(inorder, 0, inorder.size() - 1);
    }

    private TreeNode constructBST(List<TreeNode> inorder, int left, int right) {
        if (left > right) {
            return null;
        }
        int mid = left + (right - left) / 2;
        TreeNode root = inorder.get(mid);
        root.left = constructBST(inorder, left, mid - 1);
        root.right = constructBST(inorder, mid + 1, right);
        return root;
    }

    private void dfs(TreeNode root, List<TreeNode> inorder) {
        if (root == null) {
            return;
        }
        dfs(root.left, inorder);
        if (root != null) {
            inorder.add(root);
        }
        dfs(root.right, inorder);
    }
}
