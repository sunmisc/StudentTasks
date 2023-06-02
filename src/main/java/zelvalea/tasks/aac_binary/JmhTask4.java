package zelvalea.tasks.aac_binary;


import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.concurrent.TimeUnit;

@State(Scope.Thread)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@BenchmarkMode(Mode.Throughput)
@Warmup(iterations = 2, time = 1)
@Measurement(iterations = 5, time = 1)
@Fork(1)
public class JmhTask4 {

    static final int[] a = new int[]{1,5,2,4,6};
    static final int[] k = new int[]{2,3,2,3,3};

    @Param({"122", "10000"})
    int x;

    @Benchmark
    public int myFind() {
        return Task4.myComputeDaysToCutTrees(a, k, x);
    }
    @Benchmark
    public int binaryFind() {
        return Task4.binaryDaysToCutTrees(a, k, x);
    }
    @Benchmark
    public int slowFind() {
        return Task4.legacyDaysToCutTrees(a, k, x);
    }

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(JmhTask4.class.getSimpleName())
                .build();
        new Runner(opt).run();
    }
}