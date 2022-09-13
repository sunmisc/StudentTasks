package zelvalea.tasks;

import zelvalea.tasks.utils.MathUtils;
import zelvalea.tasks.utils.Pair;

import java.util.Objects;
import java.util.Scanner;

public class QuadraticSolve {

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            double a = scanner.nextDouble();
            double b = scanner.nextDouble();
            double c = scanner.nextDouble();

            Pair<Double> result = MathUtils.quadraticEquation(a,b,c);

            Double r1 = result.first(), r2 = result.second();

            System.out.printf("""
                    Корень 1: %s
                    Корень 2: %s
                    """,
                    Objects.requireNonNullElse(r1, "Нет корня"),
                    Objects.requireNonNullElse(r2, "Нет корня")
            );
        }
    }
}
