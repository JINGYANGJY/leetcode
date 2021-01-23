package amazon.String;

public class Leet8_StringtoIntegeratoi {
    public int myAtoi(String s) {
        /*
        edge cases:
            " + 234" -> 0
            "--0" -> 0
            "00000-42a1234" -> 0
            "-1000000000000000" -> Integer.MIN_VALUE
         */
        char[] arr = s.toCharArray();
        Integer sign = null;
        double count = 0;
        int numDigits = 0;
        for (char c : arr) {
            if (sign == null && numDigits == 0 && c == ' ') {
                continue;
            } else if (numDigits == 0  && sign == null && (c == '-' || c == '+') ) {
                sign = c == '-' ? -1 : 1;
            } else if (Character.isDigit(c)) {
                if (count > Integer.MAX_VALUE) {
                    return sign != null ? Integer.MIN_VALUE : Integer.MAX_VALUE;
                }
                numDigits++;
                count = count * 10 + c - '0';
            } else {
                break;
            }
        }
        return sign != null ? (int)( sign * count) : (int) count;
    }
}
