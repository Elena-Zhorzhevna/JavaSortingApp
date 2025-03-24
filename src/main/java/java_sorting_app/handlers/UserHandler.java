package java_sorting_app.handlers;

import java_sorting_app.dao.DAOBus;
import java_sorting_app.dao.DAOModel;
import java_sorting_app.dao.DAOUser;
import java_sorting_app.model.User;

public class UserHandler extends Handler<User> {
    private final DAOModel<User> daoModelUser;

    public UserHandler(Handler<User> handler) {
        super("Меню работы с пользователями", handler);
        daoModelUser = new DAOUser();
        menuController.addItem(1, "Добавить пользователя", new AddHandler<User>(this));
        menuController.addItem(2, "Удалить пользователя", this);
        menuController.addItem(3, "Найти пользователя", this);
        menuController.addItem(4, "Где я?", this);
        menuController.addItem(0, "⮌ Назад", handler);
    }

    @Override
    protected DAOModel<User> getDAOModel() {
        return daoModelUser;
    }

    @Override
    public void handle(int numberMenu) {
        switch (numberMenu) {
            case 1:
                break;
            case 2:
                break;
            case 3:
                break;
            case 4:
                System.out.println(getPWD());
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
