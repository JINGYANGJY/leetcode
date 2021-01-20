package amazon.oa;

public class constructBST {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        public TreeNode(int val) {
            this.val = val;
        }
    }

    public TreeNode constructBST(int[] values) {
        int[] index = new int[1];
        return dfs(values, index, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    private TreeNode dfs(int[] values, int[] index, int min, int max) {
        if (values[index[0]] <= min || values[index[0]] >= max) {
            return null;
        }
        TreeNode root = new TreeNode(values[index[0]]);
        index[0] += 1;
        root.left = dfs(values, index, min, root.val);
        root.right = dfs(values, index, root.val, max);
        return root;
    }

//    public int minDistance(int[] values, int n, int node1, int node2) {
//            TreeNode root = constructBST(values);
//
//    }
}
