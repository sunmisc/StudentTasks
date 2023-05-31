package zelvalea.tasks.aac_recursive;

import java.util.LinkedList;
import java.util.List;

public class Anagrams {

    public static void main(String[] args) {
        String word = "listen";

        List<String> anagrams = generateAnagrams(word);
        System.out.println("Anagrams of "+ word + ": " + anagrams);
    }
    /* O(n!)
     * Т.к первая буква в слове может быть выбрана в качестве первой
     * а оставшиеся рассматриваются рекурсивно -> всего возможно n вариантов
     * выбора первой буквы, где n - длина слова
     * n-1 вариантов выбора второй буквы и т д
     * что дает общее кол-во перестановок n*(n-1)/(n-2)...*1 => n!
     */
    public static List<String> generateAnagrams(String word) {
        int n = word.length();
        if (n == 1)
            return List.of(word); // singleton
        List<String> anagrams = new LinkedList<>();
        for (int i = 0; i < n; ++i) {
            char ch = word.charAt(i);
            String restOfWord = word.substring(0, i) + word.substring(i + 1);

            generateAnagrams(restOfWord)
                    .forEach(sub -> anagrams.add(ch + sub));
        }
        return anagrams;
    }
}
