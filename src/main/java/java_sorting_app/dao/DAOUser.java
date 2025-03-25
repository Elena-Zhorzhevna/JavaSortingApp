package java_sorting_app.dao;

import java_sorting_app.model.User;
import java_sorting_app.util.CustomArrayList;
import java_sorting_app.validator.DataValidator;

import java.io.*;
import java.util.Random;
import java.util.Scanner;

public class DAOUser extends DAOModel<User> {

    @Override
    public CustomArrayList<User> loadManual() {
        Scanner scanner = new Scanner(System.in);
        CustomArrayList<User> users = new CustomArrayList<>();

        while (true) {
            System.out.println("Введите имя пользователя (или 'exit' для завершения): ");
            String name = scanner.nextLine();
            if (name.equalsIgnoreCase("exit")) {
                break;
            }

            while (!DataValidator.isValidUserName(name)) {
                System.out.println("Некорректное имя пользователя. Попробуйте снова: ");
                name = scanner.nextLine();
                if (name.equalsIgnoreCase("exit")) {
                    return users;
                }
            }

            System.out.println("Введите пароль пользователя: ");
            String password = scanner.nextLine();

            while (!DataValidator.isValidPassword(password)) {
                System.out.println("Некорректный пароль. Попробуйте снова:\n" +
                        "Пароль должен содержать хотя бы один символ из этих категорий: \n" +
                        "Заглавную латинскую букву (A-Z)\n" +
                        "Строчную латинскую букву (a-z)\n" +
                        "Цифру\n" +
                        "Специальный символ (@$,!,%,*,?,&) и быть длиннее 8 символов.");
                password = scanner.nextLine();
                if (password.equalsIgnoreCase("exit")) {
                    return users;
                }
            }

            System.out.println("Введите email пользователя: ");
            String email = scanner.nextLine();

            while (!DataValidator.isValidEmail(email)) {
                System.out.println("Некорректный email. Попробуйте снова: ");
                email = scanner.nextLine();
                if (email.equalsIgnoreCase("exit")) {
                    return users;
                }
            }

            if (DataValidator.validateUserData(name, password, email)) {
                User user = User.create()
                        .withName(name)
                        .withPassword(password)
                        .withEmail(email)
                        .build();
                users.add(user);
            } else {
                System.out.println("Некорректные данные для пользователя, попробуйте снова.");
            }
        }

        System.out.println("Вы добавили пользователей:");
        for (int i = 0; i < users.size(); i++) {
            System.out.println(users.get(i));
        }

        saveToFile(users);
        return users;
    }

    @Override
    public CustomArrayList<User> loadFromFile() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите количество пользователей для загрузки из файла:");
        int numberToLoad = scanner.nextInt();
        CustomArrayList<User> users = new CustomArrayList<>();

        File file = new File("users.txt");
        if (!file.exists()) {
            System.out.println("Файл 'users.txt' не найден.");
            return users;
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] userData = line.split(",");
                String name = userData[0].trim();
                String password = userData[1].trim();
                String email = userData[2].trim();

                User user = User.create()
                        .withName(name)
                        .withPassword(password)
                        .withEmail(email)
                        .build();
                users.add(user);
            }
        } catch (IOException e) {
            System.out.println("Ошибка при чтении файла: " + e.getMessage());
        }
        int sizeToDisplay = Math.min(numberToLoad, users.size());
        for (int i = 0; i < sizeToDisplay; i++) {
            System.out.println(users.get(i));
        }
        CustomArrayList<User> result = new CustomArrayList<>();
        for (int i = 0; i < sizeToDisplay; i++) {
            result.add(users.get(i));
        }
        saveToFile(users);
        return users;
    }

    @Override
    public CustomArrayList<User> loadRandom() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите количество пользователей для генерации:");
        int numberToGenerate = scanner.nextInt();
        Random random = new Random();
        CustomArrayList<User> users = new CustomArrayList<>();

        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        String specialChars = "!@#$%^&*()-_=+<>?";

        for (int i = 0; i < numberToGenerate; i++) {
            String name = generateRandomString(alphabet, random, 8);

            String password = generateRandomString(alphabet + specialChars, random, 12);

            String email = "user" + generateRandomString(alphabet + "0123456789", random, 4)
                    + "@example.ru";

            User user = User.create()
                    .withName(name)
                    .withPassword(password)
                    .withEmail(email)
                    .build();
            users.add(user);
        }
        saveToFile(users);
        return users;
    }

    private void saveToFile(CustomArrayList<User> users) {
        File file = new File("users.txt");

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
            for (int i = 0; i < users.size(); i++) {
                writer.write(users.get(i).getName() + ", " + users.get(i).getPassword() + ", "
                        + users.get(i).getEmail());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Ошибка при записи в файл: " + e.getMessage());
        }
    }

    private String generateRandomString(String characterSet, Random random, int length) {
        String result = "";
        for (int i = 0; i < length; i++) {
            int index = random.nextInt(characterSet.length());
            result += characterSet.charAt(index);
        }
        return result;
    }
}