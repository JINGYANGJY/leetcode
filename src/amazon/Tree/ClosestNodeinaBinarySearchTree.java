package amazon.Tree;

public class ClosestNodeinaBinarySearchTree {
    /*
    BST
                         7
                       /  \
                      6    8
                      /     \
                     2       9
                      \
                       4
               closest node of 2
               int diff
               TreeNode res

                    root.val == target
                        res = root;
                    root.val > target
                        root = root.left
                    root.val < target
                        root = root.right

     */
    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        public TreeNode (int val) {
            this.val = val;
        }
    }
    public TreeNode closestNode(TreeNode root, int target) {
        int diff = Integer.MAX_VALUE;
        TreeNode res = null;
        TreeNode cur = root;
        while (cur != null) {
            if (Math.abs(cur.val - target) < diff) {
                diff = Math.abs(cur.val - target);
                res = cur;
            }
            if (cur.val == target) {
                return res;
            } else if (cur.val < target) {
                cur = cur.right;
            } else {
                cur = cur.left;
            }
        }
        return res;
    }


}
