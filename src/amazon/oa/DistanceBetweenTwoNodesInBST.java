package amazon.oa;

import tree.TreeNode;

public class DistanceBetweenTwoNodesInBST {
    /*
    node1 node2
    if (node1 > node2) swap
    start from the root, to find the smaller node
            find first node if (node > node1 && node < node2)
                 start from the current node to find node1
                 node2
     */

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        public TreeNode(int val) {
            this.val = val;
        }
    }

    public static int distanceBetweenTwoNodesInBST(TreeNode root, TreeNode node1, TreeNode node2) {
        while (root != null) {
            if (root.val >= node1.val && root.val <= node2.val) {
                break;
            } else if (root.val > node2.val) {
                root = root.left;
            } else if (root.val < node1.val) {
                root = root.right;
            }
        }
        System.out.println(root.val);
        int dis1 = getDistance(root, node1);
        System.out.println(dis1);
        int dis2 = getDistance(root, node2);
        System.out.println(dis2);
        return dis1 + dis2;
    }

    private static int getDistance(TreeNode root, TreeNode node) {
        TreeNode cur = root;
        int res = 0;
        while (cur != null) {
            if (cur.val == node.val) {
                return res;
            } else if (cur.val < node.val) {
                cur = cur.right;
            } else {
                cur = cur.left;
            }
            res++;
        }
        return res;
    }
//                      5
//                     / \
//                     3   6
//                     / \   \
//                     2   4   7
//                     /         \
//                     1           8

    public static void main(String[] args) {
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(3);
        root.left.left = new TreeNode(2);
        root.left.right = new TreeNode(4);
        root.left.left.left = new TreeNode(1);
        root.right = new TreeNode(6);
        root.right.right = new TreeNode(7);
        root.right.right.right = new TreeNode(8);

        int res = distanceBetweenTwoNodesInBST(root, root.left.left.left, root.right.right.right);
        System.out.println(res);
    }
}
