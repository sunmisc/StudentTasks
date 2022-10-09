package zelvalea.tasks.lab6;

public record Point(
        double x,
        double y
) {

    public double distance(Point p) {
        return Math.sqrt(distanceSquared(p));
    }

    public double distanceSquared(Point p) {
        double dx = this.x - p.x;
        double dy = this.y - p.y;
        return dx * dx + dy * dy;
    }


    @Override
    public String toString() {
        return "Point{" + "x=" + x + ", y=" + y + '}';
    }
}
