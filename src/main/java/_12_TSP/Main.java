package _12_TSP;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class Main extends Application {
    private int amountOfNodes = 500;
    private final int SCREENWIDTH = 1600;
    private final int SCREENHEIGHT = 800;
    private Scene scene;
    private Group root;
    private VBox buttonBox;
    private Label nodeAmountLabel;
    private Button matchingButton;
    private Button sptButton;
    private Random r = new Random();

    /* todo
    textfield amountOfNodes
    Button matching
    Button SPT
     */
    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) {
        root = new Group();
        final Graph[] g = {newGraphWithRandomNodes(amountOfNodes), null};

        //Labels und Buttons
        buttonBox = new VBox();
        buttonBox.setLayoutX(0);
        buttonBox.setLayoutY(0);
        root.getChildren().add(buttonBox);

        nodeAmountLabel = new Label();
        matchingButton = new Button();
        matchingButton.setText("perform perfect Matching");
        matchingButton.setOnAction(actionEvent -> {
            g[0] = TSPUtils.perfectMatching(g[0]);
            drawGraph(g[0]);
        });
        sptButton = new Button();
        sptButton.setText("perform spanning tree");
        sptButton.setOnAction(actionEvent -> {
            g[0] = TSPUtils.spanningTree(g[0]);
            drawGraph(g[0]);
        });
        buttonBox.getChildren().addAll(nodeAmountLabel, matchingButton, sptButton);


        drawGraph(g[0]);
        scene = new Scene(root, SCREENWIDTH, SCREENHEIGHT);
        stage.setTitle("Graphs (I guess)!");
        stage.setScene(scene);
        stage.show();

        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                if (keyEvent.getCode() == KeyCode.M) {  // perform matching of nodes with uneven degree
                    g[0] = TSPUtils.perfectMatching(g[0]);
                    drawGraph(g[0]);
                } else if (keyEvent.getCode() == KeyCode.R) { // new random graph
                    g[0] = newGraphWithRandomNodes(amountOfNodes);
                    drawGraph(g[0]);
                } else if (keyEvent.getCode() == KeyCode.S) { // make spanning tree of graph
                    g[0] = TSPUtils.spanningTree(g[0]);
                    drawGraph(g[0]);
                } else if (keyEvent.getCode() == KeyCode.C) { // complete graph
                    g[0].turnIntoCompleteGraph();
                    drawGraph(g[0]);
                } else if (keyEvent.getCode() == KeyCode.A) { // all combined (except random)
                    g[0].turnIntoCompleteGraph();
                    g[0] = TSPUtils.spanningTree(g[0]);
                    Graph matching = TSPUtils.perfectMatching(g[0]);
                    for (Node n : matching.getNodes()) {
                        g[0].getNode(n.getX(), n.getY()).addNeighbor(g[0].getNode(n.getNeighbors()[0].getX(), n.getNeighbors()[0].getY()));
                    }
                    g[0] = TSPUtils.combineMatchingAndSPT(matching, g[0]);
                    drawGraph(g[0]);
                    // now only eulertour is missing
                }
            }
        });

        final AtomicInteger[] counter = {new AtomicInteger()};
        Timeline tl = new Timeline(new KeyFrame(new Duration(100), event -> {

        }));

        tl.setCycleCount(Timeline.INDEFINITE);
        tl.play();
    }

    private Graph newGraphWithRandomNodesAndEdges(int amountOfNodes) {
        ArrayList<Node> nodes = new ArrayList<>(amountOfNodes);
        for (int i = 0; i < amountOfNodes; i++) {
            nodes.add(new Node(r.nextDouble(0, SCREENWIDTH), r.nextDouble(0, SCREENHEIGHT)));
        }
        for (int i = 0; i < amountOfNodes; i++) {
            for (int j = i + 1; j < amountOfNodes; j++) {
                if (r.nextInt(0, 10) == 0) {
                    nodes.get(i).addNeighbor(nodes.get(j));
                    nodes.get(j).addNeighbor(nodes.get(i));
                }
            }
        }
        return new Graph(nodes);
    }
    private Graph newGraphWithRandomNodes(int amountOfNodes) {
        ArrayList<Node> nodes = new ArrayList<>(amountOfNodes);
        for (int i = 0; i < amountOfNodes; i++) {
            nodes.add(new Node(r.nextDouble(0, SCREENWIDTH), r.nextDouble(0, SCREENHEIGHT)));
        }
        return new Graph(nodes);
    }

    private void drawGraph(Graph g) {
        nodeAmountLabel.setText(String.valueOf(amountOfNodes));
        root.getChildren().clear();
        for (Node n : g.getNodes()) {
            Circle c = new Circle(n.getX(), n.getY(), 5);
            c.setFill(Color.RED);
            root.getChildren().add(c);
            for (Node neighbor : n.getNeighbors()) {
                root.getChildren().add(new Line(n.getX(), n.getY(), neighbor.getX(), neighbor.getY()));
            }
        }
    }
}



/*
Steps:
- Minimal Spanning Tree
- Perfect Matching between Nodes with uneven degree
- Eulertour
- if you get to a node the second time (in the eulertour) skip it
- you got the TSP!
 */