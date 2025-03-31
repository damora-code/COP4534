package Prog19;

import java.util.Arrays;

public class Prog19_05 {

    public Prog19_05() {
        Graph g = new Graph("test3.txt");
        int[] p = new int[6];
        int[] d = new int[6];
        g.allShortestPaths(p, d, 0);
        int c = g.TSP_exhaustiveSearch(d);
        System.out.println("Exhaustive: " + Arrays.toString(d));
        System.out.println("Cost = " + c); // calculates cost
        g.randomPermutation(d);
        g.TSP_randomSampling(d);
        System.out.println("Random Sampling: " + Arrays.toString(d));

        g.TSP_localSearch(d);
        System.out.println("Local Search: " + Arrays.toString(d));
    }

    public static void main(String[] args) {
        new Prog19_05();
    }
}
