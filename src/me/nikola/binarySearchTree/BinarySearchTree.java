package me.nikola.binarySearchTree;

public class BinarySearchTree<E extends Comparable<E>> {
    private BSNode<E> root;

    public BinarySearchTree() {
        root = null;
    }

    private BSNode<E> insert(E x, BSNode<E> t) {
        if (t == null) {
            t = new BSNode<E>(x, null, null);
        } else if (x.compareTo(t.info) < 0) {
            t.left = insert(x, t.left);
        } else if (x.compareTo(t.info) > 0) {
            t.right = insert(x, t.right);
        } else ; //duplicate, do nothing

        return t;
    }

    public void insert(E x) {
        root = insert(x, root);
    }

    private BSNode<E> findMin(BSNode<E> t) {
        if (t == null) {
            return null;
        } else if (t.left == null) {
            return t;
        }
        return findMin(t.left);
    }

    public E findMin() {
        return findMin(root).info;
    }

    private BSNode<E> findMax(BSNode<E> t) {
        if (t == null) {
            return null;
        } else if (t.right == null) {
            return t;
        }
        return findMin(t.right);
    }

    public E findMax() {
        return findMin(root).info;
    }

    private BSNode<E> find(E x, BSNode<E> t) {
        if (t == null) {
            return null;
        }

        if (x.compareTo(t.info) < 0) {
            find(x, t.left);
        } else if (x.compareTo(t.info) > 0) {
            find(x, t.right);
        }
        return t; //Match!
    }

    public BSNode<E> find(E x) {
        return find(x, root);
    }

    private BSNode<E> remove(E x, BSNode<E> t) {
        if (t == null) {
            return t; //Item not found, do nothing
        }

        if (x.compareTo(t.info) < 0) {
            t.left = remove(x, t.left);
        } else if (x.compareTo(t.info) > 0) {
            t.right = remove(x, t.right);
        } else if (t.left != null && t.right != null) { //two children
            t.info = findMin(t.right).info;
            t.right = remove(t.info, t.right);
        } else {
            if (t.left != null) {
                return t.left;
            } else {
                return t.right;
            }
        }
        return t;
    }

    public void remove(E x) {
        root = remove(x, root);
    }

    public static <T extends Comparable<T>> void printUtil(BSNode<T> root, int space) {
        final int COUNT = 10;
        // Base case
        if (root == null)
            return;

        // Increase distance between levels
        space += COUNT;

        // Process right child first
        printUtil(root.right, space);

        // Print current node after space
        // count
        System.out.print("\n");
        for (int i = COUNT; i < space; i++)
            System.out.print(" ");
        System.out.print(root.info + "\n");

        // Process left child
        printUtil(root.left, space);
    }

    public void print() {
        printUtil(root, 0);
    }

    public static void main(String[] args) {
        BinarySearchTree<Integer> bsTree = new BinarySearchTree<>();
        bsTree.insert(4);
        bsTree.insert(7);
        bsTree.insert(6);
        bsTree.insert(10);
        bsTree.insert(16);
        bsTree.insert(2);
        bsTree.insert(8);
        bsTree.insert(23);
        bsTree.insert(1);
        bsTree.insert(18);
        bsTree.insert(12);

        bsTree.print();
    }
}
