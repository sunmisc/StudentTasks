package zelvalea.tasks.legacy.lab8;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

@State(Scope.Thread)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
public class BenchReplace {


    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(BenchReplace.class.getSimpleName())
                .forks(1)
                .warmupIterations(1)
                .measurementIterations(3)
                .build();
        new Runner(opt).run();
    }
    @Param("фывфыврарарарарафывыфвыфрарарарфвыфвфывфывфрарафывфвыфвфывфРраРРВрарарфырар")
    private String val;

    private static final Pattern R_PATTER = Pattern.compile("[Р-р][А-а]]");

    @Benchmark
    public String patternReplace() {
        return R_PATTER
                .matcher(val)
                .replaceAll("ро");
    }

    @Benchmark
    public String builderReplace() {
        char[] chars = val.toCharArray();

        for (int i = 1, n = chars.length; i < n; ) {
            char current = Character.toLowerCase(chars[i - 1]);
            char next = Character.toLowerCase(chars[i]);

            if (current == 'р' && next == 'а') {
                chars[i] = 'о';
                i += 2;
            } else if (next != 'р') {
                i += 2;
            } else {
                i++;
            }
        }
        return String.valueOf(chars);
    }
}
