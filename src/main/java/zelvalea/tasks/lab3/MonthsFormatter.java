package zelvalea.tasks.lab3;

import java.util.Scanner;

public class MonthsFormatter {


    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        String text = scanner.nextLine().toLowerCase();

        String result;
        switch (text) {
            case "декабрь":
                result = "зима";
                break;
            case "январь":
                result = "зима";
                break;
            case "февраль":
                result = "зима";
                break;
            case "март":
                result = "весна";
                break;
            case "апрель":
                result = "весна";
                break;
            case "май":
                result = "весна";
                break;
            case "июнь":
                result = "лето";
                break;
            case "июль":
                result = "лето";
                break;
            case "август":
                result = "лето";
                break;
            case "сентябрь":
                result = "осень";
                break;
            case "октябрь":
                result = "осень";
                break;
            case "ноябрь":
                result = "осень";
                break;
            default:
                result = "ошибка";
        }

        System.out.println(result);

    }

    public static void main0(String[] args) {

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
