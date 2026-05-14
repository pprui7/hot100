package solutionTest;

import org.junit.Test;
import solution.SolutionHeap;

public class SolutionHeapTest {
    @Test
    public void findKthLargestTest(){
        SolutionHeap solutionHeap = new SolutionHeap();
        int[] nums = {3,2,3,1,2,4,5,5,6};
        System.out.println(solutionHeap.findKthLargest(nums, 4));
    }
}
