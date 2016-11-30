/**
 * MIT License
 * 
 * Copyright (c) 2016 cris cryeshiren@163.com
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 * 
 */
package com.leetcode;

import java.util.Arrays;

/**
 * Given an array of integers, 
 * return indices of the two numbers such that they add up to a specific target.
 * @author cris 14:57
 * 
 */
public class Answer1TwoSum {

	public static void main(String[] args) {
		System.out.println(Arrays.toString(twoSum(new int[]{1,0,3,4,5,1}, 2)));
	}

	public static int[] twoSum(int[] nums, int target) {
		//定义结果数组
		int[] answer = new int[2];
		//循环数据数组
		for (int i = 0; i < nums.length-1; i++) {
			//如果是target是正数，则另外两个数必然比target小，如果是负数则相反
			if( nums[i]<=target || (target<0 && nums[i]>=target) ){
				//记录当前结果索引
				answer[0] = i;
				for (int j = i+1; j < nums.length; j++) {
					//判断两数相加之和是否等于target
					if( (nums[i]<=target || (target<0 && nums[i]>=target)) && nums[i]+nums[j]==target ){
						answer[1] = j;
						return answer;
					}
				}
			}
		}
        return null;
    }
}
