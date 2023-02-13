package zelvalea.tasks.legacy.vm;

import static java.lang.Math.*;

public class Newton {
    public static void main(String[] args){
        double a = -0.6, b = 1.56;
        System.out.println(findRoot(a,b,0.1));
    }
    static double f(double x) {
        return (1 / x) - PI * cos(PI*x);
    }

    static double f1(double x) {
        return pow(PI, 2) * sin(PI*x) - (1 / pow(x, 2));
    }
    static double f2(double x) {
        return pow(PI, 3) * cos(PI * x) - (2 / pow(x, 3));
    }
    static double findRoot(double a, double b, double e) {
        double x = 0, q;
        if (f(a) * f2(x) > 0) {
            x = a;
        } else if (f(b) * f2(b) > 0) {
            x = b;
        } else {
            throw new IllegalArgumentException("Корни говно");
        }
        do {
            q = f(x) / f1(x);
            x -= q;
        } while (Math.abs(q) > e);
        return x;
    }
}