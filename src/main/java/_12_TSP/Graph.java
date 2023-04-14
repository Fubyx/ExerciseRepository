package _12_TSP;

import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Random;

public class Graph {
    private ArrayList<Node> nodes = new ArrayList<>();
    private Random random = new Random();
    public Graph() {}
    public Graph(ArrayList<Node> nodes) {
        this.nodes.addAll(nodes);
    }
    public ArrayList<Node> getNodes() {
        return nodes;
    }

    public Node getNode(double x, double y){
        for (Node node:nodes) {
            if(x == node.getX() && y == node.getY()){
                return node;
            }
        }
        return null;
        //throw new NoSuchElementException("Zu angegebenen Werten besteht keine Node.");
    }

    public void addNode() {
        nodes.add(new Node(random.nextDouble(0, 100), random.nextDouble(0, 100)));
    }
    public void addNode(double x, double y) {
        nodes.add(new Node(x, y));
    }
    public void addNode(Node node) {
        nodes.add(node);
    }
    public void turnIntoCompleteGraph() {
        for (Node n : nodes) {
            n.clearNeighbors();
            for (Node o : nodes) {
                if (!n.equals(o)) {
                    n.addNeighbor(o);
                }
            }
        }
    }
    public int size (){
        return nodes.size();
    }
    @Override
    public boolean equals (Object obj){
        if(obj instanceof Graph){
            Graph g = (Graph) obj;
            int amt = 0;
            for (Node n : g.nodes) {
                for(Node n1: this.nodes){
                    if(n.equals(n1)){
                        ++amt;
                    }
                }
            }
            if(amt == this.size()){
                return true;
            }
        }
        return false;
    }

}