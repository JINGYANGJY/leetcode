package amazon.Tree;

import java.util.ArrayDeque;
import java.util.Deque;

public class Leet1597_BuildBinaryExpressionTreeFromInfixExpression {
    
     class Node {
          char val;
          Node left;
          Node right;
          Node() {this.val = ' ';}
          Node(char val) { this.val = val; }
          Node(char val, Node left, Node right) {
              this.val = val;
              this.left = left;
              this.right = right;
          }
      }
    class OperatorWithLevel {
        char operator;
        int priority;
        public OperatorWithLevel(char operator, int priority) {
            this.operator = operator;
            this.priority = priority;
        }
    }
    public Node expTree(String s) {
        /*
        '(' priority + 1
        "* /" priority + 2
         '+ -' no change
         Deque<Node> operand
         Deque<OperatorWithPriority>
        */
        Deque<OperatorWithLevel> operators = new ArrayDeque<>();
        Deque<Node> operands = new ArrayDeque<>();
        char[] arr = s.toCharArray();
        int priority = 0;
        for (int i = 0; i < arr.length; i++) {
            if (Character.isDigit(arr[i])) {
                operands.push(new Node(arr[i]));
            } else if (arr[i] == '(' || arr[i] == ')') {
                if (arr[i] == '(') {
                    priority++;
                } else {
                    priority--;
                }
            } else {
                while (!operators.isEmpty() && operators.peek().priority >= priority) {
                    calculator(operands, operators);
                }
                if (arr[i] == '*'|| arr[i] == '/') {
                    operators.push(new OperatorWithLevel(arr[i], priority + 2));
                } else {
                    operators.push(new OperatorWithLevel(arr[i], priority));
                }
            }
        }
        while (!operators.isEmpty()) {
            calculator(operands, operators);
        }
        return operands.peek();
    }

    private void calculator(Deque<Node> operands, Deque<OperatorWithLevel> operators) {
        char c = operators.pop().operator;
        Node rightOperand = operands.isEmpty() ? null : operands.pop();
        Node leftOperand = operands.isEmpty() ? null : operands.pop();
        Node root = new Node(c);
        root.left = leftOperand;
        root.right = rightOperand;
        operands.push(root);
    }
}
