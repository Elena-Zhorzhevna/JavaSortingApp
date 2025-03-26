package java_sorting_app.dao;

import java_sorting_app.model.User;
import java_sorting_app.util.CustomArrayList;

import java.util.Optional;
import java.util.Random;
import java.util.Scanner;

public class DAOUser implements DAOModel {

    CustomArrayList<User> users;

    public DAOUser() {
        users = new CustomArrayList<>();
    }

    public void add(User element) {
        users.add(element);
    }

    public void addAll(CustomArrayList<User> elements) {
        for (int i = 0; i < elements.size(); i++) {
            users.add(elements.get(i));
        }
    }

    public CustomArrayList<User> getElements() {
        return users;
    }

    @Override
    public void sortElements() {

    }

    @Override
    public int findElement() {
        return -1;
    }

    @Override
    public void loadManual() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Введите пользователя в формате: наименование;e-mail;пароль");
            System.out.println("Или введите 'exit' для завершения");
            System.out.print("? > ");

            String inputLine = scanner.nextLine();
            if (inputLine.equalsIgnoreCase("exit")) {
                break;
            }

            Optional<User> userOptional = User.fromCSVString(inputLine);
            userOptional.ifPresent(users::add);
            userOptional.ifPresentOrElse(
                    user -> System.out.println("Вы добавили пользователя: " + user),
                    () -> System.out.println("Некорректные данные"));
        }
    }

    @Override
    public void loadFromFile() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите количество пользователей для загрузки из файла:");
        int numberToLoad = scanner.nextInt();

        Optional<String[]> resultOptional = getRowsFromFile("users.csv", numberToLoad);
        if (resultOptional.isPresent()) {
            String[] rows = resultOptional.get();
            for (String stringObjectCSV : rows) {
                Optional<User> userOptional = User.fromCSVString(stringObjectCSV);
                userOptional.ifPresent(users::add);
            }
        }
        else {
            System.out.println("Не удалось загрузить данные из файла");
        }
    }

    @Override
    public void loadRandom() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите количество пользователей для генерации:");
        int numberToGenerate = scanner.nextInt();


        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        String specialChars = "!@#$%^&*()-_=+<>?";

        for (int i = 0; i < numberToGenerate; i++) {
            String name = generateRandomString(alphabet, 8);

            String password = generateRandomString(alphabet + specialChars, 12);

            String email = "user" + generateRandomString(alphabet + "0123456789", 4)
                    + "@example.ru";

            User user = User.create()
                    .withName(name)
                    .withPassword(password)
                    .withEmail(email)
                    .build();
            users.add(user);
        }
    }

    private String generateRandomString(String characterSet, int length) {
        Random random = new Random();
        String result = "";
        for (int i = 0; i < length; i++) {
            int index = random.nextInt(characterSet.length());
            result += characterSet.charAt(index);
        }
        return result;
    }
}