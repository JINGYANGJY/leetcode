package amazon.Tree;

public class BinarySearchTreeVertification {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        public TreeNode(int val) {
            this.val = val;
        }
    }
    public boolean BinarySearchTreeVertification (TreeNode root) {
        /*
        BST:
            root > the largest node of left subtree
            root < the smallest node of its right subtree
            recursion
                min, max
                node > min && node < max

                        3
                      /   \
                     2     5
                    /      /
                   1      4
               2: < parent.val
               1: < parent.val
               5 > parent.val
               4 < parent.val
               dfs(root.left, root.val, min)
               dfs(root.right, min, root.val)
                Integer.MIN < root < Integer.max
              Time:O(n)
              Space: O(H)
         */
         boolean[] res = new boolean[1];
         dfs(root, res, Integer.MIN_VALUE, Integer.MAX_VALUE);
         return res[0];
    }

    private void dfs(TreeNode root, boolean[] res, int min, int max) {
        if (root == null) {
            return;
        }
        if (root.val < min || root.val < max) {
            return;
        }
        dfs(root.left, res, min, root.val);
        dfs(root.right, res, root.val, max);
    }
}
