package _12_TSP;

import org.testng.internal.collections.Pair;

import java.util.Comparator;
import java.util.PriorityQueue;

import java.awt.geom.Line2D;
import java.util.ArrayList;

import static java.lang.Math.sqrt;

public class TSPUtils {
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
            System.out.println("something went wrong");
            return null;
        }

        // now wizardry to get a perfect matching

        // not perfect, but it should at least work; matching-lines could intersect
        ArrayList<Object[]> lines = new ArrayList<>();
        for (Node n : matching.getNodes()) {
            if (n.getNeighbors().length != 0) {
                continue;
            }
            double min = 100000;
            Node matchingNode = null;
            for (Node possibleNeighbor : matching.getNodes()) {
                if (n.equals(possibleNeighbor) || possibleNeighbor.getNeighbors().length != 0 || n.getCostTo(possibleNeighbor) >= min) {
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
            Object[] pair = new Object[] {new Line2D.Double(n.getX(), n.getY(), matchingNode.getX(), matchingNode.getY()), n, matchingNode};
            lines.add(pair);
            //lines.add(new Line2D.Double(n.getX(), n.getY(), matchingNode.getX(), matchingNode.getY()));
        }
        for (Object[] o1 : lines) {
            for (Object[] o2 : lines) {
                Line2D l = (Line2D) o1[0];
                Line2D possibleIntersect = (Line2D) o2[0];
                if (l.equals(possibleIntersect) || !l.intersectsLine(possibleIntersect)) {
                    continue;
                }
                Node n11 = (Node) o1[1];
                Node n12 = (Node) o1[2];
                Node n21 = (Node) o2[1];
                Node n22 = (Node) o2[2];
                n11.clearNeighbors();
                n12.clearNeighbors();
                n21.clearNeighbors();
                n22.clearNeighbors();
                if (sqrt((l.getX1() - possibleIntersect.getX1()) * (l.getX1() - possibleIntersect.getX1()) +
                         (l.getY1() - possibleIntersect.getY1()) * (l.getY1() - possibleIntersect.getY1())) +
                    sqrt((l.getX2() - possibleIntersect.getX2()) * (l.getX2() - possibleIntersect.getX2()) +
                         (l.getY2() - possibleIntersect.getY2()) * (l.getY2() - possibleIntersect.getY2())) <=
                    sqrt((l.getX1() - possibleIntersect.getX2()) * (l.getX1() - possibleIntersect.getX2()) +
                         (l.getY1() - possibleIntersect.getY2()) * (l.getY1() - possibleIntersect.getY2())) +
                    sqrt((l.getX2() - possibleIntersect.getX1()) * (l.getX2() - possibleIntersect.getX1()) +
                         (l.getY2() - possibleIntersect.getY1()) * (l.getY2() - possibleIntersect.getY1()))) {
                    n11.addNeighbor(n21);
                    n21.addNeighbor(n11);
                    n12.addNeighbor(n22);
                    n22.addNeighbor(n21);
                } else {
                    n11.addNeighbor(n22);
                    n22.addNeighbor(n11);
                    n12.addNeighbor(n21);
                    n21.addNeighbor(n12);
                }
            }
        }
        // now integrate this into SPT
        return matching;
    }

    public static Graph spanningTree(Graph origin){
        Graph stGraph = new Graph();

         /*  Pair: jeweils das neue Element in der STP Menge und für jeden Nachbarn außer er ist in Menge
                Die Pairs in einer Queue nach Abstände sortieren
                Den kleinsten Abstand nehmen und den Nachbar an die Menge anfügen
            */
        PriorityQueue<Pair<Node, Node>> queue = new PriorityQueue<Pair<Node, Node>>(new Comparator<Pair<Node, Node>>() {
            @Override
            public int compare(Pair<Node, Node> o1, Pair<Node, Node> o2) {
                if(o1.first().getCostToNeighbor(o1.second()) > o2.first().getCostToNeighbor(o2.second())){
                    return 1;
                }else if(o1.first().getCostToNeighbor(o1.second()) < o2.first().getCostToNeighbor(o2.second())){
                    return -1;
                }
                return 0;
            }
        });

        //erstes Element in Menge hinzufügen und die Neighbors eintragen
        stGraph.addNode(origin.getNodes().get(0));
        for (Node node:origin.getNodes().get(0).getNeighbors()) {
            queue.add(new Pair<>(stGraph.getNodes().get(0), node));
        }

        //queue durchgehen, bis sie leer ist
        while(!queue.isEmpty()) {
            //neuen Node wählen mit kleinsten Kosten und noch nicht in Graph
            Pair<Node, Node> minimum = queue.poll();
            while(stGraph.getNodes().contains(minimum.second())){
                minimum = queue.poll();
            };

            Node neighbor = minimum.second();

            Node newNode = new Node(neighbor.getX(),neighbor.getY());
            stGraph.addNode(newNode);
            

            //Zugang auf bestehenden stGraph, weil sonst schon Nachbarn eingetragen sind, die falsch sind
            //System.out.println(minimum.first().getX()+ " "+ minimum.first().getY());
            newNode.addNeighbor(stGraph.getNode(minimum.first().getX(), minimum.first().getY()));
            stGraph.getNode(minimum.first().getX(), minimum.first().getY()).addNeighbor(newNode);

            //alle neuen Neighbors in Queue geben
            for (Node node : neighbor.getNeighbors()) {
                if(!(stGraph.getNodes().contains(node))){
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
