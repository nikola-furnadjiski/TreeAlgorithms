package me.nikola.avlTree;

public class AVLNode<E extends Comparable<E>> {
    public E info;
    public AVLNode<E> left;
    public AVLNode<E> right;
    public int height;

    public AVLNode(E element) {
        this(element, null, null);
    }

    public AVLNode(E i, AVLNode<E> l, AVLNode<E> r) {
        info = i;
        left = l;
        right = r;
        height = 0;
    }

}
