package Prog15;

/*
 * Deterministic distribution
 *
 *
 * */
public class Prog15_02 {
    public static void main(String[] args) {
        int[] list = new int[1000000];

        /* Deterministic quicksort*/

        /* Start time */
        long startTime = System.nanoTime();

        //System.out.println("Filled uniform array");
        Algorithms.fillArrayUniformDistribution(list);
        // Algorithms.printArray(list);
        //System.out.println("quicksorted array");
        Algorithms.quickSort(list);
        //Algorithms.printArray(list);
        //System.out.println("Filled normal array");
        Algorithms.fillArrayNormalDistribution(list);
        //Algorithms.printArray(list);
        //System.out.println("quicksorted array");
        Algorithms.quickSort(list);
        //Algorithms.printArray(list);

        /* End time */
        long elapsedTime = System.nanoTime() - startTime;
        System.out.println("finished: " + elapsedTime);

        /* Las Vegas quicksort*/
        /* Start time */
        startTime = System.nanoTime();

        //System.out.println("Filled uniform array");
        Algorithms.fillArrayUniformDistribution(list);
        //Algorithms.printArray(list);
        //System.out.println("quicksorted array");
        Algorithms.randomizedQuickSort(list);
        //Algorithms.printArray(list);
       // System.out.println("Filled normal array");
        Algorithms.fillArrayNormalDistribution(list);
        //Algorithms.printArray(list);
        //System.out.println("quicksorted array");
        Algorithms.randomizedQuickSort(list);
        //Algorithms.printArray(list);

        elapsedTime = System.nanoTime() - startTime;
        System.out.println("finished: " + elapsedTime);
    }
}
