package com.leetcode.problems;

import java.util.PriorityQueue;

// https://leetcode.com/problems/trapping-rain-water-ii/
// 407 - Trapping Rain Water II
// Given an m x n integer matrix heightMap representing the height of each unit cell in a 2D elevation map, return the volume of water it can trap after raining.
// Input: heightMap = [[1,4,3,1,3,2],[3,2,1,3,2,4],[2,3,3,2,3,1]]
// Output: 4
// Explanation: After the rain, water is trapped between the blocks.
// We have two small ponds 1 and 3 units trapped.
// The total volume of water trapped is 4.
public class Problem407 {
    private class Node {
        public int x;
        public int y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public int trappingWater2(int[][] heightMap) {
        int width=heightMap.length;
        int length=heightMap[0].length;

        PriorityQueue<Node> pq=new PriorityQueue<>(
            (left, right) -> heightMap[left.x][left.y] - heightMap[right.x][right.y]
        );

        boolean visited[][]=new boolean[width][length];

        // Go along the outside of the grid and put the all the nodes we find
        // Put in the top, bottom, and left, right at the same time
        for (int i = 0; i < width; ++i) {
            pq.add(new Node(i, 0));
            pq.add(new Node(i, length - 1));
            visited[i][0] = true;
            visited[i][length-1] = true;
        }

        for (int i = 0; i < length; ++i) {
            pq.add(new Node(0, i));
            pq.add(new Node(width - 1, i));
            visited[0][i] = true;
            visited[width-1][i] = true;
        }
        
        int [][]dirs = {{0,1},{0,-1},{1,0},{-1,0}};
        int water = 0;
        int max = Integer.MIN_VALUE;
        while(pq.size()>0){
            Node node = pq.remove();
            int curLevel = heightMap[node.x][node.y];
            max = Math.max(max, curLevel);

            if (curLevel < max) {
                water += max - curLevel;
            }

            for(int dir[]:dirs){
                int x = node.x + dir[0];
                int y = node.y + dir[1];

                if (x < 0 || x >= width || y < 0 || y >= length || visited[x][y]) {
                    continue;
                }

                pq.add(new Node(x, y));
                visited[x][y] = true;
            }
        }
        return water;
    }
}

// Explanation:
// Let's look at edges.  The edges can't hold any water because it will flow right off the side.
// What we really want is to look at the spaces where the edges are higher than the inside.  Whenever the outside is higher than the inside, the
// inside will hold some water.  So we start with any given node and call it the boundary.  Whenever a boundary is lower than it's neighbors, then
// the neighbors will become the new boundary. So if the all the edges are higher than what's on the inside, you effectively have a tub and we will
// have lots of water.  Let's start by examining the outside edges.  Start with the shortest node and look at its neighbors.  If any neighbors are taller,
// then it will be the new boundary containing water.  Work your way inwards, looking for taller nodes to become the new boundary.

// So, add all the outside nodes to a queue and pop out the shortest.
// We have essentially 2 cases:
// Outside is lower than inside, nothing is held
// Inside is lower that outside, you store the difference between the inside and outside.

// Add all the edge nodes to a heap and then pop off the smallest one.  If it is lower than the current water level, then
// add to the total water held.  Add all of the node's neighbors to the queue if they haven't already been processed.
// Since we are always working off of the smallest node, we're always going from shortest to heighest, so we can just track the water level
// globally.