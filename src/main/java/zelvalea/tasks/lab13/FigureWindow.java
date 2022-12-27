package zelvalea.tasks.lab13;

import javax.swing.*;

public class FigureWindow extends JFrame {

    public FigureWindow(int x, int y) {
        setSize(x,y);
        //  add(new AffineRotate());
        add(new Rectangles());
    }
}
