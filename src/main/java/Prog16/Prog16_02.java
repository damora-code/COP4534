package Prog16;

import java.util.ArrayList;

public class Prog16_02 {
    int count = 0;
    public Prog16_02(){
       // printPermutations(5);
        int[] a = new int[5];
        printPermutations(a,5);
    }

    public static void main(String[] args) {
        new Prog16_02();
    }
    /**
     * Print all permutations with values in [0, n-1].
     */
    public void printPermutations(int n) {
        int[] a = new int[n];
        printPermutations(a, 0);
    }

    /**
     * Recursive algorithm.
     *
     * @param a array partially filled with permutation
     * @param k index of current element in permutation
     */
    private void printPermutations(int[] a, int k) {
        if (k == a.length) {
            printArray(a);
        } else {
            ArrayList<Integer> Sk = constructCandidateSet(a, k);
            for (int s : Sk) {
                a[k] = s;
                printPermutations(a, k + 1);
            }
        }
    }

    /**
     * Construct candidate set (set will contain elements not used
     * in locations [0, k-1] of array a).
     */
    private ArrayList<Integer> constructCandidateSet(int[] a, int k) {
        ArrayList<Integer> candidates = new ArrayList<>();
        boolean[] b = new boolean[a.length];

        for (int i = 0; i < k; i++) {
            b[a[i]] = true;
        }

        for (int i = 0; i < a.length; i++) {
            if (!b[i]) candidates.add(i);
        }

        return candidates;
    }

    /**
     * Prints array a
     */
    private void printArray(int[] a) {

        System.out.printf("%4d: ", ++count);
        for (int v : a) {
            System.out.print(v + " ");
        }

        System.out.println();
    }
}