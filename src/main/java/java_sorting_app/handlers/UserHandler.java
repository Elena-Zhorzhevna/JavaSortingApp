package java_sorting_app.handlers;

import java_sorting_app.dao.DAOModel;
import java_sorting_app.dao.DAOUser;

public class UserHandler extends Handler {
    private final DAOModel daoModelUser;

    public UserHandler(Handler handler) {
        super("Меню работы с пользователями", handler);
        daoModelUser = new DAOUser();
        Handler addUserHandler = new AddHandler(this);
        menuController.addItem(1, "Добавить пользователя", addUserHandler::getHandler);
        menuController.addItem(2, "Сортировать коллекцию", this::sortCollection);
        menuController.addItem(3, "Найти пользователя", this::findUser);
        menuController.addItem(4, "Сохранить коллекцию", this::saveToFile);
        menuController.addItem(5, "Вывести на экран коллекцию", this::printElements);
        menuController.addItem(6, "Магическая сортировка", this::magicSortCollection);
        menuController.addItem(0, "⮌ Назад", handler::getHandler);
    }

    @Override
    protected DAOModel getDAOModel() {
        return daoModelUser;
    }

    private Handler sortCollection() {
        getDAOModel().sortElements();
        return this;
    }

    private Handler magicSortCollection() {
        getDAOModel().magicSortElements();
        return this;
    }

    private Handler findUser() {
        getDAOModel().findElement();
        return this;
    }

    private Handler saveToFile() {
        getDAOModel().saveToFile();
        return this;
    }

    private Handler printElements(){
        getDAOModel().printElements();
        return this;
    }

}

