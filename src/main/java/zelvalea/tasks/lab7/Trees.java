package zelvalea.tasks.lab7;

import java.util.TreeSet;

public class Trees {


    public static void main(String[] args) {
        TreeSet<Tree> trees = new TreeSet<>((o1, o2) -> {
            double weight1 = o1.height() - o1.years();
            double weight2 = o2.height() - o2.years();
            return Double.compare(weight2, weight1);
        });

        trees.add(new Tree("", 3, 15));
        trees.add(new Tree("", 6, 10));
        trees.add(new Tree("", 1, 20));

        System.out.println(trees);

    }
}
