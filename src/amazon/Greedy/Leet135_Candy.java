package amazon.Greedy;

import java.util.PriorityQueue;

public class Leet135_Candy {
    /*
    别人的好方法
     */
    public int candyBetter(int[] ratings) {
        if (ratings.length == 0) return 0;
        int ret = 1;
        int up = 0, down = 0, peak = 0;
        for (int i = 1; i < ratings.length; i++) {
            if (ratings[i - 1] < ratings[i]) {
                peak = ++up;
                down = 0;
                ret += 1 + up;
            } else if (ratings[i - 1] == ratings[i])  {
                peak = up = down = 0;
                ret += 1;
            } else {
                up = 0;
                down++;
                ret += 1 + down + (peak >= down ? -1 : 0);
            }
        }

        return ret;

    }
    /*
    我的渣方法
     */
    class Pair{
        int rating;
        int index;
        public Pair(int rating, int index) {
            this.rating = rating;
            this.index = index;
        }
    }
    public int candy(int[] ratings) {
        /*
        start from lowest rating
        */
        int[] res = new int[ratings.length];
        PriorityQueue<Pair> pq = new PriorityQueue<>((a, b) -> {
            int ans = a.rating - b.rating;
            if (ans == 0) {
                int aLeft = a.index > 0 ? res[a.index - 1] : 0;
                int aRight = a.index < res.length - 1 ? res[a.index + 1] : 0;
                int maxNearA = Math.max(aLeft, aRight);
                int bLeft = b.index > 0 ? res[b.index - 1] : 0;
                int bRight = b.index < res.length - 1 ? res[b.index + 1] : 0;
                int maxNearB = Math.max(bLeft, bRight);
                if (maxNearA < maxNearB) {
                    return -1;
                } else {
                    return 1;
                }
            }
            return ans;
        });
        for (int i = 0; i < ratings.length; i++) {
            pq.offer(new Pair(ratings[i], i));
        }

        while (!pq.isEmpty()) {
            int index = pq.poll().index;
            if ((index > 0 && ratings[index] <= ratings[index - 1] || index == 0) &&
                    (index + 1 < ratings.length && ratings[index] <= ratings[index + 1] || index == ratings.length - 1)) {
                res[index] = 1;
            } else {
                int left = index == 0 ? 0 : res[index - 1];
                int right = index == res.length - 1 ? 0 : res[index + 1];
                if (index - 1 > 0 && ratings[index - 1] == ratings[index] && left > right) {
                    res[index] = left;
                } else if (index + 1 < res.length && ratings[index + 1] == ratings[index] && right > left) {
                    res[index] = right;
                } else {
                    res[index] = Math.max(left, right) + 1;
                }
            }
        }

        //System.out.println(Arrays.toString(res));
        int ans = 0;
        for (int i : res) {
            ans += i;
        }
        return ans;
    }
}
