package zelvalea.tasks.lab7;

import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;

public class MatrixSumDefSerial {

    public static void main(String[] args) throws IOException {
        main0();
    }
    public static void main0() throws IOException {
        String resource = "E:\\IdeaProjects\\StudentTasks\\src\\main\\resources\\";
        String[] args = {
                resource + "/file1/",
                resource + "/file2/",
                resource + "/file3/",
        };
        writeMatrix(args[0], new double[][]{
                {0,1,3},
                {1,5,9},
                {6,2,8}
        });
        writeMatrix(args[1], new double[][]{
                {0,1,3},
                {1,5,9},
                {6,2,8}
        });
        main1(args);
    }


    public static void main1(String[] args) throws IOException {
        if (args.length < 3) throw new IllegalArgumentException();
        String input1 = args[0], input2 = args[1], output = args[2];

        try (
                ObjectInputStream oi1 = new ObjectInputStream(new FileInputStream(input1));
                ObjectInputStream oi2 = new ObjectInputStream(new FileInputStream(input2));
        ) {
            double[][] matrix1 = (double[][]) oi1.readObject();
            double[][] matrix2 = (double[][]) oi2.readObject();

            int minX = Math.min(matrix1.length, matrix2.length);
            int minY = Math.min(matrix1[0].length, matrix2[0].length);

            double[][] dataOutput = new double[minX][minY];
            for (int x = 0; x < minX; ++x) {
                for (int y = 0; y < minY; ++y) {
                    dataOutput[x][y] = matrix1[x][y] + matrix2[x][y];
                }
            }
            System.out.println(Arrays.deepToString(dataOutput));
            writeMatrix(output, dataOutput);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private static void writeMatrix(String path, double[][] data) throws IOException {
        Path outputPath = Path.of(path);
        System.out.println(outputPath.toAbsolutePath());
        if (Files.notExists(outputPath)) { Files.createFile(outputPath); }

        try (ObjectOutputStream oo = new ObjectOutputStream(Files.newOutputStream(outputPath))) {
            oo.writeObject(data);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
