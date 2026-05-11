package entity;

public class ListDoubleNode{
    public int key;
    public int val;
    public ListDoubleNode pre;
    public ListDoubleNode next;
    public ListDoubleNode() {}
    public ListDoubleNode(int key, int val) {
        this.key = key;
        this.val = val;
        this.pre = null;
        this.next = null;
    }
}
