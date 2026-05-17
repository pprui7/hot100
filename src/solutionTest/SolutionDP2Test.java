package solutionTest;

import org.junit.Test;
import solution.SolutionDP2;

public class SolutionDP2Test {
    private final SolutionDP2 solutionDP2 = new SolutionDP2();

    @Test
    public void uniquePathsTest() {
        int m = 3;
        int n = 7;
        System.out.println(solutionDP2.uniquePaths(m, n));
    }

    @Test
    public void minPathSumTest() {
        int[][] grid = {{1, 3, 1}, {1, 5, 1}, {4, 2, 1}};
        System.out.println(solutionDP2.minPathSum(grid));
    }

    @Test
    public void longestPalindromeTest() {
        String s = "aaaa";
        System.out.println(solutionDP2.longestPalindrome(s));
    }

    @Test
    public void longestCommonSubsequenceTest() {
        String text1 = "abcde";
        String text2 = "ace";
        System.out.println(solutionDP2.longestCommonSubsequence(text1, text2));
    }

    @Test
    public void minDistanceTest() {
        String word1 = "horse";
        String word2 = "ros";
        System.out.println(solutionDP2.minDistance(word1, word2));
    }
}
