/**
 * Daniel Morales
 * PID: 6014232
 * COP4534 U01 1251
 */

package Prog15;

import java.math.BigInteger;
import java.util.Random;

public class Assignment1 {
    BigInteger FIRST = new BigInteger("10000000000000819");
    BigInteger TEST = new BigInteger("2");
    BigInteger ZERO = BigInteger.ZERO;
    BigInteger ONE = BigInteger.ONE;
    BigInteger TWO = BigInteger.TWO;

    public Assignment1() {
        System.out.println(isPrimeDeterministic(TEST));;
    }

    public static void main(String[] args) {
        new Assignment1();
    }

    /**
     * This is the primality test used in Prog15_05 from main and the isPrime function, modified to
     * be used in a single function, where we implement a deterministic approach
     *
     * @param n - the number that will be tested if it is prime
     * @return - will return true if number is prime, false if not
     */
    public boolean isPrimeDeterministic(BigInteger n) {
        BigInteger s = n.sqrt();

        if (n.compareTo(ONE) <= 0) { // if 1, not prime
            return false;
        } else if (n.compareTo(TWO) == 0) { // if 2, not prime
            return false;
        } else return !n.mod(BigInteger.TWO).equals(BigInteger.ZERO); // even numbers are not prime so we use modulus to remove them


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
