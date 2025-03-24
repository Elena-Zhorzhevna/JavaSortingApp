package java_sorting_app.handlers;
import java_sorting_app.loaders.RandomLoader;
import java_sorting_app.loaders.FileLoader;
import java_sorting_app.loaders.ManualLoader;
import java_sorting_app.model.User;

import java.util.Scanner;

public class UserHandler extends Handler {
    public UserHandler(Handler handler) {
        super("Меню работы с пользователями");
        menuController.addItem(1, "Добавить пользователя", this);
        menuController.addItem(2, "Удалить пользователя", this);
        menuController.addItem(3, "Найти пользователя", this);
        menuController.addItem(4, "Где я?", this);
        menuController.addItem(0, "Назад", handler);
    }

    @Override
    public void handle(int numberMenu) {
        switch (numberMenu) {
            case 1:
                addUser();
                break;
            case 2:
                break;
            case 3:
                break;
            case 4:
                break;
        }
    }

        private void addUser() {
            System.out.println("Выберите способ добавления пользователя:");
            System.out.println("1 > Ввести вручную");
            System.out.println("2 > Загрузить из файла");
            System.out.println("3 > Генерация случайных данных");

            Scanner scanner = new Scanner(System.in);
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    ManualLoader<User> userManualLoader = new ManualLoader<>();
                    userManualLoader.loadManual(User.class);
                    break;
                case 2:
                    FileLoader<User> userFileLoader = new FileLoader<>();
                    userFileLoader.read(User.class, 12);
                    break;
                case 3:
                    RandomLoader<User> userRandomLoader = new RandomLoader<>();
                    userRandomLoader.loadRandom(10, User.class);
                    break;
                default:
                    System.out.println("Неверный выбор.");
                    break;
            }
        }
    }


/*

    private void deleteUser() {
        // Логика
        System.out.println("Удаление пользователя...");
    }

    private void listUsers() {
        // Логика
        System.out.println("Список пользователей...");
    }

    private void searchUser() {
        // Логика
        System.out.println("Поиск пользователя...");
    }*/
