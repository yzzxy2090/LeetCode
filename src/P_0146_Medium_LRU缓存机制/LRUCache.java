package P_0146_Medium_LRU缓存机制;

import java.util.HashMap;

public class LRUCache {

    //双向链表节点
    class Node {
        int key;
        int value;
        Node pre = null;
        Node next = null;

        public Node() {}

        public Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    //将新节点加在链表尾部
    private void offerTail(Node node) {
        if(null == node) {
            return;
        }

        Node originTail = dummyTail.pre;
        originTail.next = node;
        dummyTail.pre = node;
        node.pre = originTail;
        node.next = dummyTail;
    }

    //从链表中删除node节点
    private void removeNode(Node node){
        if(null == node) {
            return;
        }

        Node originPre = node.pre;
        Node originNext = node.next;
        node.next = null;
        node.pre = null;
        originPre.next = originNext;
        originNext.pre = originPre;
    }

    //删除队头节点
    private Node pollHead() {
        if(size <= 0) {
            return null;
        }

        Node deleteNode = dummyHead.next;
        removeNode(deleteNode);

        return deleteNode;
    }

    //node最近刚被操作过，优先级最高，移至链表尾，即node此时最晚被缓存淘汰
    private void moveToTail(Node cur){
        Node originTail = dummyTail.pre;
        if(cur == originTail) {
            return;
        }

        //先从链表中删除cur节点，再将cur添加到队尾
        removeNode(cur);
        offerTail(cur);
    }


    //当前缓存结构中的缓存值数量
    private int size = 0;
    //缓存结构最大容量
    private int capacity;

    //双向链表头尾辅助节点，不存值，只为简化null的处理
    private Node dummyHead;
    private Node dummyTail;

    //HashMap存key和Node内存地址的映射关系，方便通过key找到Node内存地址，从而直接修改Node指针而修改双向链表结构
    private HashMap<Integer, Node> keyNodeMap = new HashMap<Integer, Node>();

    public LRUCache(int capacity) {
        this.capacity = capacity;

        dummyHead = new Node();
        dummyTail = new Node();

        dummyHead.next = dummyTail;
        dummyTail.pre = dummyHead;
    }

    //从缓存中查找key对应的值
    public int get(int key) {
        Node node = keyNodeMap.get(key);

        if(null == node) {
            return -1;
        }

        moveToTail(node);
        return node.value;
    }

    //向缓存中新建或更新值
    public void put(int key, int value) {
        Node node = keyNodeMap.get(key);

        //缓存中无此key，插入新值
        if(node == null) {
            //当前缓存结构size达到最大容量，删掉LRU缓存
            if(size >= capacity) {
                removeLRU();
                size--;
            }

            //插入新节点
            Node newNode = new Node(key, value);
            offerTail(newNode);
            keyNodeMap.put(key, newNode);

            size++;
        }

        //否则更新此key对应的node，由于刚访问了该节点，此时其优先级最高，并将该节点移至队尾
        else {
            node.value = value;
            moveToTail(node);
        }

    }

    //删除缓存结构中最近最久未使用元素
    private void removeLRU() {
        if(size < 1) {
            return;
        }

        //最近最久未使用元素即为双向链表的头结点
        Node lruNode = pollHead();
        keyNodeMap.remove(lruNode.key);
    }

}
