package com.leetcode.problems;

import java.util.HashMap;
// https://leetcode.com/problems/two-sum/
// Given an array of integers nums and an integer target, return indices of the two numbers such that they add up to target.
// You may assume that each input would have exactly one solution, and you may not use the same element twice.
// You can return the answer in any order.
// Constraints:
// 2 <= nums.length <= 104
// -109 <= nums[i] <= 109
// -109 <= target <= 109
public class Problem001 {
  public int[] twoSum(int[] nums, int target) {
    HashMap<Integer, Integer> used = new HashMap<>();
    for (int i = 0; i < nums.length; ++i) {
      int x = nums[i];
      int y = target - x;
      Integer yIdx = used.getOrDefault(y, null);
      if (yIdx != null) {
        return new int[]{i, yIdx};
      } 

      used.put(x, i);
    }

    return null;
  }
}

// Explanation:
// The naive approach is obvious, but gives us O(n^2). Take each number, add it to all other numbers one at a time and see if they match the target.
// Let's try to do better.  target == t. The two numbers we are comparing are x and y. There is a pair in which t = x + y.
// Therefore, t - y = x. We can go through each index (x) and check to see if there is another number that satisfies that condition (y) such that
// t - x = y. Add the numbers to a map as we go so we don't have to search through the array again. Hash maps are O(k). Loop through the array and
// compare our current number with ones we have found already. O(N) for the loop + O(k) for each lookup = O(N)

// Runtime Complexity O(N) - going through the loop
// Space Complexity O(N) - might add every integer to a map
