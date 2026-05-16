package solution;

import java.util.*;

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
        if (nums.length == 1)
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
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, Integer.MAX_VALUE - 1);
        dp[0] = 0;
        for (int i = 1; i <= amount; i++) {
            for (int coin : coins) {
                if (i >= coin)
                    dp[i] = Math.min(dp[i - coin] + 1, dp[i]);
            }
        }
        return dp[amount] == Integer.MAX_VALUE - 1 ? -1 : dp[amount];
    }

    public boolean wordBreak(String s, List<String> wordDict) {
        boolean[] dp = new boolean[s.length() + 1];
        dp[0] = true;
        for (int i = 1; i <= s.length(); i++) {
            for (String word : wordDict) {
                int length = word.length();
                if (i >= length && dp[i - length] && s.substring(i - length, i).equals(word)) {
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[s.length()];
    }

    public int lengthOfLIS(int[] nums) {
        int[] dp = new int[nums.length];
        Arrays.fill(dp, 1);
        int max = dp[0];
        for (int i = 1; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j])
                    dp[i] = Math.max(dp[i], dp[j] + 1);
            }
            max = Math.max(max, dp[i]);
        }
        return max;
    }

    public int maxProduct(int[] nums) {

        int[] dp1 = new int[nums.length];
        dp1[0] = nums[0];
        int max = dp1[0];

        int[] dp2 = new int[nums.length];
        dp2[0] = nums[0];


        for (int i = 1; i < nums.length; i++) {
            dp1[i] = Math.max(dp2[i - 1] * nums[i], Math.max(dp1[i - 1] * nums[i], nums[i]));
            dp2[i] = Math.min(dp2[i - 1] * nums[i], Math.min(dp1[i - 1] * nums[i], nums[i]));
            max = Math.max(max, dp1[i]);
            System.out.println(Arrays.toString(dp1));
            System.out.println(Arrays.toString(dp2));
            System.out.println(max);
            System.out.println("--------------------");
        }
        return max;
    }

    public boolean canPartition(int[] nums) {
        int sum = Arrays.stream(nums).sum();
        if (sum % 2 != 0)
            return false;
        int[] dp = new int[sum / 2];
        dp[0] = 0;
        for (int i = 0; i < nums.length; i++) {
            for (int j = sum / 2; j >= nums[i]; j--) {
                dp[j] = Math.max(dp[j], dp[j - nums[i]] + nums[i]);
            }
        }
        return dp[sum / 2] == sum / 2;
    }

    /* 0-1背包 外层物品n 内层容量target 由大到小
       完全背包 外层物品n 内层容量target 由小到大
       组合外层物品

       排列外层容量

       二维
       dp[i][j] = max(dp[i - 1][j], dp[i - 1][j - weight[i]] + value[i])
       一维
       dp[j] = max(dp[j], dp[j - weight[i]] + value[i]) // j是第二层容量
       求次数
       dp[j] += dp[j - weight[i]]

       初始化 new dp[target + 1]
       求最大 dp[0] = 0;
       求次数 dp[0] = 1;
    */

    public int longestValidParentheses(String s) {
        int maxLen = 0;
        int[] dp = new int[s.length() + 1];
        for (int i = 2; i <= s.length(); i++) {
            char c1 = s.charAt(i - 2);
            char c2 = s.charAt(i - 1);
            // ...()
            if(c1 == '(' && c2 == ')'){
                dp[i] = dp[i - 2] + 2;
            // ...))
            } else if(c1 == ')' && c2 == ')'){
                char c3 = s.charAt(i - 1 - dp[i - 1]);
                if(c3 == '(')
                    dp[i] = dp[i - 2] + 2 + dp[i - 2 - dp[i - 1]];
            }
            maxLen = Math.max(maxLen, dp[i]);
        }
        return maxLen;
    }

}
