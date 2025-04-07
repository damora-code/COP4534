package Prog21;

public class Prog21_02 {
    public static void main(String[] args) {
        new Prog21_02();
    }

    double tolerance = 0.0001;
    int maxIterations = 100;

    public Prog21_02()
    {
        System.out.println(" Root of f(x) = x^3 - 2:");
        System.out.println(" x = " + root(1, 3) + " (Numerical method)");
        System.out.println(" x = " + Math.pow(2, 1.0/3.0) + " (Analytic method)");
    }

    /**
     * Computes f(x).
     *
     * @param x input parameter
     * @return the evaluation of f at x
     */
    public double f(double x)
    {
        // f(x) = x^3 - 2
        return Math.pow(x, 3) - 2;
    }

    /** Computes root of f(x) = x^3 - 2 in [a, b] using numerical method. */
    public double root(double a, double b)
    {
        int i = 0;
        double mid = a;
        double rae; // relative approximate error

        do
        {
            double oldMid = mid;
            mid = (a + b) / 2;
            i++;
            rae = Math.abs((mid - oldMid) / mid); // for simplicity, it's assumed that mid != 0

            double test = f(a) * f(mid);
            if (test < 0)
                b = mid;
            else if (test > 0)
                a = mid;
            else
                rae = 0;

        } while (rae >= tolerance && i < maxIterations);

        System.out.println(" number of iterations = " + i);
        System.out.printf(" tolerance = %.4f\n", tolerance);

        return mid;
    }
}
