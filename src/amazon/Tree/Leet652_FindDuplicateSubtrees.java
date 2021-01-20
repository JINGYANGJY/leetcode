package amazon.Tree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
https://leetcode.com/problems/find-duplicate-subtrees/
 */
public class Leet652_FindDuplicateSubtrees {
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
    class ReturnValue {
        String serialize;
        TreeNode node;
        public ReturnValue(String serialize, TreeNode node) {
            this.serialize = serialize;
            this.node = node;
        }
    }
    public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
        Map<String, List<TreeNode>> duplicates = new HashMap<>();
        dfs(root, duplicates);
        List<TreeNode> res = new ArrayList<>();
        for (String s : duplicates.keySet()) {
            if (duplicates.get(s).size() >= 2) {
                res.add(duplicates.get(s).get(0));
            }
        }
        return res;
    }

    private ReturnValue dfs(TreeNode root, Map<String, List<TreeNode>> duplicates) {
        if (root == null) {
            return new ReturnValue("null", root);
        }
        ReturnValue left = dfs(root.left, duplicates);
        ReturnValue right = dfs(root.right, duplicates);
        if (!left.serialize.equals("null")) {
            duplicates.putIfAbsent(left.serialize, new ArrayList<>());
            duplicates.get(left.serialize).add(left.node);
        }
        if (!right.serialize.equals("null")) {
            duplicates.putIfAbsent(right.serialize, new ArrayList<>());
            duplicates.get(right.serialize).add(right.node);
        }
        StringBuilder str = new StringBuilder();
        str.append(left.serialize);
        str.append(",");
        str.append(right.serialize);
        str.append(",");
        str.append(String.valueOf(root.val));
        return new ReturnValue(str.toString(), root);
    }
}
