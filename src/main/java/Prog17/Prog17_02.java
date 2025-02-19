package Prog17;

public class Prog17_02 {

    public Prog17_02() {

        int rows = 8;
        int cols = 8;
        int[][] array = new int[rows][cols];

        for (int i = 0; i < rows - 1; i++) {
            array[i][0] = 1;

        }

        for (int i = 1; i < rows; i++) {
            for (int j = 1; j < cols; j++) {

                array[i][j] = array[i - 1][j - 1] + array[i - 1][j];

            }
        }

        for (int i = 0; i < rows - 1; i++) {
            for (int j = 0; j < cols; j++) {

                if (array[i][j] != 0)
                    System.out.print(array[i][j] + " ");


            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        new Prog17_02();
    }

}
