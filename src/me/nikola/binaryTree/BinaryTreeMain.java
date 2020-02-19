package me.nikola.binaryTree;

public class BinaryTreeMain {
    public static void main(String[] args) {
        /*
        Constructing this tree:
                     15
                   /     \
                  /       \
                 6         18
               /  \        / \
              3    7      17  20
             / \    \
            2   4    13
                    /
                   9
         */
        BNode<Integer> node = new BNode<Integer>(15);

        node.left = new BNode<>(6);
        node.left.left = new BNode<>(3);
        node.left.left.left = new BNode<>(2);
        node.left.left.right = new BNode<>(4);
        node.left.right = new BNode<>(7);
        node.left.right.right = new BNode<>(13);
        node.left.right.right.left = new BNode<>(9);

        node.right = new BNode<>(18);
        node.right.left = new BNode<>(17);
        node.right.right = new BNode<>(20);

        BTree<Integer> btree = new BTree<>();
        btree.root = node;

        //btree.postorderR(btree.root);

        btree.maxDepth();
    }
}
