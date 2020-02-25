package me.nikola.heapTree;

import java.util.Comparator;

public class Heap<E extends Comparable<E>> {
    public E elements[];

    private Comparator<? super E> comparator;

    private int compare(E k1, E k2) {
        return (comparator == null ? k1.compareTo(k2) : comparator.compare(k1, k2));
    }

    public int getParent(int i) {
        return (i - 1) / 2;
    }

    public int getLeft(int i) {
        return (2 * i) + 1;
    }

    public int getRight(int i) {
        return (2 * i) + 2;
    }

    public E getAt(int i) {
        return elements[i];
    }

    public void setElement(int index, E elem) {
        elements[index] = elem;
    }

    public void swapElementsAtGivenIndexes(int i, int j) {
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

            swapElementsAtGivenIndexes(i, largest);
            i = largest;
        }
    }

    public void buildHeap() {
        int i;
        for (i = elements.length / 2 - 1; i >= 0; i--)
            adjust(i, elements.length);
    }

    public void heapSort() {
        int i;
        buildHeap();
        for(i=elements.length; i>1; i--) {
            swapElementsAtGivenIndexes(0, i-1);
            adjust(0, i-1);
        }
    }

    public static void main(String[] args) {
        Heap<Integer> heapTree = new Heap<>();
        Integer[] el = {16, 4, 10, 14, 7, 9, 3, 2, 8, 1};
        heapTree.elements = el;

        heapTree.heapSort();
        for(int i=0; i<heapTree.elements.length; i++)
        System.out.print(heapTree.elements[i] + " ");
    }

}


