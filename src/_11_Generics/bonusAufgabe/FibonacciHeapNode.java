package _11_Generics.bonusAufgabe;

class FibonacciHeapNode<T extends Comparable<T>> implements Comparable<FibonacciHeapNode<T>> {
        private T key;
        private int degree;
        private boolean mark;
        private FibonacciHeapNode<T> parent;
        private FibonacciHeapNode<T> child;
        private FibonacciHeapNode<T> left;
        private FibonacciHeapNode<T> right;

        public FibonacciHeapNode(T key) {
            this.key = key;
            degree = 0;
            mark = false;
            parent = null;
            child = null;
            left = this;
            right = this;
        }

        public T getKey() {
            return key;
        }

        public void setKey(T key) {
            this.key = key;
        }

        public int getDegree() {
            return degree;
        }

        public void setDegree(int degree) {
            this.degree = degree;
        }

        public boolean isMarked() {
            return mark;
        }

        public void setMark(boolean mark) {
            this.mark = mark;
        }

        public FibonacciHeapNode<T> getParent() {
            return parent;
        }

        public void setParent(FibonacciHeapNode<T> parent) {
            this.parent = parent;
        }

        public FibonacciHeapNode<T> getChild() {
            return child;
        }

        public void setChild(FibonacciHeapNode<T> child) {
            this.child = child;
        }

        public FibonacciHeapNode<T> getLeft() {
            return left;
        }

        public void setLeft(FibonacciHeapNode<T> left) {
            this.left = left;
        }
        public FibonacciHeapNode<T> getRight() {
            return right;
        }

        public void setRight(FibonacciHeapNode<T> right) {
            this.right = right;
        }

        @Override
        public int compareTo(FibonacciHeapNode<T> o) {
            return key.compareTo(o.getKey());
        }

}
