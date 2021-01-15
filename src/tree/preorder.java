package tree;

import java.util.ArrayList;
import java.util.List;

public class preorder {
    public static List<Integer> preorder(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        TreeNode cur = firstNode(root);
        while (cur != null) {
            res.add(cur.value);
            cur = nextNode(cur);
        }
        return res;
    }

    private static TreeNode firstNode(TreeNode cur) {
        return cur;
    }

    private static TreeNode nextNode(TreeNode cur) {
        if (cur == null) {
            return cur;
        }
        if (cur.left != null) {
            return firstNode(cur.left);
        }
        if (cur.right != null) {
            return firstNode(cur.right);
        }
        while (cur.parent != null && (cur == cur.parent.right || cur == cur.parent.left && cur.parent.right == null)) {
            cur = cur.parent;
        }
        return cur.parent == null ? null :cur.parent.right;
    }

    public static void main(String[] args) {
        TreeNode t1 = new TreeNode(1);
        TreeNode t2 = new TreeNode(2);
        TreeNode t3 = new TreeNode(3);
        TreeNode t4 = new TreeNode(4);
        TreeNode t5 = new TreeNode(5);
        TreeNode t6 = new TreeNode(6);
        t5.left = t1;
        t1.parent = t5;
        t5.right = t6;
        t6.parent = t5;
        t1.left = t2;
        t1.right = t4;
        t4.parent = t1;
        t2.parent = t1;
        t2.left = t3;
        t3.parent = t2;
        for (Integer i : preorder(t5)) {
            System.out.println(i);
        }
    }

}
