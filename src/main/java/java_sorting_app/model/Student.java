package java_sorting_app.model;

import java_sorting_app.validator.DataValidator;

import java.util.Comparator;
import java.util.Optional;

public class Student implements Comparable<Student>, SerializableToCSVString {
    private int groupNumber;
    private double averageGrade;
    private long studentBookNumber;

    // Конструктор с параметрами, который используется в билдере
    private Student(int groupNumber, double averageGrade, long studentBookNumber) {
        this.groupNumber = groupNumber;
        this.averageGrade = averageGrade;
        this.studentBookNumber = studentBookNumber;
    }

    public int getGroupNumber() {
        return groupNumber;
    }

    public double getAverageGrade() {
        return averageGrade;
    }

    public long getStudentBookNumber() {
        return studentBookNumber;
    }

    @Override
    public String toString() {
        return "Student{" +
                "groupNumber=" + groupNumber +
                ", averageGrade=" + averageGrade +
                ", studentBookNumber=" + studentBookNumber +
                '}';
    }

    @Override
    public int compareTo(Student o) {
        return Comparator.comparing(Student::getGroupNumber)
                .thenComparing(Student::getStudentBookNumber)
                .thenComparing(Student::getAverageGrade)
                .compare(this, o);
    }

    public int magicCompare(Student o) {
        int thisGroupNumber = this.getGroupNumber();
        int oGroupNumber = o.getGroupNumber();

        if (thisGroupNumber % 2 == 0 && oGroupNumber % 2 == 0) {
            return Integer.compare(thisGroupNumber, oGroupNumber);
        } else {
            return 0;
        }
    }

    public static StudentBuilder create() {
        return new StudentBuilder();
    }

    // Статический вложенный класс StudentBuilder для паттерна "Строитель"
    public static class StudentBuilder {
        private int groupNumber;
        private double averageGrade;
        private long studentBookNumber;

        // Методы для установки значений
        public StudentBuilder withGroupNumber(int groupNumber) {
            this.groupNumber = groupNumber;
            return this;
        }

        public StudentBuilder withAverageGrade(double averageGrade) {
            this.averageGrade = averageGrade;
            return this;
        }

        public StudentBuilder withStudentBookNumber(long studentBookNumber) {
            this.studentBookNumber = studentBookNumber;
            return this;
        }

        public Student build() {
            return new Student(groupNumber, averageGrade, studentBookNumber);
        }
    }

    public static Optional<Student> fromCSVString(String stringObjectCSV) {
        String[] studentData = stringObjectCSV.split(";");
        if (studentData.length != 3) {
            System.err.println("Ошибка в данных файла: строка не соответствует формату.");
            return Optional.empty();
        }

        StudentBuilder studentBuilder = Student.create();

        Optional<Integer> groupNumberOptional = DataValidator.validateAndReturnGroupNumber(studentData[0]);
        groupNumberOptional.ifPresentOrElse(studentBuilder::withGroupNumber,
                () -> System.err.println("Некорректный номер группы."));

        Optional<Double> averageGradeOptional = DataValidator.validateAndReturnAverageGrade(studentData[1]);
        averageGradeOptional.ifPresentOrElse(studentBuilder::withAverageGrade,
                () -> System.err.println("Некорректный средний балл."));

        Optional<Long> studentBookNumberOptional = DataValidator.validateAndReturnStudentBookNumber(studentData[2]);
        studentBookNumberOptional.ifPresentOrElse(studentBuilder::withStudentBookNumber,
                () -> System.err.println("Некорректный номер студенческого билета."));

        Student student = studentBuilder.build();
        return Optional.of(student);
    }

    @Override
    public String toCSVString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(groupNumber)
                .append(";")
                .append(averageGrade)
                .append(";")
                .append(studentBookNumber);

        return stringBuilder.toString();
    }
}