package zelvalea.tasks.lab7;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.StringJoiner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MatrixSumDefParse {

    private static final String DELIMITER = " ";

    public static void main(String[] args) throws IOException {
        if (args.length < 3) throw new IllegalArgumentException();
        String input1 = args[0], input2 = args[1], output = args[2];

        double[][] matrix1 = readMatrix(Files.lines(Path.of(input1)));
        double[][] matrix2 = readMatrix(Files.lines(Path.of(input2)));


        int minX = Math.min(matrix1.length, matrix2.length);
        int minY = Math.min(matrix1[0].length, matrix2[0].length);

        double[][] dataOutput = new double[minX][minY];
        for (int x = 0; x < minX; ++x) {
            for (int y = 0; y < minY; ++y) {
                dataOutput[x][y] = matrix1[x][y] + matrix2[x][y];
            }
        }
        Path outputPath = Path.of(output);
        if (Files.notExists(outputPath)) { Files.createFile(outputPath); }

        String write = Arrays.stream(dataOutput)
                .map(doubles -> {
                    StringJoiner joiner = new StringJoiner(DELIMITER);
                    for (double d : doubles) {
                        joiner.add(Double.toString(d));
                    }
                    return joiner.toString();
                }).collect(Collectors.joining("\n"));

        Files.writeString(outputPath, write);
    }

    private static double[][] readMatrix(Stream<String> lines) {
        return lines
                .map(x -> {
                    String[] strings = x.split(DELIMITER);
                    int n = strings.length;
                    double[] data = new double[n];
                    for (int i = 0; i < n; ++i) {
                        data[i] = Double.parseDouble(strings[i]);
                    }
                    return data;
                }).toArray(double[][]::new);
    }
}
