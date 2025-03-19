package Prog18;

import java.util.Arrays;

public class Prog18_07 {

    public Prog18_07() {
        Graph g = new Graph("test_file.txt");
        int[] p = new int[5];
        int[] d = new int[5];
        g.allShortestPaths(p, d, 0);
        System.out.println(Arrays.toString(p));
        System.out.println(Arrays.toString(d));

    }

    public static void main(String[] args) {
        new Prog18_07();
    }
}
