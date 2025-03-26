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
        menuController.addItem(2, "Удалить автобус", this::deleteBus);
        menuController.addItem(3, "Найти автобус", this::findBus);
        menuController.addItem(0, "⮌ Назад", handler::getHandler);
    }

    @Override
    protected DAOModel getDAOModel() {
        return daoModelBus;
    }


    private Handler deleteBus() {
        //todo логика удаления автобуса
        System.out.println("Удаление автобуса...");
        return this;
    }

    private Handler findBus() {
        //todo логика поиска автобуса
        System.out.println("Поиск автобуса...");
        return this;
    }
}

