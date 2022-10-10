package zelvalea.tasks.lab6;

public record Triangle(Point a, Point b, Point c) {

    private static double computePerimeter(Point a, Point b, Point c) {
        return a.distance(b) + b.distance(c) + c.distance(a);
    }

    public static Triangle largerTriangle(Point... source) {
        int len = source.length;
        if (len < 3) {
            throw new IllegalArgumentException();
        } else if (len == 3) {
            return new Triangle(source[0], source[1], source[2]);
        } else { // O(n^3)
            double p = 0;
            Point aT = null, bT = null, cT = null;
            for (Point a : source) {
                for (Point b : source) {
                    for (Point c : source) {
                        double t = computePerimeter(a,b,c);
                        if (t > p) {
                            p = t; aT = a; bT = b; cT = c;
                        }
                    }
                }
            }
            return new Triangle(aT, bT, cT);
        }
    }
    public Point pointA() { return a; }
    public Point pointB() { return b; }
    public Point pointC() { return c; }

    public double perimeter() {
        return computePerimeter(a,b,c);
    }
}
