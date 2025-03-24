package java_sorting_app.loaders;


import java_sorting_app.model.Bus;
import java_sorting_app.model.Student;
import java_sorting_app.model.User;
import java_sorting_app.util.CustomArrayList;
import java_sorting_app.validator.DataValidator;

import java.util.*;

public class ManualLoader<T> {
    public T loadManual(Class<T> clazz) {
        Scanner scanner = new Scanner(System.in);
        T data = null;

        if (clazz == Bus.class) {
            data = (T) loadBusManual(scanner);
        } else if (clazz == Student.class) {
            data = (T) loadStudentManual(scanner);
        } else if (clazz == User.class) {
            data = (T) loadUserManual(scanner);
        }

        return data;
    }

    private CustomArrayList<Bus> loadBusManual(Scanner scanner) {
        CustomArrayList<Bus> buses = new CustomArrayList<>();

        while (true) {
            System.out.println("Введите номер автобуса (или 'exit' для завершения): ");
            String number = scanner.nextLine();
            if (number.equalsIgnoreCase("exit")) {
                break;
            }

            while (!DataValidator.isValidBusNumber(number)) {
                System.out.println("Некорректный номер автобуса. Попробуйте снова: ");
                number = scanner.nextLine();
                if (number.equalsIgnoreCase("exit")) {
                    return buses;
                }
            }

            System.out.println("Введите модель автобуса: ");
            String model = scanner.nextLine();
            while (!DataValidator.isValidBusModel(model)) {
                System.out.println("Некорректная модель автобуса. Попробуйте снова: ");
                model = scanner.nextLine();
                if (model.equalsIgnoreCase("exit")) {
                    return buses;
                }
            }

            System.out.println("Введите количество пройденных миль: ");
            int mileage = -1;

            while (true) {
                try {
                    mileage = scanner.nextInt();
                    scanner.nextLine();

                    if (!DataValidator.isValidMileage(String.valueOf(mileage))) {
                        System.out.println("Некорректный пробег. Попробуйте снова: ");
                    } else {
                        break;  // Выходим из цикла, если пробег корректен
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Ошибка: введено не число. Попробуйте снова.");
                    scanner.nextLine();  // Считываем некорректный ввод, чтобы избежать зацикливания
                }
                // Если введено -1, выходим из цикла
                if (mileage == -1) {
                    return buses;
                }
            }
            Bus bus = Bus.create()
                    .withNumber(number)
                    .withModel(model)
                    .withMileage(mileage)
                    .build();
            buses.add(bus);
        }
        return buses;
    }


    private CustomArrayList<Student> loadStudentManual(Scanner scanner) {
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

    private CustomArrayList<User> loadUserManual(Scanner scanner) {
        CustomArrayList<User> users = new CustomArrayList<>();
        System.out.println("Введите количество пользователей: ");
        int numUsers = scanner.nextInt();
        scanner.nextLine();

        for (int i = 0; i < numUsers; i++) {
            System.out.println("Введите имя пользователя: ");
            String name = scanner.nextLine();
            System.out.println("Введите пароль пользователя: ");
            String password = scanner.nextLine();
            System.out.println("Введите email пользователя: ");
            String email = scanner.nextLine();

            if (DataValidator.validateUserData(name, password, email)) {
                User user = User.create()
                        .withName(name)
                        .withPassword(password)
                        .withEmail(email)
                        .build();
                users.add(user);
            } else {
                System.out.println("Некорректные данные для пользователя, попробуйте снова.");
                i--; //вводим повторно
            }
        }
        System.out.println("Вы добавили пользователей:");
        for (int i = 0; i < users.size(); i++) {
            System.out.println(users.get(i));
        }
        return users;
    }
}