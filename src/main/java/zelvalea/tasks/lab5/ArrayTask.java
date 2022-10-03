package zelvalea.tasks.lab5;

import java.util.Arrays;
import java.util.Scanner;

public class ArrayTask {

    public static void main1(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            String[] split = scanner.nextLine().split(" ");
            int n = split.length;
            int[] input = new int[n];
            try {
                for (int i = 0; i < n; ++i) {
                    int q = Integer.parseInt(split[i]);
                    if ((q & 1) != 0) {
                        input[i] = q;
                    }
                }
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
            System.out.println("Результат: "+ Arrays.toString(input));
        }
    }
    public static void main2(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            String[] split = scanner.nextLine().split(" ");
            int n = split.length;
            int[] input = new int[n];
            try {
                for (int i = 0; i < n; ++i) {
                    int q = Integer.parseInt(split[i]);
                    input[i] = (q & 1) != 0 ? 1 : q;
                }
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
            System.out.println("Результат: "+ Arrays.toString(input));
        }
    }
    public static void main3(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            String[] split = scanner.nextLine().split(" ");
            int n = split.length;
            int[] input = new int[n];
            try {
                for (int i = 0; i < n; ++i) {
                    int q = Integer.parseInt(split[i]);
                    input[i] = q;
                    // 7 8 7 3 12 0
                    // 7 0 0 3 12 0
                    if ((q & 1) != 0 && i > 0) {
                        input[i - 1] = 0;
                    }
                }
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
            System.out.println("Результат: "+ Arrays.toString(input));
        }
    }
    public static void main4(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            String[] split = scanner.nextLine().split(" ");
            int n = split.length;
            int[] input = new int[n];
            try {
                // 0 7 8 5 3
                // 0 9 8 9 3
                int iMax = n-1;
                for (int i = iMax; i >= 0; --i) {
                    int q = Integer.parseInt(split[i]);
                    input[i] = q;
                    if ((q & 1) == 0 && i < iMax) {
                        input[i + 1] = 9;
                    }
                }
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
            System.out.println("Результат: "+ Arrays.toString(input));
        }
    }
    public static void main5(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            String[] split = scanner.nextLine().split(" ");
            int n = split.length;
            int[] input = new int[n];
            try {
                // 0 7 8 5 3
                // 0 9 8 9 3
                int iMax = n-1;
                for (int i = iMax; i >= 0; --i) {
                    int q = Integer.parseInt(split[i]);
                    input[i] = q;
                    if ((q & 1) == 0 && i < iMax) {
                        input[i + 1] = 9;
                    }
                }
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
            System.out.println("Результат: "+ Arrays.toString(input));
        }
    }
}
