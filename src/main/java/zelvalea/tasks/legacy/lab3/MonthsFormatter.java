package zelvalea.tasks.legacy.lab3;

import java.util.Scanner;

public class MonthsFormatter {

    public static void main(String[] args) {

        try (Scanner scanner = new Scanner(System.in)) {

            String input = scanner
                    .nextLine()
                    .toLowerCase();

            System.out.println(switch (input) {
                case "декабрь","январь","февраль" -> "зима";
                case "март","апрель","май" -> "весна";
                case "июнь","июль","август" -> "лето";
                case "сентябрь","октябрь","ноябрь" -> "осень";

                default -> "unknown";
            });
        }
    }
}
