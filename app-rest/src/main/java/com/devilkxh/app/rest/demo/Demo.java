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
    public List<String> findAndReplacePattern(String[] words, String pattern) {
        List<String> list = new ArrayList<>();
        for(String word: words) {
            if (isPattern(word, pattern)) {
                list.add(word);
            }
        }
        return list;
    }

    private boolean isPattern(String word, String pattern) {
        if (word.length() != pattern.length()) {
            return false;
        }
        Map<String, Character> map = new HashMap<>();
        int len = word.length();
        for(int i = 0; i < len; i ++) {
            char w = word.charAt(i);
            char p = pattern.charAt(i);
            String pkey = "p" + p;
            String wkey = "w" + w;

            if (map.containsKey(pkey) && map.containsKey(wkey)) {
                char w1 = map.get(pkey);
                char p1 = map.get(wkey);
                if (w1 != w || p1 != p) {
                    return false;
                }
            } else if (!map.containsKey(pkey) && !map.containsKey(wkey)) {
                map.put(pkey, w);
                map.put(wkey, p);
            }  else {
                return false;
            }
        }
        return true;
    }

    public static void main(String []agrs) {
        new Demo().findAndReplacePattern(new String[]{"abc","deq","mee","aqq","dkd","ccc"}, "abb");
    }
}
