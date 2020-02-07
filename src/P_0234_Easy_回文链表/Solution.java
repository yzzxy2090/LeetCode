package P_0234_Easy_回文链表;

/**
 *
 * 234. Palindrome Linked List (Easy)
 *
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */

public class Solution {
    public boolean isPalindrome(ListNode head) {
        if(head == null || head.next == null) {
            return true;
        }

        /**
         * 设置慢指针n1,每次往后遍历1位
         * 快指针n2，每次往后遍历2位
         *
         * 当快指针n2遍历完链表即
         *   当链表元素个数为奇数时，n2.next==null，慢指针n1指向链表中点元素
         *      例1->2->3->2->1，n2遍历完链表指向链表最后一位的1时，n1指向中点3
         *   当链表元素个数为偶数时，n2.next.next==null，慢指针n1指向链表中点偏左的一个元素
         *      例1->2->2->1，n2遍历完链表指向链表倒数第二位的2时，n1指向中点偏左的2
         */
        ListNode n1 = head;
        ListNode n2= head;

        while(n2.next != null && n2.next.next != null) {
            n1 = n1.next;
            n2 = n2.next.next;
        }

        /**
         * 将中点元素之后的链表反转
         * 例如1->2->3->2->1
         * 将2->1反转，反转后变为1->2->3<-2<-1，其中中点3.next=null
         * 又如1->2->2->1
         * 将2->1反转，反转后变为1->2<-2<-1，其中中点2.next=null
         * 之后分别从两部分的头结点往尾部遍历，也就是两边往中间遍历，若遍历中两指针所指向元素值不同，则说明不是回文链表
         * 若遍历过程中只要有一方指针为空(链表个数为奇数时，两指针都指向空；链表元素个数为偶数时，左半部分遍历指针先为空)
         * 说明遍历完成，链表是回文链表
         */
        n2 = n1.next;
        n1.next = null;
        ListNode n3 = null;
        while(n2 != null) {
            n3 = n2.next;
            n2.next = n1;
            n1 = n2;
            n2 = n3;
        }

        n3 = n1;//此时n1指向原链表最后一个元素，用n3保存该元素，同时n1也是当前右半部分链表的头结点
        n2 = head;//将n2作为左半部分链表的头结点
        boolean res = true;
        while(n1 != null && n2 != null) {//左右部分链表分别从两边向中间遍历
            if(n1.val != n2.val) {
                res = false;
                break;
            }

            n1 = n1.next;
            n2 = n2.next;
        }

        //判断完回文性后还要把链表恢复原状
        n1 = n3.next;
        n3.next = null;
        while(n1 != null) {
            n2 = n1.next;
            n1.next = n3;
            n3 = n1;
            n1 = n2;
        }

        return res;
    }
}
