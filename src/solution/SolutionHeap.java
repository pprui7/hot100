package solution;

import java.util.*;

public class SolutionHeap {
    public int findKthLargest(int[] nums, int k) {
//        int n = nums.length;
//        return quickselect(nums, 0, n - 1, k);
        Heap heap = new Heap(nums);
        while(k > 1){
            heap.pop();
            k--;
        }
        return heap.peek();
    }
    private int quickselect(int[] nums, int left, int right, int k) {
        if (left == right)
            return nums[left];
        int x = nums[left];
        int i = left - 1;
        int j = right + 1;
        while (i < j) {
            do i++; while (nums[i] > x);
            do j--; while (nums[j] < x);
            if (i < j)
                swap(nums, i, j);
        }
        if (j < k)
            return quickselect(nums, j + 1, right, k);
        else
            return quickselect(nums, left, j, k);
    }
    private void swap(int[] nums, int x, int right) {
        int temp = nums[x];
        nums[x] = nums[right];
        nums[right] = temp;
    }

    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        PriorityQueue<Map.Entry<Integer, Integer>> priorityQueue
                = new PriorityQueue<>((o1, o2) -> o2.getValue() - o1.getValue());
        map.entrySet().forEach(priorityQueue::offer);
        int[] result = new int[k];
        while(k > 0){
            result[--k] = priorityQueue.poll().getKey();
        }
        return result;
    }

    class MedianFinder {
        PriorityQueue<Integer> queMin;
        PriorityQueue<Integer> queMax;

        public MedianFinder() {
            queMin = new PriorityQueue<>(((o1, o2) -> o2 - o1));
            queMax = new PriorityQueue<>(((o1, o2) -> o1 - o2));
        }

        public void addNum(int num) {
            if(queMin.isEmpty() || num <= queMin.peek()){
                queMin.offer(num);
                if(queMin.size() - 1 > queMax.size())
                    queMax.offer(queMin.poll());
            }else {
                queMax.offer(num);
                if(queMax.size() > queMin.size())
                    queMin.offer(queMax.poll());
            }
        }

        public double findMedian() {
            if(queMin.size() > queMax.size())
                return queMin.peek();
            return (double) (queMin.peek() + queMax.peek()) /2;
        }
    }



}
