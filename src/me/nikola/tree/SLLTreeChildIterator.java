package me.nikola.tree;

import java.util.Iterator;
import java.util.NoSuchElementException;
import me.nikola.tree.SLLTree.SLLNode;

public class SLLTreeChildIterator<T> implements Iterator<T> {

    SLLNode<T> start, current;

    public SLLTreeChildIterator(SLLNode<T> node) {
        start = node;
        current = node;
    }

    @Override
    public boolean hasNext() {
        return current != null;
    }

    @Override
    public T next() throws NoSuchElementException {
        if(current != null) {
            SLLNode<T> tmp = current;
            current = current.sibling;
            return tmp.getElement();
        } else {
            throw new NoSuchElementException();
        }
    }
}
