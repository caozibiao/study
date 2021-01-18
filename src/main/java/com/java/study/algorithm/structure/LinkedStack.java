package com.java.study.algorithm.structure;

public class LinkedStack<E> {
    public static void main(String[] args) {
        LinkedStack<Object> stack = new LinkedStack<>();
        stack.push("test1");
        System.out.println(stack.topNode + ", next" + stack.topNode.next);
        stack.push("test2");
        stack.peek();
        stack.pop();
    }

    private Node<E> topNode;

    public LinkedStack() {
        topNode = null;
    }

    public void push(E e) {
        Node<E> newNode = new Node<>(e, topNode);
        topNode = newNode;
    }

    public E peek() {
        // isEmpty()
        return topNode.item;
    }

    public E pop() {
        E top = peek();
        //topNode != null
        topNode = topNode.next;
        return top;
    }

    private class Node<E> {
        private E item;
        private Node<E> next;

        Node(E item, Node<E> topNode) {
            this.item = item;
            next = topNode;
        }
    }
}
