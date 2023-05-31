package zelvalea.tasks.aac_binary;

import java.util.Objects;

public class Search {


    public static void main(String[] args) {
        Integer[] array = {0, 1, 2, 3, 4, 123};

        System.out.println(binarySearch(array, 123));


        System.out.println("-".repeat(10));

        System.out.println("Task #2 The number of numbers belonging to the segment");

        int a = 1, b = 123;

        Integer start = binarySearch(array, a);
        Integer end   = binarySearch(array, b);

        System.out.println(end - start);
    }

    // O(log(n))
    public static <E extends Comparable<? super E>> int
    binarySearch(E[] src, E key) {
        int low = 0, high = src.length - 1;

        while (low <= high) {
            int mid = (low + high) >>> 1; // / 2

            E midVal = src[mid];

            int cmp = midVal.compareTo(key);

            if (cmp < 0)
                low = mid + 1;
            else if (cmp > 0)
                high = mid - 1;
            else
                return mid;
        }
        return -1;
    }
    // for benchmark
    // O(n)
    public static <E extends Comparable<? super E>> int
    unarySearch(E[] src, E key) {
        for (int i = 0, n = src.length; i < n; ++i) {
            E val = src[i];
            if (Objects.equals(val, key)) {
                return i;
            }
        }
        return -1;
    }
}
