package zelvalea.tasks.legacy.lab13;

import javax.swing.*;
import java.io.IOException;

public class Starter {

    public static void main (String[] args) throws IOException {
        JFrame myWindow = new FigureWindow(
                512,512);
        myWindow.setVisible(true);
    }
}
