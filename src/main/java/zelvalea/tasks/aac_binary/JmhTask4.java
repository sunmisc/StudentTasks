package zelvalea.tasks.aac_binary;


import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

@State(Scope.Thread)
public class JmhTask4 {


    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(JmhTask4.class.getSimpleName())
                .forks(1)
                .measurementBatchSize(3)
                .warmupIterations(1)
                .build();
        new Runner(opt).run();
    }

    static final int[] a = new int[]{1, 2, 2, 2};
    static final int[] k = new int[]{2,2,2,2,2};

    static final int x = 81179;


    @Benchmark
    public int fastFind() {

        return Task4.compute(a, k, x);
    }
    @Benchmark
    public int slowFind() {

        return Task4.legacyCompute(a, k,1,0, x);
    }
}