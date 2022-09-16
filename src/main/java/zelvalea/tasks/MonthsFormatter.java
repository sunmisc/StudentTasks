package zelvalea.tasks;

import java.util.Locale;
import java.util.Map;
import java.util.Scanner;

public class MonthsFormatter {

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            String m = scanner.nextLine().toLowerCase(Locale.ROOT);

            System.out.println(switch (m) {
                case "декабрь", "январь", "февраль" -> "зима";

                case "март", "апрель", "май" -> "весна";

                case "июнь", "июль", "август" -> "лето";

                case "сентябрь", "октябрь", "ноябрь" -> "осень";

                default -> "Unknown";
            });
        }
    }
}
