package amazon.TwoPointers.normal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Leet826_MostProfitAssigningWork {
    class Work {
        int difficulty;
        int maxProfit;
        int profit;
        public Work(int difficulty, int profit) {
            this.difficulty = difficulty;
            this.profit = profit;
        }
    }
    public int maxProfitAssignment(int[] difficulty, int[] profit, int[] worker) {
        /*
        for each worker
            find the max profit work from works which difficulty[j] < worker[i]
            ->
                sort by difficulty
                1. find boundary of the work
                2. find max profit from the range
        */
        List<Work> works = new ArrayList<>();
        for (int i = 0; i < difficulty.length; i++) {
            works.add(new Work(difficulty[i], profit[i]));
        }
        Collections.sort(works, (a, b) -> {
            int res = a.difficulty - b.difficulty;
            return res;
        });
        int maxProfit = 0;
        for (int i = 0; i < works.size(); i++) {
            maxProfit = Math.max(maxProfit, works.get(i).profit);
            works.get(i).maxProfit = maxProfit;
        }
        int res = 0;
        for (int i = 0; i < worker.length; i++) {
            int mostDifficultJob = binarySearch(works, worker[i]);
            if (mostDifficultJob == -1) continue;
            res += works.get(mostDifficultJob).maxProfit;
        }
        return res;
    }

    private int binarySearch(List<Work> works, int target) {
        int left = 0;
        int right = works.size() - 1;
        int res = -1;
        while (left <=  right) {
            int mid = left + (right - left) / 2;
            if (works.get(mid).difficulty <= target) {
                res = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return res;
    }
}
