package Prog19;

public class Prog19_04 {

    public static void main(String[] args) {
        int[] a = {0, 1, 2, 3, 4, 5};
        PermutationNeighborhood pn = new PermutationNeighborhood(a);
        while (pn.hasNext()) {
            int[] b = pn.next();
            for (int i = 0; i < b.length; i++) {

                System.out.print(b[i] + " ");
            }
            System.out.println();
        }
    }
}
