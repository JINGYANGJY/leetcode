package amazon.Design;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

public class Leet895_MaximumFrequencyStack {
    class FreqStack {
        /*
        for each frequency, we have a stack
        - most frequency
          Map<Integer, Integer>
          Map<Integer, Stack>
          push()
            int frequency of x
            push into stack whose frequency = x
          pop()
             most frequency
             if is empty
             requency - 1
        */
        Map<Integer, Integer> freqMap;
        Map<Integer, Deque> stackMap;
        int mostFreq;
        public FreqStack() {
            freqMap = new HashMap<>();
            stackMap = new HashMap<>();
            mostFreq = 0;
        }

        public void push(int x) {
            int freq = freqMap.getOrDefault(x, 0);
            freq += 1;
            freqMap.put(x, freq);
            mostFreq = Math.max(mostFreq, freq);
            stackMap.computeIfAbsent(freq, k -> new ArrayDeque<>()).push(x);
        }

        public int pop() {
            Deque<Integer> stack = stackMap.get(mostFreq);
            if (stack == null) return -1;
            int res = stack.isEmpty() ? -1 : stack.pop();
            int freq = freqMap.get(res);
            if (freq == 1) {
                freqMap.remove(res);
            } else {
                freqMap.put(res, freq - 1);
            }
            if (stack.isEmpty()) {
                stackMap.remove(mostFreq);
                mostFreq -= 1;
            }
            return res;
        }
    }
}
