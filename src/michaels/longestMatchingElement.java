package michaels;

import DataStructure.ListNode;

import java.util.ArrayList;
import java.util.List;

public class longestMatchingElement {
    /*
    two linked list
    longest Matching elements
    !!! couldn't random access
    Clarification:
        elements
        length?
    step 1:
        copy two linked list into data structure which allows random access
    dp[i][j]
        the longest matching elements ends with index i in input1 and ends with index j in input2
        int startIndex
        int maxLen
        dp[i][j]:
            list1[i] == list2[j]
            dp[i][j] = dp[i - 1][j - 1] + 1;
                     !=
            dp[i][j] = 0;
            update
                startIndex;
                maxLen
     */

    public static ListNode longestMatchingElements(ListNode l1, ListNode l2) {
        List<Integer> list1 = copyToList(l1);
        List<Integer> list2 = copyToList(l2);
        int[][] dp = new int[list1.size() + 1][list2.size() + 1];
        int startIndex = -1;
        int maxLen = 0;
        for (int i = 1; i <= list1.size(); i++) {
            for (int j = 1; j <= list2.size(); j++) {
                if (list1.get(i - 1) == list2.get(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                }
                if (dp[i][j] > maxLen) {
                    maxLen = dp[i][j];
                    startIndex = i - maxLen;
                }
             }
        }
        ListNode res = getRes(l1, startIndex, maxLen);
        return res;
    }

    private static List<Integer> copyToList(ListNode head) {
        List<Integer> res = new ArrayList<>();
        ListNode cur = head;
        while (cur != null) {
            res.add(cur.val);
            cur = cur.next;
        }
        return res;
    }

    private static ListNode getRes(ListNode head, int startIndex, int maxLen) {
        ListNode dummyHead = new ListNode(-1);
        ListNode cur = head;
        while (cur != null && startIndex != 0) {
            cur = cur.next;
            startIndex--;
        }
        if (cur == null) return null;
        ListNode pointer = new ListNode(cur.val);
        cur = cur.next;
        maxLen -= 1;
        dummyHead.next = pointer;
        while (cur != null && maxLen > 0) {
            pointer.next = new ListNode(cur.val);
            pointer = pointer.next;
            cur = cur.next;
            maxLen -= 1;
        }
        return dummyHead.next;
    }

    public static void main(String[] args) {
        ListNode head1 = new ListNode(0);
        head1.next = new ListNode(2);
        head1.next.next = new ListNode(3);
        head1.next.next.next = new ListNode(4);
        head1.next.next.next.next = new ListNode(5);
        head1.next.next.next.next.next = new ListNode(6);
        ListNode head2 = new ListNode(0);
        head2.next = new ListNode(4);
        head2.next.next = new ListNode(5);
        head2.next.next.next = new ListNode(6);
        ListNode res = longestMatchingElements(head1, head2);
        while (res != null) {
            System.out.println(res.val);
            res = res.next;
        }
    }
}
