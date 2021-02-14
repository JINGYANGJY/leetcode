package amazon.Tree;

import java.util.ArrayDeque;
import java.util.Deque;

public class BinarySearchTreeandDouble_linkedListlinkedList {
    /*
    Question: Convert a binary search tree to a sorted double-linked list.
    We can only change the target of pointers, but cannot create any new nodes.
    For example, if we input a binary search tree as shown on the left side of the Figure 1,
    the output double-linked list is shown on the right side.
     */
    /*
    convert a binary tree to sorted doubly-linked list
            1
          /   \
         3     4

         3 ->  1  ->  4
           <-     <-

         after convert:
            inorder sequence
         iterate traversal
            FirstNode
            nextNode


            firstNode 3  1
            nextNode  1  4
             3 -> 1
               <-
         Time: O(n)
         Space: O(n) -- talk more details later

         recursion
            inorder
            left middle right
            for each node currently traversing
                settle up pointers
                left node
                right node
         Time: O(n)
         Space: O(Height)
     */

     static class TreeNode {
         int val;
         TreeNode left; // prev
         TreeNode right; // next
         public TreeNode(int val) {
             this.val = val;
         }
     }


     public static TreeNode convertToDoublyLinkedList(TreeNode root) {
         if (root == null) {
             return root;
         }
         Deque<TreeNode> stack = new ArrayDeque<>();
         TreeNode firstNode = firstNode(root, stack);
         TreeNode dummyHead = new TreeNode(-1);
         dummyHead.left = firstNode;
         TreeNode tail = root;
         while (firstNode != null) {
             TreeNode nextNode = nextNode(firstNode, stack);
             firstNode.right = null;
             firstNode.right = nextNode;
             if (nextNode != null) {
                 nextNode.left = null;
                 nextNode.left = firstNode;
             }
             tail = firstNode;
             firstNode = nextNode;
         }
         TreeNode head = dummyHead.left;
         if (head == null || tail == null) {
             return null;
         }
         tail.right = head;
         head.left = tail;
         return head;
     }

     private static TreeNode firstNode(TreeNode cur, Deque<TreeNode> stack) {
         while (cur != null) {
             stack.push(cur);
             cur = cur.left;
         }
         return stack.isEmpty() ? null : stack.pop();
     }

     private static TreeNode nextNode(TreeNode cur, Deque<TreeNode> stack) {
         if (cur != null && cur.right != null) {
             return firstNode(cur.right, stack);
         }
         return stack.isEmpty() ? null : stack.pop();
     }

     public static void main(String[] args) {
         TreeNode root = new TreeNode(4);
         root.left = new TreeNode(2);
         root.left.left = new TreeNode(1);
         root.left.right = new TreeNode(3);
         root.right = new TreeNode(5);
         TreeNode res = convertToDoublyLinkedList(root);
         System.out.println(res);
     }

    static TreeNode prev = null;
    static TreeNode first = null;
    public static TreeNode treeToDoublyList(TreeNode root) {
        if (root == null) return root;
        convert(root);
        prev.right = first;
        first.left = prev;
        return first;
    }

    private static void convert(TreeNode root) {
        if (root == null) {
            return;
        }
        convert(root.left);
        if (prev != null) {
            prev.right = root;
            root.left = prev;
        } else {
            first = root;
        }
        prev = root;
        convert(root.right);
    }


}
