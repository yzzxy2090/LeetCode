package P_0146_Medium_LRU缓存机制;

import java.util.HashMap;

public class ZuoCache {

    //双向链表节点结构
    public static class Node<K, V> {
        K key;
        V value;

        //指向前驱节点的指针
        Node<K, V> next;
        //指向后继节点的指针
        Node<K, V> pre;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    public static class NodeList<K, V> {

        private Node<K, V> head;
        private Node<K, V> tail;

        public NodeList() {
            this.head = null;
            this.tail = null;
        }

        //向双向链表尾部添加元素
        public void offer(Node<K, V> newNode) {
            if(newNode == null) {
                return;
            }

            //当前链表中没有元素，将newNode设为链表head和tail
            if(this.head == null) {
                this.head = newNode;
                this.tail = newNode;
            }

            //否则将新元素插入链表尾部即tail后面，并将该新节点更新为链表tail
            else {
                this.tail.next = newNode;
                newNode.pre = this.tail;
                this.tail = newNode;
            }
        }

        //删除双向链表头结点，并返回该节点
        public Node<K, V> poll() {
            //链表为空
            if(head == null) {
                return null;
            }

            Node<K, V> res = this.head;

            //链表中只有一个节点
            if(this.head == this.tail) {
                this.head = null;
                this.tail = null;
            }

            //链表中不止一个节点，将头结点下一个节点作为新的头结点
            else {
                this.head = res.next;
                //要删除的节点的next指针清空
                res.next = null;
                //新的头结点pre设置为null
                this.head.pre = null;
            }

            return res;
        }

        //将链表中cur节点移至链表尾部，即将其优先级提至最高
        public void moveToTail(Node<K, V> cur) {
            if(cur == null) {
                return;
            }

            //如果当前cur节点已经是链表尾结点，直接return
            if(this.tail == cur) {
                return;
            }

            //如果当前ucr节点是链表头结点
            if(this.head == cur) {
                this.head = cur.next;
                cur.next = null;
                this.head.pre = null;
            }

            //cur为链表内部节点
            else {
                cur.pre.next = cur.next;
                cur.next.pre = cur.pre;
            }

            //将cur节点接到链表尾部成为新的尾结点
            this.tail.next = cur;
            cur.pre = this.tail;
            cur.next = null;
            this.tail = cur;
        }
    }

    public static class MyCache<K, V> {

        //HashMap存key和对应节点Node的映射关系
        private HashMap<K, Node<K, V>> keyNodeMap;

        //NodeList为窗口结构
        private NodeList<K, V> nodeList;

        //缓存窗口最多容纳threshold个节点
        private int threshold;

        public MyCache(int threshold) {

            if(threshold < 1) {
                throw new RuntimeException("threshold should be more than 0.");
            }

            this.threshold = threshold;

            this.nodeList = new NodeList<K, V>();
            this.keyNodeMap = new HashMap<K, Node<K, V>>();
        }

        //查询缓存操作
        public V get(K key) {
            /**
             * HashMap中存在key值，查出该key对应的Node
             * 将双向链表中该Node调整到链表尾部，因为刚访问过该节点，将该节点优先级调整至最高
             * 返回该Node的value值
             */
            if(keyNodeMap.containsKey(key)) {
                Node<K, V> res = keyNodeMap.get(key);
                nodeList.moveToTail(res);
                return res.value;
            }
            return null;
        }

        //添加/更新缓存中值
        public void put(K key, V value) {

            /**
             * 如果该key值已经存在于HashMap中，则更新对应节点Node的value值
             * 并在双向链表中将该节点移至链表尾部，因为刚访问过该节点，将该节点优先级调制最高
             */
            if(keyNodeMap.containsKey(key)) {
                Node<K, V> node = keyNodeMap.get(key);
                node.value = value;
                nodeList.moveToTail(node);
            }

            /**
             * 如果HashMap中没有这个key，则是插入新缓存值
             */
            else {
                //如果当前缓存结构已到达容量上限，先移除最近最久未使用的缓存值
                if(this.keyNodeMap.size() == threshold) {
                    this.removeLRUCache();
                }

                //将新节点加入缓存结构
                Node<K, V> newNode = new Node(key, value);
                nodeList.offer(newNode);
                keyNodeMap.put(key, newNode);
            }
        }

        //删除缓存结构中最近最近未使用的缓存值
        private void removeLRUCache() {
            Node<K, V> removeNode = nodeList.poll();
            keyNodeMap.remove(removeNode.key);
        }
    }
}
