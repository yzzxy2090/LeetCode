package P_0206_Easy_反转链表;

/**
 *
 * 206.Reverse Linked List (Easy)
 *
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
public class Solution {
    public ListNode reverseList(ListNode head) {
        if(head == null || head.next == null) {
            return head;
        }

        ListNode n1 = head;
        ListNode n2 = head.next;
        ListNode n3 = null;
        n1.next = null;

        while(n2 != null) {
            n3 = n2.next;
            n2.next = n1;
            n1 = n2;
            n2 = n3;
        }

        head = n1;
        return head;
    }
}

