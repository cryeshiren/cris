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

/**
 * Reverse digits of an integer.
 * Example1: x = 123, return 321
 * Example2: x = -123, return -321
 * 
 * @author CR 2017年6月20日20:23:19
 *
 */
public class ReverseInteger {
	public static int reverse(int x) {
		
//only 3
//		if( x == 0 ){
//			return 0;
//		}
//		int numMid = (x-(x/100)*100)/10;
//		if( x > 0 ){
//			return (numMid+1)*100+numMid*10+numMid-1;
//		}else{
//			numMid = 0-numMid;
//			return 0-((numMid+1)*100+numMid*10+numMid-1);
//		}
		if( x == 0 ){
			return 0;
		}
		boolean flag = false;
		if( x<0 ){
			x = 0-x;
			flag = true;
		}
		StringBuffer num = new StringBuffer(String.valueOf(x));
		num = num.reverse();
		try{
			Integer result = Integer.valueOf(num.toString());
			if( flag ){
				return 0-result;
			}
			return result;
		}catch( Exception e ){
			return 0;
		}
    }
	
	public static void main(String[] args) {
		System.out.println(reverse(-1234567899));
	}
}
