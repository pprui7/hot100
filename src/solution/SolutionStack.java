package solution;

import java.util.*;

public class SolutionStack {

    public boolean isValid(String s) {
        Map<Character, Character> map = Map.of(
                ')', '(',
                '}', '{',
                ']', '['
        );
        Deque<Character> stack = new ArrayDeque<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (map.containsKey(c)) {
                if (stack.isEmpty() || stack.pop() != map.get(c))
                    return false;
            } else {
                stack.push(c);
            }
        }
        return stack.isEmpty();
    }
    public static class MinStack {
        Deque<Integer> stack;
        Deque<Integer> min_stack;
        public MinStack() {
            stack = new ArrayDeque<>();
            min_stack = new ArrayDeque<>();
            min_stack.push(Integer.MAX_VALUE);
        }

        public void push(int val) {
            stack.push(val);
            min_stack.push(Math.min(val, min_stack.peek()));
        }

        public void pop() {
            stack.pop();
            min_stack.pop();
        }

        public int top() {
            return stack.peek();
        }

        public int getMin() {
            return min_stack.peek();
        }
    }
    public String decodeString(String s) {
        Deque<Integer> countStack = new ArrayDeque<>();
        Deque<StringBuilder> strStack = new ArrayDeque<>();
        StringBuilder current = new StringBuilder();
        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if(Character.isDigit(c)){
                count = count * 10 + (c - '0');
            } else if (c == '[') {
                countStack.push(count);
                strStack.push(current);
                current = new StringBuilder();
                count = 0;
            } else if (c == ']') {
                StringBuilder tmp = current;
                current = strStack.pop();
                int repeatCount = countStack.pop();
                current.append(String.valueOf(tmp).repeat(repeatCount));
            }else{
                current.append(c);
            }
        }
        return new String(current);
    }

    // 单调栈
    public int[] dailyTemperatures(int[] temperatures) {
        int[] result = new int[temperatures.length];
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = 0; i < temperatures.length; i++) {
            while(!stack.isEmpty() && temperatures[stack.peekLast()] < temperatures[i]){
                Integer j = stack.removeLast();
                result[j] = i - j;
            }
            stack.offer(i);
        }
        return result;
    }
    public int largestRectangleArea(int[] heights) {
        int[] newHeights = new int[heights.length + 2];
        newHeights[0] = 0;
        System.arraycopy(heights, 0, newHeights, 1, heights.length);
        newHeights[newHeights.length - 1] = 0;
        int maxArea = 0;
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = 0; i < newHeights.length; i++) {
            // (j, i) -1   [j, i] +1
            while (!stack.isEmpty() && newHeights[stack.peekLast()] > newHeights[i]) {
                int height = newHeights[stack.removeLast()];
                int j = stack.peekLast();
                int width = i - j - 1;
                maxArea = Math.max(maxArea, height * width);
            }
            stack.offer(i);
        }
        return maxArea;
    }
    // 接雨水 重要单调栈、dp、双指针
    public int trap(int[] height) {
        Deque<Integer> stack = new ArrayDeque<>();
        int result = 0;
        for (int i = 0; i < height.length; i++) {
            while(!stack.isEmpty() && height[stack.peek()] < height[i]){
                int midHigh = height[stack.pop()];
                if(stack.isEmpty())
                    break;
                int j = stack.peek();
                int high = Math.min(height[i], height[j]) - midHigh;
                int width = i - j - 1;
                result += high * width;
            }
            stack.push(i);
        }
        return result;
    }
    public int longestValidParentheses(String s) {
        int maxLen = 0;
        Deque<Integer> stack = new ArrayDeque<>();
        stack.push(-1);
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                stack.push(i);
            } else {
                stack.pop();
                if (stack.isEmpty()) {
                    stack.push(i);
                } else {
                    maxLen = Math.max(maxLen, i - stack.peek());
                }
            }
        }
        return maxLen;
    }





}
