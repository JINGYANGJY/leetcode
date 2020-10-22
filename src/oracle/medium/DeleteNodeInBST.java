package oracle.medium;
/*
Given a root node reference of a BST and a key, delete the node with the given key in the BST. Return the root node reference (possibly updated) of the BST.

Basically, the deletion can be divided into two stages:

Search for a node to remove.
If the node is found, delete the node.
Follow up: Can you solve it with time complexity O(height of tree)?
 */

import DataStructure.TreeNode;

public class DeleteNodeInBST {
    /*
    clarification:
        TreeNodes are unique or not?
        if not exists TreeNode whose value equals to key?
            return root
        else
          step1: find the TreeNode whose value is key
          step2:
          if node has two children
                            5
                           /  \
                          3    6
                         / \   /
                        2  4   5
              left represents the left subtree's root
              right represents the right subtree's root
              if root's value is key
                    find leftmost of right tree
                    leftmost.left = left
                    return right
              else
                    parent.right == key
                    parent.right = right
                    leftmost.left = left
                    return root
          if node has no children





     */
    public TreeNode deleteNode(TreeNode root, int key) {
        if (root == null) {
            return root;
        }
        TreeNode parent = root;
        TreeNode cur = root;
        while (cur != null && cur.val != key) {
            parent = cur;
            if (key < cur.val) {
                cur = cur.left;
            } else if (key > cur.val) {
                cur = cur.right;
            }
        }
        if (cur == null) {
            return root;
        }
        if (cur.left == null && cur.right == null) {
            if (parent == cur) {
                return null;
            } else if (parent.left == cur) {
                parent.left = null;
            } else {
                parent.right = null;
            }
        } else if (cur.left != null && cur.right != null) {
            TreeNode right = cur.right;
            TreeNode left = cur.left;
            TreeNode leftmost = null;
            TreeNode newRoot = cur.right;
            while (newRoot != null) {
                leftmost = newRoot;
                newRoot = newRoot.left;
            }
            if (parent == cur) {
                leftmost.left = left;
                return right;
            } else {
                if (parent.left == cur) {
                    parent.left = right;
                    leftmost.left = left;
                } else {
                    parent.right = right;
                    leftmost.left = left;
                }
            }
        } else if (cur.left == null) {
            if (parent == cur) {
                return parent.right;
            } else if (parent.left == cur) {
                parent.left = cur.right;
            } else {
                parent.right = cur.right;
            }

        } else {
            if (parent == cur) {
                return parent.left;
            } else if (parent.left == cur) {
                parent.left = cur.left;
            } else {
                parent.right = cur.left;
            }
        }
        return root;
    }
}

//class Solution {
//    public TreeNode deleteNode(TreeNode root, int key) {
//        if (root == null) {
//            return null;
//        }
//        if (root.val == key) {
//            if (root.left == null) {
//                return root.right;
//            } else if (root.right == null) {
//                return root.left;
//            } else if (root.right.left == null) {
//                root.right.left = root.left;
//                return root.right;
//            } else {
//                TreeNode largestSmaller = getLargestSmaller(root.right);
//                largestSmaller.left = root.left;
//                largestSmaller.right = root.right;
//                return largestSmaller;
//            }
//        }
//        if (root.val > key) {
//            root.left = deleteNode(root.left, key);
//        } else {
//            root.right = deleteNode(root.right, key);
//        }
//        return root;
//    }
//    private TreeNode getLargestSmaller(TreeNode root) {
//        while (root.left.left != null) {
//            root = root.left;
//        }
//        TreeNode result = root.left;
//        root.left = root.left.right;
//        return result;
//
//    }
//}

