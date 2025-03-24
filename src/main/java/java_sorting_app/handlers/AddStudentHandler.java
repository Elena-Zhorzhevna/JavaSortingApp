package java_sorting_app.handlers;

import java_sorting_app.loaders.FileLoader;
import java_sorting_app.loaders.ManualLoader;
import java_sorting_app.loaders.RandomLoader;
import java_sorting_app.model.Student;

import java.util.Scanner;

public class AddStudentHandler extends Handler {
    public AddStudentHandler(Handler handler) {
        super("Меню добавления студентов");
        menuController.addItem(1, "Ввести вручную", this);
        menuController.addItem(2, "Загрузить из файла", this);
        menuController.addItem(3, "Генерация случайных данных", this);
        menuController.addItem(4, "Где я?", this);
        menuController.addItem(0, "⮌ Назад", handler);
        menuController.setAnnotation("Выберите способ добавления студента:");
    }

    @Override
    protected void handle(int numberMenu) {
        switch (numberMenu) {
            case 1:
                ManualLoader<Student> studentManualLoader = new ManualLoader<>();
                studentManualLoader.loadManual(Student.class);
                break;
            case 2:
                FileLoader<Student> studentFileLoader = new FileLoader<>();
                studentFileLoader.read(Student.class, 10);
                break;
            case 3:
                RandomLoader<Student> studentRandomLoader = new RandomLoader<>();
                studentRandomLoader.loadRandom(10, Student.class);
                break;
            case 4:
                System.out.println(getPWD());
                break;
        }
    }
}
