package zelvalea.tasks.legacy.vm;

import java.util.function.DoubleUnaryOperator;

public class IntegratorTrapezoid {
    static final double ACCURACY = Math.pow(10, -8) * 0.5;
    public static void main(String[] args) {
        System.out.println(trapezoidRule(
                x -> x*Math.pow(Math.log(x), 2),
                2,3, ACCURACY)
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

            if (Math.abs(area - prev_area) <= accuracy) {
                return area;
            }
        }
    }
}
