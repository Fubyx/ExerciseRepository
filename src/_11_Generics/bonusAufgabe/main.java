package _11_Generics.bonusAufgabe;

class main {
    public static void main(String[] args) {
        FibonacciHeap<Integer> heap = new FibonacciHeap<>();
        FibonacciHeapNode<Integer> node30 = heap.insert(30);
        heap.insert(20);
        heap.insert(10);
        heap.insert(40);
        heap.insert(50);
        System.out.println("Minimum value: " + heap.findMin()); // output: Minimum value: 10
        heap.deleteMin();
        System.out.println("Minimum value after deleteMin: " + heap.findMin()); // output: Minimum value after deleteMin: 20
        heap.decreaseKey(node30, 5);
        System.out.println("Minimum value after decreaseKey: " + heap.findMin()); // output: Minimum value after decreaseKey: 5
    }
}
/*
        findMin(): O(1)
        insert(T key): O(1)
        deleteMin(): O(log n) amortized, where n is the number of nodes in the heap
        decreaseKey(FibonacciHeapNode<T> node, T newKey): O(1) amortized
        delete(FibonacciHeapNode<T> node): O(log n) amortized, where n is the number of nodes in the heap

        Note that the time complexity of deleteMin, decreaseKey, and delete methods is amortized because
        these methods can potentially require a cascading cut operation, which may cause some nodes to be
        removed from the heap and added to the root list.

 */