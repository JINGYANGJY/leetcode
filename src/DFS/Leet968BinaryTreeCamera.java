package DFS;

import DataStructure.TreeNode;

public class Leet968BinaryTreeCamera {
    private int total;
    public int minCameraCover(TreeNode root) {
        /*
            Binary
                recursion

                    post
                for each node
                    place
                    no plcae
        return value is the state of the node
             state = 1: left no || right no   must place
             state = 2: no camera but can be monitored state 2 include null
             state = 0: can not monitored  state = 0 left == 2 && right == 2

                0           state == 2
                /
               0           left == 2 right == 2  state = 1
              / \
              0  0          state = 0

        */
        total = 0;
        if (helper(root) == 0) {
            total += 1;
        }
        return total;

    }

    private int helper(TreeNode root) {
        if (root == null) {
            return 2;
        }
        int left = helper(root.left);
        int right = helper(root.right);
        if (left == 0 || right == 0) {
            total += 1;
            return 1;
        }
        if (left == 2 && right == 2) {
            return 0;
        } else {
            return 2;
        }
    }
}
