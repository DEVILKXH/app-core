package com.devilkxh.app.rest.demo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author kexiaohong
 * @since 2020/7/20
 */
public class Demo {
    public int minDominoRotations(int[] A, int[] B) {
        int len = A.length - 1;
        int []a = new int[6];
        for (int i = 0; i < A.length; i++) {
            a[A[i] - 1] ++;
            if (A[i] != B[i]) {
                a[B[i] - 1] ++;
            }
        }

        boolean flag = false;
        int index = 0;
        for (int i = 0; i < a.length; i ++) {
            if (a[i] > len) {
                flag = true;
                index = i + 1;
            }
        }
        if (!flag) {
            return -1;
        }
        int num = 0;
        int num2 = 0;

        for(int i = 0; i < A.length; i ++) {
            if(A[i] == index) {
                num ++;
            }
            if(B[i] == index) {
                num2 ++;
            }
        }
        if (num > len || num2 > len) {
            return 0;
        }
        if (num < num2) {
            return len + 1 - num2;
        } else {
            return len + 1 - num;
        }
    }

    public static void main(String []agrs) {
        new Demo().minDominoRotations(new int[]{2,1,2,4,2,2},  new int[]{5,2,6,2,3,2});
    }
}
