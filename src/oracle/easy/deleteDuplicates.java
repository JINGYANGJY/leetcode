package oracle.easy;

import DataStructure.ListNode;

/*
Given a sorted linked list, delete all duplicates such that each element appear only once.
input: sorted linked list
output: linked list only contains unique number

1->2->2->3->3->4->4->5
   s     f

    if (f != s) {
        s.next = fast;
        s = s.next;
    }
    fast = fast.next;
1->2->3->4->5

Time: O(n)
 */
public class deleteDuplicates {
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null) return head;//!!!!
        ListNode fast = head, slow = head;
        while (fast != null) {
            if (slow.val != fast.val) {
                slow.next = fast;
                slow = slow.next;
            }
            fast = fast.next;
        }
        slow.next = null; //!!!!
        return head;
    }
}
