package solutionTest;

import org.junit.Test;
import solution.SolutionStack;

import java.util.Arrays;

public class SolutionStackTest {
    private final SolutionStack solutionStack = new SolutionStack();

    @Test
    public void isValidTest() {
        String s = "()[]{}";
        boolean actual = solutionStack.isValid(s);
        System.out.println("20. 有效的括号");
        System.out.println("输入: " + s);
        System.out.println("输出: " + actual);
    }

    @Test
    public void minStackTest() {
        SolutionStack.MinStack minStack = new SolutionStack.MinStack();
        minStack.push(-2);
        minStack.push(0);
        minStack.push(-3);

        int actualMin1 = minStack.getMin();
        minStack.pop();
        int actualTop = minStack.top();
        int actualMin2 = minStack.getMin();

        System.out.println("155. 最小栈");
        System.out.println("操作: push(-2), push(0), push(-3), getMin(), pop(), top(), getMin()");
        System.out.println("输出: [" + actualMin1 + ", " + actualTop + ", " + actualMin2 + "]");
    }

    @Test
    public void decodeStringTest() {
        String s = "3[a2[c]]";
        String actual = solutionStack.decodeString(s);
        System.out.println("394. 字符串解码");
        System.out.println("输入: " + s);
        System.out.println("输出: " + actual);
    }

    @Test
    public void dailyTemperaturesTest() {
        int[] temperatures = {73, 74, 75, 71, 69, 72, 76, 73};
        int[] actual = solutionStack.dailyTemperatures(temperatures);
        System.out.println("739. 每日温度");
        System.out.println("输入: " + Arrays.toString(temperatures));
        System.out.println("输出: " + Arrays.toString(actual));
    }

    @Test
    public void largestRectangleAreaTest() {
        int[] heights = {2, 1, 5, 6, 2, 3};
        int actual = solutionStack.largestRectangleArea(heights);
        System.out.println("84. 柱状图中最大的矩形");
        System.out.println("输入: " + Arrays.toString(heights));
        System.out.println("输出: " + actual);
    }
}
