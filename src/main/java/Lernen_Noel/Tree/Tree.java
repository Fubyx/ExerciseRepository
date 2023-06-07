package Lernen_Noel.Tree;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Comparator;

public class Tree<T extends Comparable<T>> {
    public Node<T>root = null;
    public void insert(T value){
        if(root == null){
            root = new Node<>();
            root.value = value;
            return;
        }
        Node<T>currentNode = root;
        while (true){
            if(root.value.compareTo(value) > 0){
                if(currentNode.childLeft == null){
                    currentNode.childLeft = new Node<>();
                    currentNode.childLeft.value = value;
                    break;
                }else{
                    currentNode = currentNode.childLeft;
                }
            }else if(root.value.compareTo(value) < 0){
                if(currentNode.childRight == null){
                    currentNode.childRight = new Node<>();
                    currentNode.childRight.value = value;
                    break;
                }else{
                    currentNode = currentNode.childRight;
                }
            }else{
                throw new RuntimeException("Number already contained");
            }
        }
    }
    public void reSort(){
        ArrayList<T> valuesSorted = new ArrayList<>();

        root.insertIntoList(valuesSorted);//Werte des Tree in die ArrayList einfügen

        //ArrayList sortieren
        valuesSorted.sort(new Comparator<T>() {//Möglichkeit 1
            @Override
            public int compare(T o1, T o2) {
                return o1.compareTo(o2);
            }
        });
        valuesSorted.sort((o1, o2) -> o1.compareTo(o2));//Möglichkeit 2
        valuesSorted.sort(Comparable::compareTo);//Möglichkeit 3

        root = null;
        reSortRecursion(valuesSorted);//Tree neu erstellen mit geordneter ArrayList / AVL-Tree für aktuellen Stand erstellen
    }

    public void reSortRecursion(ArrayList<T> list){
        if(list.size() == 0){
            return;
        }
        int indexHalf = (list.size()-1)/2;// Den Zentralen index berechnen

        this.insert(list.get(indexHalf)); //Den Wert an dem Index in den Tree einfügen

        // Neue ArrayList für die Werte links vom zentralen Wert erstellen
        ArrayList<T> leftList = new ArrayList<>();
        for(int i = 0; i < indexHalf; ++i){
            leftList.add(list.get(i));
        }
        reSortRecursion(leftList);//Die Methode erneut mit der linken Liste aufrufen

        // Neue ArrayList für die Werte rechts vom zentralen Wert erstellen
        ArrayList<T>rightList = new ArrayList<>();
        for(int i = indexHalf + 1; i < list.size(); ++i){
            rightList.add(list.get(i));
        }
        reSortRecursion(rightList);//Die Methode erneut mit der rechten Liste aufrufen
    }

    public void remove (T value){
        ArrayList<T> valuesSorted = new ArrayList<>();

        root.insertIntoList(valuesSorted);//Werte des Tree in die ArrayList einfügen

        valuesSorted.remove(value);

        valuesSorted.sort(Comparable::compareTo);//Möglichkeit 3

        root = null;
        reSortRecursion(valuesSorted);//Tree neu erstellen mit geordneter ArrayList / AVL-Tree für aktuellen Stand erstellen
    }
}
