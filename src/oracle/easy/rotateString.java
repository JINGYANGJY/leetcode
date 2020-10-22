package oracle.easy;

public class rotateString {
    /*
    We are given two strings, A and B.
    A shift on A consists of taking string A and moving the leftmost character to the rightmost position.
    For example, if A = 'abcde', then it will be 'bcdea' after one shift on A.
    Return True if and only if A can become B after some number of shifts on A.
    Clarification:
     A and B has the same length?

     Brute force:
        O(n^2)
        check if B startswith any substring of A
        then check the rest part
     rolling hash
        initial hash code
        for each {
            delete head add to tail
            check if equals to B's hash
        }
        "abc"
        a * 26^2 + b * 26 + c * 26^0
     */
    private static int MOD = 10 ^ 9 + 7;
    private static int p = 113;
    public static boolean rotateString(String A, String B) {
        long initialA = 0, initialB = 0;
        int M = 1;
        for (int i = 0; i < A.length(); i++) {
            M += M * 113;
        }
        int va = M;
        for (int i = 0; i < A.length(); i++) {
            initialA = (((A.charAt(i) - '0') * va) % MOD + initialA) % MOD;
            initialB = (((B.charAt(i) - '0') * va) % MOD + initialB) % MOD;
            va /= 113;
        }
        if (initialA == initialB) return true;
        va = M;
        for (int i = 0; i < A.length(); i++) {
            char c = A.charAt(i);
            initialA = (initialA -  ((c - 'a') * va) % MOD) * 113+ c - 'a';
            if (initialA == initialB) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println(rotateString("ABCD", "CDAB"));
    }
}
