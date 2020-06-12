import java.util.Collections;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // put your code here
        int input = scanner.nextInt();
        int[] arr = new int[input];
        for (int i = 0; i < input; ++i) {
            arr[(i + 1) % input] = scanner.nextInt();
        }
        for (int i = 0; i < input; ++i) {
            System.out.print(arr[i] + " ");
        }
    }
}