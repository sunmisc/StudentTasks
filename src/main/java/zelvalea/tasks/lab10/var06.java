package zelvalea.tasks.lab10;

import java.util.ArrayList;
import java.util.List;

public class var06 implements test {

    /*
     * values : [213.0, -123.0, 2.0]
     * min value: -123.0
     * --------------------------------------------------
     * reverseCandidate: 1234567
     * reverse: 7654321
     * --------------------------------------------------
     * array: [1231.0, 888.0, 123.0, -1312.0, -13122.0, 1231.0]
     * local minimum index: 4
     */

    public static void main(String[] args) {
        test("123456789142342343123456789142342343123456789142342343123456789142342343");


    }
    public static void test(String val) {
        int cursor = 0, len = val.length();
        while (cursor < len &&
                val.charAt(cursor) == '0') {
            cursor++;
        }
        int numDigits = len - cursor;
        int firstGroupLen = numDigits % 9;
        if (firstGroupLen == 0)
            firstGroupLen = 9;

        int numWords = numDigits / 9;

        String group = val.substring(cursor, cursor += firstGroupLen);
        String p = group;
        List<Integer> list = new ArrayList<>(numWords);
        list.add(Integer.parseInt(p));
        while (cursor < len) {
            group = val.substring(cursor, cursor += 9);
            int v = Integer.parseInt(group);
            list.add(v);
        }
        System.out.println(list.size() +" " + numWords);
        System.out.println(list);
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
            } else if (mid != 0 && arr[mid - 1] < arr[mid]) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return -(low + 1);  // index not found.
    }
}
