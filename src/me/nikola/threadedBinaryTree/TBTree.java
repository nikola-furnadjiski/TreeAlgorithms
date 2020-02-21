package me.nikola.threadedBinaryTree;

public class TBTree<E> {

    public TBNode<E> head;

    public TBTree() {
        head = new TBNode<E>(null);

        //by definition if there is no root i.e. if tree is empty we put left pointer to itself (head)
        head.left = head;
        head.leftTag = '-';

        //in head node the right pointer always points to itself (head)
        head.right = head;
        head.rightTag = '+';
    }

    public TBNode<E> makeRoot(E elem) {
        TBNode<E> tmp = new TBNode<>(elem);
        head.left = tmp;
        head.leftTag = '+';
        tmp.left = head;
        tmp.right = head;
        return tmp;
    }

    public TBNode<E> addChild(TBNode<E> node, int where, E elem) {
        TBNode<E> tmp = new TBNode<>(elem);

        if (where == TBNode.LEFT) {
            if (node.leftTag == '+')
                return null; //there is already a child in that place
            tmp.left = node.left;
            tmp.right = node;
            node.left = tmp;
            node.leftTag = '+';
        } else {
            if (node.rightTag == '+')
                return null; //there is already a child in that place
            tmp.right = node.right;
            tmp.left = node;
            node.right = tmp;
            node.rightTag = '+';
        }
        return tmp;
    }

    public TBNode<E> predecessorInorder(TBNode<E> node) {
        if (node.leftTag == '-')
            return node.left;

        TBNode<E> p = node.left;
        while (p.rightTag == '+')
            p = p.right;
        return p;
    }

    public TBNode<E> successorInorder(TBNode<E> node) {
        if (node.rightTag == '-')
            return node.right;

        TBNode<E> p = node.right;
        while (p.leftTag == '+')
            p = p.left;
        return p;
    }

    public void inorderNonRecursive() {
        if (head.left == head) //is tree is empty
            return;

        System.out.print("INORDER (nonrecursive): ");

        TBNode<E> node = head.left;
        while (node.leftTag == '+')
            node = node.left;

        while (node != head) {
            System.out.print(node.info.toString() + " ");
            node = successorInorder(node);
        }
    }

    public TBNode<E> successorPreorder(TBNode<E> node) {
        if (node.leftTag == '+')
            return node.left;
        if (node.rightTag == '+')
            return node.right;

        //if there is no left nor right subtree, successor is the root of right subtree
        // in which left tree is P (we iterate over nodes while we find a link)
        TBNode<E> p = node;
        while(p.rightTag == '-')
            p = p.right;

        if(p == head)
            return head; //we reached the head, we can return it or null
        else
            return p.right;

    }

    public void preorderNonRecursive() {
        if(head.leftTag == '-')
            return; //empty tree

        System.out.println("PREORDER (nonrecursive): ");

        TBNode<E> p = head.left; //i.e. root

        while(p != head) {
            System.out.print(p.info.toString() + " ");
            p = successorPreorder(p);
        }
        System.out.println();
    }

    public TBNode<E> predcessorPostorder(TBNode<E> node) {
        if(node.rightTag == '+')
            return node.right;
        if(node.leftTag == '-')
            node = node.left;
        return node.left;
    }

    public void postorderOppNonRecursive() {
        if(head.leftTag == '-')
            return; //empty tree

        System.out.println("POSTORDER OPPOSITE (nonrecursive) :");
        TBNode<E> p = head.left; //root

        while(true) {
            System.out.print(p.info.toString() + " ");
            p = predcessorPostorder(p);
            if(p == head.left)
                break;
        }
        System.out.println();
    }

    public TBNode<E> insertRight(TBNode<E> parent, E info) {
        TBNode<E> child = new TBNode<>(info);

        child.leftTag = '-';
        child.left = parent;
        child.rightTag = parent.rightTag;
        child.right = parent.right;

        parent.right = child;
        parent.rightTag = '+';

        if(child.rightTag == '+') {
            TBNode<E> temp = child.right;
            while(temp.leftTag == '+')
                temp = temp.left;
            temp.left = child;
        }

        return child;
    }
}
