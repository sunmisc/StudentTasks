package zelvalea.tasks.lab6;

import java.util.function.Predicate;

public class Test {

    public static void main(String[] args) {
        Point[] points = {
                new Point(2131315, 2131315),
                new Point(-2131315, 2131315),

                new Point(-2131314, -2131314),
                new Point(2131315, -2131315),

                new Point(2131315, -2131315),
                new Point(1, 0),
        };

        Point target = null; Double minSum = null;
        for (Point outer : points) {
            double sum = 0;

            for (Point p : points) {
                sum += outer.distanceSquared(p);
            }
            if (minSum == null || sum < minSum) {
                minSum = sum;
                target = outer;
            }
        }
        MaxTriangle triangle = new MaxTriangle(points);
        System.out.println(triangle.a);
        System.out.println(triangle.b);
        System.out.println(triangle.c);

    }
}