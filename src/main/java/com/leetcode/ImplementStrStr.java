package com.leetcode;

/**
 * Created with IntelliJ IDEA.
 * Author: CR
 * Date: 2017/8/8
 * Time: 12:57
 * GitHub: https://github.com/cryeshiren
 * Description: Implement strStr().
 * Returns the index of the first occurrence of needle in haystack, or -1 if needle is not part of haystack.
 */
public class ImplementStrStr {
    public int strStr(String haystack, String needle) {
        return haystack==null || needle == null ? -1 : haystack.indexOf(needle);
    }
}