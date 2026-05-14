package solution;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class SolutionGreedy {
    public int maxProfit(int[] prices) {
        int min = Integer.MAX_VALUE;
        int maxProfit = 0;
        for (int i = 0; i < prices.length; i++) {
            min = Math.min(min, prices[i]);
            maxProfit = Math.max(maxProfit, prices[i] - min);
        }
        return maxProfit;
    }

    public boolean canJump(int[] nums) {
        int last = 0;
        for (int i = 0; i < nums.length; i++) {
            if (i <= last) {
                last = Math.max(nums[i] + i, last);
                if (last >= nums.length - 1)
                    return true;
            }
        }
        return false;
    }

    public int jump(int[] nums) {
        int last = 0;
        int end = 0;
        int step = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            last = Math.max(nums[i] + i, last);
            if(i == end){
                step++;
                end = last;
            }
        }
        return step;
    }

    public List<Integer> partitionLabels(String s) {
        int[] map = new int[26];
        List<Integer> result = new ArrayList<>();
        Arrays.fill(map, -1);
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            map[c - 'a'] = i;
        }
        int start = 0;
        int end = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            end = Math.max(end, map[c - 'a']);
            if(i == end){
                result.add(end - start + 1);
                start = end;
            }
        }
        return result;
    }
}
