package Prog15;

import java.math.BigInteger;
import java.util.Random;

public class Prog15_06 {

    public Prog15_06() {
        for (int i = 4; i < 20; i++) {
            System.out.println(isProbablyPrime(i, 20));
        }
    }

    public static void main(String[] args) {
        new Prog15_06();
    }

    private long exp(int b, int x) {
        return (long) Math.pow(b, x); // this should be overflow soon
    }                                // due to limited precision of long

    public boolean isProbablyPrime(int n, int iterations) {
        if (n <= 1) return false;
        if (n == 2) return false;

        Random rnd = new Random();

        for (int i = 0; i < iterations; i++) {
            int a = rnd.nextInt(2, n - 1);
            BigInteger x;

            if (exp(a, n - 1) % n != 1)
                return false;
        }
        return true;
    }
}
