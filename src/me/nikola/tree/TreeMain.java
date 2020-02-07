package me.nikola.tree;

public class TreeMain {

    public static void main(String[] args) {
        Tree.Node<String> a,b,c,d;
        SLLTree<String> t = new SLLTree<>();

        t.makeRoot("C:");

        a = t.addChild(t.root, "Program Files");
        b=t.addChild(a, "CodeBlocks");
        c = t.addChild(b, "codeblocks.dll");
        c = t.addChild(b, "codeblocks.exe");
        b = t.addChild(a, "Nodepad++");
        c = t.addChild(b, "langs.xml");
        c = t.addChild(b, "notepad++.exe");
        a = t.addChild(t.root, "Users");
        b = t.addChild(a, "Darko");
        c = t.addChild(b, "Desktop");
        c = t.addChild(b, "Downloads");
        c = t.addChild(b, "My Documents");
        c = t.addChild(b, "My Pictures");
        b = t.addChild(a, "Public");
        a = t.addChild(t.root, "Windows");
        b = t.addChild(a, "Media");
        t.printTree();

        System.out.println(t.countMaxChildren());
    }
}

