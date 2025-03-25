package java_sorting_app.handlers;


public class AddHandler<T> extends Handler<T> {

    public AddHandler(Handler handler) {
        super("Меню добавления элементов", handler);
        menuController.addItem(1, "Ввести вручную", this);
        menuController.addItem(2, "Загрузить из файла", this);
        menuController.addItem(3, "Генерация случайных данных", this);
        menuController.addItem(4, "Где я?", this);
        menuController.addItem(0, "⮌ Назад", handler);
        menuController.setAnnotation("Выберите способ добавления:");
    }

    @Override
    protected void handle(int numberMenu) {
        switch (numberMenu) {
            case 1:
                getDAOModel().loadManual();
                break;
            case 2:
                getDAOModel().loadFromFile();
                break;
            case 3:
                getDAOModel().loadRandom();
                break;
            case 4:
                System.out.println(getPWD());
                break;
        }
    }
}