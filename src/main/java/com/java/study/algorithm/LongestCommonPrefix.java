package com.java.study.algorithm;

/**
 * @Author zibiao cao
 * @date 2021/1/4 10:10
 * @Version 1.0
 */
public class LongestCommonPrefix {
    public static void main(String[] args) {
        String [] strs = {"ab","a"};
        String s = new LongestCommonPrefix().longestCommonPrefix(strs);
        System.out.println(s);
    }
    public String longestCommonPrefix(String[] strs) {
        if(strs.length <= 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for(int m = 0; m< strs[0].length(); m++ ){
            sb.append(strs[0].charAt(m));
            for(int i =1; i<strs.length; i++) {
                while(strs[i].length() < sb.length() || !strs[i].startsWith(sb.toString())) {
                    return sb.substring(0, sb.length() - 1);
                }
            }
        }
        return sb.toString();
    }
}
