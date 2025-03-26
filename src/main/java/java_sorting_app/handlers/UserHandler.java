package java_sorting_app.handlers;

import java_sorting_app.dao.DAOBus;
import java_sorting_app.dao.DAOModel;
import java_sorting_app.dao.DAOUser;
import java_sorting_app.model.User;

public class UserHandler extends Handler {
    private final DAOModel daoModelUser;

    public UserHandler(Handler handler) {
        super("Меню работы с пользователями", handler);
        daoModelUser = new DAOUser();
        Handler addUserHandler = new AddHandler(this);
        menuController.addItem(1, "Добавить пользователя", addUserHandler::getHandler);
        menuController.addItem(2, "Удалить пользователя", this::deleteUser);
        menuController.addItem(3, "Найти пользователя", this::searchUser);
        menuController.addItem(0, "⮌ Назад", handler::getHandler);
    }

    @Override
    protected DAOModel getDAOModel() {
        return daoModelUser;
    }

    private Handler deleteUser() {
        System.out.println("Удаление пользователя...");
        return this;
    }

    private Handler listUsers() {
        System.out.println("Список пользователей...");
        return this;
    }

    private Handler searchUser() {
        System.out.println("Поиск пользователя...");
        return this;
    }

}

