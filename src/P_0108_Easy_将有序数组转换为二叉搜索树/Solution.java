package P_0108_Easy_将有序数组转换为二叉搜索树;

/**
 * 108. Convert Sorted Array to Binary Search Tree (Easy)
 *
 * 构造一棵树的过程可以拆分成无数个这样的子问题：构造树的每个节点以及节点之间的关系。对于每个节点来说，都需要：
 *
 * Step1.选取节点
 * Step2.构造该节点的左子树
 * Step3.构造该节点的右子树
 * 因题目要求构造一棵「高度平衡」的树，所以我们在选取节点时选择数组的中点作为根节点，以此来保证平衡性。
 */

public class Solution {
    public TreeNode sortedArrayToBST(int[] nums) {
        TreeNode res = process(nums, 0, nums.length);

        return res;
    }

    private TreeNode process(int[] nums, int start, int end) {
        if(start == end) {
            return null;
        }

        /**
         * mid = (start + end) / 2 但这样写容易导致溢出
         * mid = (start + end + start - start) / 2
         *     = (2start + (end - start)) / 2
         *     = start + (end - start) / 2
         * jdk中，求中点是 mid = (start + end) >>> 1
         */
        int mid = (start + end) >>> 1;//start + (end - start) / 2
        TreeNode root = new TreeNode(nums[mid]);
        root.left = process(nums, start, mid);
        root.right = process(nums, mid + 1, end);

        return root;
    }
}
