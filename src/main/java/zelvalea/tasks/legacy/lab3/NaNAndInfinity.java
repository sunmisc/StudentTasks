package zelvalea.tasks.legacy.lab3;

import java.util.Scanner;

public class NaNAndInfinity {

    public static void main(String[] args) {

        try (Scanner scanner = new Scanner(System.in)) {

            System.out.printf("""
                    неопределенность: %s,
                    положительная бесконечность: %s
                    отрицательная бесконечность: %s
                    """,
                    Double.NaN,
                    Double.POSITIVE_INFINITY,
                    Double.NEGATIVE_INFINITY);


            while (scanner.hasNext()) {
                double x = scanner.nextDouble();

                System.out.println(Double.isNaN(Math.sqrt(x))
                        ? "не число"
                        : "число"
                );
            }
        }
    }
}
