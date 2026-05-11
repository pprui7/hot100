package solution;

import org.junit.Test;

import java.util.*;

public class SolutionBackTrack {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        boolean[] used = new boolean[nums.length];
        List<Integer> tmp = new ArrayList<>();
        permuteBackTrack(nums, used, result, tmp);
        return result;
    }

    public void permuteBackTrack(int[] nums, boolean[] used,
                                 List<List<Integer>> result, List<Integer> tmp) {
        if (tmp.size() == nums.length) {
            List<Integer> list = List.copyOf(tmp);
            result.add(list);
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (!used[i]) {
                used[i] = true;
                tmp.add(nums[i]);
                permuteBackTrack(nums, used, result, tmp);
                tmp.remove(tmp.size() - 1);
                used[i] = false;
            }
        }
    }

    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> tmp = new ArrayList<>();
        subsetsBackTrack(nums, 0, result, tmp);
        System.out.println(result);
        return result;
    }

    public void subsetsBackTrack(int[] nums, int start,
                                 List<List<Integer>> result, List<Integer> tmp) {
        result.add(List.copyOf(tmp));
        for (int i = start; i < nums.length; i++) {
            tmp.add(nums[i]);
            subsetsBackTrack(nums, i + 1, result, tmp);
            tmp.remove(tmp.size() - 1);
        }
    }

    public List<List<Integer>> combination(int[] candidates, int k) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> tmp = new ArrayList<>();
        combinationBackTrack(candidates, k, 0, result, tmp);
        return result;
    }

    private void combinationBackTrack(int[] candidates, int k, int start,
                                      List<List<Integer>> result, List<Integer> tmp) {
        if (tmp.size() == k) {
            result.add(List.copyOf(tmp));
            return;
        }
        for (int i = start; i <= candidates.length + tmp.size() - k; i++) {
            tmp.add(candidates[i]);
            combinationBackTrack(candidates, k, i + 1, result, tmp);
            tmp.remove(tmp.size() - 1);
        }
    }

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> tmp = new ArrayList<>();
        Arrays.sort(candidates);
        combinationSumBackTrack(candidates, target, 0, 0, result, tmp);
        return result;
    }

    private void combinationSumBackTrack(int[] candidates, int target, int sum, int start,
                                         List<List<Integer>> result, List<Integer> tmp) {
        if (sum == target) {
            result.add(List.copyOf(tmp));
            return;
        }
        for (int i = start; i < candidates.length && sum + candidates[i] <= target; i++) {
            tmp.add(candidates[i]);
            combinationSumBackTrack(candidates, target, sum + candidates[i], i, result, tmp);
            tmp.remove(tmp.size() - 1);
        }
    }


    Map<Character, String> map;

    public List<String> letterCombinations(String digits) {
        StringBuilder tmp = new StringBuilder();
        List<String> result = new ArrayList<>();
        map = new HashMap<>();
        map.put('2', "abc");
        map.put('3', "def");
        map.put('4', "ghi");
        map.put('5', "jkl");
        map.put('6', "mno");
        map.put('7', "pqrs");
        map.put('8', "tuv");
        map.put('9', "wxyz");
        letterCombinationsBackTrack(digits, 0, result, tmp);
        return result;
    }

    private void letterCombinationsBackTrack(String digits, int start,
                                             List<String> result, StringBuilder tmp) {
        if (tmp.length() == digits.length()) {
            result.add(new String(tmp));
            return;
        }

        char c = digits.charAt(start);
        String s = map.get(c);
        for (int i = 0; i < s.length(); i++) {
            tmp.append(s.charAt(i));
            letterCombinationsBackTrack(digits, start + 1, result, tmp);
            tmp.delete(tmp.length() - 1, tmp.length());
        }
    }

    public List<String> generateParenthesis(int n) {
        List<String> result = new ArrayList<>();
        StringBuilder tmp = new StringBuilder();
        generateParenthesisBackTrack(n, 0, result, tmp);
        return result;
    }

    private void generateParenthesisBackTrack(int n, int count,
                                              List<String> result, StringBuilder tmp) {
        System.out.println(tmp);
        System.out.println(count);
        if (tmp.length() == n * 2) {
            result.add(new String(tmp));
            return;
        }

        if (count < n) {
            tmp.append('(');
            generateParenthesisBackTrack(n, count + 1, result, tmp);
            tmp.deleteCharAt(tmp.length() - 1);
        }
        if (tmp.length() - count < count) {
            tmp.append(')');
            generateParenthesisBackTrack(n, count, result, tmp);
            tmp.deleteCharAt(tmp.length() - 1);
        }
    }

    public boolean exist(char[][] board, String word) {
        boolean[][] used = new boolean[board.length][board[0].length];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (existBackTrack(board, word, used, i, j, 0))
                    return true;
            }
        }
        return false;
    }

    public boolean existBackTrack(char[][] board, String word, boolean[][] used,
                                  int x, int y, int length) {
        if (length == word.length())
            return true;
        if (x >= board.length || x < 0 || y >= board[0].length || y < 0 || used[x][y])
            return false;
        if (word.charAt(length) == board[x][y]) {
            used[x][y] = true;
            boolean flag = existBackTrack(board, word, used, x + 1, y, length + 1);
            flag = flag || existBackTrack(board, word, used, x - 1, y, length + 1);
            flag = flag || existBackTrack(board, word, used, x, y + 1, length + 1);
            flag = flag || existBackTrack(board, word, used, x, y - 1, length + 1);
            used[x][y] = false;
            return flag;
        }
        return false;
    }

    public List<List<String>> partition(String s) {
        List<List<String>> result = new ArrayList<>();
        List<String> tmp = new ArrayList<>();
        boolean[][] flag = getBoolean(s);
        System.out.println(Arrays.deepToString(flag));
        partitionBackTrack(s, 0, flag, result, tmp);
        return result;
    }

    public void partitionBackTrack(String s, int start, boolean[][] flag,
                                   List<List<String>> result, List<String> tmp) {
        if (start == s.length()) {
            result.add(List.copyOf(tmp));
            System.out.println(tmp);
            return;
        }
        int k = 0;
        for (int i = start; i < s.length(); i++) {
            while (i + k < s.length()) {
                if (k == 0 || (k == 1 && s.charAt(i) == s.charAt(i + k))
                        || (k > 1 && s.charAt(i) == s.charAt(i + k) && flag[i + 1][i + k - 1])) {
                    tmp.add(s.substring(i, i + k + 1));
                    System.out.println(i + " " + i + k);
                    partitionBackTrack(s, k + 1, flag, result, tmp);
                    tmp.remove(tmp.size() - 1);
                }
                k++;
            }
        }
    }

    private boolean[][] getBoolean(String s) {
        boolean[][] flag = new boolean[s.length()][s.length()];

        for (int k = 0; k < s.length(); k++) {
            for (int i = 0; i + k < s.length(); i++) {
                if (k == 0 || (k == 1 && s.charAt(i) == s.charAt(i + k))
                        || (k > 1 && s.charAt(i) == s.charAt(i + k) && flag[i + 1][i + k - 1])) {
                    flag[i][i + k] = true;
                }
            }
        }
        return flag;
    }


    public List<List<String>> solveNQueens(int n) {
        char[][] tmp = new char[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                tmp[i][j] = '.';
            }
        }
        List<List<String>> board = new ArrayList<>();
        solveNQueensBackTrack(n, new int[n], new int[n], 0, tmp, board);
        return board;
    }

    public void solveNQueensBackTrack(int n, int[] row, int[] col, int count,
                                      char[][] tmp, List<List<String>> board) {
        if (n == count) {
            List<String> answer = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                String s = new String(tmp[i]);
                answer.add(s);
            }
            board.add(answer);
            return;
        }

        for (int j = 0; j < n; j++) {
            if (row[count] == 1 || col[j] == 1)
                continue;
            int x = count;
            int y = j;
            boolean flag = true;
            while (x >= 0 && y >= 0) {
                if (tmp[x][y] == 'Q') {
                    flag = false;
                    break;
                }
                x--;
                y--;
            }
            x = count;
            y = j;
            while (x >= 0 && y < n) {
                if (tmp[x][y] == 'Q') {
                    flag = false;
                    break;
                }
                x--;
                y++;
            }
            x = count;
            y = j;
            while (x < n && y >= 0) {
                if (tmp[x][y] == 'Q') {
                    flag = false;
                    break;
                }
                x++;
                y--;
            }
            x = count;
            y = j;
            while (x < n && y < n) {
                if (tmp[x][y] == 'Q') {
                    flag = false;
                    break;
                }
                x++;
                y++;
            }
            if (flag) {
                tmp[count][j] = 'Q';
                row[count] = 1;
                col[j] = 1;
                solveNQueensBackTrack(n, row, col, count + 1, tmp, board);
                tmp[count][j] = '.';
                row[count] = 0;
                col[j] = 0;
            }

        }
    }
}

