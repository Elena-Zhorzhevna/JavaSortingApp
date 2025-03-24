package java_sorting_app.handlers;

import java.util.Scanner;

public class BusHandler extends Handler {

    public BusHandler(Handler handler) {
        super("Меню работы с автобусами");
        menuController.addItem(1, "Добавить автобус", this);
        menuController.addItem(2, "Удалить автобус", this);
        menuController.addItem(3, "Найти автобус", this);
        menuController.addItem(4, "Где я?", this);
        menuController.addItem(0, "Назад", handler);
    }

    @Override
    protected void handle(int numberMenu) {
        switch (numberMenu) {
            case 1:
                addElement();
                break;
            case 2:
                break;
            case 4:
                System.out.println(getPWD());
                break;
        }
    }

    public void addElement() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите номер автобуса: ");
        String number = scanner.nextLine();

        System.out.println("Введите модель автобуса: ");
        String modelBus = scanner.nextLine();

        System.out.println("Введите количество пройденных миль: ");
        int mileage = scanner.nextInt();

        //Bus bus = new Bus(number, modelBus, mileage);

        //model.addBus(bus);
    }

}
