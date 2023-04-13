package _12_TSP;

import org.testng.internal.collections.Pair;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

import java.awt.geom.Line2D;
import java.util.ArrayList;

import static java.lang.Math.sqrt;

public class TSPUtils {
    /**
     * not really a perfect matching, but it works with a time complexity of O(2)
     * @param origin the graph in which the perfect matching of the Nodes with uneven degree should be performed
     * @return a graph with only the nodes and vertices which were needed for the perfect matching
     */
    public static Graph perfectMatching(Graph origin) {
        if (origin.getNodes().size() < 2) {
            throw new RuntimeException();
        }
        Graph matching = new Graph();
        for (Node n : origin.getNodes()) {
            if (n.getNeighbors().length % 2 == 1) {
                matching.addNode(n.getX(), n.getY());
            }
        }
        if (matching.getNodes().size() < 2 || matching.getNodes().size() % 2 == 1) {
            return matching;
        }

        // not perfect, but it should at least work; matching-lines could intersect
        ArrayList<Node[]> lines = new ArrayList<>();
        for (Node n : matching.getNodes()) {
            if (n.getNeighbors().length > 0) {
                continue;
            }
            double min = 100000;
            Node matchingNode = null;
            for (Node possibleNeighbor : matching.getNodes()) {
                if (n.equals(possibleNeighbor) || possibleNeighbor.getNeighbors().length > 0 || n.getCostTo(possibleNeighbor) >= min) {
                    continue;
                }
                min = n.getCostTo(possibleNeighbor);
                matchingNode = possibleNeighbor;
            }
            if (matchingNode == null) {
                throw new RuntimeException();
            }
            n.addNeighbor(matchingNode);
            matchingNode.addNeighbor(n);
            Node[] pair = new Node[]{n, matchingNode};
            lines.add(pair);
        }
        for (int cycleCount = 0; cycleCount < 5; cycleCount++)
        {
            for (Node[] n1 : lines) {
                for (Node[] n2 : lines) {
                    if (Arrays.equals(n1, n2) || !Line2D.linesIntersect(n1[0].getX(), n1[0].getY(), n1[1].getX(), n1[1].getY(), n2[0].getX(), n2[0].getY(), n2[1].getX(), n2[1].getY())) {
                        continue;
                    }
                    n1[0].clearNeighbors();
                    n1[1].clearNeighbors();
                    n2[0].clearNeighbors();
                    n2[1].clearNeighbors();
                    if (n1[0].getCostTo(n2[0]) + n1[1].getCostTo(n2[1]) <= n1[0].getCostTo(n2[1]) + n1[1].getCostTo(n2[0])) {
                        Node temp = n1[1];
                        n1[1] = n2[0];
                        n2[0] = temp;
                    } else {
                        Node temp = n1[1];
                        n1[1] = n2[1];
                        n2[1] = temp;
                    }
                    n1[0].addNeighbor(n1[1]);
                    n1[1].addNeighbor(n1[0]);
                    n2[0].addNeighbor(n2[1]);
                    n2[1].addNeighbor(n2[0]);
                }
            }
        }


        return matching;
    }

    public static Graph spanningTree(Graph origin) {
        Graph stGraph = new Graph();

         /*  Pair: jeweils das neue Element in der STP Menge und für jeden Nachbarn außer er ist in Menge
                Die Pairs in einer Queue nach Abstände sortieren
                Den kleinsten Abstand nehmen und den Nachbar an die Menge anfügen
            */
        PriorityQueue<Pair<Node, Node>> queue = new PriorityQueue<Pair<Node, Node>>(new Comparator<Pair<Node, Node>>() {
            @Override
            public int compare(Pair<Node, Node> o1, Pair<Node, Node> o2) {
                if (o1.first().getCostToNeighbor(o1.second()) > o2.first().getCostToNeighbor(o2.second())) {
                    return 1;
                } else if (o1.first().getCostToNeighbor(o1.second()) < o2.first().getCostToNeighbor(o2.second())) {
                    return -1;
                }
                return 0;
            }
        });

        //erstes Element in Menge hinzufügen und die Neighbors eintragen
        stGraph.addNode(origin.getNodes().get(0));
        for (Node node : origin.getNodes().get(0).getNeighbors()) {
            queue.add(new Pair<>(stGraph.getNodes().get(0), node));
        }

        //queue durchgehen, bis sie leer ist
        while (!queue.isEmpty()) {
            //neuen Node wählen mit kleinsten Kosten und noch nicht in Graph
            Pair<Node, Node> minimum = queue.poll();
            while (stGraph.getNodes().contains(minimum.second())) {
                minimum = queue.poll();
            }
            ;

            Node neighbor = minimum.second();

            Node newNode = new Node(neighbor.getX(), neighbor.getY());
            stGraph.addNode(newNode);


            //Zugang auf bestehenden stGraph, weil sonst schon Nachbarn eingetragen sind, die falsch sind
            //System.out.println(minimum.first().getX()+ " "+ minimum.first().getY());
            newNode.addNeighbor(stGraph.getNode(minimum.first().getX(), minimum.first().getY()));
            stGraph.getNode(minimum.first().getX(), minimum.first().getY()).addNeighbor(newNode);

            //alle neuen Neighbors in Queue geben
            for (Node node : neighbor.getNeighbors()) {
                if (!(stGraph.getNodes().contains(node))) {
                    queue.add(new Pair<>(neighbor, node));
                }
            }
        }
/*
            //alle Nachfolger des aktuellen Knotens u durchgehen
            for (int i = 0; i < u.getAmountOfNeighbors(); i++) {
                Node neighbor = u.getNeighbor(i);

                //dist = Kosten zu Nachfolger
                long dist = u.getCostLabel() + u.getEdgeToNeighborCost(i);

                //wenn Nachfolger keinen Parent hat, wird er in die queue gegeben
                if (parents[neighbor.getX()][neighbor.getY()] == null) {
                    //Aktualisieren der Kosten und Hinzufuegen von u als Parent
                    neighbor.setCostLabel(dist);
                    parents[neighbor.getX()][neighbor.getY()] = u;
                    queue.add(neighbor);

                    //wenn Nachfolger bereits in queue und dist kleiner als die aktuell eingetragenen Kosten
                    //werden die Kosten und das parent-Array aktualisiert
                } else if (queue.contains(neighbor) && neighbor.getCostLabel() > dist) {
                    neighbor.setCostLabel(dist);
                    parents[neighbor.getX()][neighbor.getY()] = u;
                }
            }
        }

         */
        return stGraph;
    }
}
