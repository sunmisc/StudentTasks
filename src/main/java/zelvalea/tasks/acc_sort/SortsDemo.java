package zelvalea.tasks.acc_sort;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.util.Arrays;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class SortsDemo {

    private static final ObjectMapper MAPPER
            = new ObjectMapper(); // jaskson powered!
    private static final int ARRAY_SIZE = 6;


    public static void main(String[] args) throws Throwable {

        try (Scanner scanner = new Scanner(System.in)) {

            System.out.println("Enter number to specify the sorting algorithm 1 2 or 3");
            if (!scanner.hasNextInt()) {
                throw new IllegalArgumentException();
            }

            int sortCode = scanner.nextInt();

            SortOps sortOps = SortOps.BUBBLE;

            Double[] in = ThreadLocalRandom.current()
                    .doubles(ARRAY_SIZE)
                    .boxed()
                    .toArray(Double[]::new);

            Double[] out = in.clone();

            System.out.println(sortOps.sort(out, Double::compare));


            System.out.println(Arrays.toString(out));
            File result = new File(
                    "sort.json");

            System.out.println(result.getPath());
            if (result.createNewFile()) {
                System.out.println("file has been created");
            }

            MAPPER.writerWithDefaultPrettyPrinter()
                    .writeValue(result, new ArrayPair<>(
                            in, out
                    ));

        }
    }

    private record ArrayPair<T>(
            T[] input,
            T[] output
    ) { }
}
