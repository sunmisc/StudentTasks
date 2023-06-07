package zelvalea.tasks.aac_binary;


import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

@State(Scope.Benchmark)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@BenchmarkMode(Mode.AverageTime)
@Warmup(iterations = 2, time = 1)
@Measurement(iterations = 5, time = 2)
@Threads(1)
@Fork(1)
public class JmhTask4 {

    static final int CAPACITY = 32;
    int[] a, k;
    @Param({"80", "12000"})
    int x;

    @Setup
    public void prepare() {
        a = new int[CAPACITY]; k = new int[CAPACITY];
        ThreadLocalRandom r = ThreadLocalRandom.current();
        for (int i = 0; i < CAPACITY; ++i) {
            a[i] = r.nextInt(1, 10);
            k[i] = r.nextInt(1, 6);
        }
    }

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