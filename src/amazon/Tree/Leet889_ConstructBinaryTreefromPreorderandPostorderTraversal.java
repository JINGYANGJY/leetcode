package amazon.Tree;

import java.util.HashMap;
import java.util.Map;

public class Leet889_ConstructBinaryTreefromPreorderandPostorderTraversal {
    /*
   recursion
   step 1:
       find root
   step 2:
       -> left root
       ->
           left
           right

           base case:
               if (preleft == preright)
                   return new TreeNode

       -> use leftsubtree root split the post order into left and right part

   */
    
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
    public TreeNode constructFromPrePost(int[] pre, int[] post) {
        Map<Integer, Integer> indexMap = new HashMap<>();
        for (int i = 0; i < post.length; i++) {
            indexMap.put(post[i], i);
        }
        return dfs(0, pre.length - 1, 0, post.length - 1, pre, post, indexMap);
    }

    private TreeNode dfs(int preLeft, int preRight, int postLeft, int postRight, int[] pre, int[] post, Map<Integer, Integer> indexMap) {
        if (preLeft == preRight) {
            return new TreeNode(pre[preLeft]);
        }
        if (preLeft > preRight) {
            return null;
        }
        TreeNode root = new TreeNode(pre[preLeft]);
        int index = indexMap.get(pre[preLeft + 1]);
        int leftSize = index - postLeft + 1;
        int rightSize = postRight - index - 1;
        root.left = dfs(preLeft + 1, leftSize + preLeft, postLeft, index, pre, post, indexMap);
        root.right = dfs(preRight - rightSize + 1, preRight, index + 1, postRight - 1, pre, post, indexMap);
        return root;
    }
}
