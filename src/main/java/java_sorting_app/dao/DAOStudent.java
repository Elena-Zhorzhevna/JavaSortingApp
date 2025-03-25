package java_sorting_app.dao;

import java_sorting_app.model.Student;
import java_sorting_app.util.CustomArrayList;
import java_sorting_app.validator.DataValidator;

import java.io.*;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class DAOStudent extends DAOModel<Student> {

    @Override
    public CustomArrayList<Student> loadManual() {
        Scanner scanner = new Scanner(System.in);
        CustomArrayList<Student> students = new CustomArrayList<>();

        while (true) {
            System.out.println("Введите номер группы студента (или 'exit' для завершения): ");
            String groupNumberInput = scanner.nextLine();
            if (groupNumberInput.equalsIgnoreCase("exit")) {
                break;
            }

            int groupNumber;
            try {
                groupNumber = Integer.parseInt(groupNumberInput);
            } catch (NumberFormatException e) {
                System.out.println("Ошибка: введен некорректный номер группы. Попробуйте снова.");
                continue;
            }

            while (!DataValidator.isValidGroupNumber(String.valueOf(groupNumber))) {
                System.out.println("Некорректный номер группы. Попробуйте снова: ");
                groupNumberInput = scanner.nextLine();
                if (groupNumberInput.equalsIgnoreCase("exit")) {
                    return students;
                }

                try {
                    groupNumber = Integer.parseInt(groupNumberInput);
                } catch (NumberFormatException e) {
                    System.out.println("Ошибка: введен некорректный номер группы. Попробуйте снова.");
                    continue;
                }
            }

            System.out.println("Введите средний балл студента: ");
            double averageGrade = -1;
            while (true) {
                try {
                    averageGrade = scanner.nextDouble();
                    scanner.nextLine();

                    if (!DataValidator.isValidAverageGrade(String.valueOf(averageGrade))) {
                        System.out.println("Некорректный средний балл. Попробуйте снова: ");
                    } else {
                        break;
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Ошибка: введено не число. Попробуйте снова.");
                    scanner.nextLine();
                }
            }

            System.out.println("Введите номер студенческого билета: ");
            long studentBookNumber = -1;
            while (true) {
                try {
                    studentBookNumber = scanner.nextLong();
                    scanner.nextLine();

                    if (!DataValidator.isValidStudentBookNumber(String.valueOf(studentBookNumber))) {
                        System.out.println("Некорректный номер студенческого билета. Попробуйте снова: ");
                    } else {
                        break;
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Ошибка: введен некорректный номер студенческого билета. Попробуйте снова.");
                    scanner.nextLine();
                }
            }

            if (DataValidator.validateStudentData(groupNumber, averageGrade, studentBookNumber)) {
                Student student = Student.create()
                        .withGroupNumber(groupNumber)
                        .withAverageGrade(averageGrade)
                        .withStudentBookNumber(studentBookNumber)
                        .build();
                students.add(student);
            } else {
                System.out.println("Некорректные данные для студента, попробуйте снова.");
            }
        }
        System.out.println("Вы добавили студентов:");
        for (int i = 0; i < students.size(); i++) {
            System.out.println(students.get(i));
        }
        saveToFile(students);
        return students;
    }

    @Override
    public CustomArrayList<Student> loadFromFile() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите количество студентов для загрузки из файла:");
        int numberToGenerate = scanner.nextInt();
        CustomArrayList<Student> students = new CustomArrayList<>();

        File file = new File("students.txt");
        if (!file.exists()) {
            System.out.println("Файл 'students.txt' не найден.");
            return students;
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] studentData = line.split(",");
                int groupNumber = Integer.parseInt(studentData[0].trim());
                double averageGrade = Double.parseDouble(studentData[1].trim());
                long studentBookNumber = Long.parseLong(studentData[2].trim());

                Student student = new Student.StudentBuilder()
                        .withGroupNumber(groupNumber)
                        .withAverageGrade(averageGrade)
                        .withStudentBookNumber(studentBookNumber)
                        .build();
                students.add(student);
            }
        } catch (IOException e) {
            System.out.println("Ошибка при чтении файла: " + e.getMessage());
        }
        int sizeToDisplay = Math.min(numberToGenerate, students.size());
        for (int i = 0; i < sizeToDisplay; i++) {
            System.out.println(students.get(i));
        }
        CustomArrayList<Student> result = new CustomArrayList<>();
        for (int i = 0; i < sizeToDisplay; i++) {
            result.add(students.get(i));
        }
        return students;
    }

    @Override
    public CustomArrayList<Student> loadRandom() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите количество студентов для генерации:");
        int numberToGenerate = scanner.nextInt();

        Random random = new Random();
        CustomArrayList<Student> students = new CustomArrayList<>();

        for (int i = 0; i < numberToGenerate; i++) {
            int groupNumber = generateValidGroupNumber(random);
            double averageGrade = generateValidAverageGrade(random);
            long studentBookNumber = generateValidStudentBookNumber(random);

            Student student = Student.create()
                    .withGroupNumber(groupNumber)
                    .withAverageGrade(averageGrade)
                    .withStudentBookNumber(studentBookNumber)
                    .build();

            students.add(student);
        }

        saveToFile(students);
        return students;
    }

    private int generateValidGroupNumber(Random random) {
        return random.nextInt(10) + 1;
    }

    private double generateValidAverageGrade(Random random) {
        return random.nextDouble() * 5.0;
    }

    private long generateValidStudentBookNumber(Random random) {
        return Math.abs(random.nextLong()) + 1L;
    }

    private void saveToFile(CustomArrayList<Student> students) {

        File file = new File("students.txt");

        if (!file.exists()) {
            try {
                boolean created = file.createNewFile();
                if (!created) {
                    throw new IOException("Не удалось создать файл.");
                }
            } catch (IOException e) {
                System.out.println("Ошибка при создании файла: " + e.getMessage());
                return;
            }
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            for (int i = 0; i < students.size(); i++) {
                writer.write(students.get(i).getGroupNumber() + ", " + students.get(i).getAverageGrade() + ", "
                        + students.get(i).getStudentBookNumber());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Ошибка при записи в файл: " + e.getMessage());
        }
    }
}