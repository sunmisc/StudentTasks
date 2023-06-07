package zelvalea.tasks.aac_binary;

import java.util.concurrent.ThreadLocalRandom;


public class Task4 {

    public static void main(String[] args) {

        int[] a = {2, 3};
        int[] k = {4, 3};
        int x = 25;
        System.out.println(binaryDaysToCutTrees(a,k, x));
        System.out.println(legacyDaysToCutTrees(a,k, x));
        System.out.println(myComputeDaysToCutTrees(a,k,x));

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
        for (int days = 1;;) {
            long treesCut = calculateChoppedTrees(days, a, k);
            if (treesCut >= target)
                return days;
            days++;
        }
    }
    public static int
    myComputeDaysToCutTrees(int[] a, int[] k, long target) {
        double delta = 0;
        for (int i = 0, n = a.length; i < n; i++)
            delta += (double) a[i] / k[i]; // (1.0D / k[i]) * a[i];
        return compute0(a, k, delta, target) + 1;
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
        double cntTrees = 0;
        for (int i = 0, n = a.length; i < n; i++)
            cntTrees += ((double) numDays / k[i]) * a[i];
        return (long) cntTrees;
    }
}