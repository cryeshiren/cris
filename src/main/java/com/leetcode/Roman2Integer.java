package com.leetcode;

/**
 *      Given a roman numeral, convert it to an integer.

        Input is guaranteed to be within the range from 1 to 3999.
 */
public class Roman2Integer {
    public static int romanToInt(String s){
        char[] roman = {'I','V','X','L','C','D','M'};
        int[] num =     {1,   5, 10,  50,  100,500,1000};
//        XCIV
//        100-10+4
        char[] romans = s.toCharArray();
        int pos = -1;
        //计算出罗马数字串中最大单字符的位置
        for( int i = roman.length-1; i>=0; i-- ){
            for( int j = 0; j<romans.length; j++ ){
                if( roman[i] == romans[j] ){
                    pos = j;
                    i = -1;
                    break;
                }
            }
        }

        //计算出罗马字符串中每个字符的值
        int[] resultNum = new int[romans.length];
        for( int i = 0; i<romans.length; i++ ){
            for( int j = 0; j<roman.length; j++ ){
                if( romans[i] == roman[j] ){
                    resultNum[i] = num[j];
                    continue;
                }
            }
        }
        int result = 0;
        //数值计算
        //额外情况
        //左减情况
        for( int i = 0; i<resultNum.length; i++ ) {
            if( i<pos ){
                result -= resultNum[i];
            }else{
                if( i<resultNum.length-1 && resultNum[i+1] > resultNum[i] ){
                    result -= resultNum[i];
                }else{
                    result += resultNum[i];
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
//        System.out.println(romanToInt("rromanToInt"));
//        System.out.println(romanToInt("C"));
//        System.out.println(romanToInt("MDCCC"));
//        System.out.println(romanToInt("LXXXIX"));
        System.out.println(romanToInt("LXXXVIII"));
        System.out.println(romanToIntGood("LXXXVIII"));
    }

    //good solution
    public static int romanToIntGood(String s) {
        char[] sc = s.toCharArray();
        int sum = 0;
        for (int index = sc.length - 1; index >= 0; index--) {
            if (sc[index] == 'I') {
                sum += (sum < 5 ? 1 : -1);
            } else if (sc[index] == 'V') {
                sum += 5;
            } else if (sc[index] == 'X') {
                sum += (sum < 50 ? 10 : -10);
            } else if (sc[index] == 'L') {
                sum += 50;
            } else if (sc[index] == 'C') {
                sum += (sum < 500 ? 100 : -100);
            } else if (sc[index] == 'D') {
                sum += 500;
            } else if (sc[index] == 'M') {
                sum += 1000;
            }
        }
        return sum;
    }
}
