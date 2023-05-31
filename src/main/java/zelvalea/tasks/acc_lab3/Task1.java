package zelvalea.tasks.acc_lab3;

import java.util.Arrays;
import java.util.Scanner;

public class Task1 {


    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {

            int size = scanner.nextInt();

            if (size > 1000)
                throw new IllegalArgumentException(
                    "array size must not exceed 1000");

            scanner.nextLine(); // skip

            String raw = scanner.nextLine();

            int[] array = Arrays.stream(raw.split(" "))
                    .mapToInt(Integer::parseInt)
                    .filter(x -> Math.abs(x) <= 1000)
                    .toArray();

            int maxVal = Integer.MIN_VALUE, maxIndex = 0;
            // O(n)
            for (int i = 0, x, n = array.length; i < n; ++i) {
                if ((x = array[i]) > maxVal)
                    maxVal = x; maxIndex = i;
            }
            int tmp = array[0];
            array[0] = array[maxIndex];
            array[maxIndex] = tmp;

            System.out.println(Arrays.toString(array));
        }
    }
}
