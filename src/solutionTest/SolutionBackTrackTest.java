package solutionTest;


import org.junit.Test;
import solution.SolutionBackTrack;

public class SolutionBackTrackTest {

    @Test
    public void combinationSumTest() {
        SolutionBackTrack solutionBackTrack = new SolutionBackTrack();
        int[] candidates = {2,3,6,7};
        System.out.println(solutionBackTrack.combinationSum(candidates, 7));
    }
    @Test
    public void existTest() {
        SolutionBackTrack solutionBackTrack = new SolutionBackTrack();
        char[][] board = {{'A','B','C','E'},{'S','F','C','S'},{'A','D','E','E'}};
        System.out.println(solutionBackTrack.exist(board, "SEE"));
    }

}
