package P_0099_Hard_恢复二叉搜索树;

import java.util.Stack;

/**
 * 非递归中序遍历法
 * <p>
 * 二分查找树的一个性质，左孩子小于根节点，根节点小于右孩子。
 * 所以做一次中序遍历，产生的序列就是从小到大排列的有序序列。
 * <p>
 * 回到这道题，题目交换了两个数字，其实就是在有序序列中交换了两个数字。而我们只需要把它还原。
 * <p>
 * 交换的位置的话就是两种情况。
 * <p>
 * 1.相邻的两个数字交换
 * <p>
 * [ 1 2 3 4 5 ] 中 2 和 3 进行交换，[ 1 3 2 4 5 ]，
 * 这样的话只产生一组逆序的数字（正常情况是从小到大排序，交换后产生了从大到小），3 2。
 * <p>
 * 我们只需要遍历数组，找到后，把这一组的两个数字进行交换即可。
 * <p>
 * <p>
 * 2.不相邻的两个数字交换
 * <p>
 * [ 1 2 3 4 5 ] 中 2 和 5 进行交换，[ 1 5 3 4 2 ]，
 * 这样的话其实就是产生了两组逆序的数字对。5 3 和 4 2。
 * <p>
 * 所以我们只需要遍历数组，然后找到这两组逆序对，然后把第一组前一个数字和第二组后一个数字进行交换即完成了还原。
 * <p>
 * 所以在中序遍历中，只需要利用一个 pre 节点和当前节点比较，
 * 如果 pre 节点的值大于当前节点的值，那么就是我们要找的逆序的数字。分别用两个指针 first 和 second 保存即可。
 * 如果找到第二组逆序的数字，我们就把 second 更新为当前节点。
 * 最后把 first 和 second 两个的数字交换即可。
 */

public class Solution_Inorder {
    public void recoverTree(TreeNode root) {
        if (root == null) {
            return;
        }

        /**
         * first用于保存第一个逆序数字对的第一个数
         * second用于保存第一个逆序数字对的第二个数，如果有第二个逆序数字对，则用第二个逆序数字对的第二个数替换当前值
         *
         * pre用于保存当前遍历节点的前一个节点
         */
        TreeNode first = null;
        TreeNode second = null;
        TreeNode pre = null;

        Stack<TreeNode> stack = new Stack<TreeNode>();
        TreeNode cur = root;

        while (cur != null || !stack.isEmpty()) {
            while (cur != null) {
                stack.push(cur);
                cur = cur.left;
            }

            TreeNode popNode = stack.pop();
            //如果当前节点popNode值小于前驱节点pre值，说明出现逆序数字对
            if (pre != null && popNode.val < pre.val) {
                if (first == null) {//首次出现逆序数字对
                    first = pre;
                    second = popNode;
                } else {//第二次出现逆序数字对
                    second = popNode;
                }
            }

            pre = popNode;

            cur = popNode.right;
        }

        //注意交换的是节点值而不是节点
        int temp = first.val;
        first.val = second.val;
        second.val = temp;
    }
}
