package com.java.study.algorithm;

import java.util.HashSet;

/**
 以 \texttt{(a)bcabcbb}(a)bcabcbb 开始的最长字符串为 \texttt{(abc)abcbb}(abc)abcbb；
 以 \texttt{a(b)cabcbb}a(b)cabcbb 开始的最长字符串为 \texttt{a(bca)bcbb}a(bca)bcbb；
 以 \texttt{ab(c)abcbb}ab(c)abcbb 开始的最长字符串为 \texttt{ab(cab)cbb}ab(cab)cbb；
 以 \texttt{abc(a)bcbb}abc(a)bcbb 开始的最长字符串为 \texttt{abc(abc)bb}abc(abc)bb；
 以 \texttt{abca(b)cbb}abca(b)cbb 开始的最长字符串为 \texttt{abca(bc)bb}abca(bc)bb；
 以 \texttt{abcab(c)bb}abcab(c)bb 开始的最长字符串为 \texttt{abcab(cb)b}abcab(cb)b；
 以 \texttt{abcabc(b)b}abcabc(b)b 开始的最长字符串为 \texttt{abcabc(b)b}abcabc(b)b；
 以 \texttt{abcabcb(b)}abcabcb(b) 开始的最长字符串为 \texttt{abcabcb(b)}abcabcb(b)。

 */
public class MaxSubStrLen {
    public int lengthOfLongestSubstring(String s) {
        int len = s.length();
        HashSet<Character> set = new HashSet<>();
        int rp = -1;
        int num = 0;
        for(int i =0; i< len; i++) {
            if(i != 0) {
                set.remove(s.charAt(i-1));
            }
            while (rp + 1 < len && !set.contains(s.charAt(rp + 1))) {
                set.add(s.charAt(rp + 1));
                ++rp;
            }
            num = Math.max(num, rp -i +1);
        }
        return num;
    }
}
