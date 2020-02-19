package me.nikola.binaryTree;

import java.util.Stack;

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

    public void inorderNonRecursive() {
        System.out.print("INORDER (nonrecursive): ");
        Stack<BNode<E>> s = new Stack<>();
        BNode<E> node = root;
        while(true) {
            // pridvizuvanje do kraj vo leva nasoka pri sto site koreni
            // na poddrva se dodavaat vo stekot za podocnezna obrabotka
            while(node != null) {
                s.push(node);
                node = node.left;
            }

            // ako stekot e prazen znaci deka drvoto e celosno izminato
            if(s.isEmpty()) {
                break;
            }

            node = s.peek();
            System.out.print(node.info.toString() + " ");
            s.pop();
            node = node.right;
        }
        System.out.println();
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

    public void preorderNonRecursive() {
        System.out.print("PREORDER (nonrecursive): ");
        Stack<BNode<E>> s = new Stack<>();
        s.push(root);

        while(!s.isEmpty()) {
            BNode<E> node = s.pop();
            System.out.print(node.info.toString() + " ");

            if(node.right != null) {
                s.push(node.right);
            }
            if(node.left != null) {
                s.push(node.left);
            }
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

    public void postorderNonRecursive() {
        System.out.print("POSTORDER (nonrecursive): ");
        // Create two stacks
        Stack<BNode<E>>s1 = new Stack<>();
        Stack<BNode<E>>s2 = new Stack<>();

        if (root == null)
            return;

        // push root to first stack
        s1.push(root);

        // Run while first stack is not empty
        while (!s1.isEmpty()) {
            // Pop an item from s1 and push it to s2
            BNode<E> temp = s1.pop();
            s2.push(temp);

            // Push left and right children ofremoved item to s1
            if (temp.left != null)
                s1.push(temp.left);
            if (temp.right != null)
                s1.push(temp.right);
        }

        // Print all elements of second stack
        while (!s2.isEmpty()) {
            BNode<E> temp = s2.pop();
            System.out.print(temp.info.toString() + " ");
        }
    }

    public void insideNodes() {
        System.out.println(insideNodesR(root));
    }

    public int insideNodesR(BNode<E> node) {
        if(node == null)
            return 0;
        if(node.left == null && node.right == null)
            return 0;

        return insideNodesR(node.left) +insideNodesR(node.right) + 1;
    }

    public void leafNodes() {
        System.out.println(leafNodesR(root));
    }

    public int leafNodesR(BNode<E> node) {
        if(node != null) {
            if (node.left == null && node.right == null)
                return 1;
            return leafNodesR(node.left) + leafNodesR(node.right);
        } else {
            return 0;
        }
    }

    public void maxDepth() {
        System.out.println(maxDepthR(root));
    }

    public int maxDepthR(BNode<E> node) {
        if(node == null) {
            return 0;
        }
        if(node.left == null && node.right == null) {
            return 1;
        }
        return Math.max(maxDepthR(node.left), maxDepthR(node.right)) + 1;
    }

    public void mirror() {
        mirrorR(root);
    }

    public void mirrorR(BNode<E> node) {
        BNode<E> tmp;

        if(node == null)
            return;

        // simetricno preslikuvanje na levoto i desnoto poddrvo
        mirrorR(node.left);
        mirrorR(node.right);

        // smena na ulogite na pokazuvacite na momentalniot jazel
        tmp = node.left;
        node.left = node.right;
        node.right = tmp;
    }


}
