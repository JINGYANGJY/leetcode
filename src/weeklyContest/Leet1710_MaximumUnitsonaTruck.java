package weeklyContest;

import java.util.Arrays;

public class Leet1710_MaximumUnitsonaTruck {
    // greedy method
    public int maximumUnits(int[][] boxTypes, int truckSize) {
        Arrays.sort(boxTypes, (a, b) -> b[1] - a[1]);
        int unitCount = 0;
        for (int[] boxType : boxTypes) {
            int boxCount = Math.min(truckSize, boxType[0]);
            unitCount += boxCount * boxType[1];
            truckSize -= boxCount;
            if (truckSize == 0)
                break;
        }
        return unitCount;
    }
    /*
    Memory limited method
     */
    public int maximumUnitsMemoryLimited(int[][] boxTypes, int truckSize) {
        int[] res = new int[1];
        int[][] memo = new int[boxTypes.length][truckSize + 1];
        return dfs(0, boxTypes, truckSize, memo);
    }

    private int dfs(int level, int[][] boxTypes, int truckSize, int[][] memo) {
        if (level >= boxTypes.length) {
            return 0;
        }
        if (memo[level][truckSize] > 0) {
            return memo[level][truckSize];
        }
        int res = 0;
        for (int i = 0; i <= boxTypes[level][0]; i++) {
            if (truckSize - i >= 0) {
                res = Math.max(res, i * boxTypes[level][1] + dfs(level + 1, boxTypes, truckSize - i, memo));
            }
        }
        memo[level][truckSize] = res;
        return res;
    }
}
