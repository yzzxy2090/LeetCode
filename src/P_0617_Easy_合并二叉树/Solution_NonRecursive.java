package P_0617_Easy_合并二叉树;

import java.util.Stack;

public class Solution_NonRecursive {
    public TreeNode mergeTrees(TreeNode t1, TreeNode t2) {
        if(t1 == null) {
            return t2;
        }

        Stack<TreeNode[]> stack = new Stack<TreeNode[]>();
        stack.push(new TreeNode[] {t1, t2});

        while(!stack.isEmpty()) {
            TreeNode[] arr = stack.pop();
            /**
             * 这里只会出现{t1, null}的情况
             * 只有{t1, t2}或{t1, null}的情况会被压栈
             *
             * {null, null}和{null, t2}的情况会被其父节点的
             * if(arr[0].left == null) {arr[0].left = arr[1].left;}
             * 以及if(arr[0].right == null) {arr[0].right = arr[1].right;}
             * 所拦截，不会被压入栈中
             *
             * 因此这里arr[0] == null可以省略
             *
             * 而对于这里出现的{t1, null}的情况
             * 即当前上一轮的t1存在左/右孩子，而t2不存在
             * 这里就无需处理，因为我们最终返回的结果就是t1，直接进入下一次循环
             */
            if(arr[0] == null || arr[1] == null) {
                continue;
            }

            //当前t1和t2同位置的节点都不为空，则将两节点值相加
            arr[0].val += arr[1].val;

            /**
             * 若t1的当前节点没有左孩子，就将t2当前节点的左孩子赋给t1当前节点
             * 否则将t1，t2当前节点的左孩子同时压栈
             */
            if(arr[0].left == null) {
                arr[0].left = arr[1].left;
            } else {
                stack.push(new TreeNode[] {arr[0].left, arr[1].left});
            }

            /**
             * 若t1的当前节点没有右孩子，就将t2当前节点的右孩子赋给t1当前节点
             * 否则将t1，t2当前节点的右孩子同时压栈
             */
            if(arr[0].right == null) {
                arr[0].right = arr[1].right;
            } else {
                stack.push(new TreeNode[] {arr[0].right, arr[1].right});
            }
        }

        return t1;
    }
}
