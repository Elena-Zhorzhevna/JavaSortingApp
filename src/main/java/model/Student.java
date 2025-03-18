package main.java.model;

public class Student {
    private int groupNumber;
    private double averageGrade;
    private long studentBookNumber;

    public Student(int groupNumber, double averageGrade, long studentBookNumber) {
        this.groupNumber = groupNumber;
        this.averageGrade = averageGrade;
        this.studentBookNumber = studentBookNumber;
    }

    public int getGroupNumber() {
        return groupNumber;
    }

    public void setGroupNumber(int groupNumber) {
        this.groupNumber = groupNumber;
    }

    public double getAverageGrade() {
        return averageGrade;
    }

    public void setAverageGrade(double averageGrade) {
        this.averageGrade = averageGrade;
    }

    public long getStudentBookNumber() {
        return studentBookNumber;
    }

    public void setStudentBookNumber(long studentBookNumber) {
        this.studentBookNumber = studentBookNumber;
    }
}
