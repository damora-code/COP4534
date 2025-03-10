package Prog18;

public class Prog18_01 {

    public Prog18_01(){
        Graph g = new Graph(5);
        g.addEdge(0,1);
        g.addEdge(0,2);
        g.addEdge(0,3);
        g.addEdge(1,2);
        g.addEdge(2,2);
        g.addEdge(2,3);
        g.addEdge(1,3);
        g.addEdge(4,3);
        System.out.println(g);
    }

    public static void main(String[] args) {
        new Prog18_01();
    }
}
