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
 * Could negative integers be palindromes? (ie, -1)
 * 
 * If you are thinking of converting the integer to string, note the restriction of using extra space.
 * 
 * You could also try reversing an integer. 
 * 
 * However, if you have solved the problem "Reverse Integer", you know that the reversed integer might overflow. 
 * 
 * How would you handle such case?There is a more generic way of solving this problem.
 * 
 * @author CR 2017年6月20日20:46:47
 *
 */
public class PalindromeNumber {
	
	public static boolean isPalindrome(int x) {
		return ( x >= 0 && x == reverse(x) );
	}

    public static int reverse( int x ){
		int y = 0;

		while ( x != 0 ) {
			y = y * 10 + x % 10;
			x /= 10;
		}

		return y;
	}
	public static void main(String[] args) {
		System.out.println(isPalindrome(121 ));
	}
}
