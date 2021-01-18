package com.java.study.algorithm.structure;

import java.util.Deque;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Stack;

public class BinaryTree<T> {
    private BinaryNode<T> root;

    public BinaryTree() {
        root = null;
    }

    public BinaryTree(T rootData) {
        root = new BinaryNode<>(rootData);
    }

    public BinaryTree(T rootData, BinaryTree<T> leftTree, BinaryTree<T> rightTree) {
        privateSetTree(rootData, leftTree, rightTree);
    }

    public void setTree(T rootData) {
        root = new BinaryNode<>(rootData);
    }

    private void privateSetTree(T rootData, BinaryTree<T> leftTree, BinaryTree<T> rightTree) {
        root = new BinaryNode<>(rootData);
        if((leftTree != null) && !leftTree.isEmpty()) {
            root.setLeftChild(leftTree.root.copy());
        }

        if((rightTree != null) && !leftTree.isEmpty()) {
            root.setRightChild(rightTree.root.copy());
        }
    }

    private boolean isEmpty() {
        return root == null;
    }

    public int getHeight() {
        return root.getHeight(root);
    }

    public int getNumberOfNodes() {
        return root.getNumberOfNodes();
    }

    public void inOrderTraverse() {
        inOrderTraverse(root);
    }

    private void inOrderTraverse(BinaryNode<T> node) {
        if(node != null) {
            System.out.println(node.getData());
            inOrderTraverse(node.getLeftChild());
            inOrderTraverse(node.getRightChild());
        }
    }
    // 中序遍历
    private void iterativeInorderTraverse() {
        Deque<BinaryNode> nodeStack = new LinkedList<>();
        BinaryNode<T> currentNode = root;
        while(!nodeStack.isEmpty() || currentNode != null) {
            while (currentNode != null) {
                nodeStack.push(currentNode);
                currentNode = currentNode.getLeftChild();
            }

            if (!nodeStack.isEmpty()) {
                BinaryNode<T> nextNode = nodeStack.pop();
                assert nextNode != null;
                System.out.println(nextNode.getData());
                currentNode = nextNode.getRightChild();
            }
        }
    }
}

