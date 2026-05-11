package solutionTest;

import entity.ListNode;
import org.junit.Test;
import solution.LRUCache;
import solution.Solution;

import java.util.List;
import java.util.Arrays;


public class SolutionTest {

    private final Solution solution = new Solution();
    @Test
    public void threeSumTest(){
        int[] nums = {-1, 0, 1, 2, -1, -4};
        List<List<Integer>> result = solution.threeSum(nums);
        
        result.forEach(System.out::println);
    }
    @Test
    public void TrapDoubleTest(){
        int[] nums = {0,1,0,2,1,0,1,3,2,1,2,1};
        System.out.println(solution.trapWithStack(nums));
    }

    @Test
    public void lengthOfLongestSubstringTest(){
        String s = "abba";
        System.out.println(solution.lengthOfLongestSubstring(s));
    }

    @Test
    public void findAnagramsTest(){
        String s = "abab";
        String p = "ab";
        System.out.println(solution.findAnagrams(s, p));
    }
    @Test
    public void subarraySumTest(){
        int[] nums = {1,1,1};
        System.out.println(solution.subarraySum(nums, 2));
    }
    @Test
    public void maxSlidingWindowTest(){
        int[] nums = {3,1,1,3};
        System.out.println(Arrays.toString(solution.maxSlidingWindow(nums, 3)));
    }
    @Test
    public void minWindowTest(){
        String s = "acbdbaab";
        String t = "aabd";
        System.out.println(solution.minWindow(s, t));
    }
    @Test
    public void maxSubArrayTest(){
        int[] nums = {5,4,-1,7,8};
        System.out.println(solution.maxSubArray(nums));
    }
    @Test
    public void mergeTest(){
        int[][] nums = {{2,3},{4,5},{6,7},{8,9},{1,10}};
        System.out.println(Arrays.deepToString(solution.merge(nums)));
    }

    @Test
    public void rotateTest(){
        int[] nums = {1,2,3,4,5,6,7};
        solution.rotateReserve(nums, 3);
        System.out.println(Arrays.toString(nums));
    }

    @Test
    public void firstMissingPositiveTest(){
        int[] nums = {3,4,-1,1};
        System.out.println(solution.firstMissingPositive(nums));
    }

    @Test
    public void setZeroesTest(){
        int[][] nums = {{0,1,2},{3,4,5},{6,7,8}};
        solution.setZeroes(nums);
        System.out.println(Arrays.deepToString(nums));
    }

    @Test
    public void spiralOrderTest(){
        int[][] nums = {{0,1,2},{3,4,5},{6,7,8}};
        System.out.println(solution.spiralOrder(nums));
    }

    public ListNode createLinkedList(int[] nums){
        ListNode head = new ListNode();
        ListNode cur = head;
        for (int num : nums) {
            ListNode tmp = new ListNode(num, null);
            cur.next = tmp;
            cur = cur.next;
        }
        return head.next;
    }
    @Test
    public void addTwoNumbersTest(){
        ListNode l1 = createLinkedList(new int[]{1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1});
        ListNode l2 = createLinkedList(new int[]{5,6,4});
        ListNode s = solution.addTwoNumbers(l1, l2);
        while(s != null){
            System.out.println(s.val);
            s = s.next;
        }
    }

    @Test
    public void swapPairsTest(){
        ListNode l1 = createLinkedList(new int[]{1,2,3,4});
        ListNode s = solution.swapPairs(l1);
        while(s != null){
            System.out.println(s.val);
            s = s.next;
        }
    }
    @Test
    public void reverseKGroupTest(){
        ListNode l1 = createLinkedList(new int[]{1,2,3,4,5,6,7,8});
        ListNode s = solution.recursivelyReverseKGroup(l1, 3);
        while(s != null){
            System.out.println(s.val);
            s = s.next;
        }
    }
    @Test
    public void sortListTest(){
        ListNode l1 = createLinkedList(new int[]{4,2,1,3});
        ListNode s = solution.sortList(l1);
        while(s != null){
            System.out.println(s.val);
            s = s.next;
        }
    }
    @Test
    public void mergeKListsTest(){
        ListNode l1 = createLinkedList(new int[]{});
        ListNode l2 = createLinkedList(new int[]{-1,5,11});
        ListNode l3 = createLinkedList(new int[]{});
        ListNode l4 = createLinkedList(new int[]{6,10});
        ListNode s = solution.mergeKListsGB(new ListNode[]{l1, l2, l3, l4});
        while(s != null){
            System.out.println(s.val);
            s = s.next;
        }
    }
    @Test
    public void LRUCacheTest(){
        LRUCache lRUCache = new LRUCache(1);
        lRUCache.put(2, 1);
        System.out.println(lRUCache.get(2));
        lRUCache.put(3, 2);
        System.out.println(lRUCache.get(2));
        System.out.println(lRUCache.get(3));

    }







}