package java_sorting_app.handlers;

import java_sorting_app.model.Bus;
import java_sorting_app.view.BusMenu;

import java.util.Scanner;

public class BusHandler extends Handler {

    public BusHandler(Handler handler) {
        super(handler, new BusMenu());
    }

    @Override
    public Handler process(int numberMenu) {
        Handler handler = this;
        switch (numberMenu) {
            case 0:
                handler = parentHandler;
                break;
            case 1:
                addElement();
                break;
            case 2:
                break;
            case 4:
                System.out.println(getPWD());
        }
        return handler;
    }

    public void addElement() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите номер автобуса: ");
        String number = scanner.nextLine();

        System.out.println("Введите модель автобуса: ");
        String modelBus = scanner.nextLine();

        System.out.println("Введите количество пройденных миль: ");
        int mileage = scanner.nextInt();

        Bus bus = new Bus(number, modelBus, mileage);

        model.addBus(bus);
    }

}
