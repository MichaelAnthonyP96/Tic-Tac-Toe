import java.util.Scanner;
class Main {
    public static void main(String[] args) {
        // put your code here
        Scanner scanner = new Scanner(System.in);

        int dim1 = scanner.nextInt();
        int dim2 = scanner.nextInt();
        int[][] arr = new int[dim1][dim2];

        for (int i = 0; i < dim1; ++i) {
            for (int j = 0; j < dim2; ++j) {
                arr[i][j] = scanner.nextInt();
            }
        }

        for (int j = 0; j < dim2; ++j) {
            for (int i = dim1 - 1; i >= 0; --i) {
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }

    }
}