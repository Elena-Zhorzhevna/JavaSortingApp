package java_sorting_app.handlers;

import java_sorting_app.loaders.FileLoader;
import java_sorting_app.loaders.ManualLoader;
import java_sorting_app.loaders.RandomLoader;
import java_sorting_app.model.Student;
import java_sorting_app.view.StudentMenu;

import java.util.Scanner;


public class StudentHandler extends Handler {
    public StudentHandler(Handler handler) {
        super(handler, new StudentMenu());
        menuMap.put(1, this);
        menuMap.put(2, this);
        menuMap.put(3, this);
        menuMap.put(4, this);
    }

    @Override
    protected void handle(int numberMenu) {
        switch (numberMenu) {
            case 1:
                addStudent();
                break;
            case 2:
                break;
            case 4:
                System.out.println(getPWD());
                break;
        }
    }
    private void addStudent() {
        System.out.println("Выберите способ добавления студента:");
        System.out.println("1 > Ввести вручную");
        System.out.println("2 > Загрузить из файла");
        System.out.println("3 > Генерация случайных данных");

        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();

        switch (choice) {
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
            default:
                System.out.println("Неверный выбор.");
                break;
        }
    }
}
/*

    private void deleteStudent() {
        // Логика
        System.out.println("Удаление студента...");
    }

    private void listStudents() {
      //логика
        System.out.println("Список студентов...");
    }

    private void searchStudent() {
        // Логика
        System.out.println("Поиск студента...");
    }
}
*/