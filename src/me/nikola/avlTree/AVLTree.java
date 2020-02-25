package me.nikola.avlTree;

import me.nikola.binarySearchTree.BinarySearchTree;

public class AVLTree<E extends Comparable<E>> {
    private AVLNode<E> root;

    public AVLTree() {
        root = null;
    }

    private int height(AVLNode<E> t) {
        if(t == null) {
            return -1;
        } else {
            return t.height;
        }
    }

    private int max(int lhs, int rhs) {
        if(lhs > rhs) {
            return lhs;
        } else {
            return rhs;
        }
    }

    public int getBalance(AVLNode<E> n) {
        if(n == null) {
            return 0;
        } else {
            return height(n.left) - height(n.right);
        }
    }


    /**
     *          K1                                       x
     *         / \                                    /   \
     *        x   T4      Right Rotate (K1)          y      K1
     *       / \          - - - - - - - - ->       /  \    /  \
     *      y   T3                                T1  T2  T3  T4
     *     / \
     *   T1   T2
     *
     * Left left case means rotate with RIGHT ROTATION.
     */
    private AVLNode<E> leftLeftCase(AVLNode<E> k1) {
        AVLNode<E> x = k1.left;
        k1.left = x.right;
        x.right = k1;
        k1.height = max(height(k1.left),height(k1.right)) + 1;
        x.height = max(height(x.left),height(k1)) + 1;
        return x;
    }

    /**
     *   K2                                x
     *  /  \                            /    \
     * T1   x     Left Rotate(z)       K2     y
     *     /  \   - - - - - - - ->    / \    / \
     *    T2   y                     T1  T2 T3  T4
     *        / \
     *      T3  T4
     *
     *
     * Right right case means rotate with LEFT ROTATION.
     */
    private AVLNode<E> rightRightCase(AVLNode<E> k2) {
        AVLNode<E> x = k2.right;
        k2.right = x.left;
        x.left = k2;
        k2.height = max(height(k2.left),height(k2.right)) + 1;
        x.height = max(height(k2), height(x.right)) + 1;
        return x;
    }

    /**
     *      K3                               K3                             x
     *     / \                             /   \                          /  \
     *    K2   T4  Left Rotate (K2)       x    T4  Right Rotate(K3)    K2      K3
     *   / \      - - - - - - - - ->    /  \      - - - - - - - ->    / \    / \
     * T1   x                          K2    T3                     T1  T2 T3  T4
     *     / \                        / \
     *   T2   T3                    T1   T2
     */
    private AVLNode<E> leftRightCase(AVLNode<E> k3) {
        k3.left = rightRightCase(k3.left);
        return leftLeftCase(k3);
    }

    /**
     *    K4                             K4                              x
     *   / \                            / \                            /   \
     * T1   K1   Right Rotate (K1)    T1   x      Left Rotate(K4)    K4     K1
     *     / \  - - - - - - - - ->      /  \   - - - - - - - ->     / \    / \
     *    x   T4                      T2   K1                    T1  T2  T3  T4
     *   / \                              /  \
     * T2   T3                           T3   T4
     */
    private AVLNode<E> rightLeftCase(AVLNode<E> k4) {
        k4.right = leftLeftCase(k4.right);
        return rightRightCase(k4);
    }

    private AVLNode<E> insert (E x, AVLNode<E> t) {
        if(t == null) {
            t = new AVLNode<E>(x, null, null);
        } else if(x.compareTo(t.info) < 0) { // needs to be inserted in left subtree
            t.left = insert(x, t.left); // goes recursively all the way down until finds place to insert 'x'
            if(height(t.left) - height(t.right) == 2) { // after insertion comes recursively up to find first unbalanced node
                if(x.compareTo(t.left.info) < 0) { // to determine if 'x' was added in the left or right subtree of 't'
                    t = leftLeftCase(t); // if inserted in right subtree make left-left rotation and we are done
                } else {
                    t = leftRightCase(t);  // if inserted in left subtree make left-right rotation and we are done
                }
            }

        } else if(x.compareTo(t.info) > 0) { // needs to be inserted in right subtree
            t.right = insert(x, t.right); // goes recursively all the way down until finds place to insert 'x'
            if(height(t.right)-height(t.left) == 2) { // after insertion comes recursively up to find first unbalanced node
                if(x.compareTo(t.right.info) > 0) { // to determine if 'x' was added in the left or right subtree of 't'
                    t = rightRightCase(t); // if inserted in right subtree make right-right rotation and we are done
                } else {
                    t = rightLeftCase(t); // if inserted in left subtree make right-left rotation and we are done
                }
            }

        } else; // Duplicate; Do nothing

        t.height = max(height(t.left), height(t.right)) + 1;
        return t;
    }

    public void insert (E x) {
        root = insert(x, root);
    }

    public static <T extends Comparable<T>> void printUtil(AVLNode<T> root, int space) {
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
        // 8, 9, 10, 2, 1, 5, 3, 6, 4, 7, 11, 12
        AVLTree<Integer> avlTree = new AVLTree<>();

        avlTree.insert(8);
        System.out.println("--------------------");
        System.out.println("After inserting 8: ");
        avlTree.print();

        avlTree.insert(9);
        System.out.println("--------------------");
        System.out.println("After inserting 9: ");
        avlTree.print();

        avlTree.insert(10);
        System.out.println("--------------------");
        System.out.println("After inserting 10: ");
        avlTree.print();

        avlTree.insert(2);
        System.out.println("--------------------");
        System.out.println("After inserting 2: ");
        avlTree.print();

        avlTree.insert(1);
        System.out.println("--------------------");
        System.out.println("After inserting 1: ");
        avlTree.print();

        avlTree.insert(5);
        System.out.println("--------------------");
        System.out.println("After inserting 5: ");
        avlTree.print();

        avlTree.insert(3);
        System.out.println("--------------------");
        System.out.println("After inserting 3: ");
        avlTree.print();

        avlTree.insert(6);
        System.out.println("--------------------");
        System.out.println("After inserting 6: ");
        avlTree.print();

        avlTree.insert(4);
        System.out.println("--------------------");
        System.out.println("After inserting 4: ");
        avlTree.print();

        avlTree.insert(7);
        System.out.println("--------------------");
        System.out.println("After inserting 7: ");
        avlTree.print();

        avlTree.insert(11);
        System.out.println("--------------------");
        System.out.println("After inserting 11: ");
        avlTree.print();

        avlTree.insert(12);
        System.out.println("--------------------");
        System.out.println("After inserting 12: ");
        avlTree.print();

    }
}
