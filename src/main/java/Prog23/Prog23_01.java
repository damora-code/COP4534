package Prog23;

import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;

public class Prog23_01 {

    public Prog23_01() {

        Graph<String, DefaultEdge> g = new SimpleGraph<>(DefaultEdge.class);

        g.addVertex("0");
        g.addVertex("1");
        g.addEdge("0", "1");

        System.out.println("Graph: " + g);
    }


    public static void main(String[] args) {
        new Prog23_01();
    }
}
