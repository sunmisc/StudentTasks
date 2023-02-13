package zelvalea.tasks.legacy.lab6;

public record Polygon (Point[] vertex) {

    // Шнуровка Гаусса
    public double square() {
        double sum = 0;

        for (int i = 0, n = vertex.length, l = n - 1; i < n; i++) {
            Point p = vertex[i], q = vertex[l];
            sum += (q.x() + p.x()) * (q.y() - p.y());
            l = i;
        }

        return Math.abs(sum / 2);
    }
}
