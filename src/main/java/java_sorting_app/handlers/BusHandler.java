package java_sorting_app.handlers;

import java_sorting_app.loaders.FileLoader;
import java_sorting_app.loaders.ManualLoader;
import java_sorting_app.loaders.RandomLoader;
import java_sorting_app.model.Bus;
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
                addBus();
                break;
            case 2:
                break;
            case 4:
                System.out.println(getPWD());
                break;
        }
    }

    private void addBus() {
        System.out.println("Выберите способ добавления автобуса:");
        System.out.println("1 > Ввести вручную");
        System.out.println("2 > Загрузить из файла");
        System.out.println("3 > Генерация случайных данных");

        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();

        switch (choice) {
            case 1:
                ManualLoader<Bus> busManualLoader = new ManualLoader<>();
                busManualLoader.loadManual(Bus.class);
                break;
            case 2:
                System.out.println("Введите количество автобусов для загрузки из файла:");
                int numberToLoad = scanner.nextInt();

                FileLoader<Bus> busFileLoader = new FileLoader<>();
                busFileLoader.read(Bus.class, numberToLoad);
                break;
            case 3:
                System.out.println("Введите количество автобусов для генерации:");
                int numberToGenerate = scanner.nextInt();

                RandomLoader<Bus> busRandomLoader = new RandomLoader<>();
                busRandomLoader.loadRandom(numberToGenerate, Bus.class);
                break;
            default:
                System.out.println("Неверный выбор.");
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
