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
import java.util.function.IntFunction;

@State(Scope.Thread)
public class SortBench {

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(SortBench.class.getSimpleName())
                .forks(1)
                .measurementBatchSize(3)
                .warmupIterations(1)
                .build();
        new Runner(opt).run();
    }
    private Integer[] array;

    @Setup
    public void prepare() {

        array = ThreadLocalRandom.current().ints(1024, -100, 100).boxed().toArray(new IntFunction<Integer[]>() {
            @Override
            public Integer[] apply(int value) {
                return new Integer[0];
            }
        });
    }
    @Benchmark
    public StatsSnapshot bubble() {
        return SortOps.BUBBLE.sort(array, Integer::compareTo);
    }
    @Benchmark
    public StatsSnapshot insertion() {
        return SortOps.INSERTION.sort(array, Integer::compareTo);
    }
    @Benchmark
    public StatsSnapshot selection() {
        return SortOps.SELECTION.sort(array, Integer::compareTo);
    }
}
