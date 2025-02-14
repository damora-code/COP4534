/**
 * Daniel Morales
 * PID: 6014232
 * COP4534 U01 1251
 *
 * This program tests primality of big integers using a deterministic approach and a randomized approach
 * The program starts with the large number given FIRST and checks numbers for primality for an hour
 * It then records the time it took for each test, the result of the boolean if it is prime or not and stored in a csv
 */
package ASG1;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.Random;

public class Assignment1 {

    // set variables for values
    BigInteger FIRST = new BigInteger("10000000000000819");
    BigInteger ZERO = BigInteger.ZERO;
    BigInteger ONE = BigInteger.ONE;
    BigInteger TWO = BigInteger.TWO;
    BigInteger THREE = BigInteger.valueOf(3);
    // BigInteger TEST = new BigInteger("9"); -- test case for prime testing



    public Assignment1() {
        long startTime = System.currentTimeMillis();// capture time in milliseconds
        long endTime = startTime + (60 * 60 * 1000); // 60 minutes x 60 seconds x 1000 milliseconds to have an hour

        initCSV("output.csv");

        for (BigInteger CURRENT = FIRST; System.currentTimeMillis() < endTime; CURRENT = CURRENT.add(ONE)) {

            // deterministic approach
            long daStartTime = System.nanoTime();
            boolean isPrimeDA = isPrimeDeterministic(CURRENT);
            long daEndTime = System.nanoTime();
            long daTime = (daEndTime - daStartTime);

            // randomized approach
            long raStartTime = System.nanoTime();
            boolean isPrimeRA = isProbablyPrimeRandomized(CURRENT, 40);
            long raEndTime = System.nanoTime();
            long raTime = (raEndTime - raStartTime);

            // write to CSV
            writeToTextfile("output.csv", CURRENT, daTime, isPrimeDA, raTime, isPrimeRA);
        }
    }

    public static void main(String[] args) {
        new Assignment1();
    }

    /**
     * This is the primality test used in Prog15_05 from main and the isPrime function, modified to
     * be used in a single function, where we implement a deterministic approach
     *
     * @param n the BigInteger that will be tested if it is prime
     * @return will return true if number is prime, false if not
     */
    public boolean isPrimeDeterministic(BigInteger n) {
        BigInteger s = n.sqrt();

        if (n.compareTo(ONE) <= 0) { // if 1, not prime
            return false;
        } else if (n.compareTo(TWO) == 0 || n.compareTo(BigInteger.valueOf(3)) == 0) { // 2 is the smallest prime, 3 is also prime
            return true;
        } else if (n.mod(TWO).equals(ZERO)) {// even numbers are not prime, so we use modulus to remove them
            return false;
        }

        // check if divisible by 3 to sqrt
        BigInteger i;
        for (i = THREE; i.compareTo(s) <= 0; i = i.add(TWO)) {
            if (n.mod(i).compareTo(ZERO) == 0) return false;
        }

        return true;
    }

    /**
     * This is a primality test used in Prog15_06 from the isProbablyPrime function, modified to use
     * Fermat's Little Theorem using random bases to check if it satisfies the theorem
     *
     * @param n          the BigInteger that will be tested if it is prime
     * @param iterations how many times it will run with different random bases
     * @return will return true if number is prime, false if not
     */
    public boolean isProbablyPrimeRandomized(BigInteger n, int iterations) {
        if (n.compareTo(TWO) < 0) return false; // 0,1 are not prime
        if (n.equals(TWO) || n.equals(THREE)) return true; // 2 and 3 are prime
        if (n.mod(TWO).equals(ZERO)) return false; // even numbers > 2 are not prime

        for (int i = 0; i < iterations; i++) {
            // use professors function to generate a random base a in [2, n-1]
            BigInteger a = randomBigInteger(n.subtract(TWO)).add(TWO);

            // apply fermat's test: a^(n-1) ≡ 1 (mod n)
            if (!a.modPow(n.subtract(ONE), n).equals(ONE)) {
                return false; // Composite number
            }
        }
        return true; // probably prime
    }


    /**
     * This code was created by
     * professor @Antonio Hernandez
     * <p>
     * Generates a random BigInteger, uniformly distributed over
     * the interval [0, value-1].
     *
     * @param value upper end of interval, must be greater than 0
     * @return random BigInteger
     */
    BigInteger randomBigInteger(BigInteger value) {
        int bitLength = value.bitLength();
        return (new BigInteger(bitLength, new Random()).mod(value));
    }

    /**
     * modified version of professor function on how to create csv in Java program
     *
     * @author Prof. A. Hernandez
     * @author Daniel morales
     */
    public void writeToTextfile(String filename, BigInteger currentValue, long timeDA, boolean daPrime, long timeRA, boolean raPrime) {
        //open output stream
        try (PrintWriter output = new PrintWriter(new FileWriter(filename, true))) {
            output.println(currentValue + "," + timeDA + "," + (daPrime ? "YES" : "NO") + "," + timeRA + "," + (raPrime ? "YES" : "NO"));
        } catch (IOException ex) {
            System.exit(1);
        }
    }

    /**
     * initializes the CSV file with the column headers.
     *
     * @param filename the name of the CSV file were going to call it
     */
    private void initCSV(String filename) {
        try (PrintWriter output = new PrintWriter(new FileWriter(filename))) {
            output.println("Value, Time of DA (ns), Prime? (According to DA), Time of RA (ns), Prime? (According to RA)");
        } catch (IOException ex) {
            System.err.println("error with CSV file: " + ex.getMessage());
        }
    }
}

