package zelvalea.tasks.vm;

import java.util.*;
import java.util.function.DoubleUnaryOperator;

public class Lagrange {
    private static final Map<Double, Double> TAB_IO;

    private static final DoubleUnaryOperator FUNCTION =
            x -> Math.tan(x/Math.PI);

    static {
        double[] input = {0.9, 0.1, 0.2, 5.5, 9.0, 20.5};

        Map<Double,Double> map = new HashMap<>(input.length);
        for (double i : input) {
            map.put(i, FUNCTION.applyAsDouble(i));
        }
        TAB_IO = Collections.unmodifiableMap(map);
    }

    public static void main(String[] args) {
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
                if (Objects.equals(x,y))
                    continue;
                p *= ((point - y) / (x - y));
            }
            r += p * nodeX.getValue();
        }
        return r;
    }
}
