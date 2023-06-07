package Lernen_Noel.Tree;

public class Main {
    public static void main(String[] args) {
        /*
        Node<Integer> rootNode = new Node<>();
        rootNode.value = 5;
        Node<Integer> leftChild = new Node<>();
        leftChild.value = 2;
        rootNode.childLeft = leftChild;

        System.out.println(rootNode.childRight.value);*/

        Tree<Integer> tree = new Tree<>();//Binärbaum erstellen

        //Werte einfügen (nicht ausbalanciert)
        tree.insert(12);
        tree.insert(3);
        tree.insert(4);
        tree.insert(5);
        tree.insert(6);
        tree.insert(7);
        tree.insert(13);
        tree.insert(15);

        //Überprüfen
        System.out.println(tree.root.value); //Sollwert 12
        System.out.println(tree.root.childLeft.value);//Sollwert 3
        System.out.println(tree.root.childRight.value);//Sollwert 13

        tree.reSort();//Tree neu ordnen mittels ArrayList (avl)

        //Überprüfen
        System.out.println(tree.root.value); //Sollwert 6
        System.out.println(tree.root.childLeft.value);//Sollwert 4
        System.out.println(tree.root.childRight.value);//Sollwert 12

    }
}
