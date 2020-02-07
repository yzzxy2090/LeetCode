package P_0019_Medium_删除链表的倒数第N个节点;

/**
 *
 * 19. Remove Nth Node From End of List (Medium)
 *
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */

public class Solution {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode fast = head;
        ListNode slow = head;
        ListNode pre = null;//用于记录第n个元素的前置元素

        //快指针先走到第n个元素位置上
        while(--n > 0) {
            fast = fast.next;
        }
        //若快指针走完fast.next为空，即第n个元素为链表最后一个节点，说明倒数第n个元素就是头结点，直接返回head.next
        if(fast.next == null) {
            return head.next;
        }

        /**
         * 之后快慢节点同步往后遍历
         * 当快指针已经指向链表最后一个节点时，即fast.next==null
         * 此时慢指针slow指向链表倒数第n个元素
         */
        while(fast.next != null) {
            pre = slow;//先记录慢指针所指元素的前一个元素
            slow = slow.next;
            fast = fast.next;
        }

        pre.next = slow.next;//删掉慢指针slow指向的链表倒数第n个元素

        return head;
    }
}
