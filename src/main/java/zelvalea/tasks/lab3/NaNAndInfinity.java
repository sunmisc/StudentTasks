package zelvalea.tasks.lab3;

import java.util.Scanner;

public class NaNAndInfinity {



    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("неопределенность: "+00.0/0.0);
        System.out.println("положительная бесконечность: "+(1.0/0.0));
        System.out.println("отрицательная бесконечность: "+(-1.0/0.0));

        double x = scanner.nextDouble();

        System.out.println(Double.isNaN(Math.sqrt(x))
                ? "не число"
                : "число"
        );
    }

    public static void main0(String[] args) {

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
