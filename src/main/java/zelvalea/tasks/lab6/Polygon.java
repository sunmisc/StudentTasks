package zelvalea.tasks.lab6;

public class Polygon {
    private final Point[] vertex;

    public Polygon(Point[] vertex) {
        this.vertex = vertex;
    }

    // Шнуровка Гаусса
    public double square() {
        double sum = 0;

        for (int i = 0, n = vertex.length, l = n - 1; i < n; i++) {
            sum += (vertex[l].x() + vertex[i].x()) * (vertex[l].y() - vertex[i].y());
            l = i;
        }

        return Math.abs(sum / 2);
    }
}
