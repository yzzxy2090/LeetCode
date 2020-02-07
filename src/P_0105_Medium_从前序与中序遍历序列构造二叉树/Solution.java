package P_0105_Medium_从前序与中序遍历序列构造二叉树;

import java.util.HashMap;
import java.util.Map;

/**
 * 105. Construct Binary Tree from Preorder and Inorder Traversal (Medium)
 *
 * preorder 中的第一个元素一定是树的根
 * 这个根又将 inorder 序列分成了左右两棵子树
 */

public class Solution {
    //从先序遍历序列依次取节点作为根节点
    int pre_index = 0;

    //用一个HashMap把中序遍历数组的每个元素的值和下标存起来，这样寻找根节点的位置就可以直接得到
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        Map<Integer, Integer> in_seq = new HashMap<Integer, Integer>();
        for(int i=0; i<inorder.length; i++) {
            in_seq.put(inorder[i], i);
        }

        return process(in_seq, preorder, 0, preorder.length);
    }

    /**
     *
     * @param in_seq 中序遍历数组Map<key:节点值, value:节点值对应的数组下标>，主要是用于给节点定位(是左孩子还是右孩子)
     * @param preorder 先序遍历数组，提供各节点，该数组排列顺序就是构造二叉树节点的顺序
     * @param in_start 构造范围的中序遍历数组的起始位置，为闭区间
     * @param in_end 构造范围的中序遍历数组的结束位置，为开区间
     *                即[in_start, in_end)
     * @return
     */
    private TreeNode process(Map<Integer, Integer> in_seq, int[] preorder, int in_start, int in_end) {
        if(in_start == in_end) {
            return null;
        }

        int root_val = preorder[pre_index];
        TreeNode root = new TreeNode(root_val);

        /**
         * 在中序遍历数组中找到刚构造的根节点root的下标值
         */
        int index = in_seq.get(root_val);

        pre_index++;

        /**
         * 确定根节点后
         * 中序遍历数组中该根节点以左部分即inorder[in_start]至inorder[index - 1]为该根节点的左子树
         * 中序遍历数组中该根节点以右部分即inorder[index + 1]至inorder[in_end - 1]为该根节点的右子树
         * 为int root_val = preorder[pre_index];TreeNode root = new TreeNode(root_val);
         * 提供方向
         */
        root.left = process(in_seq, preorder, in_start, index);
        root.right = process(in_seq, preorder, index + 1, in_end);

        return root;
    }
}
