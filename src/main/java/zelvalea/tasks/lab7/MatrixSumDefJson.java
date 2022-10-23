package zelvalea.tasks.lab7;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class MatrixSumDefJson {

    // jackson
    private static final ObjectMapper MAPPER
            = new ObjectMapper();

    public static void main(String[] args) throws IOException {
        if (args.length < 3) {
            throw new IllegalArgumentException();
        }
        String file1 = args[0], file2 = args[1], file3 = args[2];

        Matrix matrix1 = MAPPER.readValue(new File(file1), Matrix.class);

        Matrix matrix2 = MAPPER.readValue(new File(file2), Matrix.class);

        double[][] data1 = matrix1.data(), data2 = matrix2.data();

        int minLenX = Math.min(data1.length, data2.length);
        int minLenY = Math.min(data1[0].length, data2[0].length);

        double[][] dataOutput = new double[minLenX][minLenY];

        for (int x = 0; x < minLenX; ++x) {
            for (int y = 0; y < minLenY; ++y) {
                dataOutput[x][y] = data1[x][y] + data2[x][y];
            }
        }

        Matrix output = new Matrix(dataOutput);

        MAPPER.writeValue(new File(file3), output);

    }

}
