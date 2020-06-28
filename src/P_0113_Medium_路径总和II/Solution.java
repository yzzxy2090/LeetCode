package P_0113_Medium_路径总和II;

import java.util.ArrayList;
import java.util.List;

public class Solution {
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<List<Integer>> res = new ArrayList<>();
        if(root == null) {
            return res;
        }

        dfs(root, sum, new ArrayList<Integer>(), res);

        return res;
    }

    private void dfs(TreeNode root, int target, List<Integer> path, List<List<Integer>> res) {

        if(root == null) {
            return;
        }

        path.add(root.val);

        /**
         * 若当前搜索到的是叶节点，判断该节点值是否就等于target
         * 是的话就加入结果集
         *
         * 注意，List<Integer> path传的是引用，所有的path对象是同一个，这里如果直接加入res的是path
         * 则后面对path的修改会改变res中的结果
         * 要新建一个List来保存当前的path
         */
        if(root.left == null && root.right == null && target == root.val) {
            res.add(new ArrayList<Integer>(path));
        }

        dfs(root.left, target - root.val, path, res);
        dfs(root.right, target - root.val, path, res);

        //左右子树递归回来后再将path中的当前节点删掉，以实现回溯
        path.remove(path.size() - 1);
    }
}
