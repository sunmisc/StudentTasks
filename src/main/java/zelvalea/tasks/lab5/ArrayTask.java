package zelvalea.tasks.lab5;

import java.util.*;
import java.util.function.Predicate;

public class ArrayTask {

    public static void main_1_6(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            String[] split = scanner.nextLine().split(" ");
            int n = split.length;
            int[] input = new int[n];
            try {
                boolean found = false;
                for (int i = 0; i < n; ++i) {
                    int q = found ? 1 : Integer.parseInt(split[i]);
                    input[i] = q;
                    if ((q % 5) == 0) {
                        found = true;
                    }
                }
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
            System.out.println("Результат: "+ Arrays.toString(input));
        }
    }
    public static void main_2_6(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            String[] split = scanner.nextLine().split(" ");
            int n = split.length;
            int[] input = new int[n];
            try {
                boolean found = false;
                for (int i = 0; i < n; ++i) {
                    int q = Integer.parseInt(split[i]);
                    input[i] = q;

                    if (!found && (q & 1) == 0) {
                        if (i < n-1) {
                            input[++i] = 0; // and skip
                        }
                        found = true;
                    }
                }
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
            System.out.println("Результат: "+ Arrays.toString(input));
        }
    }

    public static void main_3_6(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            String[] split = scanner.nextLine().split(" ");
            int n = split.length;
            Integer[] input = new Integer[n];
            try {
                Integer minVal = null, maxVal = minVal;
                int minIndex = 0, maxIndex = minIndex;
                for (int i = 0; i < n; ++i) {
                    int q = input[i] = Integer.parseInt(split[i]);

                    if (maxVal == null || q > maxVal) {
                        maxVal = q; maxIndex = i;
                    }
                    if (minVal == null || q < minVal) {
                        minVal = q; minIndex = i;
                    }
                }
                if (maxIndex > minIndex) {
                    remove(input, minIndex, maxIndex, i -> i % 3 == 0);
                } else if (minIndex > maxIndex) {
                    remove(input, maxIndex, minIndex, i -> i % 3 == 0);
                }
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
            System.out.println("Результат: "+ Arrays.toString(input));
        }
    }
    private static <T> void remove(T[] source,
                                   int start, int end,
                                   Predicate<T> predicate) {
        for (; start < end; ++start) {
            if (predicate.test(source[start])) {
                source[start] = null;
            }
        }
    }
}
