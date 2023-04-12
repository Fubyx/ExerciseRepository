package _12_TSP;

import org.testng.internal.collections.Pair;

import java.util.Comparator;
import java.util.PriorityQueue;

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

        //erstes ELement in Menge hinzufuegen und die Neighbors eintragen
        stGraph.addNode(origin.getNodes().get(0));
        for (Node node:origin.getNodes().get(0).getNeighbors()) {
            queue.add(new Pair<>(stGraph.getNodes().get(0), node));
        }

        //queue durchgehen, bis sie leer ist
        while(!queue.isEmpty()) {
            //neuen Node waehöen mit kleinsten Kosten und noch nicht in Graph
            Pair<Node, Node> minimum = queue.poll();
            while(stGraph.getNodes().contains(minimum.second())){
                minimum = queue.poll();
            };

            Node neighbor = minimum.second();

            Node newNode = new Node(neighbor.getX(),neighbor.getY());
            stGraph.addNode(newNode);
            

            //ZUgang auf bestehenden stGraph, weil sonst schon Nachbarn eingetragen sind, die falsch sind
            System.out.println(minimum.first().getX()+ " "+ minimum.first().getY());
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
