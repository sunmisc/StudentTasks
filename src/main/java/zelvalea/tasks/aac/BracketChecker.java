package zelvalea.tasks.aac;

import zelvalea.tasks.acc_lab3.QDequeLinked;

import java.util.Map;

public class BracketChecker {
    private static final Map<Character, Character> brackets = Map.of(
            '(', ')',
            '{', '}',
            '[', ']',
            '<', '>'
    );


    public static void main(String[] args) {
        String input = "[()(){}]";
        boolean result = isBalanced(input);
        System.out.println(result); // true

        input = "[()]}";
        result = isBalanced(input);
        System.out.println(result); // false
    }

    public static boolean isBalanced(String input) {
        QDequeLinked<Character> stack = new QDequeLinked<>();
        for (int i = 0; i < input.length(); i++) {
            char ch = input.charAt(i);
            if (brackets.containsKey(ch)) {
                stack.push_front(ch);
            } else if (brackets.containsValue(ch)) {
                if (stack.isEmpty() || stack.pop_front()
                        .map(top -> brackets.get(top) != ch)
                        .orElse(false))
                    return false;
            }
        }
        return stack.isEmpty();
    }
}