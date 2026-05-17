package solution;

import java.util.Arrays;

public class SolutionDP2 {
    public int uniquePaths(int m, int n) {
        int[][] dp = new int[m][n];
        dp[0][0] = 1;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i - 1 >= 0)
                    dp[i][j] += dp[i - 1][j];
                if (j - 1 >= 0)
                    dp[i][j] += dp[i][j - 1];
            }
        }
        return dp[m - 1][n - 1];
    }

    public int minPathSum(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[][] dp = new int[m][n];
        for (int i = 0; i < m; i++) {
            Arrays.fill(dp[i], Integer.MAX_VALUE);
        }
        dp[0][0] = grid[0][0];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i - 1 >= 0)
                    dp[i][j] = Math.min(dp[i - 1][j] + grid[i][j], dp[i][j]);
                if (j - 1 >= 0)
                    dp[i][j] = Math.min(dp[i][j - 1] + grid[i][j], dp[i][j]);
            }
        }
        return dp[m - 1][n - 1];
    }

    public String longestPalindrome(String s) {
        boolean[][] dp = new boolean[s.length()][s.length()];
        for (int i = 0; i < s.length(); i++) {
            dp[i][i] = true;
        }
        int left = 0;
        int right = 0;
        int len = 0;
        for (int i = 0; i < s.length(); i++) {
            for (int j = 0; j < i; j++) {
                boolean flag = s.charAt(i) == s.charAt(j);
                if ((i - j == 1 && flag)
                        || (i - j > 1 && flag && dp[j + 1][i - 1]))
                    dp[j][i] = true;
                if (dp[j][i] && i - j + 1 > len) {
                    left = j;
                    right = i;
                    len = i - j + 1;
                }
            }
        }
        return s.substring(left, right + 1);
    }

    public int longestCommonSubsequence(String text1, String text2) {
        int m = text1.length();
        int n = text2.length();
        int[][] dp = new int[m + 1][n + 1];
        int max = 0;
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (text1.charAt(i - 1) == text2.charAt(j - 1))
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                dp[i][j] = Math.max(dp[i][j], Math.max(dp[i][j - 1], dp[i - 1][j]));
                max = Math.max(max, dp[i][j]);
            }
        }
        return max;
    }

    public int minDistance(String word1, String word2) {
        int m = word1.length();
        int n = word2.length();
        int[][] dp = new int[m + 1][n + 1];
        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                dp[i][j] = Integer.MAX_VALUE;
            }
        }
        for (int i = 0; i <= m; i++) {
            dp[i][0] = i;
        }
        for (int i = 0; i <= n; i++) {
            dp[0][i] = i;
        }
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1))
                    dp[i][j] = dp[i - 1][j - 1];
                else
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                dp[i][j] = Math.min(Math.min(dp[i - 1][j], dp[i][j - 1]) + 1, dp[i][j]);
            }
        }
        return dp[m][n];
    }
}
