package me.nikola.binaryTree;

public class BTree<E> {

    public BNode<E> root;

    public BTree() {
        root = null;
    }

    public BTree(E info) {
        root = new BNode<>(info);
    }

    public void makeRoot(E elem) {
        root = new BNode<>(elem);
    }

    public BNode<E> addChild(BNode<E> node, int where, E elem) {
        BNode<E> tmp = new BNode<>(elem);
        if(where == BNode.LEFT) {
            if(node.left != null) {
                //veke postoi element
                return null;
            }
            node.left = tmp;
        } else {
            if(node.right != null) {
                //veke postoi element
                return null;
            }
            node.right = tmp;
        }
        return tmp;
    }

    public void inorder() {
        System.out.println("INORDER: ");
        inorderR(root);
        System.out.println();
    }

    public void inorderR(BNode<E> node) {
        if(node != null) {
            inorderR(node.left);
            System.out.print(node.info.toString()+" ");
            inorderR(node.right);
        }
    }

    public void preorder() {
        System.out.println("PREORDER: ");
        preorderR(root);
        System.out.println();
    }

    public void preorderR(BNode<E> node) {
        if(node != null) {
            System.out.print(node.info.toString()+" ");
            preorderR(node.left);
            preorderR(node.right);
        }
    }

    public void postorder() {
        System.out.println("POSTORDER: ");
        postorderR(root);
        System.out.println();
    }

    public void postorderR(BNode<E> node) {
        if(node != null) {
            postorderR(node.left);
            postorderR(node.right);
            System.out.print(node.info.toString()+" ");
        }
    }

}
