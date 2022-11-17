package zelvalea.tasks.lab10;

import java.util.Arrays;

public class var06 implements test {

    public static void main(String[] args) {
        var06 v = new var06();
        double[] values = {213,-123,2};
        System.out.println("values : "+ Arrays.toString(values));
        System.out.println("min value: "+v.logika(values[0],values[1],values[2]));
        System.out.println("-".repeat(50));
        final int reverseCandidate = 1234567;
        System.out.println("reverseCandidate: "+ reverseCandidate);
        v.poka(reverseCandidate);
        System.out.println("-".repeat(50));
        double[] a = new double[] {1231, 888, 123, -1312, -13122, 1231};
        System.out.println("array: " + Arrays.toString(a));
        v.massivy(a);
    }

    @Override
    public double logika(double d1, double d2, double d3) {
        return Math.min(d1, Math.min(d2, d3));
    }

    @Override
    public void poka(int n) {
        int i;
        for (i = 0; n > 0; n /= 10) {
            i = i * 10 + n % 10;
        }
        System.out.println("reverse: "+i);
    }

    @Override
    public void massivy(double[] a) {
        System.out.println("local minimum index: " +
                binarySearchLocalMinimum(a, a.length)
        );
    }
    static int binarySearchLocalMinimum(double[] arr, int n) {
        int low = 0, high = n - 1;
        while (low <= high) {
            int mid = (high + low) >>> 1; // half

            if ((mid == 0 || arr[mid - 1] > arr[mid]) &&
                    (mid == n - 1 || arr[mid + 1] > arr[mid])) {
                return mid; // index found
            } else if (mid > 0 && arr[mid - 1] < arr[mid]) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return -(low + 1);  // index not found.
    }
}
