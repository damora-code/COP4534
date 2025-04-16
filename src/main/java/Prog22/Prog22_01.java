package Prog22;

import java.util.Arrays;

public class Prog22_01 {

    public Prog22_01() {
        String text = "A generic point in algebraic geometry";
        String pattern = "ge";

        int[] location = locatePattern(text, pattern);
        System.out.println(Arrays.toString(location));
    }

    public static void main(String[] args) {
        new Prog22_01();
    }

    public int[] locatePattern(String text, String pattern) {
        int n = text.length();
        int m = pattern.length();
        int[] loc = new int[text.length()];

        int total = 0;
        for (int i = 0; i <= n - m; i++) {
            // compare substring starting at location i with pattern
            int j = 0;
            while (j < m && text.charAt(i + j) == pattern.charAt(j)) j++;

            if (j == m) loc[total++] = i;
        }

        return Arrays.copyOf(loc, total);

    }
}
