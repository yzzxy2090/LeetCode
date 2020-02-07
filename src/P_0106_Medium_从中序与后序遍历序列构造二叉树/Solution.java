package P_0106_Medium_从中序与后序遍历序列构造二叉树;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * 106. Construct Binary Tree from Inorder and Postorder Traversal (Medium)
 *
 * 采用和105题一样的思路
 *
 * 后续遍历数组最后一个元素为根节点，从后往前顺序为根节点->右孩子->左孩子
 * 这里为了方便处理，将后续遍历数组进行了逆转，即逆转后从前往后顺序为根节点->右孩子->左孩子
 *
 * 这种情况下，应先构造右子树，再构造左子树
 */

public class Solution {
    int post_index = 0;

    public TreeNode buildTree(int[] inorder, int[] postorder) {
        Map<Integer, Integer> in_map = new HashMap<Integer, Integer>();
        for(int i=0; i<inorder.length; i++) {
            in_map.put(inorder[i], i);
        }

        reverseArr(postorder);

        return process(in_map, postorder, 0, inorder.length);
    }

    private void reverseArr(int[] arr) {
        if(arr == null || arr.length < 2) {
            return;
        }

        int low = 0, high = arr.length - 1;
        while(low < high) {
            swap(arr, low, high);
            low++;
            high--;
        }
    }

    private void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    private TreeNode process(Map<Integer, Integer> in_map, int[] postorder, int start, int end) {
        if(start == end) {
            return null;
        }

        int root_val = postorder[post_index];
        TreeNode root = new TreeNode(root_val);

        int index = in_map.get(root_val);

        //不要忘记将post_index指针向后移动
        post_index++;

        root.right = process(in_map, postorder, index + 1, end);
        root.left = process(in_map, postorder, start, index);

        return root;
    }
}
