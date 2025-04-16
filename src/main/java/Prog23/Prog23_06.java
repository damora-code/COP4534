package Prog23;

import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;

public class Prog23_06 {
    public Prog23_06() {

        Graph<String, DefaultEdge> g = new SimpleGraph<>(DefaultEdge.class);

        g.addVertex("0");
        g.addVertex("1");
        g.addVertex("2");
        g.addVertex("3");
        g.addVertex("4");
        g.addEdge("0", "1");
        g.addEdge("0", "2");
        g.addEdge("1", "2");
        g.addEdge("1", "3");
        g.addEdge("2", "3");
        g.addEdge("2", "4");

        for (String v : g.vertexSet()) {
            System.out.println("deg(" + v + ") = " + g.degreeOf(v));
        }
    }


    public static void main(String[] args) {
        new Prog23_06();
    }
}
