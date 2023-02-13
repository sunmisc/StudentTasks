package zelvalea.tasks.aac;

public class QReverse {

    public static void main(String[] args) {

        String text = "foo/bar";

        char[] chars = text.toCharArray();

        int n = chars.length;

        QStack stack = new QStack(n);

        // O(n)
        for (char e : chars) { stack.push(e); }

        StringBuilder sb = new StringBuilder(n);

        // O(n)
        for (int i = 0, sz = stack.size(); i < sz; ++i) {
            sb.append(stack.pop());
        }

        System.out.println(sb); // <- rab/oof
    }
}
