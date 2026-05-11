package solution;

import entity.ListNode;
import entity.Node;

import java.util.*;
import java.util.stream.IntStream;

public class Solution {
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(target - nums[i]))
                return new int[]{map.get(target - nums[i]), i};
            map.put(nums[i], i);
        }
        return new int[]{};
    }

    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();
        for (String string : strs) {
            byte[] s = string.getBytes();
            Arrays.sort(s);
            String str = Arrays.toString(s);
            if (!map.containsKey(str))
                map.put(str, new ArrayList<>());
            map.get(str).add(string);
        }
        return new ArrayList<>(map.values());
    }

    public int longestConsecutive(int[] nums) {
//        Map<Integer, Integer> map = new HashMap<>();
//        int length = 0;
//        for (int num : nums) {
//            if(map.containsKey(num))
//                continue;
//            Integer leftLen = map.get(num - 1);
//            Integer rightLen = map.get(num + 1);
//            int maxLen = (leftLen != null ? leftLen : 0) +
//                         (rightLen != null ? rightLen : 0) + 1;
//            if(leftLen != null){
//                map.put(num - leftLen, maxLen);
//            }
//            if(rightLen != null){
//                map.put(num + rightLen, maxLen);
//            }
//            map.put(num, maxLen);
//            length = Math.max(length, maxLen);
//        }
//        return length;
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }
        int maxLength = 0;
        for (Integer i : set) {
            if (!set.contains(i - 1)) {
                int currentLength = 1;
                int currentNum = i;
                while (set.contains(currentNum + 1)) {
                    currentLength++;
                    currentNum++;
                }
                maxLength = Math.max(maxLength, currentLength);
            }
        }
        return maxLength;
    }

    public void moveZeroes(int[] nums) {
        int j = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                nums[j++] = nums[i];
            }
        }
        while (j < nums.length) {
            nums[j++] = 0;
        }
    }

    public int maxArea(int[] height) {
        int left = 0;
        int right = height.length - 1;
        int max = 0;
        while (left < right) {
            int area = (right - left) * (Math.min(height[left], height[right]));
            max = Math.max(area, max);
            if (height[left] < height[right])
                left++;
            else
                right--;
        }
        return max;
    }

    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> result = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            if (i > 0 && nums[i - 1] == nums[i])
                continue;
            int left = i + 1;
            int right = nums.length - 1;
            while (left < right) {
                int sum = nums[left] + nums[right] + nums[i];
                if (sum == 0) {
                    result.add(Arrays.asList(nums[left], nums[right], nums[i]));
                    while (left < right && nums[left] == nums[left + 1]) left++;
                    while (left < right && nums[right] == nums[right - 1]) right--;
                    left++;
                    right--;
                } else if (sum < 0) {
                    left++;
                } else {
                    right--;
                }
            }
        }
        return result;
    }

    public int trapWithStack(int[] height) {
        if (height == null || height.length < 3) return 0;
        int area = 0;
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < height.length; i++) {
            while (!stack.isEmpty() && height[stack.peek()] < height[i]) {
                int bottom = stack.pop();
                if (!stack.isEmpty()) {
                    int left = stack.peek();
                    int wide = i - left - 1;
                    int waterHeight = Math.min(height[i], height[left]) - height[bottom];
                    area += wide * waterHeight;
                }
            }
            stack.push(i);
        }
        return area;
    }

    public int maxRectangleArea(int[] heights) {
        if (heights == null || heights.length < 1) return 0;
        int[] newHeight = IntStream.concat(
                IntStream.of(0),           // 头部加数
                IntStream.concat(
                        IntStream.of(heights),
                        IntStream.of(0)        // 尾部加数
                )
        ).toArray();

        int maxArea = 0;
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < newHeight.length; i++) {
            while (!stack.isEmpty() && newHeight[stack.peek()] > newHeight[i]) {
                int height = newHeight[stack.pop()];
                int left = stack.peek();
                int width = i - left - 1;
                maxArea = Math.max(maxArea, height * width);
            }
            stack.push(i);
        }
        return maxArea;
    }

    public int trapWithTwoPointers(int[] height) {
        if (height == null || height.length < 3) return 0;
        int area = 0;
        int left = 0;
        int right = height.length - 1;
        int leftMax = 0, rightMax = 0;

        while (left < right) {
            if (height[left] < height[right]) {
                if (height[left] > leftMax) {
                    leftMax = height[left];
                } else {
                    area += leftMax - height[left];
                }
                left++;
            } else {
                if (height[right] > rightMax) {
                    rightMax = height[right];
                } else {
                    area += rightMax - height[right];
                }
                right--;
            }
        }
        return area;
    }

    public int lengthOfLongestSubstring(String s) {
        int maxLength = 0;
        int left = 0;
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (map.containsKey(c) && left <= map.get(c)) {
                left = map.get(c) + 1;
            }
            map.put(c, i);
            maxLength = Math.max(i - left + 1, maxLength);
        }
        return maxLength;
    }

    public List<Integer> findAnagrams(String s, String p) {
        int SLen = s.length();
        int PLen = p.length();
        List<Integer> result = new ArrayList<>();
        if (SLen < PLen) {
            return result;
        }
        int[] SCount = new int[26];
        int[] PCount = new int[26];
        for (int i = 0; i < PLen; i++) {
            PCount[p.charAt(i) - 'a']++;
            SCount[s.charAt(i) - 'a']++;
        }
        if (Arrays.equals(SCount, PCount)) {
            result.add(0);
        }
        for (int i = 0; i < SLen - PLen; i++) {
            SCount[s.charAt(i + PLen) - 'a']++;
            SCount[s.charAt(i) - 'a']--;
            if (Arrays.equals(SCount, PCount)) {
                result.add(i + 1);
            }
        }
        return result;
    }

    public int subarraySum(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>(); // 前缀和为k出现的次数v
        map.put(0, 1);
        int presum = 0;
        int count = 0;
        for (int num : nums) {
            presum += num;
            if (map.containsKey(presum - k)) {
                count += map.get(presum - k);
            }
            map.put(presum, map.getOrDefault(presum, 1));
        }
        return count;
    }

    public int[] maxSlidingWindow(int[] nums, int k) {
        if (nums.length < k)
            return new int[0];
        List<Integer> list = new ArrayList<>();
        Deque<Integer> deque = new ArrayDeque<>();
        for (int i = 0; i < nums.length; i++) {
            if (!deque.isEmpty() && deque.getLast() < i - k + 1)
                deque.removeLast();
            while (!deque.isEmpty() && nums[deque.getFirst()] <= nums[i])
                deque.removeFirst();
            deque.push(i);
            if (i >= k - 1)
                list.add(nums[deque.getLast()]);
        }
        return list.stream().mapToInt(i -> i).toArray();
    }

    public String minWindow(String s, String t) {
        int SLen = s.length();
        int TLen = t.length();
        Map<Character, Integer> map_t = new HashMap<>();
        Map<Character, Integer> map_s = new HashMap<>();
        for (int i = 0; i < TLen; i++) {
            map_t.put(t.charAt(i), map_t.getOrDefault(t.charAt(i), 0) + 1);
        }
        int needSize = map_t.size();
        int valid = 0;
        String result = "";
        int left = 0;
        for (int i = 0; i < SLen; i++) {
            if (map_t.containsKey(s.charAt(i))) {
                map_s.put(s.charAt(i), map_s.getOrDefault(s.charAt(i), 0) + 1);
                if (Objects.equals(map_s.get(s.charAt(i)), map_t.get(s.charAt(i))))
                    valid++;
            }
            while (valid == needSize && left <= i) {
                if (result.isEmpty() || i - left + 1 < result.length()) {
                    result = s.substring(left, i + 1);
                }
                if (map_s.containsKey(s.charAt(left))) {
                    map_s.put(s.charAt(left), map_s.get(s.charAt(left)) - 1);
                    if (map_s.get(s.charAt(left)) < map_t.get(s.charAt(left)))
                        valid--;
                }
                left++;
            }
        }

        return result;
    }

    public int maxSubArray(int[] nums) {
        int[] result = new int[nums.length];
        result[0] = nums[0];
        int max = result[0];
        for (int i = 1; i < nums.length; i++) {
            if (result[i - 1] > 0) {
                result[i] = result[i - 1] + nums[i];
                max = Math.max(max, result[i]);
            } else {
                result[i] = nums[i];
            }
        }
        return max;
    }

    public int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, (i1, i2) -> i1[0] - i2[0]);
        List<int[]> list = new ArrayList<>();
        for (int i = 0; i < intervals.length; i++) {
            int start = intervals[i][0];
            int end = intervals[i][1];
            if (list.isEmpty() || start > list.get(list.size() - 1)[1]) {
                list.add(new int[]{start, end});
            } else {
                list.get(list.size() - 1)[1] = Math.max(list.get(list.size() - 1)[1], end);
            }
        }
        return list.toArray(new int[list.size()][]);
    }

    public void rotate(int[] nums, int k) {
        k = k % nums.length;
        int start = 0;
        int cycles = gcd(nums.length, k);
        for (int j = 0; j < cycles; j++) {
            int startNum = nums[start];
            do {
                int newLocation = (start + k) % nums.length;
                int temp = nums[newLocation];
                nums[newLocation] = startNum;
                start = newLocation;
                startNum = temp;
            } while (start != 0);
        }
    }

    private int gcd(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }

    public void rotateReserve(int[] nums, int k) {
        reserve(nums, 0, nums.length);
        System.out.println(Arrays.toString(nums));
        reserve(nums, 0, k);
        System.out.println(Arrays.toString(nums));
        reserve(nums, k, nums.length);
    }

    public void reserve(int[] nums, int start, int end) {
        while (start < end - 1) {
            int temp = nums[start];
            nums[start] = nums[end - 1];
            nums[end - 1] = temp;
            start++;
            end--;
        }
    }

    public int[] productExceptSelf(int[] nums) {
        int n = nums.length;
        int[] preMul = new int[n];
        int[] postMul = new int[n];
        for (int i = 0; i < nums.length; i++) {
            if (i == 0)
                preMul[i] = 1;
            else
                preMul[i] = preMul[i - 1] * nums[i - 1];
        }
        for (int i = n - 1; i >= 0; i--) {
            if (i == n - 1)
                postMul[i] = 1;
            else
                postMul[i] = postMul[i + 1] * postMul[i + 1];
        }
        int[] answer = new int[n];
        for (int i = 0; i < nums.length; i++) {
            answer[i] = preMul[i] * postMul[i];
        }
        return answer;
//        int[] answer = new int[n];
//
//        answer[0] = 1;
//        for (int i = 1; i < n; i++) {
//            answer[i] = answer[i - 1] * nums[i - 1];
//        }
//
//        int pre = 1;
//        for (int i = n - 1; i >= 0; i--) {
//            answer[i] = answer[i] * pre;
//            pre *= nums[i];
//        }
//        return answer;
    }

    public int firstMissingPositive(int[] nums) {
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            while (nums[i] > 0 && nums[i] <= n && nums[nums[i] - 1] != nums[i]) {
                int targetIndex = nums[i] - 1;
                int temp = nums[targetIndex];
                nums[targetIndex] = nums[i];
                nums[i] = temp;
            }
        }

        for (int i = 0; i < n; i++) {
            if (nums[i] != i + 1) {
                return i + 1;
            }
        }

        return n + 1;
    }

    // O(1)空间未实现
    public void setZeroes(int[][] matrix) {
        int n = matrix.length;
        int m = matrix[0].length;
        boolean[] flag = new boolean[n + m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (matrix[i][j] == 0) {
                    flag[i] = true;
                    flag[n + j] = true;
                }
                System.out.println(Arrays.toString(flag));
            }
            System.out.println("-----------------------");
        }

        for (int i = 0; i < n; i++) {
            if (flag[i]) {
                for (int j = 0; j < m; j++) {
                    matrix[i][j] = 0;
                }
            }
            System.out.println(Arrays.deepToString(matrix));
        }
        for (int i = n; i < n + m; i++) {
            if (flag[i]) {
                for (int j = 0; j < n; j++) {
                    matrix[j][i - n] = 0;
                }
            }
            System.out.println(Arrays.deepToString(matrix));
        }
    }

    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> list = new ArrayList<>();
        int top = 0;
        int bottom = matrix.length - 1;
        int left = 0;
        int right = matrix[0].length - 1;
        while (top <= bottom && left <= right) {
            for (int i = left; i <= right; i++) {
                list.add(matrix[top][i]);
            }
            top++;
            for (int i = top; i <= bottom; i++) {
                list.add(matrix[i][right]);
            }
            right--;
            if (top <= bottom) {
                for (int i = right; i >= left; i--) {
                    list.add(matrix[bottom][i]);
                }
                bottom--;
            }
            if (left <= right) {
                for (int i = bottom; i >= top; i--) {
                    list.add(matrix[i][left]);
                }
                left++;
            }

        }
        return list;
    }

    public void rotate(int[][] matrix) {
        List<Integer> list = new ArrayList<>();
        int n = matrix.length;
        int m = matrix[0].length;
        for (int i = 0; i < n / 2; i++) {
            for (int j = 0; j < m; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[n - i - 1][j];
                matrix[n - i - 1][j] = temp;
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }
    }

    public boolean searchMatrix(int[][] matrix, int target) {
        int n = matrix.length;
        int m = matrix[0].length;

        int x = 0;
        int y = m - 1;
        while (x < n && y >= 0) {
            if (matrix[x][y] == target)
                return true;
            else if (matrix[x][y] > target)
                y--;
            else
                x++;

        }
        return false;

    }

    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        Set<ListNode> set = new HashSet<>();
        while (headA != null) {
            set.add(headA);
            headA = headA.next;
        }
        while (headB != null) {
            if (set.contains(headB))
                return headB;
            headB = headB.next;
        }
        return null;
    }

    // 递归
    public ListNode reverseList(ListNode head) {
        return recursivelyReverseList(head);
    }

    public ListNode recursivelyReverseList(ListNode head) {
        if (head == null || head.next == null)
            return head;
        ListNode newHead = recursivelyReverseList(head.next);
        head.next.next = head;
        head.next = null;
        return newHead;
    }

    private ListNode pre;

    public boolean isPalindrome(ListNode head) {
        pre = head;
        return recursivelyIsPalindrome(head);
    }

    private boolean recursivelyIsPalindrome(ListNode currentNode) {
        if (currentNode == null)
            return true;
        boolean flag = recursivelyIsPalindrome(currentNode.next) && currentNode.val == pre.val;
        pre = pre.next;
        return flag;
    }

    public boolean hasCycle(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast)
                return true;
        }
        return false;
    }

    public ListNode detectCycle(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast)
                break;
        }
        if (fast == null)
            return null;
        ListNode begin = head;
        while (begin != slow) {
            slow = slow.next;
            begin = begin.next;
        }
        return begin;
    }


    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode head = new ListNode(0, list1);
        ListNode pre = head;
        while (list1 != null && list2 != null) {
            if (list1.val < list2.val) {
                pre.next = list1;
                pre = list1;
                list1 = list1.next;
            } else {
                pre.next = list2;
                pre = list2;
                list2 = list2.next;
            }
        }
        if (list1 != null) {
            pre.next = list1;
        }
        if (list2 != null) {
            pre.next = list2;
        }
        return head.next;
    }

    public ListNode recursivelyMergeTwoLists(ListNode list1, ListNode list2) {
        if (list1 == null)
            return list2;
        if (list2 == null)
            return list1;
        if (list1.val < list2.val) {
            list1.next = recursivelyMergeTwoLists(list1.next, list2);
            return list1;
        } else {
            list2.next = recursivelyMergeTwoLists(list1, list2.next);
            return list2;
        }
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode head = new ListNode();
        ListNode cur = head;
        int num = 0;
        while (l1 != null && l2 != null) {
            num += l1.val + l2.val;
            cur.next = new ListNode((num % 10), null);
            cur = cur.next;
            num /= 10;
            l1 = l1.next;
            l2 = l2.next;
        }
        while (l1 != null) {
            num += l1.val;
            cur.next = new ListNode((num % 10), null);
            cur = cur.next;
            num /= 10;
            l1 = l1.next;
        }
        while (l2 != null) {
            num += l2.val;
            cur.next = new ListNode((num % 10), null);
            cur = cur.next;
            num /= 10;
            l2 = l2.next;
        }
        if (num != 0) {
            cur.next = new ListNode((num % 10), null);
        }
        return head.next;
    }

    public ListNode removeNthFromEnd(ListNode head, int n) {
        if (head == null || head.next == null)
            return null;
        ListNode dummy = new ListNode(0, head);
        ListNode pre = dummy;
        ListNode slow = head;
        ListNode fast = head;
        while (n != 0) {
            fast = fast.next;
            n--;
        }
        while (fast != null) {
            pre = slow;
            slow = slow.next;
            fast = fast.next;
        }
        pre.next = slow.next;
        return dummy.next;
    }

    public ListNode swapPairs(ListNode head) {

        ListNode dummy = new ListNode(0, head);
        ListNode one = dummy;
        while (one.next != null && one.next.next != null) {
            ListNode two = one.next;
            ListNode three = two.next;

            two.next = three.next;
            three.next = two;
            one.next = three;

            one = two;
        }
        return dummy.next;
    }

    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode dummy = new ListNode(0, head);
        ListNode pre = dummy;
        while (true) {
            ListNode check = pre;
            for (int i = 0; i < k; i++) {
                if (check.next == null)
                    return dummy.next;
                check = check.next;
            }
            ListNode cur = pre.next;
            ListNode one = pre.next;
            ListNode two = one.next;
            for (int i = 0; i < k - 1; i++) {
                ListNode twoNext = two.next;
                two.next = one;
                one = two;
                two = twoNext;
            }
            pre.next = one;
            cur.next = two;
            pre = cur;
        }
    }

    public ListNode recursivelyReverseKGroup(ListNode head, int k) {
        ListNode check = head;
        for (int i = 0; i < k; i++) {
            if (check == null)
                return head;
            check = check.next;
        }

        ListNode newHead = recursivelyReverseKGroup(check, k);
        ListNode cur = head;
        ListNode next = head.next;
        for (int i = 0; i < k - 1; i++) {
            ListNode tmp = next.next;
            next.next = cur;
            cur = next;
            next = tmp;
        }
        head.next = newHead;
        return cur;

    }

    public Node copyRandomList(Node head) {
        Map<Node, Node> map = new HashMap<>();
        Node curr1 = head;
        Node dummy = new Node(0);
        Node curr2 = dummy;
        while (curr1 != null) {
            curr2.next = new Node(curr1.val);
            map.put(curr1, curr2.next);
            curr2 = curr2.next;
            curr1 = curr1.next;
        }

        curr1 = head;
        curr2 = dummy.next;
        while (curr1 != null) {
            curr2.random = map.get(curr1.random);
            curr2 = curr2.next;
            curr1 = curr1.next;
        }
        return dummy.next;
    }


    public ListNode sortList(ListNode head) {
        int length = 0;
        ListNode curr = head;
        while (curr != null) {
            length++;
            curr = curr.next;
        }
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        for (int step = 1; step < length; step <<= 1){
            ListNode prev = dummy;
            curr = dummy.next;
            while (curr != null) {
                ListNode first = curr;
                ListNode second = split(first, step);
                ListNode nextStart = split(second, step);
                prev.next = mergeLists(first, second);
                while (prev.next != null) {
                    prev = prev.next;
                }
                curr = nextStart;
            }
        }
        return dummy.next;
    }
    private ListNode split(ListNode head, int step) {
        ListNode curr = head;
        for (int i = 1; i < step && curr != null && curr.next != null; i++) {
            curr = curr.next;
        }
        if (curr == null || curr.next == null) return null;
        ListNode next = curr.next;
        curr.next = null;
        return next;
    }
    public ListNode recursivelySortList(ListNode head, ListNode tail){
        if (head == null) {
            return head;
        }
        if (head.next == tail) {
            head.next = null;
            return head;
        }

        ListNode fast = head;
        ListNode slow = head;
        while(fast != tail && fast.next != tail){
            slow = slow.next;
            fast = fast.next.next;
        }
        ListNode left = recursivelySortList(head, slow);
        ListNode right = recursivelySortList(slow, tail);
        return mergeLists(left, right);
    }
    private ListNode mergeLists(ListNode left, ListNode right) {
        if(left == null)
            return right;
        if(right == null)
            return left;
        if(left.val < right.val){
            left.next = mergeLists(left.next, right);
            return left;
        }
        else{
            right.next = mergeLists(left, right.next);
            return right;
        }
    }



    public ListNode mergeKLists(ListNode[] lists) {
        PriorityQueue<ListNode> listNodes = new PriorityQueue<>(new Comparator<ListNode>(){
            @Override
            public int compare(ListNode o1, ListNode o2) {
                return o1.val - o2.val;
            }
        });
        for (ListNode list : lists) {
            if (list != null) {
                listNodes.offer(list);
            }
        }
        ListNode dummy = new ListNode(0);
        ListNode head = dummy;
        while(!listNodes.isEmpty()){
            ListNode tmp = listNodes.poll();
            head.next = tmp;
            head = head.next;
            if(tmp.next != null)
                listNodes.offer(tmp.next);
        }
        return dummy.next;
    }
    public ListNode mergeKListsGB(ListNode[] lists) {
        if(lists.length == 0)
            return null;
        return recursivelyMergeKListsGB(lists, 0, lists.length - 1);
    }
    public ListNode recursivelyMergeKListsGB(ListNode[] lists, int start, int end) {
        if(start == end)
            return lists[start];
        int mid = (end - start)/2 + start;
        ListNode left = recursivelyMergeKListsGB(lists, start, mid);
        ListNode right = recursivelyMergeKListsGB(lists, mid + 1, end);
        return mergeLists(left, right);
    }


}











