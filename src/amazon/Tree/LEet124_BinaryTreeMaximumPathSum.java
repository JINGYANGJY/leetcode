package amazon.Tree;

public class LEet124_BinaryTreeMaximumPathSum {
    /*
    A path in a binary tree is a sequence of nodes where each pair of
    adjacent nodes in the sequence has an edge connecting them.
     A node can only appear in the sequence at most once. Note that the path does not need to pass through the root.

    The path sum of a path is the sum of the node's values in the path.

    Given the root of a binary tree, return the maximum path sum of any path.

                        1
                      /   \
                     2    9
                    /
                   -10

             10 + 2 + 1 +  9
             1 2 9
             Clarification:
                nodes value < 0 ? yes
                path? null No

            Recursion
                dfs(root)
                ending with current node, the maximum path sum {direction is  from bottom to top} including the current node
                for each node
                    dfs(node.left)
                    dfs(right.right)
                left + root, right + root, left + right + root, root

               return left + root, right + root, root
          Time: O(n)
          Space: O(H) H is height of the binary tree
     */
    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        public TreeNode(int val) {
            this.val = val;
        }
    }
    private int dfs (TreeNode root) {
        int[] res = new int[1];
        res[0] = Integer.MIN_VALUE;
        dfs(root, res);
        return res[0];
    }
    //Assume the path sum is between[Integer.MIN_VALUE, INteger.MAX_VALUE]
    private int dfs(TreeNode root, int[] res) {
        if (root == null) {
            return 0;
        }
        int left = dfs(root.left, res);
        int right = dfs(root.right, res);
        int curMax = Math.max(root.val, Math.max(left + root.val, right + root.val));
        res[0] = Math.max(left + right + root.val, curMax);
        return Math.max(0, curMax);
    }
}
