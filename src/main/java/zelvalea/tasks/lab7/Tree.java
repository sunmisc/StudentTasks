package zelvalea.tasks.lab7;

public record Tree(
        String type,
        int years,
        double height
) {


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Tree tree = (Tree) o;

        if (years != tree.years) return false;
        if (Double.compare(tree.height, height) != 0) return false;
        return type.equals(tree.type);
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = type.hashCode();
        result = 31 * result + years;
        temp = Double.doubleToLongBits(height);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}
