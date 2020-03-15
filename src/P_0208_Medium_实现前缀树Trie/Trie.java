package P_0208_Medium_实现前缀树Trie;

import java.util.HashMap;
import java.util.Map;

public class Trie {

    private TrieNode root;

    public class TrieNode {
        //以当前节点为前缀的字符串有path个
        int path;

        //以当前节点为截止的字符串有end个
        int end;

        //多叉树，可能包含多个next节点
        TrieNode[] next;

        boolean isEnd = false;

        //Map<Character, TrieNode> dict;

        public TrieNode() {
            path = 0;
            end = 0;

            //作为字符串前缀树节点，这里假设所有字符都是'a'到'z'一共有26种字母
            next = new TrieNode[26];

            //dict = new HashMap<Character, TrieNode>();
        }
    }

    public Trie() {

        root = new TrieNode();

    }

    /** Inserts a word into the trie. */
    public void insert(String word) {
        if(word == null) {
            return;
        }

        char[] arr = word.toCharArray();

        //从根节点开始遍历
        TrieNode cur = root;
        //index表示当前节点26个桶中的其中一个，index范围为0-25，分别对应a-z这26个字母
        int index = 0;

        for(int i=0; i<arr.length; i++) {
            //得到字符串当前位字符，如当前arr[i]='a'，index就为0，cur.next[index]指的就是cur节点向'a'走的路径
            index = arr[i] - 'a';

            //cur节点上还没有这条路径就新建该条路径
            if(cur.next[index] == null) {
                cur.next[index] = new TrieNode();
            }

            //cur向下一层节点移动
            cur = cur.next[index];
            //经过cur的路径增长1
            cur.path++;
        }

        //word插入完成后，此时cur为word最后一个字符的路径的末尾，树中以该字符为末尾的字符串数增长1
        cur.end++;
        cur.isEnd = true;
    }

    /** Returns if the word is in the trie. */
    public boolean search(String word) {
        if(word == null) {
            return false;
        }

        char[] arr = word.toCharArray();
        TrieNode cur = root;
        int index = 0;

        for(int i=0; i<arr.length; i++) {
            index = arr[i] - 'a';

            if(cur.next[index] == null) {
                return false;
            }

            cur = cur.next[index];
        }
        return cur.isEnd;
    }

    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String prefix) {
        if(prefix == null) {
            return true;
        }

        char[] arr = prefix.toCharArray();
        int index = 0;
        TrieNode cur = root;

        for(int i=0; i<arr.length; i++) {
            index = arr[i] - 'a';

            if(cur.next[index] == null) {
                return false;
            }

            cur = cur.next[index];
        }
        return true;
    }

    public void delete(String word) {
        //存在才delete
        if(word == null || !search(word)) {
            return;
        }

        char[] arr = word.toCharArray();
        int index = 0;
        TrieNode cur = root;

        for(int i=0; i<arr.length; i++) {
            index = arr[i] - 'a';

            //要删当前字符，把该字符的路径减1
            cur.next[index].path--;

            //如果这次删掉该字符后，经过该字符的路径变为0，那么从该路径开始往后面的所有字符路径都要被删掉
            if(cur.next[index].path == 0) {
                cur.next[index] = null;
                return;
            }

            //否则继续向下一层执行
            cur = cur.next[index];
        }
        //删完该word后，该word路径结尾参数减1
        cur.end--;
    }
}
