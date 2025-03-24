package java_sorting_app.application;

import java_sorting_app.handlers.MainHandler;
import java_sorting_app.state.State;
import java_sorting_app.view.View;

import java.util.Scanner;


public class Application {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        State state = new State(new MainHandler());
        int numberOfMenu;

        do {
            View.show(state.getMenu()); // Отображаем меню
            numberOfMenu = scanner.nextInt(); // Получаем ввод пользователя
        } while (state.handle(numberOfMenu)); // Обрабатываем выбор пользователя

        scanner.close(); // Закрываем сканнер после завершения работы
    }
}



/*public class Application {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        State state = new State(new MainHandler());
        int numberOfMenu;
        do{
            View.show(state.getMenu());
            numberOfMenu = scanner.nextInt();
        }
        while (state.handle(numberOfMenu));
    }
}*/
