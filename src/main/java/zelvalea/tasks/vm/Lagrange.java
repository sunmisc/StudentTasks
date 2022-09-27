package zelvalea.tasks.vm;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Arrays;
import java.util.Locale;
import java.util.Scanner;
import java.util.function.DoubleUnaryOperator;

public class Lagrange {
    private static final ObjectMapper MAPPER
            = new ObjectMapper();
    private static final DoubleUnaryOperator FUNCTION =
            x -> Math.tan(x/Math.PI);

    public static void main(String[] args) {
        try (InputStream inputStream = Lagrange.class
                .getClassLoader()
                .getResourceAsStream("input_vm.json")
        ) {
            if (inputStream == null) {
                return;
            }
            Reader reader = new InputStreamReader(inputStream);
            InputData data = MAPPER.readValue(reader, InputData.class);

            double[] input = data.input;

            double[] output = Arrays.stream(input)
                    .map(FUNCTION)
                    .toArray();

            try (Scanner scanner = new Scanner(System.in).useLocale(Locale.US)) {
                double node = scanner.nextDouble();

                double f = FUNCTION.applyAsDouble(node);

                System.out.println("f(x)="+f);

                double l = lagrange(input, output, node);

                System.out.println("Интерполяционный многочлен Лагранжа: "+l);
                System.out.println("Погрешность: "+Math.abs(f - l));

            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static double lagrange(double[] input, double[] output, double point) {
        double r = 0;
        int xLen = input.length, yLen = output.length;
        for (int x = 0; x < xLen; x++) {
            double p = 1;
            for (int y = 0; y < yLen; y++) {
                if (x != y) {
                    p *= ((point - input[y]) / (input[x] - input[y]));
                }
            }
            r += p * output[x];
        }
        return r;
    }

    private record InputData(double[] input) {}
}
