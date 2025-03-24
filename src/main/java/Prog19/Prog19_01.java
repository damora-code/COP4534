package Prog19;

import java.util.Arrays;

public class Prog19_01 {

    public Prog19_01() {
        Graph g = new Graph("test2.txt");
        int[] p = new int[6];
        int[] d = new int[6];
        g.allShortestPaths(p, d, 0);
        int c = g.TSP_exhaustiveSearch(d);
        System.out.println(Arrays.toString(d));
        System.out.println(c);

    }

    public static void main(String[] args) {
        new Prog19_01();
    }
}
