package java_sorting_app.handlers;

import java_sorting_app.loaders.FileLoader;
import java_sorting_app.loaders.ManualLoader;
import java_sorting_app.loaders.RandomLoader;
import java_sorting_app.model.User;

public class AddUserHandler extends Handler {

    public AddUserHandler(Handler handler) {
        super("Меню добавления пользователей");
        menuController.addItem(1, "Ввести вручную", this);
        menuController.addItem(2, "Загрузить из файла", this);
        menuController.addItem(3, "Генерация случайных данных", this);
        menuController.addItem(4, "Где я?", this);
        menuController.addItem(0, "⮌ Назад", handler);
        menuController.setAnnotation("Выберите способ добавления пользователя:");
    }

    @Override
    public void handle(int numberMenu) {
        switch (numberMenu) {
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
            case 4:
                System.out.println(getPWD());
                break;
        }
    }

}
