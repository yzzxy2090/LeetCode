package P_0025_Hard_K个一组翻转链表;

/**
 * 25. Reverse Nodes in k-Group (Hard)
 *
 * https://cloud.tencent.com/developer/article/1488136
 *
 * 所以这个题目实现的时候要把握住几个要点：
 * 第一，在反转子链表的时候，上一个子链表的尾必须知道
 * 第二，下一个子链表的头也必须知道
 * 第三，当前反转的链表的头尾都必须知道
 *
 * 例如：1->2->3->4->5->6->7->8
 * 首先要设置一个dummy节点作为该链表的额外头结点，即dummy.next=head
 * 即dummy->1->2->3->4->5->6->7->8
 * 这样要对1->2->3这个区间反转时，需要知道他们的前驱节点dummy，他们的后继节点4，翻转区域为(dummy, 4)
 * 还要知道该链表段的头结点start即1，以及尾结点end即3
 *
 * 1.断开该段链表和后继节点的联系，即3.next=null，此时start确定为1
 *      dummy->1->2->3 4
 * 2.翻转该段链表1->2->3，并让他们的前驱节点的next指向翻转后的链表头结点
 *   翻转时一定会遍历该段链表，遍历完成后指针start此时仍指向1，相当于是翻转后的链表的尾结点
 *      dummy->3->2->1 4
 * 3.将该段链表的尾结点的next指向后驱节点(即start.next=nextGroupHead)，即1.next=4
 *   再将遍历指针cur设为1，并将此时的1作为下一段链表的前驱节点
 *
 * 若遍历链表段时遍历元素少于k个就已遍历完整个链表，说明最后一段不足k个元素，直接退出循环
 *
 * 时间复杂度为 O(n∗K) 最好的情况为 O(n) 最差的情况未 O(n^2)
 * 空间复杂度为 O(1) 除了几个必须的节点指针外，我们并没有占用其他空间
 */

public class Solution {
    public ListNode reverseKGroup(ListNode head, int k) {
        if(head == null || head.next == null || k < 2) {
            return head;
        }

        ListNode dummy = new ListNode(0);
        dummy.next = head;

        ListNode cur = dummy;
        ListNode lastGroupTail = dummy;
        ListNode nextGroupHead = null;
        ListNode start = null;

        int count = 0;

        //如果cur.next==null说明此时链表已被遍历完成，退出循环
        while(cur.next != null) {
            //先设好上一组的尾结点
            lastGroupTail = cur;
            //cur往后遍历k次，找到带反转链表的尾结点
            while(count++ < k && cur.next != null) {
                cur = cur.next;
            }

            //如果这一组待反转链表个数不足k个，就不用翻转，直接退出循环
            if(count < k) {
                break;
            }

            //否则将计数count重置为0
            count = 0;

            //待反转链表的头结点为上一组链表的尾结点的next
            start = lastGroupTail.next;
            //将当前cur的next指向的节点置为下一组链表的头结点
            nextGroupHead = cur.next;
            //先断开当前待翻转链表段和下一段的连接
            cur.next = null;

            //逆转头节点为start的链表，并让lastGroupTail指向他逆转后的首节点
            lastGroupTail.next = reverse(start);
            //此时start已是reverse(start)这个链表的尾结点，令他的next指向nextGroupHead
            start.next = nextGroupHead;
            //对于下一组待翻转链表来说，当前start就是lastGroupTail
            lastGroupTail = start;

            //cur继续从lastGroupTail开始向后遍历
            cur = lastGroupTail;
        }
        return dummy.next;
    }

    private ListNode reverse(ListNode head) {
        if(head == null || head.next == null) {
            return head;
        }

        ListNode pre = null;
        ListNode cur = head;
        ListNode next = null;

        while(cur != null) {
            next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return pre;
    }

}
