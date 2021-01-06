package DFS.Recursion;

public class Leet776_SplitBST {
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
    public TreeNode[] splitBST(TreeNode root, int V) {
        /*
          recursion
                base case:
                    if root == null
                        return  new TreeNode[]{null, null};
                        // 0: treenode val <= V
                        // 1: treenode val > V
                for each node:
                    left
                    right

                if root.val > V
                        root.left = left[1]
                    return left[0], root
                if root.val <= V
                        root.right = right[0]
                        root.left = left[0]
                        return root, right[1]
            Time: O(n)
        */

        if (root == null) {
            return new TreeNode[]{null, null};
        }
        TreeNode[] left = splitBST(root.left, V);
        TreeNode[] right = splitBST(root.right, V);
        if (root.val > V) {
            root.left = left[1];
            return new TreeNode[]{left[0], root};
        } else {
            root.right = right[0];
            return new TreeNode[]{root, right[1]};
        }
    }
}
