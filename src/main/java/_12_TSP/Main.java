package _12_TSP;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class Main extends Application {
    private int amountOfNodes = 1000;
    private final int SCREENWIDTH = 1600;
    private final int SCREENHEIGHT = 800;
    private Scene scene;
    private Group root;
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
        final Graph[] g = {newGraphWithRandomNodes(amountOfNodes)};
        drawGraph(g[0]);
        scene = new Scene(root, SCREENWIDTH, SCREENHEIGHT);
        stage.setTitle("Graphs (I guess)!");
        stage.setScene(scene);
        stage.show();

        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                if (keyEvent.getCode() == KeyCode.M) {
                    g[0] = TSPUtils.perfectMatching(g[0]);
                    drawGraph(g[0]);
                } else if (keyEvent.getCode() == KeyCode.R) {
                    g[0] = newGraphWithRandomNodes(amountOfNodes);
                    drawGraph(g[0]);
                }
            }
        });

        final AtomicInteger[] counter = {new AtomicInteger()};
        Timeline tl = new Timeline(new KeyFrame(new Duration(100), event -> {

        }));

        tl.setCycleCount(Timeline.INDEFINITE);
        tl.play();
    }
    private Graph newGraphWithRandomNodes(int amountOfNodes) {
        ArrayList<Node> nodes = new ArrayList<>(amountOfNodes);
        for (int i = 0; i < amountOfNodes; i++) {
            nodes.add(new Node(r.nextDouble(0, SCREENWIDTH), r.nextDouble(0, SCREENHEIGHT)));
        }
        for (int i = 0; i < amountOfNodes; i++) {
            for (int j = i+1; j < amountOfNodes; j++) {
                if (r.nextInt(0, 10) == 0) {
                    nodes.get(i).addNeighbor(nodes.get(j));
                    nodes.get(j).addNeighbor(nodes.get(i));
                }
            }
        }
        return new Graph(nodes);
    }
    private void drawGraph(Graph g) {
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