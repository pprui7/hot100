package solutionTest;

import org.junit.Test;
import solution.SolutionDP;

import java.util.Arrays;
import java.util.List;

public class SolutionDPTest {
    private final SolutionDP solutionDP = new SolutionDP();

    @Test
    public void climbStairsTest() {
        int n = 3;
        int actual = solutionDP.climbStairs(n);
        System.out.println("70. 爬楼梯");
        System.out.println("输入: n = " + n);
        System.out.println("输出: " + actual);
    }

    @Test
    public void generateTest() {
        int numRows = 5;
        List<List<Integer>> actual = solutionDP.generate(numRows);
        System.out.println("118. 杨辉三角");
        System.out.println("输入: numRows = " + numRows);
        System.out.println("输出: " + actual);
    }

    @Test
    public void robTest() {
        int[] nums = {2, 7, 9, 3, 1};
        int actual = solutionDP.rob(nums);
        System.out.println("198. 打家劫舍");
        System.out.println("输入: nums = " + Arrays.toString(nums));
        System.out.println("输出: " + actual);
    }

    @Test
    public void numSquaresTest() {
        int n = 12;
        int actual = solutionDP.numSquares(n);
        System.out.println("279. 完全平方数");
        System.out.println("输入: n = " + n);
        System.out.println("输出: " + actual);
    }

    @Test
    public void coinChangeTest() {
        int[] coins = {1, 2, 5};
        int amount = 11;
        int actual = solutionDP.coinChange(coins, amount);
        System.out.println("322. 零钱兑换");
        System.out.println("输入: coins = " + Arrays.toString(coins) + ", amount = " + amount);
        System.out.println("输出: " + actual);
    }

    @Test
    public void wordBreakTest() {
        String s = "leetcode";
        List<String> wordDict = Arrays.asList("leet", "code");
        boolean actual = solutionDP.wordBreak(s, wordDict);
        System.out.println("139. 单词拆分");
        System.out.println("输入: s = " + s + ", wordDict = " + wordDict);
        System.out.println("输出: " + actual);
    }

    @Test
    public void lengthOfLISTest() {
        int[] nums = {10, 9, 2, 5, 3, 7, 101, 18};
        int actual = solutionDP.lengthOfLIS(nums);
        System.out.println("300. 最长递增子序列");
        System.out.println("输入: nums = " + Arrays.toString(nums));
        System.out.println("输出: " + actual);
    }

    @Test
    public void maxProductTest() {
        int[] nums = {2, 3, -2, 4};
        int actual = solutionDP.maxProduct(nums);
        System.out.println("152. 乘积最大子数组");
        System.out.println("输入: nums = " + Arrays.toString(nums));
        System.out.println("输出: " + actual);
    }

    @Test
    public void canPartitionTest() {
        int[] nums = {1, 5, 11, 5};
        boolean actual = solutionDP.canPartition(nums);
        System.out.println("416. 分割等和子集");
        System.out.println("输入: nums = " + Arrays.toString(nums));
        System.out.println("输出: " + actual);
    }

    @Test
    public void longestValidParenthesesTest() {
        String s = ")()())";
        int actual = solutionDP.longestValidParentheses(s);
        System.out.println("32. 最长有效括号");
        System.out.println("输入: s = " + s);
        System.out.println("输出: " + actual);
    }
}
