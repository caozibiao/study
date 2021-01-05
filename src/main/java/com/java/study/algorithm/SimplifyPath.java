package com.java.study.algorithm;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Stack;

/**
 * @Author zibiao cao
 * @date 2021/1/4 14:04
 * @Version 1.0
 */
public class SimplifyPath {
    public String simplifyPath(String path) {
        String[] pathArray = path.split("/");
        //分割后的几种情况 空格说明是多出来的/，.. .与目录
        StringBuilder res =new StringBuilder();
        Deque<String> stack = new ArrayDeque<>();
        for(int i=0;i<pathArray.length;i++){
            //2种情况，栈为空或者栈不为空
            if(pathArray[i].length()==0||pathArray[i].equals("."))    continue;
            if(!stack.isEmpty()){
                if(pathArray[i].equals("..")){
                    stack.pop();
                }else{
                    stack.push(pathArray[i]);
                }
            }else{
                if(!pathArray[i].equals(".."))  stack.push(pathArray[i]);
            }
        }
        if(stack.isEmpty())    return res.append('/').toString();
        while(!stack.isEmpty()){
            res.insert(0,stack.pop());
            res.insert(0,'/');
        }
        return res.toString();
    }
}
