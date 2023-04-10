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
    private class Node implements Comparable<Node>{
        public int x;
        public int y;
        public int h;
        public Node(int x, int y, int h) {
            this.x = x;
            this.y = y;
            this.h = h;
        }

        public int compareTo(Node n) {
            return this.h - n.h;
        }
    }

    public int trappingWater2(int[][] heightmap) {
        // Easy bounds check
        if (heightmap == null || heightmap.length == 0 || heightmap[0].length == 0) {
            return 0;
        }

        PriorityQueue<Node> q = new PriorityQueue<Node>();
        int width = heightmap.length;
        int length = heightmap[0].length;
        boolean[][] visited = new boolean[width][length];

        // Go along the outside of the grid and put the all the nodes we find
        // Put in the top, bottom, and left, right at the same time
        for (int i = 0; i < width; ++i) {
            q.add(new Node(i, 0, heightmap[i][0]));
            q.add(new Node(i, 0, heightmap[i][length - 1]));
            visited[i][0] = true;
            visited[i][length-1] = true;
        }

        for (int i = 0; i < length; ++i) {
            q.add(new Node(0, i, heightmap[0][i]));
            q.add(new Node(width - 1, i, heightmap[width - 1][i]));
            visited[0][i] = true;
            visited[width-1][i] = true;
        }

        // I now have all the outside edges in the heap.  Let's start examining neighbors.  We are effectively doing a BFS.
        int level = Integer.MIN_VALUE;
        int water = 0;
        int[][] dir = new int[][]{{0, 1},{0, -1},{1, 0},{-1, 0}};
        while (!q.isEmpty()) {
            Node node = q.poll();

            // If the height of the node is less than level of the water,
            // increase the total water held.
            // Otherwise, the max we are able to hold is now the height of the node.
            if (node.h < level) {
                water += level - node.h;
            } else {
                level = node.h;
            }
            
            for (int i = 0; i < dir.length; ++i) {
                int x = node.x + dir[i][0];
                int y = node.y + dir[i][1];
                if (x < 0 || x >= width || y < 0 || y >= length || visited[x][y] == true) {
                    continue;
                }

                int h = heightmap[x][y];
                q.add(new Node(x, y, h));
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