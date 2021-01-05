package com.java.study.algorithm;

import org.springframework.util.StringUtils;

/**
 * @Author zibiao cao
 * @date 2021/1/4 13:41
 * @Version 1.0
 */
public class ReverseWords {
    public static void main(String[] args) {
        String s = new ReverseWords().reverseWords("a good   example");
        System.out.println(s);
    }
    public String reverseWords(String s) {
        String[] strings = s.split(" ");
        StringBuilder sb = new StringBuilder();
        for(int i =strings.length-1; i>= 0; i--) {
            if(StringUtils.hasLength(strings[i])){
                sb.append(strings[i]).append(" ");
            }

        }
        return sb.toString().trim();
    }
}
