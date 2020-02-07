package P_0099_Hard_恢复二叉搜索树;

/**
 * 99. Recover Binary Search Tree (Hard)
 *
 * 考虑以下交换的位置的可能：
 *
 * 1.根节点和左子树的某个数字交换 -> 由于根节点大于左子树中的所有数，所以交换后我们只要找左子树中最大的那个数，就是所交换的那个数
 *
 * 2.根节点和右子树的某个数字交换 -> 由于根节点小于右子树中的所有数，所以交换后我们只要在右子树中最小的那个数，就是所交换的那个数
 *
 * 3.左子树和右子树的两个数字交换 -> 找左子树中最大的数，右子树中最小的数，即对应两个交换的数
 *
 * 4.左子树中的两个数字交换
 *
 * 5.右子树中的两个数字交换
 */

public class Solution_Recursive {
    public void recoverTree(TreeNode root) {
        if (root == null) {
            return;
        }

        TreeNode maxLeft = getMaxOfBST(root.left);
        TreeNode minRight = getMinOfBST(root.right);

        /**
         * Case1.左子树和右子树中两节点发生了交换
         *       即，左子树最大值大于根节点值 并且 右子树最小值小于根节点值
         */
        if(maxLeft != null && minRight != null) {
            if(maxLeft.val > root.val && minRight.val < root.val) {
                int temp = maxLeft.val;
                maxLeft.val = minRight.val;
                minRight.val = temp;
            }
        }

        /**
         * Case2.左子树中的节点和根节点发生了交换
         *       即，左子树最大值大于根节点值
         */
        if(maxLeft != null) {
            if(maxLeft.val > root.val) {
                int temp = maxLeft.val;
                maxLeft.val = root.val;
                root.val = temp;
            }
        }

        /**
         * Case3.右子树中节点和根节点发生了交换
         *       即，右子树最小值小于根节点值
         */
        if(minRight != null) {
            if(minRight.val < root.val) {
                int temp = minRight.val;
                minRight.val = root.val;
                root.val = temp;
            }
        }

        /**
         * Case4.左子树或右子树内部两节点发生了交换
         */
        recoverTree(root.left);
        recoverTree(root.right);
    }

    private TreeNode getMinOfBST(TreeNode root) {
        if(root == null) {
            return null;
        }

        TreeNode res = root;

        TreeNode leftMin = getMinOfBST(root.left);
        TreeNode rightMin = getMinOfBST(root.right);

        if(leftMin != null && leftMin.val < res.val) {
            res = leftMin;
        }
        if(rightMin != null && rightMin.val < res.val) {
            res = rightMin;
        }

        return res;
    }

    private TreeNode getMaxOfBST(TreeNode root) {
        if(root == null) {
            return null;
        }

        TreeNode res = root;

        TreeNode leftMax = getMaxOfBST(root.left);
        TreeNode rightMax = getMaxOfBST(root.right);

        if(leftMax != null && leftMax.val > res.val) {
            res = leftMax;
        }
        if(rightMax != null && rightMax.val > res.val) {
            res = rightMax;
        }

        return res;
    }
}
