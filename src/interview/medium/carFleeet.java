package interview.medium;

import java.util.*;

public class carFleeet {
        public int carFleet(int target, int[] position, int[] speed) {
            List<List<Double>> posWithTimeLeft = new ArrayList<>();
            Deque<Double> monoDeque = new ArrayDeque<>();
            for (int i = 0; i < position.length; i++) {
                posWithTimeLeft.add(new ArrayList<>());
                posWithTimeLeft.get(i).add(Double.valueOf(target - position[i]));
                posWithTimeLeft.get(i).add(Double.valueOf(target  - position[i]) / speed[i]);
            }
            Collections.sort(posWithTimeLeft, (a, b) -> (a.get(0) - b.get(0) < 0 ? -1 : 1));
            for (int i = 0; i < posWithTimeLeft.size(); i++) {
                monoDeque.offerFirst(posWithTimeLeft.get(i).get(1));
            }
            int res = 0;
            while (!monoDeque.isEmpty()) {
                Double cur = monoDeque.pollLast();
                while (!monoDeque.isEmpty() && monoDeque.peekLast() <= cur){
                    cur = monoDeque.pollLast();
                }
                res++;
            }
            return res;



            // 10 8 0 5 3
            // 10 8 5 3 0
            // 2  4 12 7 9
            // 1  1 7 3 12

        }
}
