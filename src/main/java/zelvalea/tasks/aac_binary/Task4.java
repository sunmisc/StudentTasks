package zelvalea.tasks.aac_binary;

import java.util.Scanner;


public class Task4 {

    public static void main(String[] args) {

        try (Scanner scanner = new Scanner(System.in)) {
            int[] a = new int[2];
            int[] k = new int[2];

            a[0] = scanner.nextInt();
            k[0] = scanner.nextInt();

            a[1] = scanner.nextInt();
            k[1] = scanner.nextInt();

            int x = scanner.nextInt();

            System.out.println(myComputeDaysToCutTrees(a,k,x));
        }
    }

    public static int
    binaryDaysToCutTrees(int[] a, int[] k, long target) {
        int left = 1, right = Integer.MAX_VALUE; // min / max days
        while (left < right) {
            int mid = left + ((right - left) >>> 1);

            long treesCut = calculateChoppedTrees(mid, a, k);

            if (treesCut >= target)
                right = mid;
            else
                left = mid + 1;
        }
        return left;
    }

    public static int
    legacyDaysToCutTrees(int[] a, int[] k, int target) {
        int startTrees = 0;
        int startDay = 1;
        for (int n = a.length;; ++startDay) {
            for (int i = 0; i < n; ++i) {
                if (startDay % k[i] == 0)
                    continue;
                startTrees += a[i];

                if (startTrees >= target)
                    return startDay;
            }
        }
    }
    public static int
    myComputeDaysToCutTrees(int[] a, int[] k, long target) {
        double delta = 0;
        for (int i = 0, n = a.length; i < n; i++) {
            int m = k[i];
            delta += ((double) (m - 1) / m) * a[i];
        }
        return compute0(a, k, delta, target);
    }
    private static int
    compute0(int[] a, int[] k, double delta, long x) {
        int estimatedDays = (int) (x / delta);

        long cntTrees = x - calculateChoppedTrees(estimatedDays, a, k);

        return cntTrees >= x
                ? estimatedDays
                : estimatedDays + compute0(a, k, delta, cntTrees);
    }
    static long calculateChoppedTrees(int numDays, int[] a, int[] k) {
        long cntTrees = 0;
        for (int i = 0, n = a.length; i < n; i++) {
            int m = k[i], q = m - 1;
            cntTrees += (((long) q * numDays) / m) * a[i];
        }
        return cntTrees;
    }
}