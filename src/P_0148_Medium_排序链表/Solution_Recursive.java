package P_0148_Medium_排序链表;

/**
 * 不断切分链表
 * 直至切至只剩单独节点
 * 之后开始两两归并
 */

public class Solution_Recursive {
    public ListNode sortList(ListNode head) {

        if (head == null || head.next == null)
            return head;

        return sortProcess(head);
    }

    //递归归并排序过程
    private ListNode sortProcess(ListNode head) {

        //递归终止条件，当前链表段已没有节点或只剩1个节点，不需要再分割，直接返回
        if(head == null || head.next == null) {
            return head;
        }

        //用快慢指针找链表中点
        ListNode fast = head, slow = head;

        //循环结束，若链表长度为偶数，慢指针指向中间靠左一个节点；若链表长度为奇数，慢指针指向中点节点
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        /**
         * 记录慢指针下一个节点为tmp，并从慢指针切断后面的链表
         * 即，将链表分为head——slow，tmp——tail两段
         */
        ListNode tmp = slow.next;
        slow.next = null;

        //再分别对两段链表递归排序
        ListNode left = sortList(head);
        ListNode right = sortList(tmp);

        //现在left和right两段已分别有序，进行归并
        return merge(left, right);


    }

    //归并过程
    private ListNode merge(ListNode left, ListNode right) {

        ListNode cur = new ListNode(0);
        ListNode res = cur;

        while (left != null && right != null) {
            if (left.val <= right.val) {
                cur.next = left;
                left = left.next;
            } else {
                cur.next = right;
                right = right.next;
            }
            cur = cur.next;
        }

        if(left != null) {
            cur.next = left;
        }
        if(right != null) {
            cur.next = right;
        }

        return res.next;
    }
}
