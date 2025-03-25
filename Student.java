package java_sorting_app.model;

import java.io.Serializable;

public class Student implements Serializable, Comparable<Student> {
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
    public int compareTo(Student o){
        return this.getGroupNumber() - o.getGroupNumber();
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
}
