package _12_TSP;

import java.util.ArrayList;
import java.util.Random;

public class Graph {
    private ArrayList<Node> nodes = new ArrayList<>();
    private Random random = new Random();
    public Graph() {}
    public Graph(ArrayList<Node> nodes) {
        this.nodes.addAll(nodes);
    }
    public void addNode() {
        nodes.add(new Node(random.nextDouble(0, 100), random.nextDouble(0, 100)));
    }
    public void addNode(double x, double y) {
        nodes.add(new Node(x, y));
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
    public void perfectMatching() {
        for (Node n : nodes) {
            if (n.getNeighbors().length % 2 == 1) {

            }
        }
    }
}