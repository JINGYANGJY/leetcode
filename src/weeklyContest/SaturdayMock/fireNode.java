package weeklyContest.SaturdayMock;

import DataStructure.TreeNode;

public class fireNode {
    /*
    Given a binary tree, and a random node in which is on fire, it will burn a level of nodes in one hour,
    then please return the total number of hours to burn down the whole tree
     */

    /*
                            10 (1)
                           /   \
                (2)      5      20 fire node
                       /   \     \
                (3)   4    7      30 (1)

         Actually, we want to know  the farthest distance node from the fireNode
         if (root == fireNode) {
            return -1;
         }
          left
          right
          for current node
            use - + represents direction: fire up or fire down
                if (+-)
                    |left| + |right| + 1
                    return neg - 1
                if (++)
                    find most
                    return most
     */
    public static int numOfDays(TreeNode root, TreeNode fireNode) {
        int[] res = new int[1];
        dfs(root, fireNode, res);
        return res[0];
    }
    public static int dfs(TreeNode root, TreeNode fireNode, int[] res) {
        if (root == null) {
            return 0;
        }
        if (root == fireNode) {
            return 1;
        }
        int ans = 0;
        int left = dfs(root.left, fireNode, res);
        int right = dfs(root.right, fireNode, res);
        if (left * right < 0) {
            res[0] = Math.max(res[0], Math.abs(left) + Math.abs(right) + 1);
            ans = left < 0 ? left - 1 : right - 1;
        } else {
            res[0] = Math.max(left, right) + 1;
            ans = Math.max(left, right + 1);
        }
        return ans;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(10);
        root.left = new TreeNode(5);
        root.right = new TreeNode(20);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(7);
        root.right.right = new TreeNode(30);
        System.out.println(numOfDays(root, root.right));
    }
}


