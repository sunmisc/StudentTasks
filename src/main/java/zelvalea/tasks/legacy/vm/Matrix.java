package zelvalea.tasks.legacy.vm;

import java.util.Arrays;

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
        double[] result = solution(a1, b1, 0.5);

        int n = a1.length;
        double[] r = new double[n];
        for (int i = 0; i < n; ++i) {
            for (int x = 0; x < n; ++x) {
                r[x] += a1[x][i] * result[i];
            }
        }
        System.out.println(Arrays.toString(result));
        System.out.println(Arrays.toString(r));
        System.out.println(Arrays.toString(b1));
    }
    public static double[] solution(double[][] a, double[] b, double e) {
        int n = b.length;
        double[] output = new double[n];
        Arrays.setAll(output, i -> b[i]);

        double up;
        do {
            for (int x = 0; x < n; ++x) {
                double s = 0;
                for (int y = 0; y < n; ++y) {
                    if (x != y) {
                        s += a[x][y] * output[y];
                    }
                    output[x] = b[x] / a[x][x] - s / a[x][x];
                }
            }
            up = 0;
            for (int x = 0; x < n; ++x) {
                double q = 0;
                for (int y = 0; y < n; ++y) {
                    q += a[x][y] * output[y];
                }
                up += Math.abs(q - b[x]);
            }
        } while (up > e);

        return output;
    }

}
