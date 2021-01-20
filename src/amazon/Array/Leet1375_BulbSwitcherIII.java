package amazon.Array;

public class Leet1375_BulbSwitcherIII {
    public int numTimesAllBlue(int[] light) {
        /*
            light[index] was turned on
            1 - index + 1 should already been turned on
            then res + 1
            max
        */
        int right = 0, res = 0, n = light.length;
        for (int i = 0; i < n; ++i) {
            right = Math.max(right, light[i]);
            if (right == i + 1) ++res;
        }
        return res;
    }
}
