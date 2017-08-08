package com.leetcode;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created with IntelliJ IDEA.
 * Author: CR
 * Date: 2017/8/8
 * Time: 0:53
 * GitHub: https://github.com/cryeshiren
 * Description: Given an array and a value, remove all instances of that value in place and return the new length.
 * Do not allocate extra space for another array, you must do this in place with constant memory.
 * The order of elements can be changed. It doesn't matter what you leave beyond the new length.
 * Example:
 * Given input array nums = [3,2,2,3], val = 3
 * Your function should return length = 2, with the first two elements of nums being 2.
 */
public class RemoveElement {
    public int removeElement(int[] nums, int val) {
        if( nums == null || nums.length == 0 || (nums.length == 1 && nums[0] == val) ){
            return 0;
        }
        if( nums.length == 1 && nums[0] != val ){
            return 1;
        }

        int kong = -1;
        for( int i = 0; i<nums.length; i++ ){
            if( nums[i] == val ){
                if( kong!=-1 && nums[kong] != val || kong == -1 ){
                    kong = i;
                }
            }else{
                if( kong<i && kong!=-1){
                    int temp = nums[kong];
                    nums[kong] = nums[i];
                    nums[i] = temp;
                    kong++;
                }
            }

        }

        return kong==-1?nums.length:kong;
    }

//8ms
    public int removeElementNoArray(int[] nums, int val) {
        int i = 0;
        for (int j = 0; j < nums.length; j++) {
            if (nums[j] != val) {
                nums[i] = nums[j];
                i++;
            }
        }
        return i;
    }

    @Test
    public void test(){
        int[] arr1 = {3,3,3,5,2,1,5,5};
        Assert.assertEquals(5,removeElement(arr1,5));
    }
}