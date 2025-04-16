package Prog22;

import java.util.Arrays;

public class Prog22_03 {
    public Prog22_03() {
        String text = "A generic point in algebraic geometry.";
        String pattern = "ic ";

        int n = text.length();
        int m = pattern.length();

        int[] suffixArray = new int[n];
        generateSuffixArray(text, suffixArray);

        System.out.println("Suffix Array:");
        for (int i = 0; i < n; i++) {
            int index = suffixArray[i];
            System.out.printf("%3d %s\n", index, text.substring(index));
        }

        System.out.print("\nLocations of \"" + pattern + "\" in the text: ");
        for (int i = 0; i < n; i++) {
            int index = suffixArray[i];
            String suffix = text.substring(index); // suffix at index
            if (suffix.length() >= m) // if the length of the suffix is less than the pattern's, pattern cannot be in the suffix
            {
                // check first m characters of suffix
                if (suffix.substring(0, m).equals(pattern))
                    System.out.print(index + " ");
            }
        }

        System.out.println("\n------------------");
    }

    public static void main(String[] args) {
        new Prog22_03();
    }

    public void generateSuffixArray(String text, int[] suffixArray) {
        int n = text.length();
        String[] suffix = new String[n];
        for (int i = 0; i < n; i++) {
            suffix[i] = text.substring(i);
        }

        Arrays.sort(suffix);

        for (int i = 0; i < n; i++) {
            suffixArray[i] = n - suffix[i].length();
        }
    }
}