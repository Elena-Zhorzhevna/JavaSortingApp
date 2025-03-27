package java_sorting_app.application;
import java_sorting_app.controllers.Controller;
import java_sorting_app.controllers.MainController;
import java.util.Scanner;


public class Application {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Controller controller = new MainController();
        String inputLine;
        do {
            System.out.print(controller.getMenu());
            inputLine = scanner.nextLine();
            controller = controller.process(inputLine);
        }
        while (controller != null);
    }
}