import java.util.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // write your code here
        Scanner scanner = new Scanner(System.in);
        int size = scanner.nextInt();
        Double[] arr = new Double[size];
        String[] stringList;
        scanner.nextLine();
        stringList = scanner.nextLine().split(" ");
        for(int i = 0; i < stringList.length; ++i) {
            arr[i] = Double.parseDouble(stringList[i]);
        }
        int maxIndx = 0;
        double maxTax = 0.;
        for(int i = 0; i < stringList.length; ++i) {
            arr[i] *= scanner.nextDouble() / 100.;
            if (arr[i] > maxTax) {
                maxIndx = i;
                maxTax = arr[i];
            }
        }
        System.out.println(maxIndx + 1);

    }
}