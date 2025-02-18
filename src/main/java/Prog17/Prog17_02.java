package Prog17;

public class Prog17_02 {
    int n = 6; // number of rows
    int k = 6; // number of columns

    public Prog17_02() {
        int[][] matrix = new int[n][k];

        for (int i = 0; i < n-1; i++)
            for (int j = 0; j < k-1; j++)
                matrix[n][k] = matrix[i - 1][j - 1] + matrix[i - 1][j];
                System.out.println(matrix[n][k]);
    }

    public static void main(String[] args) {
        new Prog17_02();
    }

}
