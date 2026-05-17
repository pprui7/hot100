package solution;

import java.util.Arrays;

public class SolutionOther {
    public int singleNumber(int[] nums) {
        int result = 0;
        for (int num : nums) {
            result ^= num;
        }
        return result;
    }

    public int majorityElementQS(int[] nums) {
        return majorityElementQSHelper(nums, nums.length / 2, 0, nums.length - 1);
    }

    private int majorityElementQSHelper(int[] nums, int k, int left, int right) {
        if (left == right)
            return nums[left];
        int x = nums[left];
        int i = left - 1;
        int j = right + 1;
        while (i < j) {
            do i++; while (nums[i] < x);
            do j--; while (nums[j] > x);
            if (i < j) {
                swap(nums, i, j);
            }
        }
        if (j >= k)
            return majorityElementQSHelper(nums, k, left, j);
        else
            return majorityElementQSHelper(nums, k, j + 1, right);
    }

    public int majorityElement(int[] nums) {
        return majorityElementHelper(nums, 0, nums.length - 1);
    }

    private int majorityElementHelper(int[] nums, int left, int right) {
        if (left == right)
            return nums[left];
        int mid = (left + right) / 2;
        int leftZ = majorityElementHelper(nums, left, mid);
        int rightZ = majorityElementHelper(nums, mid + 1, right);
        if (leftZ == rightZ)
            return leftZ;
        else {
            int leftCount = numsCount(nums, leftZ, left, right);
            int rightCount = numsCount(nums, rightZ, left, right);
            return leftCount > rightCount ? leftZ : rightZ;
        }
    }

    private int numsCount(int[] nums, int Z, int left, int right) {
        int count = 0;
        for (int i = left; i <= right; i++) {
            if (nums[i] == Z)
                count++;
        }
        return count;
    }

//    public int majorityElement(int[] nums) {
//        Integer result = null;
//        int count = 0;
//        for (int num : nums) {
//            if(count == 0)
//                result = num;
//            count += (num == result) ? 1 : -1;
//        }
//        return result;
//    }

    public void sortColors(int[] nums) {
        int left = 0;
        int right = nums.length - 1;
        int i = 0;
        while (i <= right) {
            if (nums[i] == 0) {
                swap(nums, i, left);
                left++;
            }
            if (nums[i] == 2) {
                swap(nums, i, right);
                right--;
                if (nums[i] != 1) i--;
            }
            i++;
        }
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public void nextPermutation(int[] nums) {

        // 从后向前，找到第一个开始下降的数
        int left = -1;
        for (int i = nums.length - 1; i > 0; i--) {
            if (nums[i - 1] < nums[i]) {
                left = i - 1;
                break;
            }
        }

        // 从后向前，找到第一个比那个下降的数大的数，然后交换
        if (left != -1) {
            int right = -1;
            for (int i = nums.length - 1; i >= left + 1; i--) {
                if (nums[i] > nums[left]) {
                    right = i;
                    break;
                }
            }
            swap(nums, left, right);
        }

        Arrays.sort(nums, left + 1, nums.length);

    }

    public int findDuplicate(int[] nums) {
        int slow = 0;
        int fast = 0;
        do {
            slow = nums[slow];
            fast = nums[nums[fast]];
        } while (slow != fast);
        slow = 0;
        while(slow != fast){
            slow = nums[slow];
            fast = nums[fast];
        }
        return nums[slow];
    }


}
