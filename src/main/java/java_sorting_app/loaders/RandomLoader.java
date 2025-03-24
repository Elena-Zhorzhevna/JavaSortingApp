package java_sorting_app.loaders;

import java_sorting_app.model.Bus;
import java_sorting_app.model.Student;
import java_sorting_app.model.User;
import java_sorting_app.util.CustomArrayList;

import java.util.*;

public class RandomLoader<T> {
    public CustomArrayList<T> loadRandom(int number, Class<T> clazz) {
        Random random = new Random();
        CustomArrayList<T> data = new CustomArrayList<>();

        if (clazz == Bus.class) {
            data = (CustomArrayList<T>) loadBusRandom(random, number);
        } else if (clazz == Student.class) {
            data = (CustomArrayList<T>) loadStudentRandom(random, number);
        } else if (clazz == User.class) {
            data = (CustomArrayList<T>) loadUserRandom(random, number);
        }

        return data;
    }

    private CustomArrayList<Bus> loadBusRandom(Random random, int number) {
        CustomArrayList<Bus> buses = new CustomArrayList<>();
        for (int i = 0; i < number; i++) {
            String numberBus = "Bus-" + (i + 1);
            String model = "Model-" + (i + 1);
            int mileage = random.nextInt(100_000) + 1;

            Bus bus = Bus.create()
                    .withNumber(numberBus)
                    .withModel(model)
                    .withMileage(mileage)
                    .build();
            buses.add(bus);
        }
        // Выводим все созданные автобусы
        for (int i = 0; i <= buses.size(); i++) {
            System.out.println(buses.get(i));
        }
        return buses;
    }

        private CustomArrayList<Student> loadStudentRandom(Random random, int number) {
            CustomArrayList<Student> students = new CustomArrayList<>();
            for (int i = 0; i < number; i++) {
                int groupNumber = random.nextInt(10) + 1;
                double averageGrade = random.nextDouble() * 5;
                long studentBookNumber = random.nextLong();

                Student student = Student.create()
                        .withGroupNumber(groupNumber)
                        .withAverageGrade(averageGrade)
                        .withStudentBookNumber(studentBookNumber)
                        .build();
                students.add(student);
            }
            for (int i=0; i <= students.size(); i++) {
                System.out.println(students.get(i));
            }
            return students;
        }

    private CustomArrayList<User> loadUserRandom(Random random, int number) {
        CustomArrayList<User> users = new CustomArrayList<>();
        for (int i = 0; i < number; i++) {
            String name = "User-" + (i + 1);
            String password = "Password-" + (i + 1);
            String email = "user" + (i + 1) + "@example.ru";

            User user = User.create()
                    .withName(name)
                    .withPassword(password)
                    .withEmail(email)
                    .build();
            users.add(user);
        }
        // Выводим всех созданных пользователей
         for (int i = 0; i < users.size(); i++) {
            System.out.println(users.get(i));
        }
        return users;
    }
}