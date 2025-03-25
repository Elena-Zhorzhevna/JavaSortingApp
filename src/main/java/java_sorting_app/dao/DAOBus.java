package java_sorting_app.dao;

import java_sorting_app.model.Bus;
import java_sorting_app.util.CustomArrayList;
import java_sorting_app.validator.DataValidator;

import java.util.InputMismatchException;
import java.util.Scanner;

public class DAOBus extends DAOModel<Bus> {

    @Override
    public CustomArrayList<Bus> loadManual() {
        Scanner scanner = new Scanner(System.in);
        CustomArrayList<Bus> buses = new CustomArrayList<>();

        while (true) {
            System.out.println("Введите номер автобуса (или 'exit' для завершения): ");
            String number = scanner.nextLine();
            if (number.equalsIgnoreCase("exit")) {
                break;
            }

            while (!DataValidator.isValidBusNumber(number)) {
                System.out.println("Некорректный номер автобуса. Попробуйте снова: ");
                number = scanner.nextLine();
                if (number.equalsIgnoreCase("exit")) {
                    return buses;
                }
            }

            System.out.println("Введите модель автобуса: ");
            String model = scanner.nextLine();
            while (!DataValidator.isValidBusModel(model)) {
                System.out.println("Некорректная модель автобуса. Попробуйте снова: ");
                model = scanner.nextLine();
                if (model.equalsIgnoreCase("exit")) {
                    return buses;
                }
            }

            System.out.println("Введите количество пройденных миль: ");
            int mileage = -1;

            while (true) {
                try {
                    mileage = scanner.nextInt();
                    scanner.nextLine();

                    if (!DataValidator.isValidMileage(String.valueOf(mileage))) {
                        System.out.println("Некорректный пробег. Попробуйте снова: ");
                    } else {
                        break;  // Выходим из цикла, если пробег корректен
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Ошибка: введено не число. Попробуйте снова.");
                    scanner.nextLine();  // Считываем некорректный ввод, чтобы избежать зацикливания
                }
                // Если введено -1, выходим из цикла
                if (mileage == -1) {
                    return buses;
                }
            }
            Bus bus = Bus.create()
                    .withNumber(number)
                    .withModel(model)
                    .withMileage(mileage)
                    .build();
            buses.add(bus);
        }
        return buses;
    }

}
