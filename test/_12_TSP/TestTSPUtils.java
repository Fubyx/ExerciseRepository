package _12_TSP;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestTSPUtils {
    @Test
    @DisplayName("Test für den Spanning-Tree")
    public void testSpanningTree(){
        /*
        nn      tn
            ff
        nt      tt
         */
        Graph graph = new Graph();
        Node nn = new Node(0,0);
        Node tt = new Node(10,10);
        Node nt = new Node(0,10);
        Node tn = new Node(10,0);
        Node ff = new Node(5,5);

        nn.addNeighbor(nt);
        nn.addNeighbor(tn);
        nn.addNeighbor(ff);

        tn.addNeighbor(nn);
        tn.addNeighbor(ff);
        tn.addNeighbor(tt);

        ff.addNeighbor(nn);
        ff.addNeighbor(tn);
        ff.addNeighbor(tt);
        ff.addNeighbor(nt);

        nt.addNeighbor(ff);
        nt.addNeighbor(tt);
        nt.addNeighbor(nn);

        tt.addNeighbor(ff);
        tt.addNeighbor(tn);
        tt.addNeighbor(nt);


        graph.addNode(nn);
        graph.addNode(tt);
        graph.addNode(nt);
        graph.addNode(tn);
        graph.addNode(ff);

        Graph spanningTree = TSPUtils.spanningTree(graph);
        //System.out.println(spanningTree.size());
        //for (Node node: spanningTree.getNodes()) {
        //    System.out.println(node.getX() + " "+node.getY());
        //}
        assertTrue(graph.equals(spanningTree));
        for (Node n:spanningTree.getNodes()) {
            if(n.equals(nn)){
                //assertTrue(n.hasNeighbour(tt));
                //assertFalse(n.hasNeighbour(tn));
                //assertFalse(n.hasNeighbour(nt));
                for(Node n2: n.getNeighbors()){
                    System.out.println(n2.getX() + " " + n2.getY());
                }
            }
        }

    }
}
