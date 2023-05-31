package zelvalea.tasks.aac;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Task2Queue {

    private static final ObjectMapper MAPPER
            = new ObjectMapper(); // powered by Jackson

    public static void main(String[] args) throws IOException {

        StudentsData data = //MAPPER.readValue(new File(args[0]), StudentsData.class);
        new StudentsData(List.of(
                Student.of("Иванов", 1),
                Student.of("Степинкин", 6),
                Student.of("Новиков", 5),
                Student.of("Кузнецов", 3),
                Student.of("Козлов", 4),
                Student.of("Степочник", 1)
        ));

        Collection<Student> students = data.students;

        int cap = students.size();

        @SuppressWarnings("unchecked")
        ArrayQueue<Student>[] table = new ArrayQueue[6];
        students.forEach(student -> {
            int c = student.course-1;
            ArrayQueue<Student> queue = table[c];
            if (queue == null)
                queue = table[c] = new ArrayQueue<>(cap);

            queue.push(student);
        });

        List<Student> sorts = new ArrayList<>(cap);

        for (ArrayQueue<Student> q : table) {
            if (q == null)
                continue;
            System.out.println(q);
            q.forEach(sorts::add);
        };


        StudentsData sortData = new StudentsData(sorts);
        System.out.println(MAPPER
                .writerWithDefaultPrettyPrinter()
                .writeValueAsString(sortData));
        MAPPER
                .writerWithDefaultPrettyPrinter()
                .writeValue(new File(args[1]), sortData);
    }

    private record StudentsData(
            Collection<Student> students
    ) { }
    private record Student(String lastName, int course) {
        public static Student of(String lastName, int course) {
            return new Student(lastName, course);
        }

        @Override
        public String toString() {
            return lastName + "=" + course;
        }
    }
}
