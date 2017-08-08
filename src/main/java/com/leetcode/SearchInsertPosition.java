package com.leetcode;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created with IntelliJ IDEA.
 * Author: CR
 * Date: 2017/8/8
 * Time: 14:11
 * GitHub: https://github.com/cryeshiren
 * Description: Given a sorted array and a target value, return the index if the target is found. If not, return the index where it would be if it were inserted in order.
 * You may assume no duplicates in the array.
 * Here are few examples.
 * [1,3,5,6], 5 → 2
 * [1,3,5,6], 2 → 1
 * [1,3,5,6], 7 → 4
 * [1,3,5,6], 0 → 0
 */
public class SearchInsertPosition {
    public int searchInsert(int[] nums, int target) {
        if( nums == null || nums.length==0 ){
            return 0;
        }

        for( int i = 0; i<nums.length; i++ ){
            if( nums[i] >= target ){
                return i;
            }
        }
        return target>nums[nums.length-1]?nums.length:0;
    }

    @Test
    public void test(){
        int[] arr1 ={1,3,5,6};
        Assert.assertEquals(2,searchInsert(arr1,5));
        Assert.assertEquals(1,searchInsert(arr1,2));
        Assert.assertEquals(4,searchInsert(arr1,7));
        Assert.assertEquals(0,searchInsert(arr1,0));
    }
}