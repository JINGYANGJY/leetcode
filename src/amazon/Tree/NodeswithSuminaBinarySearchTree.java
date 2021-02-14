package amazon.Tree;

public class NodeswithSuminaBinarySearchTree {
    /*
    inorder BST -> sorted
    Two pointers
        the first start from the fistNode of the inorder
        the last start from the lastNode of the inorder
         interator
               first + last == target

               first + last < target
                    first -> first.next

               first + last > target
                    last -> last.next

                    4
                  /   \
                 3     6
                /     / \
                1    5   8
                 \
                  2
            continue go left
            first = 1
            first.next:
                stack maintain all parent nodes
                - right != null
                       go right's leftmost node
                - right == null
                        pop from stack


            continue go right
            last =  8
            last.next
                stack maintain all parent nodes
                - left != null
                        go left's rightmost node
                - right == null
                        pop from stack
     */
    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        public TreeNode(int val) {
            this.val = val;
        }
    }
//    public boolean twoSum(TreeNode root, int target) {
//
//    }
}
