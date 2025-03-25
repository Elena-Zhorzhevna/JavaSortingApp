package java_sorting_app.dao;

import java_sorting_app.model.Bus;
import java_sorting_app.util.CustomArrayList;
import java_sorting_app.validator.DataValidator;

import java.io.*;
import java.util.InputMismatchException;
import java.util.Random;
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
                        break;
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Ошибка: введено не число. Попробуйте снова.");
                    scanner.nextLine();
                }

                if (mileage == -1) {
                    return buses;
                }
            }
            if (DataValidator.validateBusData(number, model, mileage)) {
                Bus bus = Bus.create()
                        .withNumber(number)
                        .withModel(model)
                        .withMileage(mileage)
                        .build();
                buses.add(bus);
            } else {
                System.out.println("Некорректные данные для автобуса, попробуйте снова.");
            }
        }

        System.out.println("Вы добавили автобусы:");
        for (int i = 0; i < buses.size(); i++) {
            System.out.println(buses.get(i));
        }
        saveToFile(buses);
        return buses;
    }

    @Override
    public CustomArrayList<Bus> loadFromFile() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите количество автобусов для загрузки из файла:");
        int numberToLoad = scanner.nextInt();
        CustomArrayList<Bus> buses = new CustomArrayList<>();

        File file = new File("buses.txt");
        if (!file.exists()) {
            System.out.println("Файл 'buses.txt' не найден.");
            return buses;
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] busData = line.split(",");
                if (busData.length != 3) {
                    System.err.println("Ошибка в данных файла: строка не соответствует формату.");
                    continue;
                }
                String number = busData[0].trim();
                String model = busData[1].trim();
                int mileage = Integer.parseInt(busData[2].trim());

                if (!DataValidator.isValidBusNumber(number)) {
                    System.err.println("Некорректный номер автобуса из файла: " + number);
                    continue;
                }

                if (!DataValidator.isValidBusModel(model)) {
                    System.err.println("Некорректная модель автобуса из файла: " + model);
                    continue;
                }

                if (!DataValidator.isValidMileage(String.valueOf(mileage))) {
                    System.err.println("Некорректный пробег автобуса из файла: " + mileage);
                    continue;
                }

                Bus bus = Bus.create()
                        .withNumber(number)
                        .withModel(model)
                        .withMileage(mileage)
                        .build();
                buses.add(bus);
            }
        } catch (IOException e) {
            System.out.println("Ошибка при чтении файла: " + e.getMessage());
        }
        int sizeToDisplay = Math.min(numberToLoad, buses.size());
        for (int i = 0; i < sizeToDisplay; i++) {
            System.out.println(buses.get(i));
        }
        CustomArrayList<Bus> result = new CustomArrayList<>();
        for (int i = 0; i < sizeToDisplay; i++) {
            result.add(buses.get(i));
        }
        return buses;
    }

    @Override
    public CustomArrayList<Bus> loadRandom() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите количество автобусов для генерации:");
        int numberToGenerate = scanner.nextInt();
        Random random = new Random();
        CustomArrayList<Bus> buses = new CustomArrayList<>();

        for (int i = 0; i < numberToGenerate; i++) {

            String numberBus = generateValidBusNumber(random);
            String model = generateValidBusModel(random);
            int mileage = generateValidMileage(random);

            Bus bus = Bus.create()
                    .withNumber(numberBus)
                    .withModel(model)
                    .withMileage(mileage)
                    .build();
            buses.add(bus);
        }
        saveToFile(buses);
        return buses;
    }

    private String generateValidBusNumber(Random random) {
        String letters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        String digits = "0123456789";
        String result = "";
        for (int j = 0; j < 6; j++) {
            if (j % 2 == 0) {
                result += letters.charAt(random.nextInt(letters.length()));
            } else {
                result += digits.charAt(random.nextInt(digits.length()));
            }
        }
        return result;
    }

    private String generateValidBusModel(Random random) {
        String letters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        String digits = "0123456789";
        String result = "";

        for (int j = 0; j < 5; j++) {
            if (j % 2 == 0) {
                result += letters.charAt(random.nextInt(letters.length()));
            } else {
                result += digits.charAt(random.nextInt(digits.length()));
            }
        }
        return result;
    }

    private int generateValidMileage(Random random) {
        return random.nextInt(99999) + 1;
    }

    private void saveToFile(CustomArrayList<Bus> buses) {
        File file = new File("buses.txt");

        if (!file.exists()) {
            try {
                boolean created = file.createNewFile();
                if (!created) {
                    throw new IOException("Не удалось создать файл.");
                }
            } catch (IOException e) {
                System.out.println("Ошибка при создании файла: " + e.getMessage());
                return;
            }
        }
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            for (int i = 0; i < buses.size(); i++) {
                writer.write(buses.get(i).getNumber() + ", " + buses.get(i).getModel() + ", "
                        + buses.get(i).getMileage());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Ошибка при записи в файл: " + e.getMessage());
        }
    }
}