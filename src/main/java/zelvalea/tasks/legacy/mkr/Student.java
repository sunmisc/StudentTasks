package zelvalea.tasks.legacy.mkr;

import java.util.*;

public record Student(
        Gender gender,
        int age,
        int course
) {
    public boolean compareAge(Student other) {
        return age > other.age;
    }

    public boolean compareCourse(Student other) {
        return course > other.course;
    }
    public enum Gender { MALE, FEMALE, OTHER }

    public static class SortStudentsManager {
        private final Map<Integer, NavigableSet<Student>> map
                = new HashMap<>();

        public void addStudents(Student student) {
            NavigableSet<Student> students = map.computeIfAbsent(
                    student.course,
                    k -> new TreeSet<>(Comparator.comparingInt(o -> o.age))
            );
            students.add(student);
        }

        public NavigableSet<Student> getStudents(int course) {
            NavigableSet<Student> students = map.getOrDefault(
                    course,
                    Collections.emptyNavigableSet()
            );
            return Collections.unmodifiableNavigableSet(students);
        }
    }
    public static void main(String[] args) {
        SortStudentsManager manager = new SortStudentsManager();

        manager.addStudents(new Student(Gender.MALE, 18, 2));
        manager.addStudents(new Student(Gender.MALE, 19, 2));
        manager.addStudents(new Student(Gender.MALE, 20, 2));
        manager.addStudents(new Student(Gender.MALE, 18, 2));
        manager.addStudents(new Student(Gender.MALE, 17, 2));
        manager.addStudents(new Student(Gender.MALE, 17, 1));

        System.out.println(manager.getStudents(2).last());
    }
}
