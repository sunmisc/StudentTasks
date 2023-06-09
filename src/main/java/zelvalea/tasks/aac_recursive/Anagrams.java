package zelvalea.tasks.aac_recursive;

import java.util.BitSet;
import java.util.LinkedList;
import java.util.List;

public class Anagrams {

    public static void main(String[] args) {
        String word = "мама";

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
        List<String> anagrams = new LinkedList<>();
        BitSet bitSet = new BitSet(word.length());
        nextAnagram("", word, bitSet, anagrams);
        return anagrams;
    }

    private static void
    nextAnagram(String prefix, String word,
                BitSet used,
                List<String> anagrams) {
        final int n = word.length();
        if (prefix.length() == n) {
            anagrams.add(prefix);
            return;
        }
        for (int i = 0; i < n; i++) {
            if (used.get(i) || (i > 0 && !used.get(i - 1)
                    && word.charAt(i) == word.charAt(i - 1)))
                continue;
            used.set(i);
            nextAnagram(prefix + word.charAt(i), word, used, anagrams);
            used.clear(i);
        }
    }
}
