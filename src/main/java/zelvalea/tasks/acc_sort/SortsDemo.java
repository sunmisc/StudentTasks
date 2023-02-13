package zelvalea.tasks.acc_sort;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class SortsDemo {

    private static final ObjectMapper MAPPER
            = new ObjectMapper(); // jaskson powered!
    private static final int ARRAY_SIZE = 8;


    public static void main(String[] args) throws Throwable {

        try (Scanner scanner = new Scanner(System.in)) {

            System.out.println("Enter number to specify the sorting algorithm 1 2 or 3");
            if (!scanner.hasNextInt()) {
                throw new IllegalArgumentException();
            }

            int sortCode = scanner.nextInt();

            SortOps sortOps = SortOps.getById(sortCode)
                    .orElseThrow(() -> new IllegalArgumentException(
                            "there is no such sorting algorithm")
                    );

            Double[] in = ThreadLocalRandom.current()
                    .doubles(ARRAY_SIZE)
                    .boxed()
                    .toArray(Double[]::new);

            Double[] out = in.clone();

            sortOps.sort(out, Double::compare);


            File result = new File(
                    "/media/sunmisc/disk_usb/IdeaProjects/StudentTasks/src/main/resources/sort.json");

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
