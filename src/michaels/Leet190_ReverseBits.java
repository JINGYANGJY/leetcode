package michaels;

public class Leet190_ReverseBits {
    /*
    123
    reverse 321
    res = 0;
    res = res * 10 + n % 10
    n /= 10

    binary
    res = res * 2  + n % 2;
    n /= 2;
     */
    public static int reverse(int n) {
        int res = 0;
        System.out.println(Integer.toBinaryString(n));
        for (int i = 0; i < 32; i++) {
            res = (res << 1) | (n & 1);
            n >>= 1;
        }
        return res;
    }

    public static void main(String[] args) {
        int n = 1;
        int res = reverse(n);
        System.out.println(Integer.toBinaryString(res));
        System.out.println(Integer.toBinaryString(~res));
    }
}
