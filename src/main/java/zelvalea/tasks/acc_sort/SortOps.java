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
                lastIndex--;
            }
            return new StatsSnapshot(readers, writers);
        }
    },
    SELECTION(2) { // O(n^2)
        @Override
        public <T> StatsSnapshot sort(T[] source, Comparator<T> cmp) {
            int readers = 0, writers = 0;
            for (int i = 0, n = source.length; i < n; i++) {
                int indexOfMin = i;
                int check = 0;
                for (int j = i + 1; j < n; j++) {
                    readers += 2;
                    check++;
                    if (cmp.compare(source[j], source[indexOfMin]) < 0) {
                        indexOfMin = j;
                    }
                }
                T tmp = source[i];
                source[i] = source[indexOfMin];
                source[indexOfMin] = tmp;

                System.out.println(Arrays.toString(source) + " " + check);
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
                T x = source[i]; readers++;
                int j = i - 1;
                while (j >= 0) {
                    readers++;
                    if (cmp.compare(source[j], x) <= 0) {
                        break;
                    }
                    readers++;
                    writers++;
                    source[j + 1] = source[j];
                    j = j - 1;
                }
                writers++;
                source[j + 1] = x;

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

        // 14 -> 5
        // 20
        // 27
        // 35
        Integer[] arr = new Integer[]{3,5,1,8,7,2,6};

        // 33
        StatsSnapshot snapshot =
                SortOps.SELECTION.sort(arr, Integer::compare);

        System.out.println(snapshot);
    }
}
