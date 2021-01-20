package amazon.String;

public class Leet1071_GreatestCommonDivisorofStrings {
    /*
    别人的高度
     */
    public String gcdOfStrings(String str1, String str2) {
        if (!(str1+str2).equals(str2+str1))  return "";

        int gcdVal = gcd(str1.length() , str2.length());
        return str2.substring(0, gcdVal);
    }


    /*
    我的长篇大论
     */
    public String gcdOfStrings2(String str1, String str2) {
        String common1 = shortestDivisor(str1);
        String common2 = shortestDivisor(str2);
        if (!common1.equals(common2)) {
            return "";
        }
        int num1 = str1.length() / common1.length();
        int num2 = str2.length() / common1.length();
        if (num1 % 2 == 1 || num2 % 2 == 1) {
            return common1;
        } else {
            StringBuilder res = new StringBuilder();
            for (int i = 0; i < gcd(num1, num2); i++) {
                res.append(common1);
            }
            return res.toString();
        }
    }
    public static int gcd(int p, int q) {
        if (q == 0) return p;
        else return gcd(q, p % q);
    }

    private String shortestDivisor(String input) {
        for (int len = 1; len <= input.length() / 2; len++) {
            if (hasCommon(input, input.substring(0, len))) {
                return input.substring(0, len);
            }
        }
        return input;
    }

    private boolean hasCommon(String input, String str) {
        int len = str.length();
        if (input.length() % len != 0) return false;
        for (int i = 0; i < input.length(); i += len) {
            if (i + len <= input.length() && !str.equals(input.substring(i, i + len))) {
                return false;
            }
        }
        return true;
    }
}
