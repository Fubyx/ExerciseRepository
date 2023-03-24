package _11_Generics.bonusAufgabe;

import java.util.*;
// only really useful for dijkstra algorithm
public class FibonacciHeap<T extends Comparable<T>> {
    private FibonacciHeapNode<T> min;
    private int size;

    public FibonacciHeap() {
        min = null;
        size = 0;
    }

    public boolean isEmpty() {
        return min == null;
    }

    public int size() {
        return size;
    }

    public void clear() {
        min = null;
        size = 0;
    }

    public FibonacciHeapNode insert(T key) {
        FibonacciHeapNode<T> node = new FibonacciHeapNode<>(key);
        min = mergeLists(min, node);
        size++;
        return node;
    }

    public T findMin() {
        if (isEmpty()) {
            throw new NoSuchElementException("Heap is empty.");
        }
        return min.getKey();
    }

    public T deleteMin() {
        if (isEmpty()) {
            throw new NoSuchElementException("Heap is empty.");
        }
        FibonacciHeapNode<T> z = min;
        T minKey = z.getKey();
        if (z.getChild() != null) {
            FibonacciHeapNode<T> child = z.getChild();
            do {
                FibonacciHeapNode<T> next = child.getRight();
                min = mergeLists(min, child);
                child.setParent(null);
                child = next;
            } while (child != z.getChild());
        }
        min = removeNode(z);
        size--;
        consolidate();
        return minKey;
    }

    public void decreaseKey(FibonacciHeapNode<T> node, T newKey) {
        if (newKey.compareTo(node.getKey()) > 0) {
            throw new IllegalArgumentException("New key is greater than current key.");
        }
        node.setKey(newKey);
        FibonacciHeapNode<T> parent = node.getParent();
        if (parent != null && node.compareTo(parent) < 0) {
            cut(node, parent);
            cascadingCut(parent);
        }
        if (node.compareTo(min) < 0) {
            min = node;
        }
    }

    private void cut(FibonacciHeapNode<T> node, FibonacciHeapNode<T> parent) {
        node.setParent(null);
        parent.setDegree(parent.getDegree() - 1);
        if (node.getRight() == node) {
            parent.setChild(null);
        } else {
            parent.setChild(node.getRight());
            node.getRight().setLeft(node.getLeft());
            node.getLeft().setRight(node.getRight());
        }
        min = mergeLists(min, node);
        node.setMark(false);
    }

    private void cascadingCut(FibonacciHeapNode<T> node) {
        FibonacciHeapNode<T> parent = node.getParent();
        if (parent != null) {
            if (!node.isMarked()) {
                node.setMark(true);
            } else {
                cut(node, parent);
                cascadingCut(parent);
            }
        }
    }

    private void consolidate() {
        int maxDegree = (int) Math.floor(Math.log(size) / Math.log(1.618));
        FibonacciHeapNode<T>[] A = new FibonacciHeapNode[maxDegree + 1];
        List<FibonacciHeapNode<T>> rootList = new ArrayList<>();
        FibonacciHeapNode<T> node = min;
        if (node != null) {
            rootList.add(node);
            node = node.getRight();
            while (node != min) {
                rootList.add(node);
                node = node.getRight();
            }
        }
        for (FibonacciHeapNode<T> root : rootList) {
            int degree = root.getDegree();
            while (A[degree] != null) {
                FibonacciHeapNode<T> y =A[degree];
                if (root.compareTo(y) > 0) {
                    FibonacciHeapNode<T> temp = root;
                    root = y;
                    y = temp;
                }
                link(y, root);
                A[degree] = null;
                degree++;
            }
            A[degree] = root;
        }
        min = null;
        for (int i = 0; i <= maxDegree; i++) {
            if (A[i] != null) {
                min = mergeLists(min, A[i]);
            }
        }
    }
    private void link(FibonacciHeapNode<T> y, FibonacciHeapNode<T> x) {
        y.getLeft().setRight(y.getRight());
        y.getRight().setLeft(y.getLeft());
        y.setParent(x);
        if (x.getChild() == null) {
            x.setChild(y);
            y.setLeft(y);
            y.setRight(y);
        } else {
            y.setRight(x.getChild());
            y.setLeft(x.getChild().getLeft());
            x.getChild().getLeft().setRight(y);
            x.getChild().setLeft(y);
        }
        x.setDegree(x.getDegree() + 1);
        y.setMark(false);
    }

    private FibonacciHeapNode<T> removeNode(FibonacciHeapNode<T> node) {
        if (node.getRight() == node) {
            return null;
        }
        node.getRight().setLeft(node.getLeft());
        node.getLeft().setRight(node.getRight());
        return node.getRight();
    }

    private FibonacciHeapNode<T> mergeLists(FibonacciHeapNode<T> list1, FibonacciHeapNode<T> list2) {
        if (list1 == null) {
            return list2;
        }
        if (list2 == null) {
            return list1;
        }
        FibonacciHeapNode<T> temp = list1.getRight();
        list1.setRight(list2.getRight());
        list2.getRight().setLeft(list1);
        list2.setRight(temp);
        temp.setLeft(list2);
        return list1.compareTo(list2) < 0 ? list1 : list2;
    }


}

