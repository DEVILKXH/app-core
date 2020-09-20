package com.devilkxh.app.rest.demo;

/**
 * @author kexiaohong
 * @since 2020/8/24
 */
public class Demo2 {

    public boolean isNumber(String s) {
        int len = s.length();
        int num = 0;
        int point = 0;
        if(len == 0) {
            return false;
        }
        s = s.trim();
        int index = 0;
        if(s.charAt(index) == '+' || s.charAt(index) == '-') {
            index ++;
        }
        for(;index < len; index ++) {
            char c = s.charAt(index);
            if(c == '.' && point == 0) {
                point ++;
                continue;
            }
            if(!isNum(c)) {
                return false;
            }

            if(c == 'e' || c == 'E') {
                if(num == 0) {
                    return false;
                }
                return isAllNum(index + 1, s);
            } else {
                num ++;
            }
        }
        if(num != 0) {
            return true;
        }
        return false;
    }

    public boolean isAllNum(int index, String s) {
        int len = s.length();
        int num = 0;
        if(len == 0 || index == len) {
            return false;
        }
        if(s.charAt(index) == '+' || s.charAt(index) == '-') {
            index ++;
        }
        for(;index < len; index ++) {
            char c = s.charAt(index);
            if(!isRealNum(c)) {
                return false;
            }
            num ++;
        }
        if(num == 0) {
            return false;
        }
        return true;

    }

    public boolean isNum(char c) {
        return isRealNum(c) || isENum(c);
    }

    public boolean isRealNum(char c) {
        if(c >= '0' && c <= '9') {
            return true;
        }
        return false;
    }

    public boolean isENum(char c) {
        if(c == 'e' || c == 'E') {
            return true;
        }
        return false;
    }


    public static void main(String []agrs) {
        System.out.println(new Demo2().isNumber("1"));
        System.out.println(new Demo2().isNumber("5e2"));
        System.out.println(new Demo2().isNumber("-123"));
        System.out.println(new Demo2().isNumber("-1E-16"));
        System.out.println(new Demo2().isNumber("12e"));
        System.out.println(new Demo2().isNumber("1a3.14"));
        System.out.println(new Demo2().isNumber("1.2.3"));
        System.out.println(new Demo2().isNumber("+-5"));
        System.out.println(new Demo2().isNumber("12e+5.4"));
    }
}
