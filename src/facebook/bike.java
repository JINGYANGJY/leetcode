package facebook;

import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

public class bike {
        static class WorkerDis {
            int worker;
            int bike;
            public WorkerDis(int worker, int bike) {
                this.worker = worker;
                this.bike = bike;
            }
        }
        public static int[] assignBikes(int[][] workers, int[][] bikes) {
            int n = workers.length;
            int[] res = new int[n];
            Set<Integer> usedBike = new HashSet<>();
            Set<Integer> assigned = new HashSet<>();
            PriorityQueue<WorkerDis> pq = new PriorityQueue<>((a, b) -> {
                int dis1 = getDis(workers, bikes, a.worker, a.bike);
                int dis2 = getDis(workers, bikes, b.worker, b.bike);
                int r = dis1 - dis2;
                if (r == 0) {
                    r = a.worker - b.worker;
                    if (r == 0) {
                        return a.bike - b.bike;
                    }
                    return r;
                }
                return r;
            });
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    pq.offer(new WorkerDis(i, j));
                }
            }
            while (!pq.isEmpty()) {
                if (assigned.size() == n) {
                    return res;
                }
                WorkerDis w = pq.poll();
                if (assigned.contains(w.worker) || usedBike.contains(w.bike)) {
                    continue;
                } else {
                    res[w.worker] = w.bike;
                    usedBike.add(w.bike);
                    assigned.add(w.worker);
                }
            }
            return res;
        }

        private static int getDis(int[][] workers, int[][] bikes, int i, int j) {
            return Math.abs(workers[i][0] - bikes[j][0]) + Math.abs(workers[i][1] - bikes[j][1]);
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
