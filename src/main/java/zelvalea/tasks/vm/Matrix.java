package zelvalea.tasks.vm;

import java.util.Arrays;
import java.util.Map;

public class Matrix {


    public static void main(String[] args) {
        double[][] a1 = {
                {2.336, -0.742, 0.062},
                {0.652, 2.244, -0.316},
                {0.060, -0.282, 2.234}
        };
        double[] b1 = {
                -0.9, 0.65, 3.1
        };
        System.out.println(Arrays.toString(solution(a1, b1, 0.1)));
        double a = 3,b = -1,e = 0.001, x = 0, q;
        do {
            if (f(a) * f2(a) > 0) {
                x = a;
            } else if (f(b) * f2(b) > 0) {
                x = b;
            }
            q = f(x)/f1(x);
            x -= q;
        } while (Math.abs(q) > e);
        System.out.println(x);
    }
    static double f(double x) {
        return 3 * x - Math.cos(x) - 1;
    }
    static double f1(double x) {
        return 3 + Math.sin(x);
    }
    static double f2(double x) {
        return Math.cos(x);
    }
    public static double[] solution(double[][] a, double[] b, double e) {
        int n = b.length;
        double[] output = new double[n];
        Arrays.setAll(output, i -> a[i][0]);

        double low, up;
        do {
            double[] old = output.clone();

            for (int x = 0; x < n; ++x) {
                double s = 0;
                for (int y = 0; y < n; ++y) {
                    if (x != y) {
                        s += a[x][y] * output[y];
                    }
                    output[x] = b[x] / a[x][x] - s / a[x][x];
                }
            }
            up = 0; low = 0;
            for (int i = 0; i < n; ++i) {
                up += output[i] - old[i];
                low += output[i];
            }
        } while (up / low > e);

        return output;
    }

}
