package me.nikola.binarySearchTree;

public class BSNode<E extends Comparable<E>> {
    public E info;
    public BSNode<E> left;
    public BSNode<E> right;

    public BSNode(E info) {
        this.info = info;
        left = null;
        right = null;
    }

    public BSNode(E info, BSNode<E> left, BSNode<E> right) {
        this.info = info;
        this.left = left;
        this.right = right;
    }


}
