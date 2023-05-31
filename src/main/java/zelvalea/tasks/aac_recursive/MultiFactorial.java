package zelvalea.tasks.aac_recursive;

import java.util.Hashtable;

public class MultiFactorial {

    public static void main(String[] args) {
        System.out.println(12 % 12);
    }

    // O(n/k)
    public static int multiFactorial(int n, int k) {
        if (n <= 0)
            return 1;
        return n * multiFactorial(n - k, k);
    }
}
