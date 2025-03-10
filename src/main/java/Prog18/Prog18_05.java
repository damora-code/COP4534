package Prog18;

public class Prog18_05 {

    public Prog18_05(){
        Graph g = new Graph("Prog18_05 - graph 01.txt");
        //g.BFT(0);
        g.DFT(1);
    }
    public static void main(String[] args) {
        new Prog18_05();
    }
}
