package com.devilkxh.app.rest.demo;

/**
 * @author kexiaohong
 * @since 2020/8/24
 */
public class Demo2 {

    public boolean repeatedSubstringPattern(String s) {
        int len = s.length();
        int repeat = 1;
        boolean flag = false;
        while(repeat <= len / 2) {
            if(len % repeat != 0) {
                repeat ++;
                continue;
            }
            flag = true;
            for(int i = 0; i < repeat; i ++) {
                char c = s.charAt(i);
                int j = repeat + i;
                while (j < len) {
                   if(c != s.charAt(j)) {
                       flag = false;
                       break;
                   } else {
                       j += repeat;
                   }
                }
                if(!flag) {
                    break;
                }
            }
            if(flag) {
                break;
            }
            repeat ++;
        }
        return flag;
    }

    public static void main(String []agrs) {
//        System.out.println(new Demo2().repeatedSubstringPattern("abac"));
        System.out.println(new Demo2().repeatedSubstringPattern("ababab"));
//        System.out.println(new Demo2().repeatedSubstringPattern("aba"));
//        System.out.println(new Demo2().repeatedSubstringPattern("abcabcabcabc"));
    }
}
