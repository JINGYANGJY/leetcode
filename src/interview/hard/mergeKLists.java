package interview.hard;

import DataStructure.ListNode;

import java.util.PriorityQueue;

/*
You are given an array of k linked-lists lists, each linked-list is sorted in ascending order.
Merge all the linked-lists into one sorted linked-list and return it.
 */
public class mergeKLists {
    /*
    Clarification:
        input: k sorted linked-list
        output: one sorted linked-list
    Assumption:
        return ascending order linked-list
     Sol:
        M1:
            Merge and sorted
            Time: O(nk * (nk)log(nk))
            Space: O(nk)
        M2:
            k sorted linked-list
            size == k min priorityQueue help sorting
            Time: nklogk
            Space: O(k)
     */
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) {
            return null;
        } // ❌!!!!!
        ListNode dummyHead = new ListNode();
        PriorityQueue<ListNode> pq = new PriorityQueue<>((a, b) -> (a.val - b.val));
        for (int i = 0; i < lists.length; i++) {
            if (lists[i] != null) { // ❌!!!!!
                pq.offer(lists[i]);
            }
        }
        ListNode cur = dummyHead;
        while (!pq.isEmpty()) {
            ListNode top = pq.poll();
            cur.next = top;
            if (top.next != null) {
                top = top.next;
                pq.offer(top);
            }
            cur = cur.next;
        }
        return dummyHead.next;
    }

}
