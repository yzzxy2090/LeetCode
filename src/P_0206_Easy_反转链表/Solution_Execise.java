package P_0206_Easy_反转链表;

public class Solution_Execise {
    public ListNode reverseList(ListNode head) {
        if(head == null || head.next ==null) {
            return head;
        }

        ListNode cur = head, pre = head, next = head.next;
        cur.next = null;

        while(next != null) {
            pre = cur;
            cur = next;
            next = cur.next;
            cur.next = pre;
        }
        return cur;
    }
}
