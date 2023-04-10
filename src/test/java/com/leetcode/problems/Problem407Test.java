package com.leetcode.problems;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
// import static org.junit.Assert.assertEquals;
// import java.util.vector;

public class Problem407Test {
    private class TestCase {
        int[][] heightmap;
        int expected;

        public TestCase(int[][] h, int e) {
            this.heightmap = h;
            this.expected = e;
        }
    }

    @Test
    public void trappingWater2() {
        
        TestCase[] tests = new TestCase[] {
            new TestCase(new int[][]{{1,4,3,1,3,2},{3,2,1,3,2,4},{2,3,3,2,3,1}}, 4),
            new TestCase(new int[][]{{3,3,3,3,3},{3,2,2,2,3},{3,2,1,2,3},{3,2,2,2,3},{3,3,3,3,3}}, 10),
            new TestCase(new int[][]{{1,1,1},{1,0,1},{1,1,1}}, 1),
            new TestCase(new int[][]{{3,3,3},{1,0,1},{3,3,3}}, 1),
            new TestCase(new int[][]{{3,3,3},{3,0,3},{3,3,3}}, 3),
            new TestCase(new int[][]{{3,3,3},{3,-1,3},{3,3,3}}, 4),
            new TestCase(new int[][]{{3,3,3},{0,0,3},{3,3,3}}, 0),
        };

        Problem407 p = new Problem407();
        for (TestCase t : tests) {
            int actual = p.trappingWater2(t.heightmap);
            assertEquals(t.expected, actual);
        }
    }
}