package zelvalea.tasks.lab8;

import java.util.Scanner;

public class Task6 {

    public static void main(String[] args) {

        try (Scanner scanner = new Scanner(System.in)) {

            String input = scanner.nextLine();

            char[] chars = input.toCharArray();

            for (int i = 1, n = chars.length; i < n; ) {
                char current = Character.toLowerCase(chars[i - 1]);
                char next = Character.toLowerCase(chars[i]);

                if (current == 'р' && next == 'а') {
                    chars[i] = 'о';
                    i += 2;
                } else if (next != 'р') {
                    i += 2;
                } else {
                    i++;
                }
            }
            System.out.println(String.valueOf(chars));
        }
    }
}
