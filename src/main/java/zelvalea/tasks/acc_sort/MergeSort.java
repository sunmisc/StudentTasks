package zelvalea.tasks.acc_sort;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Comparator;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicInteger;

public class MergeSort {

    public static void main(String[] args) {
        Integer[] a = ThreadLocalRandom
                .current()
                .ints(1000000, 0, 10)
                .boxed()
                .toArray(Integer[]::new);

        int o = mergeSort(a, Integer::compareTo);

        System.out.println(o / (Math.log(a.length) * a.length));
    }


    public static <T>
    int mergeSort(T[] array, Comparator<? super T> c) {

        int n = array.length;
        @SuppressWarnings("unchecked")
        T[] allocate =  (T[]) Array.newInstance(array
                .getClass()
                .getComponentType(), n);

        AtomicInteger stats = new AtomicInteger();

        MergeSortTask<T> task =
        new MergeSortTask<>(array, allocate,
                c,0, n - 1, stats);
        task.invoke();
        System.out.println(Arrays.toString(array));
        return stats.getPlain();
    }
    private final static class MergeSortTask<T>
            extends RecursiveAction {
        static final int THRESHOLD = 16;
        final T[] arr, temp;
        final Comparator<? super T> cmp;
        final int left, right;
        final AtomicInteger counter;

        MergeSortTask(T[] arr, T[] temp,
                      Comparator<? super T> cmp,
                      int left, int right,
                      AtomicInteger counter) {
            this.arr = arr;
            this.temp = temp;
            this.cmp = cmp;
            this.left = left;
            this.right = right;
            this.counter = counter;
        }

        @Override
        protected void compute() {
            final int lb = left, rb = right;

          /*  if (rb - lb < THRESHOLD)
                // sequentially   */

            if (lb < rb) {
                int mid = (lb + rb) >>> 1;

                T[] a = arr, w = temp;
                Comparator<? super T> c = cmp;

                invokeAll(
                        new MergeSortTask<>(a, w,
                                c, lb, mid, counter),
                        new MergeSortTask<>(a, w, c,
                                mid + 1, rb, counter));
                merge(lb, mid + 1, rb + 1);
            }
        }

        private void merge(int left, int mid, int right) {
            T[] a = arr, w = temp;
            int lb = left, rb = mid, k = left;

            // 1 2 3 4 5
            //
            // 6 7 8 9 10
            while (lb < mid && rb < right) {
                T t, al = a[lb], ar = a[rb];
                if (cmp.compare(al, ar) <= 0) {
                    t = al; lb++;
                } else {
                    t = ar; rb++;
                }
                w[k++] = t;
            }
            int sum = 2*right - 2 * left;

            System.arraycopy(a, lb, w, k, mid - lb);
            System.arraycopy(a, rb, w, k,  right - rb);

            System.arraycopy(w, left, a, left, right - left);

            counter.getAndAdd(sum);//sum -k + right);
        }
    }
}
