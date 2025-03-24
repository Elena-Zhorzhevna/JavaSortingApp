package java_sorting_app.handlers;

public class BusHandler extends Handler {

    public BusHandler(Handler handler) {
        super("Меню работы с автобусами");
        menuController.addItem(1, "Добавить автобус", new AddBusHandler(this));
        menuController.addItem(2, "Удалить автобус", this);
        menuController.addItem(3, "Найти автобус", this);
        menuController.addItem(4, "Где я?", this);
        menuController.addItem(0, "⮌ Назад", handler);
    }

    @Override
    protected void handle(int numberMenu) {
        switch (numberMenu) {
            case 1:
                break;
            case 2:
                break;
            case 4:
                System.out.println(getPWD());
                break;
        }
    }



    private void deleteBus() {
        //todo логика удаления автобуса
        System.out.println("Удаление автобуса...");
    }

    private void findBus() {
        //todo логика поиска автобуса
        System.out.println("Поиск автобуса...");
    }
}
/*

        // Привязываем команды к действиям
/*        menuMap.put(1, this::addBus);
        menuMap.put(2, this::deleteBus);
        menuMap.put(3, this::findBus);
        menuMap.put(4, () -> System.out.println("Возвращение в главное меню"));
    }*/
