package _12_TSP;

public class TSPUtils {
    public static Graph perfectMatching(Graph origin) {
        Graph matching = new Graph();
        for (Node n : origin.getNodes()) {
            if (n.getNeighbors().length % 2 == 1) {
                matching.addNode(n.getX(), n.getY());
            }
        }
        matching.turnIntoCompleteGraph();

        // now wizardry to get a perfect matching


        return matching;
    }
}
