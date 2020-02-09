package P_0021_Easy_合并两个有序链表;

public class Solution_NonRecursive {
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if(l1 == null) {
            return l2;
        } else if(l2 == null) {
            return l1;
        }

        if(l1.val > l2.val) {
            ListNode temp = l1;
            l1 = l2;
            l2 = temp;
        }

        ListNode cur = l1, l2Next = l2;
        while(cur.next != null && l2 != null) {
            l2Next = l2.next;
            while(cur.next != null && cur.next.val <= l2.val) {
                cur = cur.next;
            }
            l2.next = cur.next;
            cur.next = l2;
            cur = l2;
            l2 = l2Next;
        }
        if(l2 != null) {
            cur.next = l2;
        }
        return l1;
    }
}
