package me.nikola.priorityQueueWithHeap;

import java.util.Comparator;

public class PriorityQueueHeap<E extends Comparable<E>> {
    private int N;
    private E elements[];

    private Comparator<? super E> comparator;

    private int compare(E k1, E k2) {
        return (comparator == null ? k1.compareTo(k2) : comparator.compare(k1,k2));
    }

    public int getParent(int i) {
        return (i - 1) / 2;
    }

    public E getAt(int i) {
        return elements[i];
    }

    public int getLeft(int i) {
        return (i * 2) + 1;
    }

    public int getRight(int i) {
        return (i * 2) + 2;
    }

    public void setElement(int index, E elem) {
        elements[index] = elem;
    }

    public void swapElementsAtProvidedIndexes(int i, int j) {
        E tmp = elements[i];
        elements[i] = elements[j];
        elements[j] = tmp;
    }

    public void adjust(int i, int n) {
        while (i < n) {
            int left = getLeft(i);
            int right = getRight(i);
            int largest = i;

            if ((left < n) && (elements[left].compareTo(elements[largest]) > 0))
                largest = left;
            if ((right < n) && (elements[right].compareTo(elements[largest]) > 0))
                largest = right;
            if (largest == i)
                break;

            swapElementsAtProvidedIndexes(i, largest);
            i = largest;
        }
    }

    public void buildHeap() {
        int i;
        for (i = elements.length / 2 - 1; i >= 0; i--)
            adjust(i, elements.length);
    }

    // from here on come new things, up to now was ordinary heap tree

    @SuppressWarnings("unchecked")
    public PriorityQueueHeap(int size) {
        elements = (E[]) new Comparable[size];
        N = 0;
    }

    public boolean insert(E elem) {
        if(N == elements.length) {
            return false; //there is not enough space in the array
        }
        elements[N] = elem;
        N++;
        adjustUp(N-1,N);
        return true;
    }

    public E getMax() {
        if(N == 0) {
            return null;
        }
        return elements[0];
    }

    public E removeMax() {
        if(N == 0) {
            return null;
        }
        E tmp = elements[0];
        elements[0] = elements[N-1];
        N--;
        adjust(0,N);
        return tmp;
    }

    public boolean isEmpty() {
        if (N == 0)
            return true;
        return false;
    }

    public int getSize() {
        return N;
    }

    public void adjustUp(int i, int n) {
        while(i > 0) {
            int parent = getParent(i);
            if(elements[i].compareTo(elements[parent]) <= 0 ) {
                break;
            } else {
                swapElementsAtProvidedIndexes(i, parent);
                i = parent;
            }
        }
    }
}
