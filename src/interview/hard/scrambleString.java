package interview.hard;

public class scrambleString {
        public static boolean isScramble(String s1, String s2) {
        /*
                s1 = abc        s2 = acb
                  a/bc      ab/c
                    |        |
                  a/b/c     a/b/c
                  /   \      /  \
                abc   acb   abc  bac
         there is possible way for s1 ==> s2   ==> return true
         BF: backtracking
        */
            return dfs(s1, s2, 0, s1.length() - 1);
        }

        private static boolean dfs(String s1, String s2, int left, int right) {
            if (s1.equals(s2)) {
                return true;
            }
            if (left >= right - 1) {
                return false;
            }
            boolean flag = false;
            for (int i = left + 1; i <= right; i++) {
                String leftPart = "";
                String rightPart = "";
                if (left > 0) leftPart = s1.substring(0, left);
                if (right  < s1.length()) rightPart = s1.substring(right + 1, s1.length());
                flag |= dfs(leftPart + s1.substring(left, i) + s1.substring(i, right + 1) + rightPart, s2, left, i);
                flag |= dfs(leftPart + s1.substring(i, right + 1) + s1.substring(left, i) + rightPart, s2, left, i);
                flag |= dfs(leftPart + s1.substring(left, i) + s1.substring(i, right + 1) + rightPart, s2, i + 1, right);
                flag |= dfs(leftPart + s1.substring(i, right + 1) + s1.substring(left, i) + rightPart, s2, i + 1, right);
            }
            return flag;
        }

        public static void main(String[] args) {
            System.out.println(isScramble("abc", "acb"));
        }
}
