package weeklyContest.SaturdayMock;

public class Leet742_ClosestLeafinaBinaryTree {
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
    class ReturnType {
        TreeNode leafNode;
        int distance; // pos same tree negtive not same subtree
        public ReturnType(TreeNode leafNode, int distance) {
            this.leafNode = leafNode;
            this.distance = distance;
        }
    }
    public int findClosestLeaf(TreeNode root, int k) {
        /*

             1
            / \
           2   3
          /
         4
        /
       5
      /
     6


        k = 2
        -> return 3 distance = 2
        -> recursion
            same substree
                left
                right
                choose nearest one from left and right
                -> update result

            not same subtree
                -> subtree root
                    update result
       -> differ same or not same subtree
          -> return leaf node
          ReturnType
            TreeNode
            boolean
        */
        int[] res = new int[1];
        int[] shortest = new int[1];
        shortest[0] = Integer.MAX_VALUE;
        dfs(root, k, res, shortest);
        return res[0];
    }

    private ReturnType dfs(TreeNode root, int k, int[] res, int[] shortest) {
        if (root == null) {
            return null;
        }
        ReturnType left = dfs(root.left, k, res, shortest);
        ReturnType right = dfs(root.right, k, res, shortest);
        if (left == null && right == null) {
            if (root.val == k) {
                shortest[0] = 0;
                res[0] = root.val;
            }
            return new ReturnType(root, 1);
        }
        if (left != null && right != null) {
            if (left.distance > 0 && right.distance > 0) {
                if (root.val == k) {
                    int minDis = Math.min(left.distance, right.distance);
                    if (minDis < shortest[0]) {
                        shortest[0] = minDis;
                        res[0] = minDis == left.distance ? left.leafNode.val : right.leafNode.val;
                    }
                    return new ReturnType(null, -1);
                } else {
                    return left.distance < right.distance ? new ReturnType(left.leafNode, left.distance + 1) :
                            new ReturnType(right.leafNode, right.distance + 1);
                }
            }
            if (left.distance * right.distance < 0) {
                int minDis = Math.abs(left.distance) + Math.abs(right.distance);
                if (minDis < shortest[0]) {
                    shortest[0] = minDis;
                    res[0] = left.distance < 0 ? right.leafNode.val : left.leafNode.val;
                }
                return left.distance < 0 ? new ReturnType(left.leafNode, left.distance - 1) :
                        new ReturnType(right.leafNode, right.distance - 1);
            }
        }
        if (left == null) {
            if (root.val == k) {
                if (shortest[0] > right.distance) {
                    shortest[0] = right.distance;
                    res[0] = right.leafNode.val;
                }
                return new ReturnType(null, -1);
            } else {
                return right.distance < 0 ? new ReturnType(right.leafNode, right.distance - 1) :
                        new ReturnType(right.leafNode, right.distance + 1);
            }

        } else {
            if (root.val == k) {
                if (shortest[0] > left.distance) {
                    shortest[0] = left.distance;
                    res[0] = left.leafNode.val;
                }
                return new ReturnType(null, -1);
            } else {
                return left.distance < 0 ? new ReturnType(left.leafNode, left.distance - 1) :
                        new ReturnType(left.leafNode, left.distance + 1);
            }

        }
    }
}
