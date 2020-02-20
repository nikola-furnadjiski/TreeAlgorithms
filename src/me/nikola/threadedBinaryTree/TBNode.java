package me.nikola.threadedBinaryTree;

public class TBNode<E> {

    public E info;
    public TBNode<E> left;
    public TBNode<E> right;
    char leftTag;
    char rightTag;

    static final int LEFT = 1;
    static final int RIGHT = 2;

    public TBNode(E info) {
        this.info = info;
        left = null;
        right = null;
        leftTag = '-';
        rightTag = '-';
    }
}
