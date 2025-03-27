package java_sorting_app.dao;

import java_sorting_app.model.Bus;
import java_sorting_app.util.BinarySearch;
import java_sorting_app.util.CustomArrayList;

import java.util.Optional;
import java.util.Random;
import java.util.Scanner;

public class DAOBus implements DAOModel {

    private CustomArrayList<Bus> buses;

    public DAOBus() {
        buses = new CustomArrayList<>();
    }

    @Override
    public void printElements(){
        for (int i = 0; i < buses.size(); i++) {
            Bus bus = buses.get(i);
            System.out.println(bus);
        }
    }

    public void add(Bus element) {
        buses.add(element);
    }


    public void addAll(CustomArrayList<Bus> elements) {
        for (int index = 0; index < elements.size(); index++) {
            buses.add(elements.get(index));
        }
    }


    public CustomArrayList<Bus> getElements() {
        return buses;
    }

    @Override
    public void findElement() {
        Optional<Bus> busOptional = Bus.fromCSVString("");
        if(busOptional.isPresent()) {
            Bus bus = busOptional.get();
            int index = 0;
            index = BinarySearch.search(buses, bus);
            if(index >= 0){
                System.out.println("Найден автобус: " + buses.get(index));
                System.out.println("Сохранить найденный автобус в файл? (y/n)");
                Scanner scanner = new Scanner(System.in);
                if(scanner.next().toLowerCase().equals("y")){
                    saveToFile(buses.get(index), "busesFinded.csv");
                }
            }
            else {
                System.out.println("Автобус не найден :(");
            }
        }
    }

    @Override
    public void sortElements() {
        //CustomArrayList<Bus> temp = new CustomArrayList<>();
        //CustomInsertionSort.selectionSort(buses, );
    }

    @Override
    public void saveToFile(){

    }

    @Override
    public void loadManual() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Введите автобус в формате: номер;модель;пробег");
            System.out.println("Или введите 'exit' для завершения");
            System.out.print("? > ");

            String inputLine = scanner.nextLine();
            if (inputLine.equalsIgnoreCase("exit")) {
                break;
            }

            Optional<Bus> busOptional = Bus.fromCSVString(inputLine);
            busOptional.ifPresent(buses::add);
            busOptional.ifPresentOrElse(
                    bus -> System.out.println("Вы добавили автобус: " + bus),
                    () -> System.out.println("Некорректные данные"));
        }
    }

    @Override
    public void loadFromFile() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите количество автобусов для загрузки из файла:");
        int numberToLoad = scanner.nextInt();

        Optional<String[]> resultOptional = getRowsFromFile("buses.csv", numberToLoad);
        if (resultOptional.isPresent()) {
            String[] rows = resultOptional.get();
            for (String stringObjectCSV : rows) {
                if (stringObjectCSV != null && !stringObjectCSV.trim().isEmpty()) {
                    Optional<Bus> busOptional = Bus.fromCSVString(stringObjectCSV);
                    //busOptional.ifPresent(buses::add);
                    busOptional.ifPresent(bus -> {
                        buses.add(bus);
                        System.out.println("Загружен автобус: " + bus.toString());
                    });
                }
            }
            System.out.println("Всего в файле автобусов: " + buses.size());
        } else {
            System.out.println("Не удалось загрузить данные из файла");
        }
    }

    @Override
    public void loadRandom() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите количество автобусов для генерации:");
        int numberToGenerate = scanner.nextInt();
        Random random = new Random();

        for (int i = 0; i < numberToGenerate; i++) {
            String numberBus = generateRandomString(6);
            String model = generateRandomString(5);
            int mileage = random.nextInt(99999) + 1;

            Bus bus = Bus.create()
                    .withNumber(numberBus)
                    .withModel(model)
                    .withMileage(mileage)
                    .build();
            buses.add(bus);

            System.out.println("Сгенерирован автобус: " + bus.toString());
        }
        System.out.println("Всего сгенерировано " + buses.size() + " автобусов.");
    }
}