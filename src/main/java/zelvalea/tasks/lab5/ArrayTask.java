package zelvalea.tasks.lab5;

import java.util.Arrays;
import java.util.Scanner;
import java.util.function.Predicate;

public class ArrayTask {

    public static void main0(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Укажите размер массива");
        int size = scanner.nextInt();
        System.out.println("Укажите элементы");
        int[] array = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = scanner.nextInt();
        }
        int max_index = 0;
        int max = array[max_index];

        for (int i = 1; i < size; i++) {
            if (array[i] > max) {
                max = array[i];
                max_index = i;
            }
        }
        for (int i = max_index+1; i < size; ++i) {
            array[i] = 0;
        }
        System.out.println(Arrays.toString(array));
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] split = scanner.nextLine().split(" ");
        int n = split.length;
        Integer[] array = new Integer[n];

        int sum = 0;
        for (int i = 0; i < n; ++i) {
            int v = Integer.parseInt(split[i]);
            array[i] = v;
            sum += v;
        }
        sum /= n;

        for (int iMax = array.length - 1, i = iMax; i >= 0; --i) {
            if (array[i] % 2 == 0 && array[i] > sum) {
                array[i] = null;
            }
        }
        System.out.println(Arrays.toString(array));
    }
    public static void main12(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Укажите размер массива");
        int size = scanner.nextInt();
        System.out.println("Укажите элементы");
        Integer[] array = new Integer[size];
        for (int i = 0; i < size; i++) {
            array[i] = scanner.nextInt();
        }
        for (int i = 0; i < size; i++) {
            int count = 0;
            for (Integer integer : array) {
                if (integer != null && integer.equals(array[i])) {
                    count++;
                }
            }
            if (count <= 1) {
                array[i] = null;
            }
        }
        System.out.println(Arrays.toString(array));
    }
    public static void main_1_8(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int size = scanner.nextInt();
        int[] array = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = scanner.nextInt();
        }
        int minVal = array[0], minIndex = 0;
        int[] input = new int[size];
        input[0] = minVal;
        for (int i = 1; i < size; ++i) {
            int v = array[i];
            input[i] = v;
            if (v < minVal) {
                minVal = v;
                minIndex = i;
            }
        }
        for (int i = minIndex + 1; i < size; ++i) {
            input[i] = 0;
        }
        System.out.println("Результат: " + Arrays.toString(input));
    }
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
