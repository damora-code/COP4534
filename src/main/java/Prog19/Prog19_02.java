package Prog19;
import java.util.Arrays;
import java.util.Random;

public class Prog19_02 {

    public Prog19_02() {
        //generate 10 random permutations of values [0,5]
        for (int i = 0; i < 10; i++) {
            int[] a = new int[6];
            randomPermutation(a);
            System.out.println(Arrays.toString(a));
        }
    }

    public static void main(String[] args) {
        new Prog19_02();
    }

    /**
     * Given An array, generates random permutation of values in [0, n-1],
     * where n is size of given array; random permutations will be stored
     * in the array. Uses Fisher-Yates shuffle algorithm
     *
     * @param a output array
     */

    public void randomPermutation(int[] a) {
        for (int i = 0; i < a.length; i++)
            a[i] = i;

        Random rnd = new Random();

        for (int i = a.length - 1; i > 0; i--) {
            //generates a random index in [0, i]
            int randomLocation = rnd.nextInt(i + 1);

            if (randomLocation != i) {
                //swp a[i] and a[randomLocation]

                int temp = a[i];
                a[i] = a[randomLocation];
                a[randomLocation] = temp;
            }
        }
    }

}
