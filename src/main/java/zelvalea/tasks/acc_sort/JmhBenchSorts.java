package zelvalea.tasks.acc_sort;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.concurrent.ThreadLocalRandom;

@State(Scope.Thread)
public class JmhBenchSorts {

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

    @Setup
    public void prepare() {
        array = ThreadLocalRandom
                .current()
                .ints(128, -100, 100)
                .boxed()
                .toArray(Integer[]::new);
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
}
