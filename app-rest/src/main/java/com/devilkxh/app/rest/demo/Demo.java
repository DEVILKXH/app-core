package com.devilkxh.app.rest.demo;

import java.io.File;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author kexiaohong
 * @since 2020/7/20
 */
public class Demo {
    //480
    public double[] medianSlidingWindow(int[] nums, int k) {
        int []num = new int[k];
        double res[] = new double[nums.length - k + 1];
        for(int i = 0; i < k;i ++) {
            num[i] = nums[i];
        }
        Arrays.sort(num);

        for(int i = k; i < nums.length; i ++) {

        }
        return res;

    }

    public static void main(String []agrs) {

//        System.out.println(new Demo().medianSlidingWindow(new int[]{1,3,-1,-3,5,3,6,7}, 3));
        File files = new File("D:\\workspace\\wx\\link-up\\bg");
        String mesx = Arrays.stream(files.listFiles()).map(File::getName).collect(Collectors.joining("\", \"bg/"));
        System.out.println(mesx);
    }
}
