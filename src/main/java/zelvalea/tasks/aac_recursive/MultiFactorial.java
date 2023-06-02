package zelvalea.tasks.aac_recursive;

public final class MultiFactorial {

    private MultiFactorial() { }

    public static void main(String[] args) {
        System.out.println(multiFactorial(8, 3));
    }

    // O(n/k)
    public static int multiFactorial(int n, int k) {
        if (n <= 0)
            return 1;
        return n * multiFactorial(n - k, k);
    }
}
