package oracle.medium;

import sun.jvm.hotspot.opto.CallJavaNode;

import java.util.ArrayDeque;
import java.util.Deque;

public class calculator {
/*
Implement a basic calculator to evaluate a simple expression string.
The expression string contains only non-negative integers, +, -, *, / operators and empty spaces .
The integer division should truncate toward zero.

Clarification:
    1. is there any no unary operantor? (-1)
    2. is there any empty space in the string?
    3. non-negative
    4. expression is valid?
4 - 5 * 6 / 7
    operand 4  -5
    if (* or /) {
        int first = pop from operand
        int sencond
        int res
        push to operand
    } else if (-) {
        offer -num
    } else {
        offer num
    }
 */
    private static int i;
    private static int num;
    public static int calculate(String s) {
        s = s.trim();
        Deque<Integer> operand = new ArrayDeque<>();
        char[] arr = s.toCharArray();
        int sign = 1;
        i = 0;
        for (;i < arr.length; i++) {
            if (Character.isDigit(arr[i])) {
                int index = getNum(arr, i);
                i = index - 1;
                operand.push(sign * num);
                sign = 1;
            } else if (arr[i] == '*' || arr[i] == '/') {
                char op = arr[i];
                int first = operand.pop();
                ++i;
                int index = getNum(arr, i);
                i = index - 1;
                int res = op== '*' ? first * num : num != 0 ? first / num : 0;
                operand.push(res);
            } else if (arr[i] == '-' || arr[i] == '+') {
                sign = arr[i] == '-' ? -1 : 1;
            }
            num = 0;
        }
        int res = 0;
        while (!operand.isEmpty()) {
            res += operand.pop();
        }
        return res;
    }

    private static int getNum(char[] arr, int index) {
        while (index < arr.length && Character.isDigit(arr[index])) {
            num = num * 10 + arr[index] - '0';
            index++;
        }
        return index;
    }

    public static void main(String[] args) {
        System.out.println(calculate(" 3/2 "));
    }
}
