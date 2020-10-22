package oracle.hard;

import java.util.PriorityQueue;

/*
Median is the middle value in an ordered integer list.
If the size of the list is even,
there is no middle value. So the median is the mean of the two middle value.
For example,
[2,3,4], the median is 3
[2,3], the median is (2 + 3) / 2 = 2.5
Design a data structure that supports the following two operations:
void addNum(int num) - Add a integer number from the data stream to the data structure.
double findMedian() - Return the median of all elements so far.
 */
public class MedianFinder {
    /** initialize your data structure here. */
    /*
    median
        1. sort the numbers
        2. find the median one or two from the sorted numbers
     addNum(int num)
     findMedian()
     sorted data structure
        ArrayList
            add(num) O(1)
            findMedian() sort nlogn

      max heap                  min heap
      smaller part              larger part

      max heap size - 1 == min heap
      if (newNum > max heap peek)
            offer to min heap
      else
            offer to max heap
     make max heap and min heap balance
     max heap size == min heap size or max heap size == min heap size + 1
     if max heap size > min heap size + 1
        poll from max heap
        add to min heap
     else max heap size < min heap size
        poll from min heap
        add to max heap
         time:
         add(2 * log(n/2))
         findMedian O(1)
     */
    PriorityQueue<Integer> minHeap;
    PriorityQueue<Integer> maxHeap;
    int count;
    public MedianFinder() {
        minHeap = new PriorityQueue<Integer>((a, b) -> (a - b));
        maxHeap = new PriorityQueue<Integer>((a, b) -> (b - a));
        count = 0;
    }

    public void addNum(int num) {
        if (maxHeap.isEmpty() || !maxHeap.isEmpty() && maxHeap.peek() > num) {
            maxHeap.offer(num);
        } else {
            minHeap.offer(num);
        }
        if (maxHeap.size() > minHeap.size() + 1) {
            minHeap.offer(maxHeap.poll());
        } else if (maxHeap.size() < minHeap.size()) {
            maxHeap.offer(minHeap.poll());
        }
        count++;
    }

    public double findMedian() {
        if (count % 2 == 0) {
            return (maxHeap.peek() + minHeap.peek()) / 2.0;
        } else {
            return maxHeap.peek();
        }

    }
}
