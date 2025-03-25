package java_sorting_app.handlers;

import java_sorting_app.dao.DAOModel;
import java_sorting_app.loaders.FileLoader;
import java_sorting_app.loaders.RandomLoader;
import java_sorting_app.model.Bus;

import java.util.Scanner;

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
        Scanner scanner = new Scanner(System.in);
        switch (numberMenu) {
            case 1:
                getDAOModel().loadManual();
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
            case 4:
                System.out.println(getPWD());
                break;
        }
    }

}
