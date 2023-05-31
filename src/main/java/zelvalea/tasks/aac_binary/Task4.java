package zelvalea.tasks.aac_binary;

public class Task4 {

    public static void main(String[] args) {
        /*JmhTask4.fastFind  thrpt    5  1266523,862 ± 35405,870  ops/s
        JmhTask4.slowFind  thrpt    5      838,789 ±   727,469  ops/s*/
        int[] a = new int[]{1,8,8,8,9};
        int[] k = new int[]{2,2,2,2,2};

        // 0 2 4 8

        int x = 892322;
        //System.out.println(legacyCompute(a,k,1, 0, x));
        System.out.println(compute(a,k, x));
    }
    public static int legacyCompute(int[] a, int[] k,
                                    int startDay, int startTrees,
                                    int target) {
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
    public static int compute(int[] a, int[] k, long x) {

        int sum = 0;
        for (int j : a)
            sum += j;
        return compute0(a, k, sum, x);
    }
    private static int
    compute0(int[] a, int[] k, int sum, long x) {

        int estimatedDays = (int)Math.ceilDiv(x, sum),
                i, n;
        long cntTrees = x;
        for (i = 0, n = a.length; i < n; ++i) {
            int m = k[i];
            long h = Math.floorDiv((m-1)*estimatedDays, m);
            cntTrees -= h * a[i];
        }
        System.out.println(cntTrees + " | " + x + " " + estimatedDays);
        return cntTrees >= x ? estimatedDays
                : estimatedDays + compute0(a, k, sum, cntTrees);
    }
}