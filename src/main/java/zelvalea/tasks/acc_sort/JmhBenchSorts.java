package zelvalea.tasks.acc_sort;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

@State(Scope.Thread)
public class JmhBenchSorts {

    /*
     * JmhBenchSorts.comparableTimSort   thrpt    5   6593,909 ±  702,546  ops/s
     * JmhBenchSorts.dualPivotQuicksort  thrpt    5  30590,648 ± 2057,498  ops/s
     * JmhBenchSorts.sortBubble          thrpt    5    101,988 ±   44,151  ops/s
     * JmhBenchSorts.sortInsertion       thrpt    5    297,352 ±   46,477  ops/s
     * JmhBenchSorts.sortSelection       thrpt    5    687,338 ±  671,039  ops/s
     */

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(JmhBenchSorts.class.getSimpleName())
                .forks(1)
                .measurementBatchSize(3)
                .warmupIterations(1)
                .build();
        new Runner(opt).run();
    }

    private Integer[] array;
    private int[] unboxed;

    @Setup
    public void prepare() {
        array = ThreadLocalRandom
                .current()
                .ints(1024, -256, 256)
                .boxed()
                .toArray(Integer[]::new);
        unboxed = Arrays.stream(array)
                .mapToInt(x -> x)
                .toArray();
    }

    @Benchmark
    public Integer[] sortBubble() {
        Integer[] copy = array.clone();

        SortOps.BUBBLE.sort(copy, Integer::compare);

        return copy;
    }
    @Benchmark
    public Integer[] sortInsertion() {
        Integer[] copy = array.clone();

        SortOps.INSERTION.sort(copy, Integer::compare);

        return copy;
    }
    @Benchmark
    public Integer[] sortSelection() {
        Integer[] copy = array.clone();

        SortOps.SELECTION.sort(copy, Integer::compare);

        return copy;
    }
    @Benchmark
    public Integer[] comparableTimSort() {
        Integer[] copy = array.clone();

        Arrays.sort(copy);

        return copy;
    }
    @Benchmark
    public int[] dualPivotQuicksort() {
        int[] copy = unboxed.clone();

        Arrays.sort(copy);

        return copy;
    }
}
