package solution;

import entity.ListDoubleNode;

import java.util.HashMap;
import java.util.Map;


public class LRUCache {

    private int capacity = 0;
    private final ListDoubleNode head;
    private final ListDoubleNode tail;
    private final Map<Integer, ListDoubleNode> map;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.head = new ListDoubleNode();
        this.tail = new ListDoubleNode();
        head.next = tail;
        tail.pre = head;
        this.map = new HashMap<>();
    }

    public int get(int key) {
        if(map.containsKey(key)){
            ListDoubleNode node = map.get(key);
            moveToHead(node);
            return node.val;
        }
        return -1;
    }


    public void put(int key, int value) {
        if(map.containsKey(key)){
            ListDoubleNode Node = map.get(key);
            Node.val = value;
            moveToHead(Node);
            return;
        }
        ListDoubleNode newNode = new ListDoubleNode(key, value);
        addToHead(newNode);
        map.put(key, newNode);
        if(map.size() > capacity){
            ListDoubleNode removeNode = removeTail();
            map.remove(removeNode.key);
        }
    }

    private void moveToHead(ListDoubleNode node) {
        node.pre.next = node.next;
        node.next.pre = node.pre;
        node.pre = head;
        node.next = head.next;
        head.next.pre = node;
        head.next = node;
    }
    private void addToHead(ListDoubleNode node) {
        node.pre = head;
        node.next = head.next;
        head.next.pre = node;
        head.next = node;
    }
    private ListDoubleNode removeTail() {
        ListDoubleNode node = tail.pre;
        node.pre.next = tail;
        tail.pre = node.pre;
        return node;
    }

}
