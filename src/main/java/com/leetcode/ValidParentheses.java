package com.leetcode;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * Created with IntelliJ IDEA.
 * Author: CR
 * Date: 2017/8/7
 * Time: 15:47
 * GitHub: https://github.com/cryeshiren
 * Description: Given a string containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.
 *              The brackets must close in the correct order, "()" and "()[]{}" are all valid but "(]" and "([)]" are not.
 */
public class ValidParentheses {
    public boolean isValid(String s) {
        if( s.length() == 0 ){
            return true;
        }
        int smallLeft = 0;
        int smallRight = 0;
        int middleLeft = 0;
        int middleRight = 0;
        int largeLeft = 0;
        int largeRight = 0;

        char[] strs = s.toCharArray();
        String fus  = "";
        for (char c : strs) {
            if( '(' == c ){
                smallLeft++;
            }else if(')' == c){
                smallRight++;
            }else if('[' == c){
                middleLeft++;
            }else if(']' == c){
                middleRight++;
            }else if('{' == c){
                largeLeft++;
            }else if('}' == c){
                largeRight++;
            }
        }
        if( smallLeft != smallRight ){
            return false;
        }else{
            if( s.lastIndexOf('(')>s.lastIndexOf(')') ){
                return false;
            }
        }
        if( middleLeft != middleRight ){
            return false;
        }else{
            if( s.lastIndexOf('[')>s.lastIndexOf(']') ){
                return false;
            }
        }
        if( largeLeft != largeRight ){
            return false;
        }else{
            if( s.lastIndexOf('{')>s.lastIndexOf('}') ){
                return false;
            }
        }

        Map<Character, Character> map = new HashMap<>();
        map.put('(',')');
        map.put('{','}');
        map.put('[',']');

        Stack<Character> stack = new Stack<>();
        for( int i = 0; i<strs.length; i++ ){
            if( strs[i] == '(' ||  strs[i] == '{' || strs[i] == '[' ){
                stack.push(strs[i]);
            }else if( strs[i] == ')' ||  strs[i] == '}' || strs[i] == ']' ){
                if( stack.isEmpty() || strs[i] != map.get(stack.pop()) ){
                    return false;
                }
            }
        }


        return true;
    }

    public static void main(String[] args) {
    }

    @Test
    public void testValid(){
        Assert.assertFalse(isValid("())("));
        Assert.assertFalse(isValid("("));
        Assert.assertFalse(isValid("(]"));
        Assert.assertFalse(isValid("]"));
        Assert.assertFalse(isValid("(][}"));
        Assert.assertFalse(isValid("([)]"));
        Assert.assertFalse(isValid("([{)}]"));
        Assert.assertFalse(isValid("([{([)]}]"));
        Assert.assertTrue(isValid("((())()())"));
        Assert.assertTrue(isValid(""));
    }


}