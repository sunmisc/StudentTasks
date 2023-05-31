package zelvalea.tasks.aac_tree;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.Throughput)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@Warmup(iterations = 3, time = 500, timeUnit = TimeUnit.MILLISECONDS)
@Measurement(iterations = 10, time = 500, timeUnit = TimeUnit.MILLISECONDS)
@Fork(1)
@State(Scope.Thread)
public class BenchTreeCollection {

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(BenchTreeCollection.class.getSimpleName())
                .build();
        new Runner(opt).run();
    }

    private BiTreeCollection<Integer> balanced;
    private BiTreeCollection<Integer> progressive;

    private int x,y;
    @Setup
    public void prepare() {
        balanced    = new BiTreeCollection<>(Integer::compareTo);
        progressive = new BiTreeCollection<>(Integer::compareTo);
    }
    @Benchmark
    public Integer fastInsert() {
        int val = x++;
        balanced.insert(val, true);
        return val;
    }
    @Benchmark
    public Integer slowInsert() {
        int val = y++;
        progressive.insert(val, false);
        return val;
    }
}
