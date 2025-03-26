package java_sorting_app.handlers;


public class AddHandler extends Handler {

    public AddHandler(Handler handler) {
        super("Меню добавления элементов", handler);
        menuController.addItem(1, "Ввести вручную", this::loadManual);
        menuController.addItem(2, "Загрузить из файла", this::loadFromFile);
        menuController.addItem(3, "Генерация случайных данных", this::loadRandom);
        menuController.addItem(0, "⮌ Назад", handler::getHandler);
        menuController.setAnnotation("Выберите способ добавления:");
    }

    public Handler loadManual() {
        getDAOModel().loadManual();
        return this;
    }

    public Handler loadFromFile() {
        getDAOModel().loadFromFile();
        return this;
    }

    public Handler loadRandom() {
        getDAOModel().loadRandom();
        return this;
    }
}