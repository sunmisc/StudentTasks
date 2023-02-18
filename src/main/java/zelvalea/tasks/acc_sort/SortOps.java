package zelvalea.tasks.acc_sort;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

public enum SortOps {

    BUBBLE(1) { // O(n^2)

        @Override
        public <T> StatsSnapshot sort(T[] source, Comparator<T> cmp) {
            boolean isSorted = false;
            int lastIndex = source.length - 1;

            int readers = 0, writers = 0;
            while (!isSorted) {
                isSorted = true;
                for (int i = 0; i < lastIndex; i++) {

                    int next = i + 1;

                    readers += 2;
                    if (cmp.compare(source[i], source[next]) > 0) {

                        T tmp = source[i];
                        source[i] = source[next];
                        source[next] = tmp;

                        readers += 2; writers += 2;

                        isSorted = false;
                    }
                }
            }
            return new StatsSnapshot(readers, writers);
        }
    },
    SELECTION(2) { // O(n^2) best O(n)
        @Override
        public <T> StatsSnapshot sort(T[] source, Comparator<T> cmp) {
            int readers = 0, writers = 0;
            for (int i = 0, n = source.length; i < n; i++) {

                int indexOfMin = i;
                for (int j = i + 1; j < n; j++) {
                    readers += 2;
                    if (cmp.compare(source[j], source[indexOfMin]) < 0) {
                        indexOfMin = j;
                    }
                }
                T tmp = source[i];
                source[i] = source[indexOfMin];
                source[indexOfMin] = tmp;

                readers += 2; writers += 2;
            }
            return new StatsSnapshot(readers, writers);
        }
    },
    INSERTION(3) { // O(n^2) best O(n)
        @Override
        public <T> StatsSnapshot sort(T[] source, Comparator<T> cmp) {
            int readers = 0, writers = 0;
            for (int i = 1, n = source.length; i < n; i++) {
                T x = source[i]; int j = i;

                for (readers++, writers++; j > 0; readers++) {
                    if (cmp.compare(x, source[j - 1]) < 0) {
                        source[i] = source[i - 1];
                        readers++; writers++;
                        --j;
                    }
                }
                source[j] = x;
            }
            return new StatsSnapshot(readers, writers);
        }
    };

    final int state;

    SortOps(int state) {
        this.state = state;
    }

    public abstract <T> StatsSnapshot sort(T[] source, Comparator<T> cmp);

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
