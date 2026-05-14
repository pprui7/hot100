package solution;

public class Heap{
    int[] heap;
    int capacity;
    int size;
    public Heap(){
        heap = new int[16];
        capacity = 16;
        size = 0;
    }
    public Heap(int[] array){
        heap = new int[array.length * 2];
        System.arraycopy(array, 0, heap, 0, array.length);
        capacity = array.length * 2;
        size = array.length;
        for (int i = size/2 - 1; i >= 0 ; i--) {
            down(i);
        }
    }
    public void push(int e){
        if(capacity == size){
            int[] temp = new int[heap.length * 2];
            System.arraycopy(heap, 0, temp, 0, heap.length);
            heap = temp;
            capacity = heap.length;
        }
        heap[size]=e;
        up(size);
        size++;
    }
    public int pop(){
        int max = heap[0];
        heap[0] = heap[size - 1];
        size--;
        down(0);
        return max;
    }
    public int peek(){
        return heap[0];
    }

    private int parent(int index){
        return (index - 1)/2;
    }
    private int leftChild(int index){
        return 2 * index + 1;
    }
    private int rightChild(int index){
        return 2 * index + 2;
    }
    private void swap(int i, int j) {
        int temp = heap[i];
        heap[i] = heap[j];
        heap[j] = temp;
    }
    private void down(int parent){
        int max = parent;
        int left = leftChild(parent);
        int right = rightChild(parent);
        if(left < size && heap[left] > heap[max])
            max= left;
        if(right < size && heap[right] > heap[max])
            max= right;
        if(max != parent){
            swap(parent, max);
            down(max);
        }
    }
    private void up(int child){
        int parent = parent(child);
        while(parent != 0 && heap[child] > heap[parent]){
            swap(child, parent);
            child = parent;
        }
    }

}
