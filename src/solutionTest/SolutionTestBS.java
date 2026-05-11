package solutionTest;

import org.junit.Test;
import solution.SolutionBS;
import solution.SolutionBackTrack;

public class SolutionTestBS {
    @Test
    public void searchTest() {
        SolutionBS solutionBS = new SolutionBS();
        int[] nums = {1, 3};
        System.out.println(solutionBS.search(nums, 2));
    }
}
