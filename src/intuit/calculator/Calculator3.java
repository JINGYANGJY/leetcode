package intuit.calculator;
import java.util.*;
/*
第二问的基础上加上变量，给了一部分变量对应的值，化简表达式。如"(a+b)+c+d+1",
给map {"a":1,"b":2,"c":3},输出"7+d"。
用另外一个map存没有出现的变量的出现次数，其余变量or数字直接加到结果里面。
 */
//2+(1-(2+7-(5-2)))
public class Calculator3 {
    public static String calculator (String expression, Map<Character, Integer> freMap) {
       char[] arr = expression.toCharArray();
       Deque<String> operands = new ArrayDeque<>();
       int level = 0;
       Deque<Calculator2.OperatorLevel> operators = new ArrayDeque<>();
       for (int i = 0; i < arr.length; i++) {
           if (Character.isDigit(arr[i])) {
               int res = 0;
               while (i < arr.length && Character.isDigit(arr[i])) {
                   res *= 10;
                   res += arr[i] - '0';
                   i++;
               }
               i -= 1;
               operands.push(String.valueOf(res));
           } else if (Character.isLetter(arr[i])) {
               if (freMap.containsKey(arr[i])) {
                   operands.push(String.valueOf(freMap.get(arr[i])));
               } else {
                   operands.push(String.valueOf(arr[i]));
               }
           } else if (arr[i] == '(' || arr[i] == ')') {
               if (arr[i] == '(') {
                   level++;
               } else {
                   level--;
               }
           } else {
               while (!operators.isEmpty() && operators.peek().level >= level) {
                   calculator(operands, operators);
               }
               operators.push(new Calculator2.OperatorLevel(arr[i], level));
           }
       }
       while (!operators.isEmpty()) {
           calculator(operands, operators);
       }
       return operands.peek();
    }
    private static void calculator(Deque<String> operands, Deque<Calculator2.OperatorLevel> operators) {
        char operator = operators.pop().operator;
        String rightOperand = operands.isEmpty() ? null : operands.pop();
        String leftOperand = operands.isEmpty() ? null : operands.pop();
        String res = leftOperand;
        if (operator == '+') {
            res = res + "+" + rightOperand;
        } else {
            char[] arr = rightOperand.toCharArray();
            for (int i = 0; i < arr.length; i++) {
                if (Character.isDigit(arr[i])) {
                    int ans = 0;
                    while (i < arr.length && Character.isDigit(arr[i])) {
                        ans *= 10;
                        ans += arr[i] - '0';
                        i++;
                    }
                    i -= 1;
                    res += '-' +  String.valueOf(ans);
                } else if (arr[i] == '-') {
                    res += '+';
                } else if (arr[i] == '+') {
                    res += '-';
                } else {
                    res += String.valueOf(arr[i]);
                }
            }
        }
       String s = simplifyCalculate(res);
        operands.push(s);
    }

    private static String simplifyCalculate(String input) {
        StringBuilder unKnowVar = new StringBuilder();
        char[] arr = input.toCharArray();
        char oprt = '+';
        int res = 0;
        Integer oprd = 0;
        String var = null;
        for (int i = 0; i < arr.length; i++) {
            if (Character.isDigit(arr[i])) {
                int ans = 0;
                while (i < arr.length && Character.isDigit(arr[i])) {
                    ans *= 10;
                    ans += arr[i] - '0';
                    i++;
                }
                i -= 1;
                oprd = ans;
            } else if (arr[i] == '+' || arr[i] == '-') {
                oprt = arr[i];
            } else {
                var = String.valueOf(arr[i]);
            }
            if (var != null) {
                unKnowVar.append(String.valueOf(oprt));
                unKnowVar.append(var);
                var = null;
            } else if (oprd != null){
                if (oprt == '+') {
                    res += oprd;
                } else {
                    res -= oprd;
                }
                oprd = null;
            }
        }
        return res + unKnowVar.toString();
    }

    public static void main(String[] args) {
        String expression = "(a+b)+c+d";
        String e2 = "2+(1-(2+7-(5-dcd)))";
        Map<Character, Integer> freMap = new HashMap<>();
//        freMap.put('a', 1);
//        freMap.put('b', 2);
//        freMap.put('c', 3);
        Map<String, Integer> map = new HashMap<>();
//        map.put("a", 1);
//        map.put("b", 2);
//        map.put("c", 3);
        System.out.println(calculator(expression, freMap));
        System.out.println(calculator2(expression, map));
    }

    public static String calculator2(String input, Map<String, Integer> map) {
        Deque<String> operands = new ArrayDeque<>();
        Deque<Calculator2.OperatorLevel> operators = new ArrayDeque<>();
        char[] arr = input.toCharArray();
        int priority = 0;
        for (int i = 0; i < arr.length; i++) {
            if (Character.isDigit(arr[i])) {
                int operand = 0;
                while(i < arr.length && Character.isDigit(arr[i])) {
                    operand = operand * 10 + arr[i++] - '0';
                }
                i -= 1;
                operands.push(String.valueOf(operand));
            } else if (Character.isLetter(arr[i])) {
                StringBuilder operand = new StringBuilder();
                while (i < arr.length && Character.isLetter(arr[i])) {
                    operand.append(String.valueOf(arr[i++]));
                }
                i -= 1;
                String key = operand.toString();
                if (map.containsKey(key)) {
                    operands.push(String.valueOf(map.get(key)));
                } else {
                    operands.push(key);
                }
            } else if (arr[i] == '(' || arr[i] == ')') {
                if (arr[i] == '(') {
                    priority++;
                } else {
                    priority--;
                }
            } else {
                while (!operators.isEmpty() && operators.peek().level >= priority) {
                    helper(operands, operators);
                }
                operators.push(new Calculator2.OperatorLevel(arr[i], priority));
            }
        }
        while (!operators.isEmpty()) {
            helper(operands, operators);
        }
        return operands.peek();
    }
    // only + - calculate order from left to right
    private static void helper(Deque<String> operands, Deque<Calculator2.OperatorLevel> operators) {
        char operator = operators.pop().operator;
        String rightOperand = operands.isEmpty() ? "" : operands.pop();
        String leftOperand = operands.isEmpty() ? "" : operands.pop();
        int[] res = new int[1];
        StringBuilder variables = new StringBuilder();
        constructRes(res, variables, leftOperand.toCharArray(), '+');
        constructRes(res, variables, rightOperand.toCharArray(), operator);
        StringBuilder returnVal = new StringBuilder();
        if (res[0] == 0) {
            operands.push(variables.toString().substring(1));
        } else {
            operands.push(returnVal.append(String.valueOf(res[0])).append(variables).toString());
        }
    }

    private static void constructRes(int[] res, StringBuilder variables, char[] arr, char operator) {
        char op = operator;
        for (int i = 0; i < arr.length; i++) {
            if (Character.isDigit(arr[i])) {
                int oprd = 0;
                while (i < arr.length &&
                        Character.isDigit(arr[i])) {
                    oprd = oprd * 10 + arr[i++] - '0';
                }
                i -= 1;
                if (op == '+') {
                    res[0] += oprd;
                } else {
                    res[0] -= oprd;
                }
            } else if (Character.isLetter(arr[i])) {
                StringBuilder oprd = new StringBuilder();
                while (i < arr.length &&
                        Character.isLetter(arr[i])) {
                    oprd.append(String.valueOf(arr[i++]));
                }
                i -= 1;
                if (operator == '-') {
                    op = op == '-' ? '+' : '-';
                }
                if (op == '+') {
                    variables.append("+");
                    variables.append(oprd.toString());
                } else {
                    variables.append("-");
                    variables.append(oprd.toString());
                }
            } else if (arr[i] == '+' || arr[i] == '-') {
                op = arr[i];
            }
        }
    }

}
