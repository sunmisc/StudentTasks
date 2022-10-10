package zelvalea.tasks.vm;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.DoubleUnaryOperator;

public class Lagrange {
    private static final int INPUT_SIZE = 8;
    private static final Map<Double, Double> TAB_IO;

    private static final DoubleUnaryOperator FUNCTION =
            x -> Math.tan(x/Math.PI);

    static {

        double[] input = ThreadLocalRandom.current()
                .doubles(INPUT_SIZE, 0, 10)
                .toArray();
        Map<Double,Double> map = new HashMap<>(INPUT_SIZE);
        for (double i : input) {
            map.put(i,FUNCTION.applyAsDouble(i));
        }
        TAB_IO = Collections.unmodifiableMap(map);
    }

    public static void main(String[] args) {
        System.out.println(parseTable(TAB_IO));
        try (Scanner scanner = new Scanner(System.in).useLocale(Locale.US)) {
            double point = scanner.nextDouble();

            double f = FUNCTION.applyAsDouble(point);

            System.out.println("f(x)="+f);

            double l = lagrange(TAB_IO, point);

            System.out.println("Интерполяционный многочлен Лагранжа: "+l);
            System.out.println("Погрешность: "+Math.abs(f - l));
        }
    }

    private static double lagrange(Map<Double,Double> io, double point) {
        double r = 0;

        Set<Map.Entry<Double,Double>> nodes = io.entrySet();

        for (Map.Entry<Double,Double> nodeX : nodes) {
            Double x = nodeX.getKey(); double p = 1;
            for (Map.Entry<Double,Double> nodeY : nodes) {
                Double y = nodeY.getKey();
                if (x.equals(y))
                    continue;
                p *= ((point - y) / (x - y));
            }
            r += p * nodeX.getValue();
        }
        return r;
    }

    private static <K,V> String parseTable(Map<K,V> map) {
        StringBuilder builder = new StringBuilder();
        map.forEach((k,v) -> builder.append(k).append(" = ").append(v).append('\n'));
        return builder.toString();
    }
}
