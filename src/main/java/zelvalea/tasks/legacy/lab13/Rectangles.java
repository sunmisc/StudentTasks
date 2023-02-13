package zelvalea.tasks.legacy.lab13;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

public class Rectangles extends JPanel {

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        int h_half = getHeight() >> 1, w_half = getWidth() >> 1;

        for (int i = 0; i < 3; ++i) {
            ThreadLocalRandom tlr = ThreadLocalRandom.current();
            int startX = tlr.nextInt(0,h_half);
            int startY = tlr.nextInt(0,w_half);

            RectangleCoordinatesFactory factory =
                    new RectangleCoordinatesFactory(startX, startY);
            Point[] points = factory.coordinates();
            int n = points.length;

            int[] x = Arrays.stream(points)
                    .mapToInt(p -> p.x)
                    .toArray();
            int[] y = Arrays.stream(points)
                    .mapToInt(p -> p.y)
                    .toArray();

            float f1 = tlr.nextFloat(0, 1);
            float f2 = tlr.nextFloat(0, 1);
            float f3 = tlr.nextFloat(0, 1);

            g.setColor(Color.getHSBColor(f1, f2, f3));
            g.fillPolygon(x, y, n);
        }
    }


    public record RectangleCoordinatesFactory(int x,int y)
            implements CoordinatesFactory {

        @Override
        public Point[] coordinates() {
            int dx = x << 1, dy = y << 1;
            return new Point[]{
                    new Point(x,  y),
                    new Point(x,  dy),
                    new Point(dx, dy),
                    new Point(dx, y)
            };
        }
    }

    @FunctionalInterface
    public interface CoordinatesFactory {
        Point[] coordinates();
    }
}
