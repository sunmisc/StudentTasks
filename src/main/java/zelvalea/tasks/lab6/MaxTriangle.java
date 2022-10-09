package zelvalea.tasks.lab6;

import java.util.function.BiFunction;

public class MaxTriangle implements Triangle {

    public final Point a,b,c;

    public MaxTriangle(Point[] source) {

        int len = source.length;

        if (len < 3) {
            throw new IllegalArgumentException();
        } else if (len == 3) {
            this.a = source[0];
            this.b = source[1];
            this.c = source[2];
        } else {

            Point a = findBy((prev, target) -> {
                double q = target.x() + target.y();
                return prev == null || q > prev ? q : null;
            }, source);
            Point b = findBy((prev, target) -> {
                double q = -target.x() - target.y();
                return prev == null || q > prev ? q : null;
            }, source);
            Point c = findBy((prev, target) -> {
                double q = -target.x() + target.y();
                return prev == null || q > prev ? q : null;
            }, source);
            Point d = findBy((prev, target) -> {
                double q = target.x() - target.y();
                return prev == null || q > prev ? q : null;
            }, source);
            double d1 = a.distanceSquared(b);

            double d2 = c.distanceSquared(d);

            if (d1 > d2) {
                this.a = a;
                this.b = b;

                double a1 = a.distanceSquared(d) + d.distanceSquared(b);
                double a2 = a.distanceSquared(c) + c.distanceSquared(b);

                this.c = a1 > a2 ? c : d;

            } else {
                this.a = c;
                this.b = d;

                double a1 = c.distanceSquared(a) + a.distanceSquared(d);
                double a2 = c.distanceSquared(b) + b.distanceSquared(d);

                this.c = a1 > a2 ? a : b;
            }
        }
    }

    private static Point findBy(BiFunction<Double, Point, Double> function,
                                Point... points) {
        Point prevPoint = null; Double prevVal = null;
        for (Point p : points) {
            Double next = function.apply(prevVal, p);
            if (next != null) {
                prevVal = next;
                prevPoint = p;
            }
        }
        return prevPoint;
    }

    @Override public Point pointA() { return a; }

    @Override public Point pointB() { return b; }

    @Override public Point pointC() { return c; }


    @Override
    public double perimeter() {
        double a = pointA().distance(pointB());

        double b = pointB().distance(pointC());

        double c = pointC().distance(pointA());

        return a + b + c;
    }
}
