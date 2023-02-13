package zelvalea.tasks.legacy.lab12;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class SimpleWindow extends JFrame {
    private final Image source, closed;
    private final JTextField textField = new JTextField(5);

    public SimpleWindow(String name, int x, int y,
                        Image source, Image closed) {
        super(name);

        this.source = source;
        this.closed = closed;

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(x, y);
        Container container = getContentPane();
        container.setLayout(new GridLayout());
        container.add(textField);

        setIconImage(source);
        addWindowListener(new ChangeIcon(this));
        addComponentListener(new ResizeListener(this));

    }
    private record ResizeListener(SimpleWindow window)
            implements ComponentListener {

        @Override
        public void componentResized(ComponentEvent e) {
            Rectangle bounds = e.getComponent().getBounds();
            int h = bounds.height, w = bounds.width;
            window.textField.setText(w + "x"+ h);
        }

        @Override public void componentMoved(ComponentEvent e) {}

        @Override public void componentShown(ComponentEvent e) {}

        @Override public void componentHidden(ComponentEvent e) {}
    }
    private static class ChangeIcon extends WindowAdapter {
        private final SimpleWindow window;

        ChangeIcon(SimpleWindow window) {
            this.window = window;
        }

        @Override
        public void windowActivated(WindowEvent e) {
            super.windowActivated(e);
            e.getWindow().setIconImage(window.source);
        }

        @Override
        public void windowDeactivated(WindowEvent e) {
            super.windowDeactivated(e);
            e.getWindow().setIconImage(window.closed);
        }
        @Override public void windowIconified(WindowEvent e) { super.windowIconified(e); }
        @Override public void windowOpened(WindowEvent e) { super.windowOpened(e); }

        @Override public void windowClosing(WindowEvent e) { super.windowClosing(e); }

        @Override public void windowClosed(WindowEvent e) {super.windowClosed(e);}


        @Override public void windowDeiconified(WindowEvent e) { super.windowDeiconified(e); }

        @Override public void windowStateChanged(WindowEvent e) { super.windowStateChanged(e); }

        @Override public void windowGainedFocus(WindowEvent e) { super.windowGainedFocus(e); }

        @Override public void windowLostFocus(WindowEvent e) { super.windowLostFocus(e); }
    }
}
