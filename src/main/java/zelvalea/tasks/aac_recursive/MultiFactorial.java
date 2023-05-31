package zelvalea.tasks.aac_recursive;

public class MultiFactorial {

    public static void main(String[] args) {
        System.out.println(multiFactorial(12, 4));
    }

    // O(n/k)
    public static int multiFactorial(int n, int k) {
        return n < k ? 1 : n * multiFactorial(n - k, k);
    }
}
