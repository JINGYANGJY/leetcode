package amazon.DFS;

import java.util.HashMap;
import java.util.Map;

public class Leet105_ConstructBinaryTreefromPreorderandInorderTraversal {
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
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        /*
        Assume: no duplicate node value
        recuresive
            preorder: root, left, right
            inorder: left, root, right
        -> dfs(root)
              split into two parts left and right
                    left: [start, root - 1]
                    right:[root + 1, end]
              root.left = dfs(left)
              root.right = dfs(right)
              -> efficiently split
                 -> Map<Integer, Integer> indexMap -> map value to index of inorder
                 -> split preorder
                    -> size leftSize = root - 1 - start + 1
                            rightSize = end - (root + 1) + 1
        */
        Map<Integer, Integer> indexMap = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            indexMap.put(inorder[i], i);
        }
        return dfs(0, preorder.length - 1, 0, inorder.length - 1, preorder, inorder, indexMap);
    }

    private TreeNode dfs(int preLeft, int preRight, int inLeft, int inRight, int[] preorder, int[] inorder, Map<Integer, Integer> indexMap) {
        if (preLeft == preRight) {
            return new TreeNode(preorder[preLeft]);
        }
        if (preLeft > preRight) {
            return null;
        }
        TreeNode root = new TreeNode(preorder[preLeft]);
        int index = indexMap.get(preorder[preLeft]);
        int leftSize = index - inLeft;
        int rightSize = inRight - index;
        root.left = dfs(preLeft + 1, preLeft + leftSize, inLeft, index - 1, preorder, inorder, indexMap);
        root.right = dfs(preRight - rightSize + 1, preRight, index + 1, inRight, preorder, inorder, indexMap);
        return root;
    }
}
