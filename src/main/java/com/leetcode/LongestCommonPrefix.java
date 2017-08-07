package com.leetcode;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created with IntelliJ IDEA.
 * Author: CR
 * Date: 2017/8/7
 * Time: 12:29
 * GitHub: https://github.com/cryeshiren
 * Description: Write a function to find the longest common prefix string amongst an array of strings.
 */
public class LongestCommonPrefix {
    public static String longestCommonPrefix(String[] strs) {

        if( strs.length<=0 ){
            return "";
        }
        int longestLength = 0;

        char[][] strss = new char[strs.length][];
        int minLength = strs[0].length();
        for( int i= 0; i<strs.length; i++ ){
            strss[i] = strs[i].toCharArray();
            if( strss[i].length<minLength ){
                minLength = strss[i].length;
            }
        }

        int[] rr = new int[minLength];

        for( int i = 0; i<strss[0].length; i++ ){
            for( int j = 0; j<strss.length; j++ ){
                if( i<=minLength-1 ){//确保下一行有值
                    rr[i] += strss[j][i];
                }else{
                    j = strss.length;
                    i= strss[0].length;
                }
            }
        }

        boolean flag = true;
        for( int i = 0; i<rr.length; i++ ){
            for( int j = 0; j<strss.length; j++ ){
                if( (rr[i]/strss.length) == strss[j][i] && rr[i]%strss.length == 0 ){
                }else{
                    flag = false;
                    j = strss.length;
                    i = rr.length;
                }
            }
            if(flag)
                longestLength ++ ;
        }
        return strs[0].substring(0,longestLength);
    }

    public static void main(String[] args) {
//        String[] strs = {"1", "12", "4"};
//        String[] strs = {"1", "12", "14"};
//        String[] strs = {"1"};
//        String[] strs = {""};
//        String[] strs = {};
//        String[] strs = {"123","sadf","aaaaa"};
        String[] strs = {"baac","acb","bacc","cb"};

        System.out.println(longestCommonPrefix(strs));
    }

    @Test
    public void testLongest(){
        String[] strs1 = {"1", "12", "4"};
        String[] strs2 = {"1", "12", "14"};
        String[] strs3 = {"1"};
        String[] strs4 = {""};
        String[] strs5 = {};
        String[] strs6 = {"123","sadf","aaaaa"};
        String[] strs7 = {"aa","a"};
        String[] strs8 = {"abab","aba","abc"};
        String[] strs9 = {"baac","acb","bacc","cb"};
        Assert.assertEquals("",longestCommonPrefix(strs1));
        Assert.assertEquals("1",longestCommonPrefix(strs2));
        Assert.assertEquals("1",longestCommonPrefix(strs3));
        Assert.assertEquals("",longestCommonPrefix(strs4));
        Assert.assertEquals("",longestCommonPrefix(strs5));
        Assert.assertEquals("",longestCommonPrefix(strs6));
        Assert.assertEquals("a",longestCommonPrefix(strs7));
        Assert.assertEquals("ab",longestCommonPrefix(strs8));
        Assert.assertEquals("",longestCommonPrefix(strs9));
    }
}