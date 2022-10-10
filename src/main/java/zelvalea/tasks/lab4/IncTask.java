package zelvalea.tasks.lab4;

import zelvalea.tasks.utils.MathUtils;

import java.util.Scanner;

public class IncTask {

    public static void main0(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();

        if (n < 1) {
            System.out.println("число должно быть больше нуля");
        } else {

            int i = 0;
            int sum = 0;
            while (i <= n) {
                sum += i;
                i++;
                System.out.println("Индекс: " + i + " Сумма: " + sum);
            }
        }
    }

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            int n = scanner.nextInt();

            if (n < 1)
                throw new IllegalArgumentException("число должно быть больше нуля");
            /* 1 + 2 + 3 + 4 + 5 = 15 */

            int sum0 = MathUtils.sumArithmeticProgression(n);

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
