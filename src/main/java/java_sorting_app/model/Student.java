package java_sorting_app.model;

import java_sorting_app.validator.DataValidator;

import java.io.Serializable;
import java.util.InputMismatchException;
import java.util.Optional;

public class Student implements SerializableToCSVString {
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

        if(DataValidator.isValidGroupNumber(studentData[0])) {
            studentBuilder.withStudentBookNumber(Long.parseLong(studentData[0]));
        }
        else {
            System.out.println("Некорректный номер группы.");
        }

        if(DataValidator.isValidAverageGrade(studentData[1])) {
            studentBuilder.withAverageGrade(Double.parseDouble(studentData[1]));
        }
        else {
            System.out.println("Некорректный средний балл");
        }

        if(DataValidator.isValidStudentBookNumber(studentData[2])) {
            studentBuilder.withStudentBookNumber(Long.parseLong(studentData[2]));
        }
        else {
            System.out.println("Некорректный номер студенческого билета");
        }

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
