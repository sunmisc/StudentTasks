package zelvalea.tasks.aac_binary;

import java.util.concurrent.ThreadLocalRandom;

public class Task4 {
    static final int CAPACITY = 4;
    public static void main(String[] args) {
        int[] a = new int[CAPACITY], k = new int[CAPACITY];
        ThreadLocalRandom r = ThreadLocalRandom.current();
        for (int h = 0; h < 10; ++h) {
            for (int i = 0; i < CAPACITY; ++i) {
                a[i] = r.nextInt(1, 10);
                k[i] = r.nextInt(1, 5);
            }
            int x = ThreadLocalRandom.current().nextInt(10, 121232);

            System.out.println(myComputeDaysToCutTrees(a, k, x) + " = " + legacyDaysToCutTrees(a, k, x));
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
            }
            if (startTrees >= target)
                return startDay;
        }
    }
    public static int
    myComputeDaysToCutTrees(int[] a, int[] k, long target) {
        double delta = 0;
        for (int i = 0, n = a.length; i < n; i++) {
            int m = k[i];
            delta += ((double) (m - 1) / m) * a[i];
        }
        return (int) (target / delta);
    }
    static long calculateChoppedTrees(int numDays, int[] a, int[] k) {
        double cntTrees = 0;
        for (int i = 0, n = a.length; i < n; i++) {
            double m = k[i], q = (m - 1) * numDays;
            cntTrees += (q / m) * a[i];
        }
        return Math.round(cntTrees);
    }
}