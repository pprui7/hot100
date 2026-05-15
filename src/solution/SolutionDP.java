package solution;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SolutionDP {
    public int climbStairs(int n) {
        int n1 = 1;  // 第0层初始值
        int n2 = 1;  // 上第1层初始值
        for (int i = 2; i <= n; i++) {
            int temp = n2;
            n2 += n1;
            n1 = temp;
        }
        return n2;
    }

    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> result = new ArrayList<>();
        for (int i = 0; i < numRows; i++) {
            int count = i + 1;
            List<Integer> temp = new ArrayList<>();
            for (int j = 0; j < count; j++) {
                if (j == 0 || j == count - 1)
                    temp.add(1);
                else
                    temp.add(result.get(i - 1).get(j - 1) + result.get(i - 1).get(j));
            }
            result.add(temp);
        }
        return result;
    }

    public int rob(int[] nums) {
        if(nums.length == 1)
            return nums[0];
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);
        for (int i = 2; i < nums.length; i++) {
            dp[i] = Math.max(dp[i - 2] + nums[i], dp[i - 1]);
        }
        return dp[nums.length - 1];
    }

    public int numSquares(int n) {
        int[] dp = new int[n + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= Math.sqrt(i); j++) {
                dp[i] = Math.min(dp[i], dp[i - j * j] + 1);
            }
        }
        return dp[n];
    }


    public int coinChange(int[] coins, int amount) {
        return 0;
    }

    public boolean wordBreak(String s, List<String> wordDict) {
        return false;
    }

    public int lengthOfLIS(int[] nums) {
        return 0;
    }

    public int maxProduct(int[] nums) {
        return 0;
    }

    public boolean canPartition(int[] nums) {
        return false;
    }

    public int longestValidParentheses(String s) {
        return 0;
    }
}
