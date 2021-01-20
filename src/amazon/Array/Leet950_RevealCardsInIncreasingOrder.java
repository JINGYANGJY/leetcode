package amazon.Array;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class Leet950_RevealCardsInIncreasingOrder {
    public int[] deckRevealedIncreasing(int[] deck) {
        Deque<Integer> queue = new ArrayDeque<>();
        for (int i = 0; i < deck.length; i++) {
            queue.offer(i);
        }
        int[] res = new int[deck.length];
        Arrays.sort(deck);
        for (int i : deck) {
            res[queue.poll()] = i;
            if (!queue.isEmpty()) queue.offer(queue.poll());
        }
        return res;
    }
}
