package java_sorting_app.dao;

import java_sorting_app.model.Student;
import java_sorting_app.util.CustomArrayList;
import java_sorting_app.validator.DataValidator;

import java.util.Scanner;

public class DAOStudent extends DAOModel<Student> {

    @Override
    public CustomArrayList<Student> loadManual() {
        Scanner scanner = new Scanner(System.in);
        CustomArrayList<Student> students = new CustomArrayList<>();
        System.out.println("Введите количество студентов: ");
        int numStudents = scanner.nextInt();
        scanner.nextLine();

        for (int i = 0; i < numStudents; i++) {
            System.out.println("Введите номер группы студента: ");
            int groupNumber = scanner.nextInt();
            System.out.println("Введите средний балл студента: ");
            double averageGrade = scanner.nextDouble();
            System.out.println("Введите номер студенческого билета: ");
            long studentBookNumber = scanner.nextLong();
            scanner.nextLine();

            if (DataValidator.validateStudentData(groupNumber, averageGrade, studentBookNumber)) {
                Student student = new Student.StudentBuilder()
                        .withGroupNumber(groupNumber)
                        .withAverageGrade(averageGrade)
                        .withStudentBookNumber(studentBookNumber)
                        .build();
                students.add(student);
            } else {
                System.out.println("Некорректные данные для студента, попробуйте снова.");
                i--; //вводим повторно
            }
        }
        System.out.println("Вы добавили студентов:");
        for (int i = 0; i < students.size(); i++) {
            System.out.println(students.get(i));
        }
        return students;
    }
}
