package P_0094_Medium_二叉树的中序遍历;

import java.util.ArrayList;
import java.util.List;

/**
 * 中序遍历，就是遍历完左子树，然后遍历根节点。
 * 如果我们把当前根节点存起来，然后遍历左子树，左子树遍历完以后回到当前根节点就可以了
 *
 * 我们知道，左子树最后遍历的节点一定是一个叶子节点，它的左右孩子都是 null
 * 当前遍历到cur节点时，我们找到其左子树cur.left的最右孩子记作last
 * 该last节点是cur的左子树最后访问到的节点，访问完last就应该访问cur节点
 * last.right原本为null
 * 我们现在将last.right指向cur，即建立线索，这样的话我们就不需要额外空间了。
 * 这样做，遍历完当前左子树，就可以直接回到根节点了。
 *
 * 当然如果当前根节点左子树为空，那么我们只需要保存根节点的值，然后考虑右子树即可。
 *
 * 所以总体思想就是：记当前遍历的节点为 cur。
 *
 * 1、cur.left 为 null，保存 cur 的值，更新 cur = cur.right
 *
 * 2、cur.left 不为 null，找到 cur.left 这颗子树最右边的节点记做 last
 *
 *  2.1 last.right 为 null，那么将 last.right = cur，cur继续向左子树遍历，更新 cur = cur.left
 *
 *  2.2 last.right 不为 null，说明之前已经访问过，第二次来到这里，表明当前子树遍历完成，保存 cur 的值，cur向右子树遍历，更新 cur = cur.right
 */

public class Solution_Morris {
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<Integer>();

        TreeNode cur = root;

        while(cur != null) {
            //Case1: cur.left 为 null (即已遍历至当前子树最左边)，保存 cur 的值，更新 cur = cur.right
            if(cur.left == null) {
                res.add(cur.val);
                cur = cur.right;
            }
            //Case2: cur.left 不为 null (即当前子树尚未遍历至最左边)，找到 cur.left 这颗子树最右边的节点记做 last
            else {

                TreeNode last = cur.left;
                //last.right==cur时说明已经建立过线索了
                while(last.right != null && last.right != cur) {
                    last = last.right;
                }

                //Case2-1: last.right 为 null，那么构建线索 last.right = cur，cur继续往左孩子遍历 cur = cur.left
                if(last.right == null) {
                    last.right = cur;
                    cur = cur.left;
                }

                //Case2-2: last.right 不为 null，说明之前已经构建好线索，第二次来到这里，表明cur的当前子树遍历完成，现在应该访问cur，保存 cur 的值，cur往右孩子遍历 cur = cur.right
                if(last.right == cur) {
                    last.right = null;//用过之后把线索删掉
                    res.add(cur.val);
                    cur = cur.right;
                }
            }
        }
        return res;
    }
}
