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
        Double cost = costToNeighbors.get(node);
        if(cost == null) {
            return getCostTo(node);
        }
        return cost;
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

    @Override
    public boolean equals (Object obj){
        if(obj instanceof Node){
            Node n = (Node) obj;
            if(n.x == this.x && n.y == this.y){
                return true;
            }
        }
        return false;
    }

    public boolean hasNeighbour(Node node){
        for (Node n:costToNeighbors.keySet()) {
            if(n.equals(node)){
                return true;
            }
        }
        return false;
    }
}
