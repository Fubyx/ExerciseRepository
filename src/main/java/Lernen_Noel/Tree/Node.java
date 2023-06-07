package Lernen_Noel.Tree;

import java.util.ArrayList;

public class Node<T> {
    public T value;
    public Node<T> childLeft = null;
    public Node<T> childRight = null;

    public void insertIntoList(ArrayList<T> list){
        list.add(value);
        if(childLeft != null){
            childLeft.insertIntoList(list);
        }
        if(childRight != null){
            childRight.insertIntoList(list);
        }
        return;
    }
}
