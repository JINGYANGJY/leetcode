package intuit.vo;

import DataStructure.ListNode;

import java.util.List;

public class Leet876_MiddleoftheLinkedList {
    /*
    https://leetcode.com/problems/middle-of-the-linked-list/
    Given a non-empty, singly linked list with head node head, return a middle node of linked list.
    If there are two middle nodes, return the second middle node.
     */
    /*
    know the length of the linked list
    - index -> middle
    traverse two times
    O(n)
    -----------------------------------------
    -> one time
   odd length
    1->2->3->4->5
          s
                 f
    s = s.next
    f = f.next.next
    even length
    1->2->3->4
       s
          f
    if it even, we should return the n / 2 - 1 or  n / 2 ?
     */

    public ListNode middle(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            if (fast == null) {
                break;
            }
            slow = slow.next;
        }
        return slow;
    }

}

