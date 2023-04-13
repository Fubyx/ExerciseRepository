package _12_TSP;

import java.util.HashMap;

import static java.lang.Math.sqrt;

public class Node {
    private HashMap<Node, Double> costToNeighbors = new HashMap<>();
    private double x, y;

    public Node(double x, double y) {
        this.x = x;
        this.y = y;
    }
    public void addNeighbor(Node node) {
        double cost = sqrt((x-node.getX()*x-node.getX())+(y-node.getY()*y-node.getY()));
        costToNeighbors.put(node, cost);
    }
    public double getCostToNeighbor(Node node){
        return costToNeighbors.get(node);
    }
    public double getCostTo(Node node) {
        return sqrt((x-node.getX())*(x-node.getX())+(y-node.getY())*(y-node.getY()));
    }
    public Node[] getNeighbors() {
        return costToNeighbors.keySet().toArray(new Node[0]);
    }
    public void removeNeighbor(Node node) {
        costToNeighbors.remove(node);
    }
    public void clearNeighbors() {
        costToNeighbors.clear();
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }
}
