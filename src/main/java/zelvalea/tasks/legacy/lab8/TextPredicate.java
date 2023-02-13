package zelvalea.tasks.legacy.lab8;

import java.util.Set;
import java.util.StringJoiner;

public class TextPredicate {
    private static final Set<Character> VOWELS = Set.of(
            'а', 'я', 'у', 'ю', 'о', 'е', 'ё', 'э', 'и', 'ы'
    );
    public static void main(String[] args) {
        String text = "ала ла лла лалал";
        int len = 3;
        StringJoiner joiner = new StringJoiner(" ");
        for (String w : text.split(" ")) {
            if (w.length() != len || VOWELS.contains(w.charAt(0))) {
                joiner.add(w);
            }
        }
        System.out.println(joiner);
    }
}
