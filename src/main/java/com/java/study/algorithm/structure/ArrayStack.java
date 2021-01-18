package com.java.study.algorithm.structure;

import java.util.Arrays;

public class ArrayStack<E> {
    private E[] stack;
    private int topIndex;
    private boolean initialized = false;
    private static final int DEFAULT_CAPACITY = 50;
    private static final int MAX_CAPACITY = 1000;

    public ArrayStack() {
        this(DEFAULT_CAPACITY);
    }

    public ArrayStack(int initCapacity) {
        //checkCapacity(initCapacity);
        E [] tempStack = (E[])new Object[initCapacity];
        stack = tempStack;
        topIndex = -1;
        initialized = true;
    }

    public void push(E item) {
        // checkInitialization()
        ensureCapacity();
        stack[topIndex + 1] = item;
        topIndex++;
    }

    private void ensureCapacity() {
        if(topIndex == stack.length - 1) {
            int newLength = 2 * stack.length;
            //checkCapacity(newLength)
            stack = Arrays.copyOf(stack, newLength);
        }
    }

    public E peek() {
        // checkInitialization()
        // isEmpty
        return stack[topIndex];
    }

    public E pop() {
        // checkInitialization()
        // isEmpty
        E top = stack[topIndex];
        stack[topIndex] = null;
        topIndex--;
        return pop();
    }
}
