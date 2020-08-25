package amazon.oa;

public class numOfAssociates {
    /**
     *
     */

    public static void main(String[] args) {
        int[] skills = {12, 4, 6, 13, 5, 9,10};
        System.out.println(numOfAssociates(6, skills, 3, 4, 10));

    }

    public static int numOfAssociates (int num, int[] skills, int minAssociates, int minLevel, int maxLevel) {
        int count = 0;
        int res = 0;
        for (int skill : skills) {
            if (skill >= minLevel && skill <= maxLevel) {
                count++;
            }
        }
        for (int i = minAssociates; i <= count; i++) {
            res += countAssociates(count, i);
        }
        return res;
    }

    private static int countAssociates(int count, int m) {
        int res = 1;
        for (int i = count; i > m; i--) {
            res *= i;
        }
        for (int i = 1; i <= count - m; i++) {
            res /= i;
        }
        return res;
    }
}
