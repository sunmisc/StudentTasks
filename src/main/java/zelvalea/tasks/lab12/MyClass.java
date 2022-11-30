package zelvalea.tasks.lab12;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.io.File;
import java.io.IOException;

public class MyClass {

    public static void main (String [] args) throws IOException {
        JFrame myWindow = new SimpleWindow("Test",
                250,100,
                ImageIO.read(new File("E:\\IdeaProjects\\StudentTasks\\src\\main\\resources\\2288504.png")),
                ImageIO.read(new File("E:\\IdeaProjects\\StudentTasks\\src\\main\\resources\\hU7i77-jNKI.jpg")));

        myWindow.setVisible(true);
    }
}
