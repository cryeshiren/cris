package com.leetcode;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created with IntelliJ IDEA.
 * Author: CR
 * Date: 2017/8/8
 * Time: 16:59
 * GitHub: https://github.com/cryeshiren
 * Description: The count-and-say sequence is the sequence of integers with the first five terms as following:
 * 1.     1
 * 2.     11
 * 3.     21
 * 4.     1211
 * 5.     111221
 * 1 is read off as "one 1" or 11.
 * 11 is read off as "two 1s" or 21.
 * 21 is read off as "one 2, then one 1" or 1211.
 * Given an integer n, generate the nth term of the count-and-say sequence.
 * Note: Each term of the sequence of integers will be represented as a string.
 * Example 1:
 * Input: 1
 * Output: "1"
 * Example 2:
 * Input: 4
 * Output: "1211"
 */
public class CountAndSay {
    public String countAndSay(int n) {
        if( n<=0 ){
            return "";
        }

        return "";
    }

    @Test
    public void test(){
        Assert.assertEquals("1",countAndSay(1));
        Assert.assertEquals("11",countAndSay(2));
        Assert.assertEquals("21",countAndSay(3));
        Assert.assertEquals("1211",countAndSay(4));
        Assert.assertEquals("111221",countAndSay(5));
        Assert.assertEquals("312211",countAndSay(6));
        Assert.assertEquals("13112221",countAndSay(7));
        Assert.assertEquals("1113213211",countAndSay(8));
//        1.     1
//        2.     11
//        3.     21
//        4.     1211
//        5.     111221
//        6.     312211
//        7.     13112221
//        8.     1113213211
//        9.     31131211131221
//        10.     13211311123113112211
    }
}