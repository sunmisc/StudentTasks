package zelvalea.tasks.legacy.lab6;

public class NearestPoints {

    public static void main(String[] args) {
        Point[] points = {
                new Point(123, 31),
                new Point(213, 1),
                new Point(928, 213),
        };

        Point candidate = null; Double minSum = null;

        for (Point outer : points) {
            double sum = 0;
            for (Point p : points) {
                sum += outer.distanceSquared(p);
            }
            if (minSum == null || sum < minSum) {
                minSum = sum;
                candidate = outer;
            }
        }
        System.out.println(candidate);
    }
}
