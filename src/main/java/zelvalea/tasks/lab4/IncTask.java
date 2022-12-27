package zelvalea.tasks.lab4;

import java.util.Scanner;

public class IncTask {

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            int n = scanner.nextInt();

            if (n < 1)
                throw new IllegalArgumentException("число должно быть больше нуля");

            int i = 0, sum = 0;
            while (i <= n) {
                sum += i; i++;
                System.out.println("Индекс: " + i + " Сумма: " + sum);
            }
        }
    }
}
