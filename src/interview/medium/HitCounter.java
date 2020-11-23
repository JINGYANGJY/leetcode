package interview.medium;

import java.util.ArrayDeque;
import java.util.Deque;

public class HitCounter {
    /*
    Design a hit counter which counts the number of hits received in the past 5 minutes.

    Each function accepts a timestamp parameter (in seconds granularity) and
    you may assume that calls are being made to the system in chronological order
    (ie, the timestamp is monotonically increasing). You may assume that the earliest timestamp starts at 1.
    It is possible that several hits arrive roughly at the same time.
     */
    /*
        hit(int timestamp)
        getHits(int timestamp)
        data structure store  all hits happened in the last 5 minutes
        queue
        hit(int timestamp) - O(n)
            check until current, poll from the queue hits whose timestamp is 5 minutes ago.
            add current hit into queue
        getHits(int timestamp) -O(1)
            return current size of queue
     */
    Deque<Integer> deque;
    public HitCounter() {
        deque = new ArrayDeque<>();
    }

    /** Record a hit.
     @param timestamp - The current timestamp (in seconds granularity). */
    public void hit(int timestamp) {
        while (!deque.isEmpty() && deque.peekFirst() <= timestamp - 300) {
            deque.pollFirst();
        }
        deque.offerLast(timestamp);
    }

    /** Return the number of hits in the past 5 minutes.
     @param timestamp - The current timestamp (in seconds granularity). */
    public int getHits(int timestamp) {
        while (!deque.isEmpty() && deque.peekFirst() <= timestamp - 300) {
            deque.pollFirst();
        }
        return deque.size();
    }
}
