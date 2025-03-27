package java_sorting_app.dao;


import java_sorting_app.model.User;
import java_sorting_app.util.BinarySearch;
import java_sorting_app.util.CustomArrayList;
import java_sorting_app.validator.DataValidator;
import java.util.Comparator;
import java.util.Optional;
import java.util.Random;
import java.util.Scanner;

public class DAOUser implements DAOModel {

    CustomArrayList<User> users;

    public DAOUser() {
        users = new CustomArrayList<>();
    }

    @Override
    public void printElements(){
        for (int i = 0; i < users.size(); i++) {
            User user = users.get(i);
            System.out.println(user);
        }
    }

    public CustomArrayList<User> getElements() {
        return users;
    }

    @Override
    public void sortElements() {
        CustomArrayList.selectionSort(users, User::compareTo);
    }

    @Override
    public void magicSortElements(){
        CustomArrayList.selectionSort(users, User::magicCompare);
    }

    @Override
    public void findElement() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Поиск проводиться по имени и e-mail пользователя");
        System.out.println("Введите данные искомого пользователя");
        System.out.print("Имя пользователя: ");
        String nameUser = scanner.nextLine();

        System.out.print("E-mail пользователя: ");
        String emailUser = scanner.nextLine();

        User.UserBuilder userBuilder = new User.UserBuilder();
        Comparator<User> comparator;

        if (nameUser.isEmpty() && emailUser.isEmpty()) {
            System.out.println("Не введены данные для поиска");
            System.out.println("Поиск прекращен!");
            return;
        }
        else if (nameUser.isEmpty()) {
            comparator = Comparator.comparing(User::getEmail);
            userBuilder.withEmail(emailUser);
        }
        else if (emailUser.isEmpty()) {
            comparator = Comparator.comparing(User::getName);
            userBuilder.withName(nameUser);
        }
        else {
            comparator = Comparator.comparing(User::getName).thenComparing(User::getEmail);
            userBuilder.withName(nameUser).withEmail(emailUser);
        }

        User user = userBuilder.build();
        int index = BinarySearch.search(users, user, comparator);
        if(index >= 0){
            System.out.println("Найден пользователь: " + users.get(index));
            System.out.println("Сохранить найденного пользователя в файл? (y/n)");

            if(scanner.next().equalsIgnoreCase("y")){
                saveToFile(users.get(index), "usersFinded.csv");
            }
        }
        else {
            System.out.println("Пользователь не найден :(");
        }
    }

    @Override
    public void saveToFile(){
        saveToFile(users, "usersCollection.csv");
    }

    @Override
    public void loadManual() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Введите пользователя в формате: имя ; e-mail ; пароль");
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
                if (stringObjectCSV != null && !stringObjectCSV.trim().isEmpty()) {
                    Optional<User> userOptional = User.fromCSVString(stringObjectCSV);
                    userOptional.ifPresent(user -> {
                        users.add(user);
                        System.out.println("Загружен пользователь: " + user.toString());
                    });
                }
            }
            System.out.println("Пользователей в коллекции : " + users.size());
        } else {
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
                    .withEmail(email)
                    .withPassword(password)
                    .build();
            users.add(user);

            System.out.println("Сгенерирован пользователь: " + user.toString());
        }
        System.out.println("Всего сгенерировано " + users.size() + " пользователей.");
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