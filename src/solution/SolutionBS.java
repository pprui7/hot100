package solution;

import java.util.Arrays;

public class SolutionBS {
    public int searchInsert(int[] nums, int target) {
        int start = 0;
        int end = nums.length;
        while (start < end) {
            int mid = (start + end) / 2;
            if (nums[mid] < target) {
                start = mid + 1;
            } else {
                end = mid;
            }
        }
        return start;
    }
    public int[] searchRange(int[] nums, int target) {
        int[] result = new int[2];

        int start = 0, end = nums.length;
        while (start < end) {
            int mid = (start + end) / 2;
            if (nums[mid] < target) {
                start = mid + 1;
            } else {
                end = mid;
            }
        }
        if (start >= nums.length || nums[start] != target) {
            result[0] = -1;
            result[1] = -1;
            return result;
        }
        result[0] = start;

        start = -1;
        end = nums.length - 1;
        while (start < end) {
            int mid = (start + end) / 2 + 1;
            if (nums[mid] > target) {
                end = mid;
            } else {
                start = mid + 1;
            }
        }
        result[1] = start - 1;
        return result;
    }
    public boolean searchMatrix(int[][] matrix, int target) {
        int start = 0;
        int end = matrix.length * matrix[0].length - 1;
        while (start <= end) {
            int mid = (start + end) / 2;
            int x = mid / matrix.length;
            int y = mid % matrix.length;
            System.out.println(x + " " + y);
            if (matrix[x][y] == target) {
                return true;
            } else if (matrix[x][y] < target) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }
        return false;
    }

    // 两端必定有一个数组有序
    // 先寻找有序数组，在判断target是否在数组里
    public int search(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        while(left <= right){
            int mid = (left + right) / 2;
            if (nums[mid] == target)
                return mid;
            if(nums[left] <= nums[mid]){
                if(nums[left] <= target && target <= nums[mid]){
                    right = mid - 1;
                } else{
                    left = mid + 1;
                }
            } else{
                if(nums[mid] <= target && target <= nums[right]){
                    left = mid + 1;
                } else{
                    right = mid -1;
                }
            }
        }
        return -1;
    }

    // 用right判断？？
    public int findMin(int[] nums) {
        int left = 0;
        int right = nums.length - 1;
        while (left < right) {
            int mid = (left + right) / 2;
            if(nums[mid] < nums[right])
                right = mid;
            else
                left = mid + 1;
        }
        return nums[left];
    }

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int n = nums1.length;
        int m = nums2.length;
        if (n > m) {
            return findMedianSortedArrays(nums2, nums1);
        }
        int left = 0;
        int right = n;
        int total = (n + m + 1) / 2;
        while (left <= right) {
            int i = (left + right) / 2;
            int j = total - i;
            int nums1LeftMax = (i == 0) ? Integer.MIN_VALUE : nums1[i - 1];
            int nums1RightMin = (i == m) ? Integer.MAX_VALUE : nums1[i];
            int nums2LeftMax = (j == 0) ? Integer.MIN_VALUE : nums2[j - 1];
            int nums2RightMin = (j == n) ? Integer.MAX_VALUE : nums2[j];
            if (nums1LeftMax <= nums2RightMin && nums2LeftMax <= nums1RightMin) {
                if ((n + m + 1) % 2 == 0)
                    return Math.max(nums1LeftMax, nums2LeftMax);
                else{
                    int leftMax = Math.max(nums1LeftMax, nums2LeftMax);
                    int rightMin = Math.min(nums1RightMin, nums2RightMin);
                    return (leftMax + rightMin) / 2.0;
                }
            } else if (nums1LeftMax > nums2RightMin) {
                right = i - 1;
            } else {
                left = i + 1;
            }
        }
        return 0D;
    }
}
