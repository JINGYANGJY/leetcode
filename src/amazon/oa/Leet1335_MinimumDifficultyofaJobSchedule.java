package amazon.oa;

public class Leet1335_MinimumDifficultyofaJobSchedule {
    /*
    You want to schedule a list of jobs in d days. Jobs are dependent
    (i.e To work on the i-th job, you have to finish all the jobs j where 0 <= j < i).

    You have to finish at least one task every day.
    The difficulty of a job schedule is the sum of difficulties of each day of the d days.
    The difficulty of a day is the maximum difficulty of a job done in that day.
    Given an array of integers jobDifficulty and an integer d. The difficulty of the i-th job is jobDifficulty[i].
    Return the minimum difficulty of a job schedule. If you cannot find a schedule for the jobs return -1.
     */
    public int minDifficulty(int[] job, int d) {
        /*
        int[][] dp
        dp[i][j]: represents ends with index j job, use i days
                  the minimum difficulty of a job schedule
        dp[i][j] = min over {
            dp[i - 1][k]  + max(jobDifficulty[k] .. jobDifficulty[j])
        }
        k is in range [i, j - 1]
        dp[0][j] = max number from 0 - index j
        */
        if (d > job.length) return -1;
        int[][] dp = new int[d][job.length];
        int[][] maxMatrix = getMax(job);
        int max = job[0];
        for (int i = 0; i < job.length; i++) {
            max = Math.max(max, job[i]);
            dp[0][i] = max;
        }
        for (int i = 1; i < d; i++) {
            for (int j = i; j < job.length; j++) {
                dp[i][j] = Integer.MAX_VALUE;
                for (int k = i - 1; k < j; k++) {
                    dp[i][j] = Math.min(dp[i][j], dp[i - 1][k] + maxMatrix[k + 1][j]);
                }
            }
        }
        return dp[d - 1][job.length - 1];
    }


    private int[][] getMax(int[] job) {
        int[][] res = new int[job.length][job.length];
        for (int i = 0; i < job.length; i++) {
            int max = job[i];
            for (int j = i; j < job.length; j++) {
                max = Math.max(max, job[j]);
                res[i][j] = max;
            }
        }
        return res;
    }
}
