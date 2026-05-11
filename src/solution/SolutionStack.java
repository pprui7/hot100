package solution;

import java.util.ArrayDeque;
import java.util.Deque;

public class SolutionStack {
    public boolean isValid(String s) {
        Deque<Character> stack = new ArrayDeque<>();
        for (char c : s.toCharArray()) {
            // Push the expected closing bracket, then verify each closing bracket matches it.
            if (c == '(') {
                stack.push(')');
            } else if (c == '[') {
                stack.push(']');
            } else if (c == '{') {
                stack.push('}');
            } else if (stack.isEmpty() || stack.pop() != c) {
                return false;
            }
        }
        return stack.isEmpty();
    }

    public static class MinStack {
        private final Deque<Integer> stack;
        private final Deque<Integer> minStack;

        public MinStack() {
            stack = new ArrayDeque<>();
            minStack = new ArrayDeque<>();
        }

        public void push(int val) {
            stack.push(val);
            if (minStack.isEmpty() || val <= minStack.peek()) {
                minStack.push(val);
            }
        }

        public void pop() {
            int val = stack.pop();
            if (val == minStack.peek()) {
                minStack.pop();
            }
        }

        public int top() {
            return stack.peek();
        }

        public int getMin() {
            return minStack.peek();
        }
    }

    public String decodeString(String s) {
        Deque<Integer> numStack = new ArrayDeque<>();
        Deque<StringBuilder> strStack = new ArrayDeque<>();
        StringBuilder current = new StringBuilder();
        int num = 0;
        for (char c : s.toCharArray()) {
            if (Character.isDigit(c)) {
                num = num * 10 + c - '0';
            } else if (c == '[') {
                numStack.push(num);
                strStack.push(current);
                current = new StringBuilder();
                num = 0;
            } else if (c == ']') {
                int count = numStack.pop();
                StringBuilder previous = strStack.pop();
                for (int i = 0; i < count; i++) {
                    previous.append(current);
                }
                current = previous;
            } else {
                current.append(c);
            }
        }
        return current.toString();
    }

    public int[] dailyTemperatures(int[] temperatures) {
        int[] result = new int[temperatures.length];
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = 0; i < temperatures.length; i++) {
            while (!stack.isEmpty() && temperatures[stack.peek()] < temperatures[i]) {
                int index = stack.pop();
                result[index] = i - index;
            }
            stack.push(i);
        }
        return result;
    }

    public int largestRectangleArea(int[] heights) {
        int maxArea = 0;
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = 0; i <= heights.length; i++) {
            int currentHeight = i == heights.length ? 0 : heights[i];
            while (!stack.isEmpty() && heights[stack.peek()] > currentHeight) {
                int height = heights[stack.pop()];
                int left = stack.isEmpty() ? -1 : stack.peek();
                int width = i - left - 1;
                maxArea = Math.max(maxArea, height * width);
            }
            stack.push(i);
        }
        return maxArea;
    }
}
