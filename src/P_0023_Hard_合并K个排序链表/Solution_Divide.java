package P_0023_Hard_合并K个排序链表;

/**
 * @description 合并 k 个排序链表，返回合并后的排序链表。请分析和描述算法的复杂度。
 * @author: zxy
 * @create: 2020/6/28 23:25
 *
 * 分治法
 * 时间复杂度：O(kn×logk)
 * 空间复杂度：递归会使用到 O(logk) 空间代价的栈空间。
 */
public class Solution_Divide {
    public ListNode mergeKLists(ListNode[] lists) {
        if(lists == null || lists.length < 1) {
            return null;
        }

        int len = lists.length;

        return process(lists, 0, len - 1);
    }

    // 分治法，将k个链表的合并分解为多轮的两两合并，第一轮k/2组的两两合并，第二轮k/4组的两两合并，直到最后1组两两合并
    private ListNode process(ListNode[] lists, int left, int right) {
        // 边界，被分割为只有一个链表时，无需merge，直接返回
        if(left == right) {
            return lists[left];
        }

        if(left > right) {
            return null;
        }

        int mid = left + (right - left) / 2;
        // leftList认为是左侧已排好序的链表
        ListNode leftList = process(lists, left, mid);
        // rightList认为是右侧已排好序的链表
        ListNode rightList = process(lists, mid + 1, right);

        // 两有序链表合并
        return mergeTwoLists(leftList, rightList);
    }

    // merge两个链表
    private ListNode mergeTwoLists(ListNode leftList, ListNode rightList) {
        if(leftList == null || rightList == null) {
            return leftList == null ? rightList : leftList;
        }

        ListNode dummy = new ListNode(-1);
        ListNode pl = leftList, pr = rightList;
        ListNode cur = dummy;

        // 注意条件是&&
        while(pl != null && pr != null) {
            if(pl.val <= pr.val) {
                cur.next = pl;
                pl = pl.next;
            } else {
                cur.next = pr;
                pr = pr.next;
            }

            cur = cur.next;
        }

        cur.next = pl == null ? pr : pl;

        return dummy.next;
    }
}
