/**
 * Daniel Morales
 * PID: 6014232
 * COP4534 U01 1251
 */
package ASG1;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.Random;

public class Assignment1 {
    BigInteger FIRST = new BigInteger("10000000000000819");
    BigInteger TEST = new BigInteger("9");
    BigInteger ZERO = BigInteger.ZERO;
    BigInteger ONE = BigInteger.ONE;
    BigInteger TWO = BigInteger.TWO;
    BigInteger THREE = BigInteger.valueOf(3);

    public Assignment1() {

        // System.out.println(isPrimeDeterministic(TEST));
        // System.out.println(isProbablyPrimeRandomized(TEST, 10));
        WritingToTextfile.writeToTextfile("output.csv"); // FileWriter
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

        // check if divisible by 3 to sqrt, skipping even numbers
        BigInteger i;
        for (i = THREE; i.multiply(i).compareTo(n) <= 0; i = i.add(TWO)) {
            if (n.mod(i).equals(ZERO)) return false;
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
        if (n.compareTo(ONE) <= 0) { // if 1, not prime
            return false;
        } else if (n.compareTo(TWO) == 0 || n.compareTo(BigInteger.valueOf(3)) == 0) { // 2 is the smallest prime, 3 is also prime
            return true;
        }

        Random random = new Random();

        for (int i = 0; i < iterations; i++) {
            BigInteger a;

            // this helps create a random base in range [2, n-2]
            do {
                a = new BigInteger(n.bitLength(), random);
            } while (a.compareTo(TWO) < 0 || a.compareTo(n.subtract(ONE)) >= 0);

            // -- CRITICAL: USE FORMULA FROM CLASS a^(n-1) ≡ 1 (mod n) --
            if (!a.modPow(n.subtract(ONE), n).equals(ONE)) {
                return false; // composite number
            }
        }
        return true; // n is probably prime
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
}

