package zelvalea.tasks.vm;

import org.apache.commons.math3.analysis.integration.SimpsonIntegrator;

import java.util.function.DoubleUnaryOperator;

public class IntegratorSimpson {
    static final double ACCURACY = Math.pow(10, -8) * 0.5;

    public static void main(String[] args) {
        System.out.println(simpsonRule(
                x -> Math.exp(x) * Math.cos(x),
                0,1, ACCURACY)
        );
    }
    public static double simpsonRule(DoubleUnaryOperator func,
                                       double a, double b, double accuracy) {
        double dx = b - a;
        double olds = 0;
        double oldt = 0.5 * (func.applyAsDouble(a) + func.applyAsDouble(b)) * dx;
        for (int i = 1; ; i *= 2) {
            double t = 0;
            double spacing = dx / i;
            double x = a + 0.5 * spacing;
            for (int q = 0; q < i; q++) {
                t += func.applyAsDouble(x);
                x += spacing;
            }

            t = 0.5 * (olds + t * spacing);

            double s = (4 * t - oldt) / 3.0;

            if (Math.abs(s - olds) <= accuracy) {
                return s;
            }
            olds = s;
            oldt = t;
        }
    }
}
