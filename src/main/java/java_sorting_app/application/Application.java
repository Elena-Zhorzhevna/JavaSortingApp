package java_sorting_app.application;

import java_sorting_app.handlers.MainHandler;
import java_sorting_app.state.State;

import java.util.Scanner;


public class Application {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        State state = new State(new MainHandler());
        int numberOfMenu;
        do{
            System.out.print(state.getMenu());
            numberOfMenu = scanner.nextInt();
        }
        while (state.handle(numberOfMenu));
        scanner.close();
    }
}
