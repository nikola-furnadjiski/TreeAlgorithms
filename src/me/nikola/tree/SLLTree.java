package me.nikola.tree;

import java.util.Iterator;

public class SLLTree<E> implements Tree<E> {

    static class SLLNode<P> implements Node<P> {
        SLLNode<P> parent, sibling, firstChild;
        private P element;

        SLLNode(P elem) {
            element = elem;
            parent = sibling = firstChild = null;
        }

        @Override
        public P getElement() {
            return element;
        }

        @Override
        public void setElement(P element) {
            this.element = element;
        }
    }

    public SLLNode<E> root;

    public SLLTree() {
        root = null;
    }


    @Override
    public Tree.Node<E> root() {
        return root;
    }

    @Override
    public Tree.Node<E> parent(Tree.Node<E> node) {
        return ((SLLNode<E>)node).parent;
    }

    @Override
    public int childCount(Tree.Node<E> node) {
        SLLNode<E> tmp = ((SLLNode<E>) node).firstChild;
        int count=0;
        while(tmp != null) {
            count++;
            tmp = tmp.sibling;
        }
        return count;
    }

    @Override
    public void makeRoot(E elem) {
        root = new SLLNode<>(elem);
    }

    @Override
    public Tree.Node<E> addChild(Tree.Node<E> node, E elem) {
        SLLNode<E> tmp = new SLLNode<E>(elem);
        SLLNode<E> curr = (SLLNode<E>)node;
        tmp.sibling = curr.firstChild;
        tmp.parent = curr;
        curr.firstChild = tmp;
        return tmp;
    }

    @Override
    public void remove(Tree.Node<E> node) {
        SLLNode<E> curr = (SLLNode<E>)node;
        if(curr.parent != null) {
            if(curr.parent.firstChild == curr) {
                //The node to be removed is the first child of its parent
                //Reconnect the parent to the next sibling
                curr.parent.firstChild = curr.sibling;
            } else {
                //The node to be removed is not the first child of its parent
                //Start from the first child and search the node and remove it
                SLLNode<E> tmp = curr.parent.firstChild;
                while(tmp.sibling != curr) {
                    tmp = tmp.sibling;
                }
                tmp.sibling = curr.sibling;
            }
        } else {
            root = null;
        }
    }

    @Override
    public Iterator<E> children(Tree.Node<E> node) {
        return new SLLTreeChildIterator<E>(((SLLNode<E>)node).firstChild);
    }

    public void printTree() {
        printTreeRecursive(root, 0);
    }

    public void printTreeRecursive(Node<E> node, int level) {
        if(node == null) {
            return;
        }
        int i;
        SLLNode<E> tmp;

        for(i = 0; i < level; i++) {
            System.out.print(" ");
        }
        System.out.println(node.getElement().toString());
        tmp = ((SLLNode<E>)node).firstChild;
        while(tmp != null) {
            printTreeRecursive(tmp, level+1);
            tmp = tmp.sibling;
        }
    }

    public int countMaxChildren() {
        return countMaxChildrenRecursive(root);
    }

    public int countMaxChildrenRecursive(SLLNode<E> node) {
        int t = childCount(node);
        SLLNode<E> tmp = node.firstChild;
        while(tmp != null) {
            t = Math.max(t, countMaxChildrenRecursive(tmp));
            tmp = tmp.sibling;
        }
        return t;
    }
}
