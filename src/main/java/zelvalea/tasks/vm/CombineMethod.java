package zelvalea.tasks.vm;


public class CombineMethod { // todo: Method

    public static void main(String[] args) {
        double a = 0.5, b = 0.75;
        System.out.println(findRoot(a,b,0.1));
    }

    static double f(double x) { return (1 / Math.tan(x)) - (x/4); }
    static double f1(double x) { return -(1/Math.pow(Math.sin(x), 2) - 0.25D); }
    static double f2(double x) { return (2*Math.cos(x))/(Math.pow(Math.sin(x), 3)); }
    static double findRoot(double a, double b, double e) {
        double x = 0, c = 0, q;
        do {
            if (f(a) * f2(a) > 0) {
                c = b;
            } else if (f(b) * f2(b) > 0) {
                x = b;
            }
            q = f(x) * (x - c) / (f(x) - f(c));
            x -= q;
        } while (Math.abs(x) > e);
        return x;
    }
}
