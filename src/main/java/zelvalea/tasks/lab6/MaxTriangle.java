package zelvalea.tasks.lab6;

public class MaxTriangle implements Triangle {

    public final Point a,b,c;
    private final double perimeter;

    public MaxTriangle(Point[] source) {

        int len = source.length;

        if (len < 3) {
            throw new IllegalArgumentException();
        } else if (len == 3) {
            this.a = source[0];
            this.b = source[1];
            this.c = source[2];
            this.perimeter = computePerimeter(a,b,c);
        } else {
            double p = 0;
            // O(n^3)
            Point aT = null, bT = null, cT = null;
            for (Point a : source) {
                for (Point b : source) {
                    for (Point c : source) {
                        double t = computePerimeter(a,b,c);
                        if (t > p) {
                            p = t;
                            aT = a; bT = b; cT = c;
                        }
                    }
                }
            }
            this.a = aT; this.b = bT; this.c = cT;
            this.perimeter = p;
        }
    }
    private static double computePerimeter(Point a, Point b, Point c) {
        return a.distance(b) +
                b.distance(c) +
                c.distance(a);
    }

    @Override public Point pointA() { return a; }

    @Override public Point pointB() { return b; }

    @Override public Point pointC() { return c; }

    @Override public double perimeter() { return perimeter; }
}
