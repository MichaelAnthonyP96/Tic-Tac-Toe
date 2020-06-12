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

        int numTickets = scanner.nextInt();
        boolean flag = true;
        for (int i = 0; i < dim1; ++i) {
            for (int j = 0; j < dim2; ++j) {
                for (int k = 0; k < numTickets; ++k) {
                    if ((j + k) >= dim2) {
                        break;
                    }
                    if (arr[i][j+k] != 0) {
                        j += (k + 1);
                        k = -1;
                        continue;
                    }

                    if ((k+1) == numTickets) {
                        System.out.println(i+1);
                        System.exit(0);
                    }
                }

            }
        }
        System.out.println(0);
    }
}