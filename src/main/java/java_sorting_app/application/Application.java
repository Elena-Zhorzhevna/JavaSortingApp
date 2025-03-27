package java_sorting_app.application;

import java_sorting_app.handlers.Handler;
import java_sorting_app.handlers.MainHandler;

import java.util.Scanner;


public class Application {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Handler handler = new MainHandler();
        String inputLine;
        do {
            System.out.print(handler.getMenu());
            inputLine = scanner.nextLine();
            handler = handler.process(inputLine);
        }
        while (handler != null);
    }
}