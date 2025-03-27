package java_sorting_app.handlers;

import java_sorting_app.dao.DAOBus;
import java_sorting_app.dao.DAOModel;


public class BusHandler extends Handler{

    private final DAOModel daoModelBus;

    public BusHandler(Handler handler) {
        super("Меню работы с автобусами", handler);
        daoModelBus = new DAOBus();
        Handler addBusHandler = new AddHandler(this);
        menuController.addItem(1, "Добавить автобус", addBusHandler::getHandler);
        menuController.addItem(2, "Сортировать коллекцию", this::sortCollection);
        menuController.addItem(3, "Найти автобус", this::findBus);
        menuController.addItem(4, "Сохранить коллекцию", this::saveToFile);
        menuController.addItem(5, "Вывести на экран коллекцию", this::printElements);
        menuController.addItem(6, "Магическая сортировка", this::magicSortCollection);
        menuController.addItem(0, "⮌ Назад", handler::getHandler);
    }

    @Override
    protected DAOModel getDAOModel() {
        return daoModelBus;
    }

    private Handler sortCollection() {
        getDAOModel().sortElements();
        return this;
    }

    private Handler magicSortCollection() {
        getDAOModel().magicSortElements();
        return this;
    }

    private Handler findBus() {
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

