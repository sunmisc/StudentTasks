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
public class JmhSearch {

    /*
     * JmhSearch.binarySearch    1024  thrpt    4  78478,099 ± 24173,640  ops/ms
     * JmhSearch.binarySearch     512  thrpt    4  79335,256 ±  7231,373  ops/ms
     * JmhSearch.unarySearch     1024  thrpt    4   1765,068 ±   275,061  ops/ms
     * JmhSearch.unarySearch      512  thrpt    4   3447,698 ±   470,827  ops/ms
     */

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(JmhSearch.class.getSimpleName())
                .forks(1)
                .warmupIterations(2)
                .measurementIterations(4)
                .build();
        new Runner(opt).run();
    }
    static final int ARRAY_SIZE = 1024;
    private Integer[] array;

    @Param({"1024", "512"})
    private int find;

    @Setup
    public void prepare() {
        array = new Integer[ARRAY_SIZE];
        for (int i = 0; i < ARRAY_SIZE; ++i) {
            array[i] = i + 21;
        }
    }
    @Benchmark
    public int binarySearch() {
        return Search.binarySearch(array, find);
    }
    @Benchmark
    public int unarySearch() {
        return Search.unarySearch(array, find);
    }
}
