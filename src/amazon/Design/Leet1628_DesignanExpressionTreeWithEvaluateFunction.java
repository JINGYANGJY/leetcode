package amazon.Design;

import java.util.ArrayDeque;
import java.util.Deque;

public class Leet1628_DesignanExpressionTreeWithEvaluateFunction {
    abstract class Node {
        public abstract int evaluate();
        // define your fields here
    };
    class TreeNode extends Node{
        TreeNode left;
        TreeNode right;
        String val;
        public TreeNode(String val) {
            this.val = val;
        }
        public int evaluate() {
            return dfs(this);
        }
        private int dfs(TreeNode root) {
            if (root == null) {
                return 0;
            }
            if (root.left == null && root.right == null) {
                return Integer.valueOf(root.val);
            }
            int left = dfs(root.left);
            int right = dfs(root.right);
            String operator = root.val;
            if (operator.equals("+")) {
                return left + right;
            } else if (operator.equals("-")) {
                return left - right;
            } else if (operator.equals("*")) {
                return left * right;
            } else {
                return left / right;
            }
        }
    };



    /**
     * This is the TreeBuilder class.
     * You can treat it as the driver code that takes the postinfix input
     * and returns the expression tree represnting it as a Node.
     */

    class TreeBuilder {
    /*
    construct the tree using post order
    ["3","4","+","2","*","7","/"]
    stack
        if (operand)
         poll two operands from the statck

    - operator
        current subtree's root
    - operand
        node

    */

        Node buildTree(String[] postfix) {
            Deque<TreeNode> stack = new ArrayDeque<>();
            for (int i = 0; i < postfix.length; i++) {
                if (postfix[i].equals("+") || postfix[i].equals("-") || postfix[i].equals("*") || postfix[i].equals("/")) {
                    TreeNode rightOperand = stack.isEmpty() ? null : stack.pop();
                    TreeNode leftOperand = stack.isEmpty() ? null : stack.pop();
                    TreeNode root = new TreeNode(postfix[i]);
                    root.left = leftOperand;
                    root.right = rightOperand;
                    stack.push(root);
                } else {
                    stack.push(new TreeNode(postfix[i]));
                }
            }
            return stack.peek();
        }
    };

}
