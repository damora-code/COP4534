package Prog22;

import java.util.Arrays;

public class Prog22_02 {

    public Prog22_02() {
        String text = "A generic point in algebraic geometry";
        int[] suffixArray = new int[text.length()];
        generateSuffixArray(text, suffixArray);

        for (int i = 0; i < suffixArray.length; i++) {
            int index = suffixArray[i];
            System.out.printf("%3d %s\n", index, text.substring(index));
        }


    }

    public static void main(String[] args) {
        new Prog22_02();
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
