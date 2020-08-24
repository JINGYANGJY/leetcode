package amazon.oa;


import DataStructure.NaryTreeNode;

/**
 * https://leetcode.com/problems/maximum-average-subtree/
 * Given the root of a binary tree, find the maximum average value of any subtree of that tree.
 */

public class maximumAverageSubTree {
    private static double ans;
    private static NaryTreeNode ansRoot;

    public static void main(String[] args) {
        NaryTreeNode root = new NaryTreeNode(20);
        NaryTreeNode c1 = new NaryTreeNode(12);
        NaryTreeNode c2 = new NaryTreeNode(18);
        root.children.add(c1);
        root.children.add(c2);
        c1.children.add(new NaryTreeNode(11));
        c1.children.add(new NaryTreeNode(2));
        c1.children.add(new NaryTreeNode(3));
        c2.children.add(new NaryTreeNode(15));
        c1.children.add(new NaryTreeNode(8));
        System.out.println(maxAverageSubTree(root).value);

    }

    public static NaryTreeNode maxAverageSubTree(NaryTreeNode root) {
        ans = 0.0;
        ansRoot = null;
        dfs(root);
        return ansRoot;
    }

    // int[] res res[0]: numOfNodes res[1]: sum of nodes
    private static int[] dfs(NaryTreeNode root) {
        if (root == null) {
            return new int[]{0, 0};
        }
        int[] res = new int[2];
        res[0] = 1;
        res[1] = root.value;
        for (NaryTreeNode c : root.children) {
            int[] cur = dfs(c);
            res[0] += cur[0];
            res[1] += cur[1];
        }
        if (res[0] > 1) {
            double average = (double) res[1] / res[0];
            if (average > ans) {
                ans = average;
                ansRoot = root;
            }
        }
        return res;
    }
}
