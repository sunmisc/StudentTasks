package zelvalea.tasks.lab4;

import java.util.Scanner;

public class IncTask {

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            int n = scanner.nextInt();

            if (n < 1)
                throw new IllegalArgumentException("число должно быть больше нуля");
            /* 1 + 2 + 3 + 4 + 5 = 15 */

            // division by two is a right shift
            int sum0 = (n * (n + 1)) >> 1; // sum of an arithmetic progression

            if (sum0 < 0)
                throw new ArithmeticException("ошибка нехватки памяти");

            System.out.println("Целевая сумма: " + sum0);

            int i = 0, sum = 0;
            while (i <= n) {
                sum += i; i++;
                System.out.println("Индекс: " + i + " Сумма: " + sum);
            }
            if (sum0 != sum)
                throw new RuntimeException("Ошибка в вычислениях");
        }
    }
}
