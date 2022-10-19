package zelvalea.tasks.vm;


public class IntegratorSimpson {

    public static void main(String[] args) {
        System.out.println(simpsonRule(0,1, Math.pow(10, -8) * 0.5));
    }
    private static double func(double x) {
        return Math.exp(x) * Math.cos(x);
    }
    public static double simpsonRule(double a, double b, double accuracy) {
        double dx = b - a;
        double olds = 0;
        double oldt = 0.5 * (func(a) + func(b)) * dx;
        for (int i = 1; ; i *= 2) {
            double t = 0;
            double spacing = dx / i;
            double x = a + 0.5 * spacing;
            for (int q = 0; q < i; q++) {
                t += func(x);
                x += spacing;
            }

            t = 0.5 * (olds + t * spacing);

            double s = (4 * t - oldt) / 3;

            if (Math.abs(s - olds) <= accuracy) {
                return s;
            }
            olds = s;
            oldt = t;
        }
    }
}
