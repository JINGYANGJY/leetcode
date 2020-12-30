package interview.medium;

import java.util.HashMap;
import java.util.Map;

public class copyRandomList {
    class Node {
        int val;
        Node next;
        Node random;
        public Node(int val) {
            this.val = val;
        }
    }
    /*
    A linked list is given such that each node contains an additional random pointer which
    could point to any node in the list or null.
    Return a deep copy of the list.
    The Linked List is represented in the input/output as a list of n nodes.
    Each node is represented as a pair of [val, random_index] where:
    val: an integer representing Node.val
    random_index: the index of the node (range from 0 to n-1) where
    random pointer points to, or null if it does not point to any node.
     */
    public Node copyRandomList(Node head) {
        Map<Node, Node> map = new HashMap<>();
        Node dummyHead = new Node(-1);
        Node pointer = dummyHead;
        Node cur = head;
        while (cur != null) {
            Node cpNode = map.get(cur);
            if (cpNode == null) {
                cpNode = new Node(cur.val);
                map.put(cur, cpNode);
            }
            pointer.next = cpNode;
            if (cur.random != null) {
                Node cpRandom = map.get(cur.random);
                if (cpRandom == null) {
                    cpRandom = new Node(cur.random.val);
                    map.put(cur.random, cpRandom);
                }
                cpNode.random = cpRandom;
            }
            pointer = pointer.next;
            cur = cur.next;
        }
        return dummyHead.next;
    }
}
