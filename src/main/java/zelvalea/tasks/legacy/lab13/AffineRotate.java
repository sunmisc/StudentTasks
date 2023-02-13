package zelvalea.tasks.legacy.lab13;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.util.concurrent.ThreadLocalRandom;

public class AffineRotate extends JPanel {
    private static final int[] x = {20, 35, 40};
    private static final int[] y = {30, 20, 30};

    private static final double _2PI     = Math.PI * 2;
    private static final double SEGMENTS = _2PI / 12;

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        super.setBackground(Color.WHITE);

        int h_half = getHeight() >> 1, w_half = getWidth() >> 1;

        if (g instanceof Graphics2D g2) {
            for (double i = 0; i < _2PI; i += SEGMENTS) {
                AffineTransform at = new AffineTransform();
                at.translate(w_half, h_half);

                at.rotate(i);

                at.scale(2.0, 2.0);

                ThreadLocalRandom tlr = ThreadLocalRandom.current();

                float f1 = tlr.nextFloat(0, 1);
                float f2 = tlr.nextFloat(0, 1);
                float f3 = tlr.nextFloat(0, 1);

                g2.setColor(Color.getHSBColor(f1, f2, f3));

                g2.setTransform(at);

                g2.fillPolygon(x, y, 3);
            }
        }
    }
}

