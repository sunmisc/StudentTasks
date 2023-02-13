package zelvalea.tasks.acc_sort;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

public enum SortOps {

    BUDDLE(1) { // O(n^2)
        @Override
        public <T> void sort(T[] source, Comparator<T> cmp) {
            boolean isSorted = false;
            int lastIndex = source.length - 1;
            while (!isSorted) {
                isSorted = true;
                for (int i = 0; i < lastIndex; i++) {

                    int next = i + 1;
                    if (cmp.compare(source[i], source[next]) > 0) {

                        T tmp = source[i];
                        source[i] = source[next];
                        source[next] = tmp;

                        isSorted = false;
                    }
                }
            }
        }
    },
    SELECTION(2) { // O(n^2
        @Override
        public <T> void sort(T[] source, Comparator<T> cmp) {
            for (int i = 0, n = source.length; i < n; i++) {

                int indexOfMin = i;
                for (int j = i + 1; j < n; j++) {
                    if (cmp.compare(source[j], source[indexOfMin]) < 0) {
                        indexOfMin = j;
                    }
                }
                T tmp = source[i];
                source[i] = source[indexOfMin];
                source[indexOfMin] = tmp;
            }
        }
    },
    INSERTION(3) { // O(n^2) best O(n)
        @Override
        public <T> void sort(T[] source, Comparator<T> cmp) {
            for (int i = 1, n = source.length; i < n; i++) {
                T x = source[i];
                int j = i;
                while (j > 0 && cmp.compare(x, source[j - 1]) < 0) {
                    source[i] = source[i - 1];
                    --j;
                }
                source[j] = x;
            }
        }
    };

    final int state;

    SortOps(int state) {
        this.state = state;
    }

    public abstract <T> void sort(T[] source, Comparator<T> cmp);


    public static Optional<SortOps> getById(int id) {
        return Optional.ofNullable(lookup.get(id));
    }

    public int id() { return state; }

    private static final Map<Integer,SortOps> lookup;

    static {
        lookup = Arrays.stream(values())
                .collect(Collectors.toUnmodifiableMap(
                        findOps -> findOps.state,
                        Function.identity())
                );
    }

    public static void main(String[] args) {

        Integer[] arr = new Integer[]{33,11, 1231, -1, 123, 2};

        SortOps.INSERTION.sort(arr, Integer::compare);

        System.out.println(Arrays.toString(arr));

    }
}
