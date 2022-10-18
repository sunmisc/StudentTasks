package zelvalea.tasks.vm;

import java.util.function.DoubleUnaryOperator;

public final class IntegratorTrapezoid {
    static final double ACCURACY = 5.0E-9; // 0.5 * 10^-8

    public static void main(String[] args) {
        System.out.println(trapezoidRule(
                x -> Math.exp(x) / (1 + x),
                0,1, ACCURACY)
        );
    }
    public static double trapezoidRule(DoubleUnaryOperator func,
                                       double a, double b, double accuracy) {
        double dx = b - a;
        double area = 0.5 * (func.applyAsDouble(a) + func.applyAsDouble(b)) * dx;
        for (int i = 1; ; i *= 2) {
            double prev_area = area, next_area = 0;
            double spacing = dx / i;
            double x = a + 0.5 * spacing;
            for (int q = 0; q < i; q++) {
                next_area += func.applyAsDouble(x);
                x += spacing;
            }
            area = 0.5 * (prev_area + next_area * spacing);

            // equals 64 bits?
            if (Math.abs(area - prev_area) <= accuracy) {
                return area;
            }
        }
    }
}
