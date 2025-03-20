package java_sorting_app.model;

public class Student {
    private int groupNumber;
    private double averageGrade;
    private long studentBookNumber;

    private Student(int groupNumber, double averageGrade, long studentBookNumber) {
        this.groupNumber = groupNumber;
        this.averageGrade = averageGrade;
        this.studentBookNumber = studentBookNumber;
    }

    public Student() {
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

    public static class StudentBuilder {
        private int groupNumber;
        private double averageGrade;
        private long studentBookNumber;

        public static StudentBuilder create() {
            return new StudentBuilder();
        }

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