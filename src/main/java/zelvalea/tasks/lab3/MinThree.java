package zelvalea.tasks.lab3;

import java.util.Scanner;

public class MinThree {

    public static void main(String[] args) {

        try (Scanner scanner = new Scanner(System.in)) {
            double x = scanner.nextDouble();
            double y = scanner.nextDouble();
            double z = scanner.nextDouble();

            double min = Math.min(x,Math.min(y,z));

            System.out.println(min);
        }

    }
}
