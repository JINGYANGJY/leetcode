package facebook;

import java.util.ArrayList;
import java.util.List;

public class bike2 {
        static class WorkerBike {
            int[] worker;
            int[] bike;
            int wIndex;
            int bIndex;
            int distance;
            public WorkerBike(int[] worker, int[] bike, int wIndex, int bIndex) {
                this.worker = worker;
                this.bike = bike;
                this.wIndex = wIndex;
                this.bIndex = bIndex;
                this.distance = Math.abs(worker[0] - bike[0]) + Math.abs(worker[1] - bike[1]);
            }
        }
        public static int[] assignBikes(int[][] workers, int[][] bikes) {
            int[] res = new int[workers.length];
            List<WorkerBike> list = new ArrayList<>();
            for (int i = 0; i < workers.length; i++) {
                for (int j = 0; j < bikes.length; j++) {
                    list.add(new WorkerBike(workers[i], bikes[j], i, j));
                }
            }
            list.sort((w1, w2) -> {
                int ans = w1.distance - w2.distance;
                if (ans == 0) {
                    ans = w1.wIndex - w2.wIndex;
                    if(ans == 0) {
                        return w1.bIndex - w2.bIndex;
                    }
                    return ans;
                }
                return ans;
            });
            boolean[] set = new boolean[bikes.length];
            boolean[] work = new boolean[workers.length];
            for (WorkerBike wb : list) {
                int wIndex = wb.wIndex;
                int bIndex = wb.bIndex;
                if (set[bIndex] || work[wIndex]) {
                    continue;
                }
                set[bIndex] = true;
                work[wIndex] = true;
                res[wIndex] = bIndex;
            }
            return res;
        }
    public static void main(String[] args) {
        int[][] workers = {{664,994},{3,425},{599,913},{220,352},{145,348},{604,428},{519,183},{732,148}};
        int[][] bikes = {{611,698},{113,338},{579,770},{276,588},{948,679},{731,525},{925,877},{182,281},{39,299}};
        int[] res = assignBikes(workers, bikes);
        for (int r : res) {
            System.out.println(r);
        }
    }
}
