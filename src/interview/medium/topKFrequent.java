package interview.medium;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/*
Given a non-empty array of integers, return the k most frequent elements.
Example 1:
Input: nums = [1,1,1,2,2,3], k = 2
Output: [1,2]
Example 2:
Input: nums = [1], k = 1
Output: [1]
Clarification:
    [1, 1, 1, 2, 2, 3, 3] k = 2
    answers: [1, 2] or [1, 3] how to return
    Assumption: 1. k < nums.length
                2. answer is unique
    M1:
    frequency
        <integer, frequency>
    sorted data structure
    Entry
    Sort all entries according to its frequency
    O(n + nlogn)
    M2:
        min heap size = k
        used to store k candidates entry
        Map<Integer, Integer> <Integer, Frequency>
        if pq.size < k
            add to pq
        else check current entry's frequency
            if > top.frequency
                poll
                add
            else ignore
    Time: O(nlogk)
 */
public class topKFrequent {
    public static int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> freqMap = new HashMap<>();
        PriorityQueue<Integer> minHeap = new PriorityQueue<>((a, b) -> (freqMap.get(a) - freqMap.get(b)));
        for (int i = 0; i < nums.length; i++) {
            freqMap.put(nums[i], freqMap.getOrDefault(nums[i], 0) + 1);
        }
        for (Map.Entry entry : freqMap.entrySet()) {
            if (minHeap.size() < k) {
                minHeap.offer((Integer)entry.getKey());
            } else {
                Integer count = freqMap.get(minHeap.peek());
                if (count < (Integer) entry.getValue()) {
                    minHeap.poll();
                    minHeap.offer((Integer)entry.getKey());
                }
            }
        }
        int[] res = new int[k];
        int index = 0;
        while (!minHeap.isEmpty()) {
            res[index++] = minHeap.poll();
        }
        return res;
    }
    public static void main(String[] args) {
        int[] arr = {4,1,-1,2,-1,2,3};
        int k = 2;
        System.out.println(Arrays.toString(topKFrequent(arr, k)));
    }
}
