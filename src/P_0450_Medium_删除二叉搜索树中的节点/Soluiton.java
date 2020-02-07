package P_0450_Medium_删除二叉搜索树中的节点;

public class Soluiton {
    public TreeNode deleteNode(TreeNode root, int key) {
        if(root == null) {
            return null;
        }

        return processor(root, Integer.valueOf(key));
    }

    private TreeNode processor(TreeNode root, Integer key) {
        TreeNode cur = root;
        if(key > cur.val) {
            cur.right = deleteNode(cur.right, key);
        } else if(key < cur.val) {
            cur.left = deleteNode(cur.left, key);
        } else {//即root.val==key时，要删除root节点
            if(cur.left == null && cur.right == null) {
                cur = null;
            } else if(cur.right != null) {//有右子树时(或左右子树都存在时)，用当前节点的后继节点替换当前节点
                cur.val = successor(cur);
                cur.right = deleteNode(cur.right, cur.val);//递归地删除右子树中值为cur.val的节点
            } else {//没有右子树时，用当前节点的前驱节点替换当前节点
                cur.val = predecessor(cur);
                cur.left = deleteNode(cur.left, cur.val);//递归地删除左子树中值为cur.val的节点
            }
        }
        return root;
    }

    //cur有左子树时返回cur的前驱节点值，即cur左子树的最右孩子
    private Integer predecessor(TreeNode cur) {
        if(cur.left == null) {
            return null;
        }

        cur = cur.left;
        while(cur.right != null) {
            cur = cur.right;
        }

        return Integer.valueOf(cur.val);
    }

    //cur有右子树时返回cur的后继节点值，即cur右子树的最左孩子
    private Integer successor(TreeNode cur) {
        if(cur.right == null) {
            return null;
        }

        cur = cur.right;
        while(cur.left != null) {
            cur = cur.left;
        }

        return Integer.valueOf(cur.val);
    }
}
