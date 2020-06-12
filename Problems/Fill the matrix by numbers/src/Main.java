import java.util.Scanner;
import java.lang.Math;
class Main {
    public static void main(String[] args) {
        // put your code here
        Scanner scanner = new Scanner(System.in);
        int input = scanner.nextInt();
        int[][] arr = new int[input][input];
        int distance;
        for(int i = 0; i < arr.length; ++i) {
            for(int j = 0; j < arr[i].length; ++j) {
                distance = Math.abs(i - j);
                System.out.print(distance + " ");
            }
            System.out.println();
        }
    }
}