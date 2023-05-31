package zelvalea.tasks.utils;

public final class MathUtils {

    private MathUtils() {}


    public static int sumArithmeticProgression(int n) {
        int y = n + 1;
        return (y & 1) == 0
                ? n * (y >> 1)
                : (n >> 1) * y;
    }

    public static Pair<Double> quadraticEquation(
            double a, double b, double c
    ) {

        double determinant = b * b - 4 * a * c;

        if (determinant > 0) {

            double sqrt = Math.sqrt(determinant);

            double t = 2 * a;

            return new Pair<>(
                    (-b + sqrt) / t,
                    (-b - sqrt) / t
            );
        } else if (determinant == 0) {

            double root = -b / (2 * a);
            return new Pair<>(root, root);
        }

        return new Pair<>(null,null);
    }
}
