package P_0637_Easy_二叉树的层平均值;

import java.util.ArrayList;
import java.util.List;

/**
 * 637. Average of Levels in Binary Tree (Easy)
 *
 * DFS深度优先遍历
 */

public class Solution_DFS {
    public List<Double> averageOfLevels(TreeNode root) {
        List<Integer> count = new ArrayList<Integer>();
        List<Double> res = new ArrayList<Double>();
        average(root, 0, res, count);

        //此时res中存的是每层节点值的和，还要对他们取平均数
        for(int i = 0; i < res.size(); i++) {
            res.set(i, res.get(i) / count.get(i));
        }
        return res;
    }

    /**
     *
     * @param cur：表示当前遍历到的节点
     * @param index：表示当前所遍历节点所在的层，如index==0，则表示当前遍历的节点为二叉树的根节点，即第0层
     * @param sum：保存二叉树每层节点值的和
     * @param count：保存二叉树每层节点数量
     */
    private void average(TreeNode cur, int index, List<Double> sum, List<Integer> count) {
        if(cur == null) {
            return;
        }

        /**
         * 若当前index<sum.size()
         *      说明之前已经遍历到过二叉树的第index层，sum.get(index)保存了之前遍历到过该层后本层的节点值之和
         *      那么只要 sum.get(index) += 当前节点值 ，并对count.get(index)加1即可；
         * 若当前index>=sum.size()
         *      说明这是第一次遍历到二叉树第index层
         *      要对本层的sum和count做初始化
         */
        if(index < sum.size()) {
            sum.set(index, sum.get(index) + cur.val);
            count.set(index, count.get(index) + 1);
        } else {
            sum.add(cur.val * 1.0);
            count.add(1);
        }

        //然后递归地遍历当前节点cur的左右孩子节点，即深度优先
        average(cur.left, index + 1, sum, count);
        average(cur.right, index + 1, sum, count);
    }
}
