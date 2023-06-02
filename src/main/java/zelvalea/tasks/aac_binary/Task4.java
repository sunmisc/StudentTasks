package zelvalea.tasks.aac_binary;


public class Task4 {

    public static void main(String[] args) {
        int[] a = new int[]{1,8,8,8,9};
        int[] k = new int[]{2,2,1,9,1};

        int x = 55;
        System.out.println(binaryDaysToCutTrees(a,k, x));
        System.out.println(legacyDaysToCutTrees(a,k, x));
        System.out.println(myComputeDaysToCutTrees(a,k, x));

    }

    public static int
    binaryDaysToCutTrees(int[] a, int[] k, long target) {
        int left = 1, right = Integer.MAX_VALUE; // min / max days
        int n = a.length;
        while (left < right) {
            int mid = left + ((right - left) >>> 1);

            long treesCut = 0;
            for (int i = 0; i < n; i++)
                treesCut += (long) a[i] * (mid / k[i]);

            if (treesCut >= target)
                right = mid;
            else
                left = mid + 1;
        }
        return left;
    }

    public static int
    legacyDaysToCutTrees(int[] a, int[] k, int target) {
        for (int n = a.length, days = 1;;) {
            long treesCut = 0;
            for (int i = 0; i < n; i++)
                treesCut += (long) a[i] * (days / k[i]);

            if (treesCut >= target)
                return days;
            days++;
        }
    }
    public static int
    myComputeDaysToCutTrees(int[] a, int[] k, int target) {
        int sum = 0;
        for (int j : a)
            sum += j;
        return compute0(a, k, sum, target) + 1;
    }
    private static int
    compute0(int[] a, int[] k, int sum, long x) {
        int estimatedDays = (int) (x / sum);
        long cntTrees = x;

        for (int i = 0, n = a.length; i < n; ++i)
            cntTrees -= (long) (estimatedDays / k[i]) * a[i];

        return cntTrees >= x ? estimatedDays
                : estimatedDays + compute0(a, k, sum, cntTrees);
    }
}