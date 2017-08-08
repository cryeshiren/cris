package com.leetcode;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.TreeSet;

/**
 * Created with IntelliJ IDEA.
 * Author: CR
 * Date: 2017/8/8
 * Time: 0:04
 * GitHub: https://github.com/cryeshiren
 * Description:Given a sorted array,
 * remove the duplicates in place such that each element appear only once and return the new length.
 * Do not allocate extra space for another array, you must do this in place with constant memory.
 * For example,
 * Given input array nums = [1,1,2],
 * Your function should return length = 2, with the first two elements of nums being 1 and 2 respectively.
 * It doesn't matter what you leave beyond the new length.
 */
public class RemoveDuplicatesFromSortedArray {
    public static int removeDuplicates(int[] nums) {
        if (nums.length < 2)
            return nums.length;

        int j = 0;
        int i = 1;

        while (i < nums.length) {
            if (nums[i] == nums[j]) {
                i++;
            } else {
                j++;
                nums[j] = nums[i];
                i++;
            }
        }

        return j + 1;
    }

    @Test
    public void test() {
        int[] arr1 = {1, 1, 2};
        int[] arr2 = {1, 1, 1};
        int[] arr3 = {1, 1, 1, 2, 4, 5, 6};
        Assert.assertEquals(2, removeDuplicates(arr1));
        Assert.assertEquals(1, removeDuplicates(arr2));
        Assert.assertEquals(5, removeDuplicates(arr3));
    }
}