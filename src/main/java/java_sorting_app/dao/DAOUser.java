package java_sorting_app.dao;

import java_sorting_app.model.User;
import java_sorting_app.util.CustomArrayList;
import java_sorting_app.validator.DataValidator;

import java.util.Scanner;

public class DAOUser extends DAOModel<User> {

    @Override
    public CustomArrayList<User> loadManual() {
        Scanner scanner = new Scanner(System.in);
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

