package zelvalea.tasks.lab5;

import java.util.*;

public class ArrayTask {

    public static void main_1_8(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            String[] split = scanner.nextLine().split(" ");
            try {
                int minVal = Integer.parseInt(split[0]), minIndex = 0;
                int n = split.length;
                int[] input = new int[n];
                input[0] = minVal;
                for (int i = 1; i < n; ++i) {
                    int v = Integer.parseInt(split[i]);
                    input[i] = v;
                    if (v < minVal) {
                        minVal = v; minIndex = i;
                    }
                }
                for (int i = minIndex + 1; i < n; ++i) {
                    input[i] = 0;
                }
                System.out.println("Результат: "+ Arrays.toString(input));
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }

        }
    }
    public static void main_2_8(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            String[] split = scanner.nextLine().split(" ");
            int n = split.length;
            List<Integer> input = new ArrayList<>(n);

            Integer maxVal = null; int maxIndex = 0;
            try {
                for (int i = 0; i < n; ++i) {
                    int v = Integer.parseInt(split[i]);
                    input.add(v);
                    if ((v & 1) == 0 && (maxVal == null || maxVal < v)) {
                        maxVal = v; maxIndex = i;
                    }
                }
                input.remove(maxIndex);
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }

            System.out.println("Результат: "+ input);
        }
    }
    public static void main_3_8(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            String[] split = scanner.nextLine().split(" ");
            int n = split.length;

            // LinkedHashMap был бы намного эффективнее, или BitSet
            List<Integer> input = new ArrayList<>(n);
            try {
                for (String s : split) {
                    input.add(Integer.parseInt(s));
                }
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }

            input.removeIf(x -> Collections.frequency(input, x) > 1);
            System.out.println("Результат: "+ input);
        }
    }
}
