package intuit.calculator;
//输入string 只有加减和数字，如"1+2-3"。 用一个整数存数字前面的符号。
public class Calculator1 {
    public static int calculator(String expression) {
        /*
        order: from left to right
        two operands
        one operator
         */
        int res = 0;
        char[] arr = expression.toCharArray();
        Integer operand = null;
        char operator = '+';
        int[] index = new int[1];
        for (int i = 0; i < arr.length; i++) {
            index[0] = i;
            if (!Character.isDigit(arr[i])) {
                operator = arr[i];
            } else {
                operand = getOperand(arr, index);
                i = index[0] - 1;
                if (operator == '+') {
                    res = res + operand;
                } else if (operator == '-') {
                    res = res - operand;
                }
            }
        }
        return res;
    }
    private static int getOperand(char[] arr, int[] index) {
        int res = 0;
        while (index[0] < arr.length) {
            if (Character.isDigit(arr[index[0]])) {
                res *= 10;
                res += arr[index[0]] - '0';
                index[0]++;
            } else {
                break;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(calculator("2+3-999"));
    }
}
