package com.leetcode.problems;

import org.junit.Test;
import static org.junit.Assert.assertArrayEquals;

public class Problem001Test {
    private class TestCase {
        int[] nums;
        int target;
        int[] expected;

        public TestCase(int[] nums, int target, int[] expected) {
            this.nums = nums;
            this.target = target;
            this.expected = expected;
        }
    }

    @Test
    public void twoSum() {
        
        TestCase[] tests = new TestCase[] {
            new TestCase(new int[]{2, 7, 11, 15}, 9, new int[]{1,0}),
            new TestCase(new int[]{3, 2, 4}, 6, new int[]{2,1}),
            new TestCase(new int[]{3, 3}, 6, new int[]{1,0}),
        };

        Problem001 p = new Problem001();
        for (TestCase t : tests) {
            int[] actual = p.twoSum(t.nums, t.target);
            assertArrayEquals(t.expected, actual);
        }
    }
}